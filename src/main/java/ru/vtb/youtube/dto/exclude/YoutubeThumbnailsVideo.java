package ru.vtb.youtube.dto.exclude;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class YoutubeThumbnailsVideo {
    @JsonProperty("default")
    private YoutubeThumbnailsDefault thumbnailsDefault;
}
