package ru.vtb.recognize.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.marketplace.MarketplaceService;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.recognize.dto.RecognizeCarRequestVtbApiDto;
import ru.vtb.recognize.dto.RecognizeCarResponseVtbApiDto;
import ru.vtb.recognize.dto.RecognizeRequestDto;
import ru.vtb.recognize.dto.RecognizeResponseDto;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class RecognizeCarService {
    private final RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor;
    private final MarketplaceService marketplaceService;

    @Autowired
    public RecognizeCarService(RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor, MarketplaceService marketplaceService) {
        this.requestVtbApiExecutor = requestVtbApiExecutor;
        this.marketplaceService = marketplaceService;
    }

    public RecognizeResponseDto recognizeCar(RecognizeRequestDto request) {
        RecognizeCarResponseVtbApiDto responseFromVtbApi
                = requestVtbApiExecutor.execute(new RecognizeCarRequestVtbApiDto(request.getPictureCarBase64()));

        Optional<Map.Entry<String, Float>> max = responseFromVtbApi.getProbabilities().entrySet().stream()
                .filter(stringFloatEntry -> stringFloatEntry.getValue() > 0.1)
                .max(Map.Entry.comparingByValue());


        String carName = max.isEmpty() ? null : max.get().getKey();
        RecognizeResponseDto recognizeResponseDto = new RecognizeResponseDto(
                max.isPresent(),
                carName,
                marketplaceService.getCarInfo(carName).map(CarInfo::getMinPrice).orElse(0L)
        );
        log.info("Answer: {}. Probability: {}", recognizeResponseDto, responseFromVtbApi);
        return recognizeResponseDto;
    }
}
