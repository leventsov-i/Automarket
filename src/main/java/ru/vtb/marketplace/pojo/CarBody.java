package ru.vtb.marketplace.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarBody {
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("doors")
    public int doors;
    @JsonProperty("photo")
    public String photo;
    @JsonProperty("title")
    public String title;
    @JsonProperty("type")
    public String type;
    @JsonProperty("typeTitle")
    public String typeTitle;
}
