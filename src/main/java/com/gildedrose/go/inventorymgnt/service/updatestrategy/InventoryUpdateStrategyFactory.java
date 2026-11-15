package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for inventory update strategy
 */
@Component
public class InventoryUpdateStrategyFactory {

    private final Map<String, ItemUpdate> strategies;

    public InventoryUpdateStrategyFactory() {
        strategies = new HashMap<>();
        strategies.put("aged brie", new AgedBrieUpdate());
        strategies.put("conjured", new ConjuredUpdate());
        strategies.put("backstage passes", new BackstagePassUpdate());
        strategies.put("normal item", new NormalItemUpdate());
        strategies.put("sulfuras", new SulfurasUpdate());
    }

    public ItemUpdate getStrategy(String itemName) {
        return strategies.get(itemName.toLowerCase());
    }
}
