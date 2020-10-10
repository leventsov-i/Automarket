package ru.vtb.marketplace;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.marketplace.pojo.CarInfoResponse;

/**
 * @author denis-panin
 */
@RestController
@RequestMapping("/api/cars")
public class CarInfoController {
    private final MarketplaceService marketplaceService;

    @Autowired
    public CarInfoController(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @GetMapping("/info/{carName}")
    public CarInfoResponse getCarInfo(@PathVariable("carName") String carName) {
        Optional<CarInfo> carInfo = marketplaceService.getCarInfo(carName);
        return new CarInfoResponse(carInfo.isPresent(), carInfo);
    }
}
