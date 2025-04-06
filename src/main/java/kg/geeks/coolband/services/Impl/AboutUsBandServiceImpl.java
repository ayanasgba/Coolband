package kg.geeks.coolband.services.Impl;

import jakarta.annotation.PostConstruct;
import kg.geeks.coolband.dto.request.AboutUsBandRequest;
import kg.geeks.coolband.dto.response.AboutUsBandResponse;
import kg.geeks.coolband.entities.AboutUsBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.AboutUsBandMapper;
import kg.geeks.coolband.repository.AboutUsBandRepository;
import kg.geeks.coolband.services.AboutUsBandService;
import kg.geeks.coolband.services.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AboutUsBandServiceImpl implements AboutUsBandService {

    private static final String SERVER = "%s/api/about_us_band";
    private static final String DIRNAME = "AboutUsBand";

    private final AboutUsBandRepository aboutUsBandRepository;

    private final ImageService imageService;

    @Override
    public AboutUsBandResponse get() {
        return aboutUsBandRepository.getAboutUsBandById(1L).orElseThrow(() ->
                new NotFoundException("About us band with id: %s not found".formatted(1)));

    }

    @Override
    public AboutUsBandResponse patch(AboutUsBandRequest request) throws IllegalAccessException, NoSuchFieldException {
        AboutUsBand aboutUsBand = aboutUsBandRepository.findById(1L).orElseThrow(
                () -> new NotFoundException("About us band with id: %s not found".formatted(1))
        );

        String previousPath = aboutUsBand.getImagePath();

        Patch.patchWithMediaFields(1L, request, aboutUsBand, DIRNAME, SERVER);

        if (previousPath != null && request.getImage() != null) {
            imageService.delete(previousPath,aboutUsBandRepository.findAllByImagePath());
        }

        aboutUsBandRepository.save(aboutUsBand);


        return AboutUsBandMapper.INSTANCE.map(aboutUsBand);
    }

    @PostConstruct
    public void initSaveAboutUsBand() {
        if (aboutUsBandRepository.findById(1L).isPresent()) {
            log.info("\nAboutUsBand with ID 1L already exist. Skipping creation.\n");
        }
        else{
            AboutUsBand aboutUsBand = new AboutUsBand();
            aboutUsBand.setId(1L);
            aboutUsBandRepository.save(aboutUsBand);
            log.info("\nAbout us band saved with null fields under ID: %s \n".formatted(aboutUsBand.getId()));
        }
    }
}
