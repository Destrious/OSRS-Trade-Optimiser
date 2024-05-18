package com.osrs.trader.converter;

import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.dto.LatestDto;
import com.osrs.trader.dto.ProfitDto;

public class ProfitConverter {
    public static ProfitDto toDto(LatestDto latestDto, ItemDto itemDto) {
        int profit = latestDto.getSellPrice() - latestDto.getBuyPrice();
        float tax = 0.01f * latestDto.getBuyPrice();
        return ProfitDto.builder()
                .id(latestDto.getId())
                .name(itemDto.getName())
                .icon(itemDto.getIcon())
                .buyPrice(latestDto.getBuyPrice())
                .sellPrice(latestDto.getSellPrice())
                .profitRaw((int) (profit - tax))
                .profitPercent(((profit - tax) / latestDto.getBuyPrice()) * 100)
                .latestSale(latestDto.getLowTime() < latestDto.getHighTime() ? latestDto.getLowTime() : latestDto.getHighTime())
                .build();
    }
}
