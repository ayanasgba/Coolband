package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.MediaBaseService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    private static final float IMAGE_QUALITY = 0.9f; // Качество сжатия изображения (от 0.0 до 1.0)

    @Override
    public String uploadCompressed(MultipartFile file, String dirName) {
        if (file.getContentType() != null && file.getContentType().startsWith("image")) {
            try {
                File dest = MediaBaseService.makingDirectory(file, dirName);
                // Сжатие и сохранение изображения
                Thumbnails.of(file.getInputStream())
                        .scale(0.35)
                        .outputQuality(IMAGE_QUALITY)
                        .toFile(dest);

                return dest.getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image: " + e.getMessage());
            } catch (Exception e) {
                throw new BadCredentialException("Failed to upload image: " + e.getMessage());
            }
        }
        throw new BadCredentialException("Failed to upload image: File is not image");

    }


    @Override
    public String uploadOriginal(MultipartFile file, String dirName) {
        if (file.getContentType() != null && file.getContentType().startsWith("image")) {
            try {
                File dest = MediaBaseService.makingDirectory(file, dirName);
                file.transferTo(dest);
                return dest.getAbsolutePath();
            } catch (IOException e) {
                throw new RuntimeException("Failed to upload image: " + e.getMessage());
            } catch (Exception e) {
                throw new BadCredentialException("Failed to upload image: " + e.getMessage());
            }
        }
        throw new BadCredentialException("Failed to upload image: File is not image");
    }


    @Override
    public void delete(String path, List<String> imagesPath) {
        try {

            File fileToDelete = new File(path);

            if (fileToDelete.exists()) {
                FileUtils.forceDelete(fileToDelete);
            } else {
                System.out.println("File not found: " + path);
            }

            File folder = new File(path.substring(0, path.lastIndexOf(File.separator)));

            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (!imagesPath.contains(file.toString())) {
                        FileUtils.forceDelete(file);
                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete image: " + e.getMessage());
        }
    }
}
