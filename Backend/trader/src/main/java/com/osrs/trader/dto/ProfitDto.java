package com.osrs.trader.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProfitDto {

    private int buyPrice;
    private int sellPrice;
    private int profitRaw;
    private float profitPercent;
    private LocalDateTime latestSale;
}
