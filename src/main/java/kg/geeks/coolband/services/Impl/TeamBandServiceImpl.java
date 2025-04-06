package kg.geeks.coolband.services.Impl;

import kg.geeks.coolband.config.WebConfig;
import kg.geeks.coolband.dto.request.TeamBandRequest;
import kg.geeks.coolband.dto.response.TeamBandResponse;
import kg.geeks.coolband.entities.TeamBand;
import kg.geeks.coolband.exceptions.BadCredentialException;
import kg.geeks.coolband.exceptions.NotFoundException;
import kg.geeks.coolband.mapper.TeamBandMapper;
import kg.geeks.coolband.repository.TeamBandRepository;
import kg.geeks.coolband.services.ImageService;
import kg.geeks.coolband.services.TeamBandService;
import kg.geeks.coolband.services.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TeamBandServiceImpl implements TeamBandService {

    private static final String SERVER = "%s/api/team_band";
    private static final String DIRNAME = "TeamBand";

    private final TeamBandRepository teamBandRepository;

    private final ImageService imageService;

    private final VideoService videoService;

    @Override
    public List<TeamBandResponse> getAll() {
        return teamBandRepository.getAll();
    }


    @Override
    public TeamBandResponse getById(Long id) {
        return teamBandRepository.getTeamBandById(id).orElseThrow(() ->
                new NotFoundException("Team band with id: %s not found".formatted(id)));
    }

    @Override
    public TeamBandResponse deleteById(Long id) {
        TeamBand teamBand = teamBandRepository.findById(id).orElseThrow(
                () -> new BadCredentialException("Team band with id: %s doesn't exist".formatted(id))
        );
        imageService.delete(teamBand.getImagePath(),teamBandRepository.findAllByImagePath());
        videoService.delete(teamBand.getVideoPath(),teamBandRepository.findAllByVideoPath());
        teamBandRepository.deleteById(id);
        log.info("Team band is deleted");
        return TeamBandMapper.INSTANCE.map(teamBand);
    }

    @Override
    public TeamBandResponse patch(Long id, TeamBandRequest request) throws NoSuchFieldException, IllegalAccessException {
        TeamBand teamBand = teamBandRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Team band id: %s not found".formatted(id))
        );

        String previousImage = teamBand.getImagePath();
        String previousVideo = teamBand.getVideoPath();


        Patch.patchWithMediaFields(id, request, teamBand, DIRNAME, SERVER);

        if (previousVideo != null && request.getVideo() != null) {
            videoService.delete(previousVideo,teamBandRepository.findAllByVideoPath());
        }
        if (previousImage != null && request.getImage() != null){
            imageService.delete(previousImage,teamBandRepository.findAllByImagePath());
        }

        log.info("Team band is updated");

        teamBandRepository.save(teamBand);

        return TeamBandMapper.INSTANCE.map(teamBand);
    }

    @Override
    public TeamBandResponse save(TeamBandRequest teamBandRequest) {


        TeamBand teamBand = TeamBandMapper.INSTANCE.mapRequestToResponse(teamBandRequest);

        String imagePath = imageService.uploadCompressed(teamBandRequest.getImage(), DIRNAME);
        String videoPath;
        if (teamBandRequest.isCompress()){
            videoPath = videoService.uploadCompressed(teamBandRequest.getVideo(), DIRNAME);
        }
        else {
            videoPath = videoService.uploadOriginal(teamBandRequest.getVideo(), DIRNAME);
        }

        teamBand.setImagePath(imagePath);
        teamBand.setVideoPath(videoPath);

        teamBandRepository.save(teamBand);

        teamBand.setImage(SERVER.formatted(WebConfig.getServer()) + "/image/%s".formatted(teamBand.getId()));
        teamBand.setVideo(SERVER.formatted(WebConfig.getServer()) + "/video/%s".formatted(teamBand.getId()));

        teamBand.setBluer(teamBandRequest.getBluer());

        teamBandRepository.save(teamBand);

        return TeamBandMapper.INSTANCE.map(teamBand);
    }


}

