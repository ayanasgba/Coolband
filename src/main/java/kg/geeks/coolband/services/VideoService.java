package kg.geeks.coolband.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService extends MediaBaseService {

    String uploadCompressed(MultipartFile file, String dirName);

}
