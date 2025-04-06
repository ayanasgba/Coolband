package kg.geeks.coolband.services.Impl;

import jakarta.annotation.PostConstruct;
import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.HeroStudioRequest;
import kg.geeks.coolband.dto.response.HeroStudioResponse;
import kg.geeks.coolband.entities.HeroStudio;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.HeroStudioMapper;
import kg.geeks.coolband.repository.HeroStudioRepository;
import kg.geeks.coolband.services.HeroStudioService;
import kg.geeks.coolband.services.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class HeroStudioServiceImpl implements HeroStudioService {

    private static final String SERVER = "%s/api/hero_studio";
    private static final String DIRNAME = "HeroStudio";

    private final HeroStudioRepository heroStudioRepository;

    private final VideoService videoService;

    @Override
    public HeroStudioResponse get() {
        return heroStudioRepository.getHeroStudioById(1L).orElseThrow(() ->
                new NotFoundException("Hero studio with id: %s not found".formatted(1)));
    }

    @Override
    public HeroStudioResponse patch(HeroStudioRequest request) throws IllegalAccessException, NoSuchFieldException {
        HeroStudio heroStudio = heroStudioRepository.findById(1L).orElseThrow(
                () -> new NotFoundException("Hero studio with id: %s not found".formatted(1))
        );

        String previousPath = heroStudio.getVideoPath();

        Patch.patchWithMediaFields(1L, request, heroStudio, DIRNAME, SERVER);

        if (previousPath != null && request.getVideo() != null) {
            videoService.delete(previousPath,heroStudioRepository.findAllByVideoPath());
        }

        heroStudioRepository.save(heroStudio);

        return HeroStudioMapper.INSTANCE.map(heroStudio);
    }

    @PostConstruct
    public void initSaveHeroStudio() {
        if (heroStudioRepository.findById(1L).isPresent()) {
            log.info("\nHeroStudio with ID 1L already exist. Skipping creation.\n");
        } else {
            HeroStudio heroStudio = new HeroStudio();
            heroStudio.setId(1L);
            heroStudioRepository.save(heroStudio);
            log.info("\nHero studio saved with null fields under ID: %s \n".formatted(heroStudio.getId()));
        }
    }
}
