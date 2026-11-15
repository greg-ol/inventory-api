package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import com.gildedrose.go.inventorymgnt.model.Item;

/**
 * Interface for Item Update logic.
 */
public interface ItemUpdate {

    Item updateItemInventory(Item item);
}
