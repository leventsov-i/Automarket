package ru.vtb.recognize.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RecognizeCarResponseVtbApiDto {
    Map<String, Float> probabilities;
}
