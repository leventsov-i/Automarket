package ru.vtb.youtube.dto.exclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YoutubeThumbnailsVideo {
    @JsonProperty("medium")
    private YoutubeThumbnailsValue thumbnailsDefault;
}
