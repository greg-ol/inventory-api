package com.gildedrose.go.inventorymgnt.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Represents an item to update
 */
public class Item {

    @NotBlank(message = "name is required")
    private final String name;

    @NotNull(message = "sellIn is required")
    private final Integer sellIn;

    @NotNull(message = "quality is required")
    private final Integer quality;

    public Item(String name, Integer sellIn, Integer quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public String getName() {
        return this.name;
    }

    public Integer getSellIn() {
        return this.sellIn;
    }

    public Integer getQuality() {
        return this.quality;
    }

}
