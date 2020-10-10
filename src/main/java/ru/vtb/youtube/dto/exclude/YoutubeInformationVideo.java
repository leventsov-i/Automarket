package ru.vtb.youtube.dto.exclude;

import lombok.Data;

import java.util.List;

@Data
public class YoutubeInformationVideo {
    private YoutubeIdentificationInformationVideo id;
    private YoutubeSnippetVideo snippet;
}
