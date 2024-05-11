package com.osrs.trader.web;

import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
public class itemController {
    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public Mono<List<ItemDto>> getAllItems() {
        return itemService.getAllItems();
    }
}
