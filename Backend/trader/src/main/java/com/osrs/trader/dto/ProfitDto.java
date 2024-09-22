package com.osrs.trader.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfitDto {

    private int id;
    private int buyPrice;
    private int sellPrice;
    private int profitRaw;
    private float profitPercent;
    private long buyTime;
    private long sellTime;

    public ProfitDto(int id, Number buyPrice, Number sellPrice, Number buyTime, Number sellTime) {
        this.id = id;
        this.buyPrice = buyPrice.intValue();
        this.sellPrice = sellPrice.intValue();
        this.buyTime = buyTime.longValue();
        this.sellTime = sellTime.longValue();
        this.profitRaw = sellPrice.intValue() - buyPrice.intValue();
        this.profitPercent = ((float) profitRaw / buyPrice.intValue()) * 100;
    }

    public ProfitDto(int id, int buyPrice, int sellPrice, int profitRaw, float profitPercent, long buyTime, long sellTime) {
        this.id = id;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.buyTime = buyTime;
        this.sellTime = sellTime;
        this.profitRaw = profitRaw;
        this.profitPercent = profitPercent;
    }
}
