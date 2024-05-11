package com.osrs.trader.service;

import com.osrs.trader.dto.ItemDto;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ItemService {

    private final WebClient webClient;

    public ItemService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://prices.runescape.wiki/api/v1/osrs").build();
    }

    public Mono<List<ItemDto>> getAllItems() {
        return this.webClient.get()
                .uri("/mapping")
                .retrieve()
                .bodyToFlux(ItemDto.class)
                .collectList();
    }

}
