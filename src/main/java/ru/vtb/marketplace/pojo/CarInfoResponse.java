package ru.vtb.marketplace.pojo;

import java.util.Optional;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author denis-panin
 */
public class CarInfoResponse {
    @JsonProperty("found")
    private final boolean found;
    @JsonUnwrapped
    private final Optional<CarInfo> carInfo;

    public CarInfoResponse(boolean found, Optional<CarInfo> carInfo) {
        this.found = found;
        this.carInfo = carInfo;
    }
}
