package ru.vtb.youtube.dto.exclude;

import lombok.Data;

import java.util.List;

@Data
public class BaseYoutubeResponseDto {
    private List<YoutubeInformationVideo> items;
}
