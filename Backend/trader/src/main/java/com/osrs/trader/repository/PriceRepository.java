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

//    @Query("SELECT new com.osrs.trader.dto.ProfitDto(p.id, p.buyPrice, p.sellPrice, p.buyTime, p.sellTime, i.name) " +
//            "FROM Price p " +
//            "JOIN p.item i " +
//            "WHERE p.pid = (SELECT MAX(sub_p.pid) FROM Price sub_p WHERE sub_p.id = p.id)")
//    List<ProfitDto> getProfitMarginsWithItemNames();

    @Query("SELECT new com.osrs.trader.dto.ProfitDto(p.id, i.name, i.icon, p.buyPrice, p.sellPrice, p.buyTime, p.sellTime) " +
            "FROM Price p " +
            "JOIN Item i on i.id = p.id " +
            "JOIN ( " +
            "    SELECT sub_p.id AS id, MAX(sub_p.pid) AS max_pid " +
            "    FROM Price sub_p " +
            "    GROUP BY sub_p.id " +
            ") p_max ON p.id = p_max.id AND p.pid = p_max.max_pid")
    List<ProfitDto> getProfitMarginsWithItemNames();

}
