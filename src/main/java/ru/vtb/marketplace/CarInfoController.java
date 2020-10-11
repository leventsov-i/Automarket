package ru.vtb.marketplace;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.marketplace.pojo.CarInfoResponse;
import ru.vtb.marketplace.pojo.Dealer;

@RestController
@RequestMapping("/api/cars")
public class CarInfoController {
    private final MarketplaceService marketplaceService;
    private final CarDealerService carDealerService;

    @Autowired
    public CarInfoController(MarketplaceService marketplaceService,
            CarDealerService carDealerService) {
        this.marketplaceService = marketplaceService;
        this.carDealerService = carDealerService;
    }

    @GetMapping("/info/{carName}")
    public CarInfoResponse getCarInfo(@PathVariable("carName") String carName) {
        Optional<CarInfo> carInfo = marketplaceService.getCarInfo(carName);
        List<Dealer> dealers = carInfo.map(info -> carDealerService.getDealers(info.getBrand())).orElse(List.of());
        return new CarInfoResponse(carInfo.isPresent(), carInfo, dealers);
    }
}
