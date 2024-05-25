package com.osrs.trader.converter;

import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.dto.BaseDto;
import com.osrs.trader.dto.ProfitDto;

public class ProfitConverter {
    public static ProfitDto toDto(BaseDto baseDto, ItemDto itemDto) {
        int profit = baseDto.getSellPrice() - baseDto.getBuyPrice();
        float tax = 0.01f * baseDto.getBuyPrice();
        return ProfitDto.builder()
                .id(baseDto.getId())
                .name(itemDto.getName())
                .icon(itemDto.getIcon())
                .buyPrice(baseDto.getBuyPrice())
                .sellPrice(baseDto.getSellPrice())
                .profitRaw((int) (profit - tax))
                .profitPercent(((profit - tax) / baseDto.getBuyPrice()) * 100)
                .latestSale(baseDto.getLowTime() < baseDto.getHighTime() ? baseDto.getLowTime() : baseDto.getHighTime())
                .build();
    }
}
