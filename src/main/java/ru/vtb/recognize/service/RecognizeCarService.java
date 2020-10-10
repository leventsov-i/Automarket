package ru.vtb.recognize.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vtb.marketplace.CarDealerService;
import ru.vtb.marketplace.MarketplaceService;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.marketplace.pojo.CarInfoResponse;
import ru.vtb.marketplace.pojo.Dealer;
import ru.vtb.recognize.dto.RecognizeCarRequestVtbApiDto;
import ru.vtb.recognize.dto.RecognizeCarResponseVtbApiDto;
import ru.vtb.recognize.dto.RecognizeRequestDto;
import ru.vtb.recognize.dto.RecognizeResponseDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class RecognizeCarService {
    private final RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor;
    private final MarketplaceService marketplaceService;
    private final CarDealerService carDealerService;

    @Autowired
    public RecognizeCarService(RecognizeCarRequestVtbApiExecutor requestVtbApiExecutor, MarketplaceService marketplaceService, CarDealerService carDealerService) {
        this.requestVtbApiExecutor = requestVtbApiExecutor;
        this.marketplaceService = marketplaceService;
        this.carDealerService = carDealerService;
    }

    public RecognizeResponseDto recognizeCar(RecognizeRequestDto request) {
        RecognizeCarResponseVtbApiDto responseFromVtbApi
                = requestVtbApiExecutor.execute(new RecognizeCarRequestVtbApiDto(request.getPictureCarBase64()));

        Optional<Map.Entry<String, Float>> max = responseFromVtbApi.getProbabilities().entrySet().stream()
                .filter(stringFloatEntry -> stringFloatEntry.getValue() > 0.1)
                .max(Map.Entry.comparingByValue());


        String carName = max.isEmpty() ? null : max.get().getKey();
        Optional<CarInfo> carInfo = marketplaceService.getCarInfo(carName);
        List<Dealer> dealers = carInfo.map(info -> carDealerService.getDealers(info.getBrand())).orElse(List.of());
        RecognizeResponseDto recognizeResponseDto = new RecognizeResponseDto(
                max.isPresent(),
                carName,
                carInfo,
                dealers
        );
        log.info("Answer: {}. Probability: {}", recognizeResponseDto, responseFromVtbApi);
        return recognizeResponseDto;
    }
}
