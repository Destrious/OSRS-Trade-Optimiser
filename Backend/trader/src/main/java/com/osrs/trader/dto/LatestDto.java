package com.osrs.trader.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LatestDto {

    private int id;
    @JsonProperty("high")
    private int sellPrice;
    @JsonProperty("low")
    private int buyPrice;
    private long highTime;
    private long lowTime;

}
