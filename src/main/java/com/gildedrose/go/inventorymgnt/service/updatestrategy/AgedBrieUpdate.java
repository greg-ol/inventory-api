package com.gildedrose.go.inventorymgnt.service.updatestrategy;

import org.springframework.stereotype.Component;

/**
 * Strategy of updates of aged brie
 */
@Component
public class AgedBrieUpdate extends AbsItemUpdate {

    @Override
    int getUpdatedQuality(boolean passedBySellDate, int sellIn, int quality) {
        // Increase in quality older it gets (there  is no mention about doubled increase after sell by date).
        return quality + 1;
    }
}
