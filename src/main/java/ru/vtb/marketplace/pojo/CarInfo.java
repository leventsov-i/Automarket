package ru.vtb.marketplace.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarInfo {
    @JsonProperty("brand")
    private final String brand;
    @JsonProperty("logoUrl")
    private final String logoUrl;
    @JsonProperty("model")
    private final String model;
    @JsonProperty("photos")
    private final List<String> photos;
    @JsonProperty("minPrice")
    private final long minPrice;
    @JsonProperty("bodies")
    private final List<String> bodies;
    @JsonProperty("country")
    private final String country;


    public CarInfo(String brand, String logoUrl, String model, List<String> photos, long minPrice,
            List<String> bodies, String country) {
        this.brand = brand;
        this.logoUrl = logoUrl;
        this.model = model;
        this.photos = photos;
        this.minPrice = minPrice;
        this.bodies = bodies;
        this.country = country;
    }


}
