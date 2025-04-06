package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.services.MediaBaseService;
import kg.geeks.coolband.services.VideoService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;
import ws.schild.jave.encode.enums.X264_PROFILE;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
public class VideoServiceImpl implements VideoService {


    @Override
    public String uploadOriginal(MultipartFile file, String dirName) {
        if (file.getContentType() != null && file.getContentType().startsWith("video")) {
            try {

                File dest = MediaBaseService.makingDirectory(file, dirName);

                try {
                    file.transferTo(dest);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload video: " + e.getMessage());
                }
                return dest.getAbsolutePath();
            } catch (Exception e) {
                throw new BadCredentialException("Failed to upload video: " + e.getMessage());
            }

        }
        throw new RuntimeException("Failed to upload video: Not a video file");
    }


    @Override
    public void delete(String path, List<String> videoPaths) {

        try {
            // Получение пути к файлу и проверка его существования

            File fileToDelete = new File(path);

            if (fileToDelete.exists()) {
                FileUtils.forceDelete(fileToDelete);
            } else {
                System.out.println("File was deleted: " + path);
            }

            int lastSeparatorIndex = path.lastIndexOf(File.separator);
            String folderPath = path.substring(0, lastSeparatorIndex);

            File folder = new File(folderPath);

            // Получаем список файлов в папке
            File[] files = folder.listFiles();
            // Проверяем, что files не равно null и содержит хотя бы один файл
            if (files != null) {
                // Итерируемся по массиву файлов и выводим их имена на консоль
                for (File file : files) {
                    if (!videoPaths.contains(file.toString())){
                        FileUtils.forceDelete(file);
                    }
                }

            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete video: " + e.getMessage());
        }


    }


    @Override
    public String uploadCompressed(MultipartFile file, String dirName) {
        if (file.getContentType() != null && file.getContentType().startsWith("video")) {
            try {
                File dest = MediaBaseService.makingDirectory(file, dirName);

                File source = new File(System.getProperty("user.dir") + File.separator + "java_media/hello.mp4");

                file.transferTo(source);

                VideoAttributes videoAttributes = new VideoAttributes();
                videoAttributes.setCodec("h264");
                videoAttributes.setX264Profile(X264_PROFILE.HIGH444);

                EncodingAttributes attributes = new EncodingAttributes();
                attributes.setOutputFormat("mp4");
                attributes.setVideoAttributes(videoAttributes);

                Encoder encoder = new Encoder();
                encoder.encode(new MultimediaObject(source),dest,attributes);
                FileUtils.forceDelete(source);
                return dest.getAbsolutePath();
            } catch (Exception e) {
                throw new BadCredentialException("Failed to upload video: " + e.getMessage());
            }
        }
        throw new RuntimeException("Failed to upload video: Not a video file");
    }
}
