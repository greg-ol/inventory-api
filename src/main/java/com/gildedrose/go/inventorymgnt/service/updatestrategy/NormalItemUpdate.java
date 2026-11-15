package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import org.springframework.stereotype.Component;

/**
 * Strategy for updating normal item.
 */
@Component
public class NormalItemUpdate extends AbsItemUpdate {

    @Override
    int getUpdatedQuality(boolean passedBySellDate, int sellIn, int quality) {
        // Decreases in quality by 1 but twice as much when sell by date passed
        if (passedBySellDate) {
            return quality - 2;
        }
        return quality - 1;
    }
}
