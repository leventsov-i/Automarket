package ru.vtb.marketplace.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BodyPhoto {
    @JsonProperty("path")
    public String url;
    @JsonProperty("width")
    public int width;
    @JsonProperty("height")
    public int height;
}
