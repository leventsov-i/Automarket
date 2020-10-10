package ru.vtb.marketplace.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author denis-panin
 */
public class CarBrand {
    @JsonProperty("absentee")
    public boolean absentee;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("country")
    public Country country;
    @JsonProperty("id")
    public int id;
    @JsonProperty("isOutbound")
    public boolean isOutbound;
    @JsonProperty("logo")
    public String logo;
    @JsonProperty("title")
    public String title;
    @JsonProperty("titleRus")
    public String titleRus;
}
