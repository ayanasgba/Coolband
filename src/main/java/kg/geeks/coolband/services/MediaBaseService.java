package kg.geeks.coolband.services;

import com.ibm.icu.text.Transliterator;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public interface MediaBaseService {

    String uploadOriginal(MultipartFile file, String dirName);

    void delete(String path, List<String> videoPaths);

    static File makingDirectory(MultipartFile file, String dirName) {
        String path = System.getProperty("user.dir") + File.separator + "java_media/%s".formatted(dirName);

        File uploadDir = new File(path);

        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new RuntimeException("An error occurred while creating directory: " + uploadDir.getAbsolutePath());
            }
        }

        Transliterator transliterator = Transliterator.getInstance("Russian-Latin/BGN");

        String fileName = UUID.randomUUID() + "_" + transliterator.transliterate(Objects.requireNonNull(Objects.requireNonNull(file.getOriginalFilename()).replace(' ','_')));

        return new File(path + File.separator + fileName);

    }

}
