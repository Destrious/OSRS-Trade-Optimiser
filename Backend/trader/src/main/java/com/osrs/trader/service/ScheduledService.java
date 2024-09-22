package com.osrs.trader.service;

import com.osrs.trader.converter.ItemConverter;
import com.osrs.trader.converter.PriceConverter;
import com.osrs.trader.repository.ItemRepository;
import com.osrs.trader.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class ScheduledService {
    @Autowired
    ItemService itemService;
    @Autowired
    PriceService priceService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PriceRepository priceRepository;

    @Scheduled(fixedRate = 60*60*1000) // min*seconds*milliseconds
    public void refreshMapping() {
        System.out.println("Refreshing mapping");
        itemService.getItemMapping()
                .flatMapIterable(Function.identity())
                .map(ItemConverter::toEntity)
                .collectList()
                .subscribe(itemRepository::saveAll);
    }

    @Scheduled(fixedRate = 15*60*1000) // min*seconds*milliseconds
    public void retrievePricing() {
        System.out.println("Retrieving prices");
        priceService.getPrices()
                .flatMapIterable(Function.identity())
                .map(PriceConverter::toEntity)
                .collectList()
                .subscribe(priceRepository::saveAll);
    }
}
