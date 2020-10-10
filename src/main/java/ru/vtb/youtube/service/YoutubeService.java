package ru.vtb.youtube.service;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.youtube.dto.exclude.BaseYoutubeResponseDto;
import ru.vtb.youtube.dto.include.YoutubeSearchVideoResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class YoutubeService {
    private final YoutubeSearchVideoRequestExecutor youtubeSearchVideoRequestExecutor;

    @Autowired
    public YoutubeService(YoutubeSearchVideoRequestExecutor youtubeSearchVideoRequestExecutor) {
        this.youtubeSearchVideoRequestExecutor = youtubeSearchVideoRequestExecutor;
    }

    public List<YoutubeSearchVideoResponseDto> searchVideo(String searchString) {
        BaseYoutubeResponseDto baseYoutubeResponse = youtubeSearchVideoRequestExecutor.execute(searchString);
        return baseYoutubeResponse.getItems().stream()
                .map(youtubeInformationVideo ->
                        new YoutubeSearchVideoResponseDto(
                            createYoutubeVideoUrl(youtubeInformationVideo.getId().getVideoId()),
                            youtubeInformationVideo.getSnippet().getThumbnails().getThumbnailsDefault().getUrl(),
                            youtubeInformationVideo.getSnippet().getTitle()
                        )
                )
                .collect(Collectors.toList());
    }

    private String createYoutubeVideoUrl(String id) {
        return "https://www.youtube.com/watch?v=" + id;
    }
}
