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

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .examine(item.getExamine())
                .id(item.getId())
                .members(item.isMembers())
                .lowalch(item.getLowalch())
                .buyLimit(item.getBuyLimit())
                .value(item.getValue())
                .highalch(item.getHighalch())
                .icon(item.getIcon())
                .name(item.getName())
                .build();
    }
}
