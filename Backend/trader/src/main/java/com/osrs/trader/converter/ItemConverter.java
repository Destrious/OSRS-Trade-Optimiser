package com.osrs.trader.converter;

import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.entity.Item;

public class ItemConverter {

    public static Item toEntity(ItemDto itemDto) {
        return Item.builder()
                .examine(itemDto.getExamine())
                .id(itemDto.getId())
                .members(itemDto.isMembers())
                .lowalch(itemDto.getLowalch())
                .buyLimit(itemDto.getBuyLimit())
                .value(itemDto.getValue())
                .highalch(itemDto.getHighalch())
                .icon(itemDto.getIcon())
                .name(itemDto.getName())
                .build();
    }
}
