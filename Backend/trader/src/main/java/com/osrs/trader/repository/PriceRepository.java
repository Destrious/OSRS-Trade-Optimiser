package com.osrs.trader.repository;

import com.osrs.trader.dto.ProfitDto;
import com.osrs.trader.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Integer> {
//    @Query(value = "SELECT new com.osrs.trader.dto.ProfitDto(p.id, p.sellPrice - p.buyPrice, (p.sellPrice - p.buyPrice) / p.buyPrice) FROM Price p")
//    List<ProfitDto> getProfitMargins();

    @Query(value = "SELECT new com.osrs.trader.dto.ProfitDto(p.id, p.buyPrice, p.sellPrice, p.buyTime, p.sellTime) " +
            "FROM Price p " +
            "WHERE p.pid = (SELECT MAX(sub_p.pid) FROM Price sub_p WHERE sub_p.id = p.id)")
    List<ProfitDto> getProfitMargins();

}
