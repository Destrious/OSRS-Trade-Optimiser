package com.osrs.trader.converter;

import com.osrs.trader.dto.PriceDto;
import com.osrs.trader.entity.Price;

public class PriceConverter {

    public static Price toEntity(PriceDto priceDto) {
        return Price.builder()
                .pid(priceDto.getPid())
                .id(priceDto.getId())
                .buyPrice(priceDto.getBuyPrice())
                .sellPrice(priceDto.getSellPrice())
                .buyTime(priceDto.getBuyTime())
                .sellTime(priceDto.getSellTime())
                .build();
    }

}
