package ru.vtb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author denis-panin
 */
public class TransportType {
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("id")
    public int id;
    @JsonProperty("title")
    public String title;
}
