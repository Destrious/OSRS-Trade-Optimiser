package com.osrs.trader.service;

import com.osrs.trader.dto.*;
import com.osrs.trader.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;

@Service
public class ItemService {

    private final WebClient webClient;

    @Autowired
    ItemRepository itemRepository;


    public ItemService(WebClient.Builder webClientBuilder, @Value("${trader.api.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

//    public Mono<List<LatestDto>> getLatestPrices() {
//        Mono<List<BaseDto>> baseDtos = this.webClient.get()
//                .uri("/latest")
//                .retrieve()
//                .bodyToMono(BaseDataDto.class)
//                .flatMapIterable(baseDataDto -> baseDataDto.getData().entrySet())
//                .map(entry -> {
//                    BaseDto dto = entry.getValue();
//                    dto.setId(Integer.parseInt(entry.getKey()));
//                    return dto;
//                })
//                .collectList();
//
//        List<LatestDto> latestDtos = baseDtos.block().stream()
//                .flatMap(baseDto -> itemRepository.findById(baseDto.getId())
//                        .map(ItemConverter::toDto)
//                        .map(itemDto -> LatestConverter.toDto(baseDto, itemDto))
//                        .stream())
//                .sorted(Comparator.comparing(LatestDto::getName))
//                .collect(Collectors.toList());
//
//        return Mono.just(latestDtos);
//
//    }

    public Mono<List<ItemDto>> getItemMapping() {
        return this.webClient.get()
                .uri("/mapping")
                .retrieve()
                .bodyToFlux(ItemDto.class)
                .collectList();
    }

//    public Mono<List<ProfitDto>> getProfitableItems(int limit, boolean percentage) {
//        Mono<List<BaseDto>> baseDtos = this.webClient.get()
//                .uri("/latest")
//                .retrieve()
//                .bodyToMono(BaseDataDto.class)
//                .flatMapIterable(baseDataDto -> baseDataDto.getData().entrySet())
//                .map(entry -> {
//                    BaseDto dto = entry.getValue();
//                    dto.setId(Integer.parseInt(entry.getKey()));
//                    return dto;
//                })
//                .collectList();
//
//
//        List<ProfitDto> profitDtos = baseDtos.block().stream()
//                .flatMap(baseDto -> itemRepository.findById(baseDto.getId())
//                        .map(ItemConverter::toDto)
//                        .map(itemDto -> ProfitConverter.toDto(baseDto, itemDto))
//                        .stream())
//                .sorted(percentage ?
//                        Comparator.comparing(ProfitDto::getProfitPercent).reversed() :
//                        Comparator.comparing(ProfitDto::getProfitRaw).reversed())
//                .limit(limit)
//                .collect(Collectors.toList());
//
//        return Mono.just(profitDtos);
//
//
//    }

}