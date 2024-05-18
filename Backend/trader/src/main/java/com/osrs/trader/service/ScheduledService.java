package com.osrs.trader.service;

import com.osrs.trader.converter.ItemConverter;
import com.osrs.trader.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ScheduledService {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;

    @Scheduled(fixedRate = 360000)
    public void refreshMapping() {
        System.out.println("Refreshing mapping");
        itemService.getAllItems()
                .flatMapIterable(Function.identity())
                .map(ItemConverter::toEntity)
                .collectList()
                .subscribe(itemRepository::saveAll);
    }
}
