package com.osrs.trader.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private String examine;
    @Id
    private int id;
    private boolean members;
    private int lowalch;
    private int limit;
    private int value;
    private int highalch;
    private String icon;
    private String name;

}
