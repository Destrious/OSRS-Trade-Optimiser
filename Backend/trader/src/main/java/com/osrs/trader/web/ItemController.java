package com.osrs.trader.web;

import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.service.ItemService;
import com.osrs.trader.service.ScheduledService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;
    @Autowired
    ScheduledService scheduledService;

    @GetMapping("/")
    public Mono<List<ItemDto>> getAllItems() {
        return itemService.getAllItems();
    }

//    @GetMapping("/profit")
//    public Mono<List<ItemDto>> getProfitableItems(@RequestParam int limit) {
//        return itemService.getProfitableItems(limit);
//    }

    // post request to refresh
    @PostMapping("/refresh")
    public void refreshMapping() {
        scheduledService.refreshMapping();
    }

}
