package ru.vtb.recognize.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.recognize.dto.RecognizeCarRequestVtbApiDto;
import ru.vtb.recognize.dto.RecognizeCarResponseVtbApiDto;
import ru.vtb.recognize.dto.RecognizeRequestDto;
import ru.vtb.recognize.dto.RecognizeResponseDto;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

@Service
public class RecognizeCarService {
    private final RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor;

    @Autowired
    public RecognizeCarService(RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor) {
        this.requestVtbApiExecutor = requestVtbApiExecutor;
    }

    public RecognizeResponseDto recognizeCar(RecognizeRequestDto request) {
        RecognizeCarResponseVtbApiDto responseFromVtbApi
                = requestVtbApiExecutor.execute(new RecognizeCarRequestVtbApiDto(request.getPictureCarBase64()));

        Optional<Map.Entry<String, Float>> max = responseFromVtbApi.getProbabilities().entrySet().stream()
                .filter(stringFloatEntry -> stringFloatEntry.getValue() > 0.4)
                .max(Map.Entry.comparingByValue());

        return new RecognizeResponseDto(
                max.isPresent(),
                max.isEmpty() ? null : max.get().getKey(),
                (long) 0
        );

    }
}
