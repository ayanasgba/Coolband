package kg.geeks.coolband.api;


import kg.geeks.coolband.entities.*;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class Media {

    public static <T> ResponseEntity<ByteArrayResource> getImageByteArrayResourceResponseEntity(T entity, boolean isOriginal) throws IOException {

        String getImage = "";

        String getOriginalImage = "";

        if (entity.getClass() == AboutUsBand.class) {
            getImage = ((AboutUsBand) entity).getImagePath();
        } else if (entity.getClass() == AboutUsStudio.class) {
            getImage = ((AboutUsStudio) entity).getImagePath();
        } else if (entity.getClass() == CollaborationsBand.class) {
            getImage = ((CollaborationsBand) entity).getImagePath();
        } else if (entity.getClass() == EventImagesBand.class) {
            getImage = ((EventImagesBand) entity).getImagePath();
            getOriginalImage = ((EventImagesBand) entity).getOriginalImagePath();
        } else if (entity.getClass() == EventImagesStudio.class) {
            getImage = ((EventImagesStudio) entity).getImagePath();
            getOriginalImage = ((EventImagesStudio) entity).getOriginalImagePath();
        } else if (entity.getClass() == PartnersBand.class) {
            getImage = ((PartnersBand) entity).getImagePath();
        } else if (entity.getClass() == StStudentReviews.class) {
            getImage = ((StStudentReviews) entity).getImagePath();
        } else if (entity.getClass() == StTeachers.class) {
            getImage = ((StTeachers) entity).getImagePath();
        } else if (entity.getClass() == TeamBand.class) {
            getImage = ((TeamBand) entity).getImagePath();
        } else if (entity.getClass() == DirectionStudio.class) {
            getImage = ((DirectionStudio) entity).getImagePath();
        }

        File imageFile = new File(getImage);
        ByteArrayResource resource = new ByteArrayResource(FileUtils.readFileToByteArray(imageFile));

        if (isOriginal){
            imageFile = new File(getOriginalImage);
            resource = new ByteArrayResource(FileUtils.readFileToByteArray(imageFile));
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.IMAGE_JPEG);
        httpHeaders.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ByteArrayResource> getVideoByteArrayResourceResponseEntity(T entity) throws IOException {

        String getVideo = "";

        if (entity.getClass() == HeroBand.class) {
            getVideo = ((HeroBand) entity).getVideoPath();
        } else if (entity.getClass() == HeroStudio.class) {
            getVideo = ((HeroStudio) entity).getVideoPath();
        } else if (entity.getClass() == TeamBand.class) {
            getVideo = ((TeamBand) entity).getVideoPath();
        }

        ByteArrayResource resource = new ByteArrayResource(FileUtils.readFileToByteArray(new File(getVideo)));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType("video/mp4"));
        httpHeaders.setContentLength(resource.contentLength());

        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

}
