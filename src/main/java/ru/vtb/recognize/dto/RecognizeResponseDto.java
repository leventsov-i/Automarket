package ru.vtb.recognize.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.vtb.marketplace.pojo.CarInfoResponse;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecognizeResponseDto {
    private boolean found;
    private String carName;
    private CarInfoResponse carInfo;
}
