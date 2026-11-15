package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import com.gildedrose.go.inventorymgnt.model.Item;
import org.springframework.stereotype.Component;

/**
 * Strategy for sulfuras update.
 */
@Component
public class SulfurasUpdate implements ItemUpdate {

    public static final int QUALITY = 80;

    @Override
    public Item updateItemInventory(Item item) {
        return new Item(item.getName(), item.getSellIn(), QUALITY);
    }
}
