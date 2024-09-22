package com.osrs.trader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private int id;
    @JsonProperty("members")
    private boolean isMembers;
    @JsonProperty("limit")
    private int buyLimit;
    private int value;
    private int lowalch;
    private int highalch;
    private String icon;
    private String name;
    private String examine;


}
