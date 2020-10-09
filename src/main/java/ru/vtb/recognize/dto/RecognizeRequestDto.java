package ru.vtb.recognize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RecognizeRequestDto {
    @JsonProperty("content")
    private String pictureCarBase64;
}
