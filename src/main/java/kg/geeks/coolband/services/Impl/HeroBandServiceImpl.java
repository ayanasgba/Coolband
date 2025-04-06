package kg.geeks.coolband.services.Impl;


import jakarta.annotation.PostConstruct;
import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.HeroBandRequest;
import kg.geeks.coolband.dto.response.HeroBandResponse;
import kg.geeks.coolband.entities.HeroBand;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.HeroBandMapper;
import kg.geeks.coolband.repository.HeroBandRepository;
import kg.geeks.coolband.services.HeroBandService;
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
public class HeroBandServiceImpl implements HeroBandService {

    private static final String SERVER = "%s/api/hero_band";

    private static final String DIRNAME = "HeroBand";

    private final HeroBandRepository heroBandRepository;

    private final VideoService videoService;


    @Override
    public HeroBandResponse get() {
        return heroBandRepository.getHeroBandsById(1L).orElseThrow(() ->
                new NotFoundException("Hero Band with id : %s not found !".formatted(1)));
    }

    @Override
    public HeroBandResponse patch(HeroBandRequest request) throws IllegalAccessException, NoSuchFieldException {
        HeroBand heroBand = heroBandRepository.findById(1L).orElseThrow(
                () -> new NotFoundException("Hero Band with id: %s not found".formatted(1))
        );

        String previousPath = heroBand.getVideoPath();

        Patch.patchWithMediaFields(1L, request, heroBand, DIRNAME, SERVER);
        if (previousPath != null && request.getVideo() != null) {
            videoService.delete(previousPath,heroBandRepository.findAllByVideoPath());
        }

        heroBandRepository.save(heroBand);

        return HeroBandMapper.INSTANCE.map(heroBand);
    }

    @PostConstruct
    public void initSaveHeroBand() {
        if (heroBandRepository.findById(1L).isPresent()) {
            log.info("\nHeroBand with ID 1L already exist. Skipping creation.\n");
        } else {
            HeroBand heroBand = new HeroBand();
            heroBand.setId(1L);
            heroBandRepository.save(heroBand);
            log.info("\n Hero Band saved with null values with id: %s\n".formatted(1));
        }
    }

}
