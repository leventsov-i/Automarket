package ru.vtb.marketplace.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransportType {
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("id")
    public int id;
    @JsonProperty("title")
    public String title;
}
