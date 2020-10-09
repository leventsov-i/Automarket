package ru.vtb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author denis-panin
 */
public class Country {
    @JsonProperty("code")
    public String code;
    @JsonProperty("id")
    public int id;
    @JsonProperty("title")
    public String title;
}
