package ru.vtb.youtube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.youtube.dto.include.YoutubeSearchVideoResponseDto;
import ru.vtb.youtube.service.YoutubeService;

import java.util.List;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeController {
    private final YoutubeService youtubeService;

    @Autowired
    public YoutubeController(YoutubeService youtubeService) {
        this.youtubeService = youtubeService;
    }

    @GetMapping("/search/{carName}")
    public List<YoutubeSearchVideoResponseDto> getVideos(@PathVariable String carName) {
        return youtubeService.searchVideo(carName);
    }
}
