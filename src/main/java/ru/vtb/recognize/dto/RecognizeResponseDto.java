package ru.vtb.recognize.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vtb.marketplace.pojo.CarInfo;
import ru.vtb.marketplace.pojo.CarInfoResponse;
import ru.vtb.marketplace.pojo.Dealer;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecognizeResponseDto {
    private boolean found;
    private String carName;
    private final Optional<CarInfo> carInfo;
    private final List<Dealer> dealers;
}
