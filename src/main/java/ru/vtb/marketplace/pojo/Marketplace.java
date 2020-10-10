package ru.vtb.marketplace.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author denis-panin
 */
public class Marketplace {
    @JsonProperty("list")
    public List<BrandInfo> list;
}
