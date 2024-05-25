package com.osrs.trader.converter;

import com.osrs.trader.dto.BaseDto;
import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.dto.LatestDto;

public class LatestConverter {

    public static LatestDto toDto(BaseDto baseDto, ItemDto itemDto) {
        return LatestDto.builder()
                .id(baseDto.getId())
                .name(itemDto.getName())
                .icon(itemDto.getIcon())
                .buyPrice(baseDto.getBuyPrice())
                .sellPrice(baseDto.getSellPrice())
                .highTime(baseDto.getHighTime())
                .lowTime(baseDto.getLowTime())
                .build();
    }
}
