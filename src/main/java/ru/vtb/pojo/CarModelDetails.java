package ru.vtb.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author denis-panin
 */
public class CarModelDetails {
    @JsonProperty("absentee")
    public boolean absentee;
    @JsonProperty("alias")
    public String alias;
    @JsonProperty("id")
    public int id;
    @JsonProperty("prefix")
    public String prefix;
    @JsonProperty("title")
    public String title;
    @JsonProperty("titleRus")
    public String titleRus;
}
