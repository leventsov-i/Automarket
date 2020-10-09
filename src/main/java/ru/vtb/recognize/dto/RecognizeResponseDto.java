package ru.vtb.recognize.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecognizeResponseDto {
    private boolean found;
    private String carName;
    private Long price;
}
