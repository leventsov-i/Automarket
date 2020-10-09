package ru.vtb.recognize.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecognizeCarRequestVtbApiDto {
    @JsonProperty("content")
    private String pictureCarBase64;
}
