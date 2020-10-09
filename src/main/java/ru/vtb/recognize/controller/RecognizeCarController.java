package ru.vtb.recognize.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.recognize.dto.RecognizeRequestDto;
import ru.vtb.recognize.dto.RecognizeResponseDto;
import ru.vtb.recognize.service.RecognizeCarService;

@RestController
@RequestMapping("/api/recognize")
public class RecognizeCarController {
    private final RecognizeCarService recognizeCarService;

    @Autowired
    public RecognizeCarController(RecognizeCarService recognizeCarService) {
        this.recognizeCarService = recognizeCarService;
    }

    @PostMapping("/car")
    public RecognizeResponseDto recognizeCar(@RequestBody RecognizeRequestDto request) {
        return recognizeCarService.recognizeCar(request);
    }
}
