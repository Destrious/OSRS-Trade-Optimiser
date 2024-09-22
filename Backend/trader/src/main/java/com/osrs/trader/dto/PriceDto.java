package com.osrs.trader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceDto {

    private long pid;
    private int id;
    @JsonProperty("low")
    private int buyPrice;
    @JsonProperty("high")
    private int sellPrice;
    @JsonProperty("lowTime")
    private long buyTime;
    @JsonProperty("highTime")
    private long sellTime;

}
