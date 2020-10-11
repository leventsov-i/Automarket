package ru.vtb.marketplace.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Dealer {
    @JsonProperty("logo")
    private final String logo;
    @JsonProperty("title")
    private final String title;
    @JsonProperty("address")
    private final String address;

    public Dealer(String logo, String title, String address) {
        this.logo = logo;
        this.title = title;
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }
}
