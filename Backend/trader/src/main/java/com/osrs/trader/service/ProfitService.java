package com.osrs.trader.service;

import com.osrs.trader.dto.ProfitDto;
import com.osrs.trader.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
public class ProfitService {

    @Autowired
    PriceRepository priceRepository;


    public Mono<List<ProfitDto>> getProfitMargins() {
        return Mono.fromCallable(() -> {
            List<ProfitDto> profitMargins = priceRepository.getProfitMarginsWithItemNames();
            profitMargins.sort(Comparator.comparingInt(ProfitDto::getProfitRaw).reversed());
            return profitMargins;
        });
    }

}