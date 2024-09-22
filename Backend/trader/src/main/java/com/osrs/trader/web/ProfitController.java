package com.osrs.trader.web;

import com.osrs.trader.dto.ProfitDto;
import com.osrs.trader.service.ProfitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/profit")
public class ProfitController {
    @Autowired
    ProfitService profitService;

    @GetMapping("/")
    public Mono<List<ProfitDto>> getProfitMargins() {
        return profitService.getProfitMargins();
    }

}