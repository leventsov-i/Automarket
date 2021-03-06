package ru.vtb.marketplace.pojo;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarModel {
    @JsonProperty("absentee")
    public boolean absentee;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("bodies")
    public List<CarBody> bodies = List.of();
    @JsonProperty("brand")
    public CarBrand brand;
    @JsonProperty("carId")
    public String carId;
    @JsonProperty("colorsCount")
    public int colorsCount;
    @JsonProperty("count")
    public int count;
    @JsonProperty("hasSpecialPrice")
    public boolean hasSpecialPrice;
    @JsonProperty("id")
    public int id;
    @JsonProperty("metallicPay")
    public int metallicPay;
    @JsonProperty("minPrice")
    public long minPrice;
    @JsonProperty("model")
    public CarModelDetails model;
    @JsonProperty("ownTitle")
    public String ownTitle;
    @JsonProperty("pearlPay")
    public int pearlPay;
    @JsonProperty("photo")
    public String photo;
    @JsonProperty("prefix")
    public String prefix;
    @JsonProperty("renderPhotos")
    public Map<String, Map<String, BodyPhoto>> renderPhotos;
//    @JsonProperty("sizesPhotos")
//    public SizesPhotos sizesPhotos;
    @JsonProperty("specmetallicPay")
    public int specmetallicPay;
    @JsonProperty("title")
    public String title;
    @JsonProperty("titleRus")
    public String titleRus;
    @JsonProperty("transportType")
    public TransportType transportType;
}
