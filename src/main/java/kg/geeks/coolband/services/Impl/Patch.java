package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.entities.*;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.lang.reflect.Field;

@Component
@RequiredArgsConstructor
public class Patch {

    private static final ImageService imageService = new ImageServiceImpl();

    private static final VideoService videoService = new VideoServiceImpl();


    public static <T, Z> void patchWithMediaFields(Long id, T request, Z entity, String DIRNAME, String SERVER) throws IllegalAccessException, NoSuchFieldException {

        Field[] requestFields = request.getClass().getDeclaredFields();

        for (Field field : requestFields) {
            String fieldName = field.getName();
            field.setAccessible(true);
            if (fieldName.equals("compress")) continue;
            if ((entity.getClass() == EventImagesBand.class || entity.getClass() == EventImagesStudio.class) && fieldName.equals("image") && field.get(request) != null) {
                String imagePath = imageService.uploadCompressed((MultipartFile) field.get(request), DIRNAME + File.separator + "compressed");
                Field entityImagePathField = entity.getClass().getDeclaredField("imagePath");
                entityImagePathField.setAccessible(true);
                entityImagePathField.set(entity, imagePath);
                entityImagePathField.setAccessible(false);

                String image = SERVER.formatted(WebConfig.getServer()) + "/compImage/%s".formatted(id);
                Field entityImageField = entity.getClass().getDeclaredField("image");
                entityImageField.setAccessible(true);
                entityImageField.set(entity, image);
                entityImageField.setAccessible(false);

                String originalImagePath = imageService.uploadOriginal((MultipartFile) field.get(request), DIRNAME + File.separator + "original");
                Field entityOriginalImagePathField = entity.getClass().getDeclaredField("originalImagePath");
                entityOriginalImagePathField.setAccessible(true);
                entityOriginalImagePathField.set(entity, originalImagePath);
                entityOriginalImagePathField.setAccessible(false);

                String originalImage = SERVER.formatted(WebConfig.getServer()) + "/origImage/%s".formatted(id);
                Field entityOriginalImageField = entity.getClass().getDeclaredField("originalImage");
                entityOriginalImageField.setAccessible(true);
                entityOriginalImageField.set(entity, originalImage);
                entityOriginalImageField.setAccessible(false);

                continue;
            }

            if (fieldName.equals("image") && field.get(request) != null) {
                String imagePath;
                if (entity instanceof DirectionStudio || entity instanceof PartnersBand) {
                    imagePath = imageService.uploadOriginal((MultipartFile) field.get(request), DIRNAME);
                } else {
                    imagePath = imageService.uploadCompressed((MultipartFile) field.get(request), DIRNAME);
                }
                Field entityImagePathField = entity.getClass().getDeclaredField("imagePath");
                entityImagePathField.setAccessible(true);
                entityImagePathField.set(entity, imagePath);
                entityImagePathField.setAccessible(false);

                String image = SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(id);
                Field entityImageField = entity.getClass().getDeclaredField("image");
                entityImageField.setAccessible(true);
                entityImageField.set(entity, image);
                entityImageField.setAccessible(false);

                continue;
            }

            if (fieldName.equals("video") && field.get(request) != null) {
                String videoPath;
                Field comp = request.getClass().getDeclaredField("compress");
                comp.setAccessible(true);
                if (comp.getBoolean(request)) {
                    videoPath = videoService.uploadCompressed((MultipartFile) field.get(request), DIRNAME);
                } else {
                    videoPath = videoService.uploadOriginal((MultipartFile) field.get(request), DIRNAME);
                }
                comp.setAccessible(false);
                Field entityVideoPathField = entity.getClass().getDeclaredField("videoPath");
                entityVideoPathField.setAccessible(true);
                entityVideoPathField.set(entity, videoPath);
                entityVideoPathField.setAccessible(false);

                String video = SERVER.formatted(WebConfig.getServer()) + "/video/%s".formatted(id);
                Field entityVideoField = entity.getClass().getDeclaredField("video");
                entityVideoField.setAccessible(true);
                entityVideoField.set(entity, video);
                entityVideoField.setAccessible(false);
                continue;
            }


            setFields(request, entity, field, fieldName);

        }
    }

    public static <T, Z> void patchWithNonMediaFields(T request, Z entity) throws NoSuchFieldException, IllegalAccessException {
        Field[] requestFields = request.getClass().getDeclaredFields();

        for (Field field : requestFields) {
            String fieldName = field.getName();

            field.setAccessible(true);
            setFields(request, entity, field, fieldName);
            field.setAccessible(false);
        }


    }

    private static <T, Z> void setFields(T request, Z entity, Field field, String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Object newValue = field.get(request);

        if (newValue != null) {
            Field entityField = entity.getClass().getDeclaredField(fieldName);
            entityField.setAccessible(true);
            entityField.set(entity, newValue);
            entityField.setAccessible(false);
        }

        field.setAccessible(false);
    }
}