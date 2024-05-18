package com.osrs.trader.service;

import com.osrs.trader.converter.ItemConverter;
import com.osrs.trader.converter.ProfitConverter;
import com.osrs.trader.dto.ItemDto;
import com.osrs.trader.dto.LatestDataDto;
import com.osrs.trader.dto.LatestDto;
import com.osrs.trader.dto.ProfitDto;
import com.osrs.trader.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final WebClient webClient;

    @Autowired
    ItemRepository itemRepository;


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

    public Mono<List<ProfitDto>> getProfitableItems(int limit, boolean percentage) {
        Mono<List<LatestDto>> latestDtos = this.webClient.get()
                .uri("/latest")
                .retrieve()
                .bodyToMono(LatestDataDto.class)
                .flatMapIterable(latestDataDto -> latestDataDto.getData().entrySet())
                .map(entry -> {
                    LatestDto dto = entry.getValue();
                    dto.setId(Integer.parseInt(entry.getKey()));
                    return dto;
                })
                .collectList();


        List<ProfitDto> profitDtos = latestDtos.block().stream()
                .flatMap(latestDto -> itemRepository.findById(latestDto.getId())
                        .map(ItemConverter::toDto)
                        .map(itemDto -> ProfitConverter.toDto(latestDto, itemDto))
                        .stream())
                .sorted(percentage ?
                        Comparator.comparing(ProfitDto::getProfitPercent).reversed() :
                        Comparator.comparing(ProfitDto::getProfitRaw).reversed())
                .limit(limit)
                .collect(Collectors.toList());

        return Mono.just(profitDtos);


    }

}
