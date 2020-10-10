package ru.vtb.youtube.dto.include;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class YoutubeSearchVideoResponseDto {
    private String uri;
    private String pictureUri;
    private String name;
}
