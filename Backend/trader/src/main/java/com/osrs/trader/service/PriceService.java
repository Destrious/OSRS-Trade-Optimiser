package com.osrs.trader.service;

import com.osrs.trader.dto.*;
import com.osrs.trader.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PriceService {

    private final WebClient webClient;

    @Autowired
    PriceRepository priceRepository;


    public PriceService(WebClient.Builder webClientBuilder, @Value("${trader.api.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    public Mono<List<PriceDto>> getPrices() {
        return this.webClient.get()
                .uri("/latest")
                .retrieve()
                .bodyToMono(BaseDataDto.class)
                .flatMapIterable(baseDataDto -> baseDataDto.getData().entrySet())
                .map(entry -> {
                    PriceDto dto = entry.getValue();
                    dto.setId(Integer.parseInt(entry.getKey()));
                    return dto;
                })
                .collectList();
    }

}