package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import org.springframework.stereotype.Component;

/**
 * Strategy for updating conjured item.
 */
@Component
public class ConjuredUpdate extends AbsItemUpdate {


    @Override
    int getUpdatedQuality(boolean passedBySellDate, int sellIn, int quality) {
        // Decrease in quality twice as fast as normal item.
        return quality - (passedBySellDate ? 4 : 2);
    }
}
