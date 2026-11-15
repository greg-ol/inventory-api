package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import com.gildedrose.go.inventorymgnt.model.Item;

/**
 * General logic for item update.
 */
public abstract class AbsItemUpdate implements ItemUpdate {
    protected static final int MAX_QTY = 50;

    @Override
    public Item updateItemInventory(Item item) {
        int sellIn = item.getSellIn() - 1;
        boolean passedSellByDate = sellIn < 0;
        int quality = getUpdatedQuality(passedSellByDate, item.getSellIn(), item.getQuality());

        // restrict quality to be between 0 and MAX_QTY
        int finalQuality = Math.clamp(quality, 0, MAX_QTY);

        return new Item(item.getName(), sellIn, finalQuality);
    }

    /**
     * Calculated update quality based on:
     *
     * @param passedBySellDate is item passed sell by date
     * @param sellIn           old sellIn
     * @param quality          old quality
     * @return new quality value
     */
    abstract int getUpdatedQuality(boolean passedBySellDate, int sellIn, int quality);
}
