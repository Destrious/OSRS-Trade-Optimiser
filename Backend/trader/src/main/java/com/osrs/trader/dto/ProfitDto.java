package com.osrs.trader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfitDto {

    private int id;
    private String name;
    private String icon;
    @JsonProperty("low")
    private int buyPrice;
    @JsonProperty("high")
    private int sellPrice;
    private int profitRaw;
    private float profitPercent;
    private long latestSale;
}
