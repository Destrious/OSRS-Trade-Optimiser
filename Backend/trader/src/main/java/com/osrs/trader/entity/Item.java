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

    @Id
    private int id;
    private boolean isMembers;
    private int buyLimit;
    private int value;
    private int lowalch;
    private int highalch;
    private String icon;
    private String name;
    private String examine;

}
