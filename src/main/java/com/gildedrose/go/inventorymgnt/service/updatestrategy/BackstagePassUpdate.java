package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import org.springframework.stereotype.Component;

/**
 * Strategy for updating backstage pass.
 */
@Component
public class BackstagePassUpdate extends AbsItemUpdate {

    @Override
    int getUpdatedQuality(boolean passedBySellDate, int sellIn, int quality) {
        if (passedBySellDate) return 0;
        if (sellIn <= 5) return quality + 3;
        if (sellIn <= 10) return quality + 2;
        return quality + 1;
    }
}
