package com.osrs.trader.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long pid;
    private int id;
    private int buyPrice;
    private int sellPrice;
    private long buyTime;
    private long sellTime;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;


}
