package com.gildedrose.go.inventorymgnt.model;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents response body for inventory update.
 */
public record InvUpdateResponse(
        LocalDate inventoryDate,
        List<Item> items
) {
}
