package ru.vtb.marketplace.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BrandInfo {
    @JsonProperty("absentee")
    public boolean absentee;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("country")
    public Country country;
    @JsonProperty("currentCarCount")
    public int currentCarCount;
    @JsonProperty("currentModelsTotal")
    public int currentModelsTotal;
    @JsonProperty("id")
    public int id;
    @JsonProperty("isOutbound")
    public boolean isOutbound;
    @JsonProperty("logo")
    public String logo;
    @JsonProperty("models")
    public List<CarModel> models = null;
    @JsonProperty("title")
    public String title;
    @JsonProperty("titleRus")
    public String titleRus;


}
