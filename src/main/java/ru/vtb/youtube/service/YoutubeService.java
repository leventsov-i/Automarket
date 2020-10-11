package ru.vtb.youtube.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.youtube.dto.exclude.BaseYoutubeResponseDto;
import ru.vtb.youtube.dto.include.YoutubeSearchVideoResponseDto;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class YoutubeService {
    private final YoutubeSearchVideoRequestExecutor youtubeSearchVideoRequestExecutor;
    private final LoadingCache<String, List<YoutubeSearchVideoResponseDto>> cache = Caffeine.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build(this::searchVideoInternal);


    @Autowired
    public YoutubeService(YoutubeSearchVideoRequestExecutor youtubeSearchVideoRequestExecutor) {
        this.youtubeSearchVideoRequestExecutor = youtubeSearchVideoRequestExecutor;
    }

    public List<YoutubeSearchVideoResponseDto> searchVideo(String searchString) {
        return cache.get(searchString);
    }

    private List<YoutubeSearchVideoResponseDto> searchVideoInternal(String searchString) {
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
