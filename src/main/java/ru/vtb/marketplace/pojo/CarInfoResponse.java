package ru.vtb.marketplace.pojo;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarInfoResponse {
    @JsonProperty("found")
    private final boolean found;
    @JsonProperty("carInfo")
    private final Optional<CarInfo> carInfo;
    @JsonProperty("dealers")
    private final List<Dealer> dealers;

    public CarInfoResponse(boolean found, Optional<CarInfo> carInfo,
            List<Dealer> dealers) {
        this.found = found;
        this.carInfo = carInfo;
        this.dealers = dealers;
    }
}
