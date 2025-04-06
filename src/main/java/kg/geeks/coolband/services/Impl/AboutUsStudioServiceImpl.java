package kg.geeks.coolband.services.Impl;

import jakarta.annotation.PostConstruct;
import kg.geeks.coolband.dto.request.AboutUsStudioRequest;
import kg.geeks.coolband.dto.response.AboutUsStudioResponse;
import kg.geeks.coolband.entities.AboutUsStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.AboutUsStudioMapper;
import kg.geeks.coolband.repository.AboutUsStudioRepository;
import kg.geeks.coolband.services.AboutUsStudioService;
import kg.geeks.coolband.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AboutUsStudioServiceImpl implements AboutUsStudioService {

    private static final String SERVER = "%s/api/about_us_studio";
    private static final String DIRNAME = "AboutUsStudio";

    private final AboutUsStudioRepository aboutUsStudioRepository;

    private final ImageService imageService;


    @Override
    public AboutUsStudioResponse get() {
        return aboutUsStudioRepository.getAboutUsStudioById(1L).orElseThrow(() ->
                new NotFoundException("About us studio with id: %s not found".formatted(1)));

    }

    @Override
    public AboutUsStudioResponse patch(AboutUsStudioRequest request) throws IllegalAccessException, NoSuchFieldException {
        AboutUsStudio aboutUsStudio = aboutUsStudioRepository.findById(1L).orElseThrow(
                () -> new NotFoundException("About us studio with id: %s not found".formatted(1))
        );

        String previousPath = aboutUsStudio.getImagePath();

        Patch.patchWithMediaFields(1L,request,aboutUsStudio,DIRNAME,SERVER);

        if (previousPath != null && request.getImage() != null) {
            imageService.delete(previousPath,aboutUsStudioRepository.findAllByImagePath());
        }

        aboutUsStudioRepository.save(aboutUsStudio);


        return AboutUsStudioMapper.INSTANCE.map(aboutUsStudio);
    }

    @PostConstruct
    public void initSaveAboutUsStudio() {
        if (aboutUsStudioRepository.findById(1L).isPresent()) {
            log.info("\nAboutUsStudio with ID 1L already exist. Skipping creation.\n");
        }else {
        AboutUsStudio aboutUsStudio = new AboutUsStudio();
        aboutUsStudio.setId(1L);
        aboutUsStudioRepository.save(aboutUsStudio);
        log.info("\nAbout us studio saved with null fields under ID: %s \n".formatted(aboutUsStudio.getId()));
    }}

}
