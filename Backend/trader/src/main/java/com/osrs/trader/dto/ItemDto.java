package com.osrs.trader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private String examine;
    private int id;
    private boolean members;
    private int lowalch;
    @JsonProperty("limit")
    private int buyLimit;
    private int value;
    private int highalch;
    private String icon;
    private String name;


}
