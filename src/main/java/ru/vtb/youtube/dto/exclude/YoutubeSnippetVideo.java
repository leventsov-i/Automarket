package ru.vtb.youtube.dto.exclude;

import lombok.Data;

@Data
public class YoutubeSnippetVideo {
    private String title;
    private YoutubeThumbnailsVideo thumbnails;
}
