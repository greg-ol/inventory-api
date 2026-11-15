package com.gildedrose.go.inventorymgnt.service;

import com.gildedrose.go.inventorymgnt.model.InvUpdateRequest;
import com.gildedrose.go.inventorymgnt.model.InvUpdateResponse;
import com.gildedrose.go.inventorymgnt.model.Item;
import com.gildedrose.go.inventorymgnt.service.updatestrategy.InventoryUpdateStrategyFactory;
import com.gildedrose.go.inventorymgnt.service.updatestrategy.ItemUpdate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service for application of business logic for inventory update.
 */
@Service
public class InventoryUpdateService {

    private final InventoryUpdateStrategyFactory factory;

    public InventoryUpdateService(InventoryUpdateStrategyFactory factory) {
        this.factory = factory;
    }


    public InvUpdateResponse updateInventory(InvUpdateRequest request) {
        int days = request.days();
        LocalDate submissionDate = request.date();

        List<Item> items = request.items();
        for (int i = 0; i < days; i++) {
            items = items.stream().map(this::updateItem).toList();
        }

        LocalDate inventoryDate = submissionDate.plusDays(days);
        return new InvUpdateResponse(inventoryDate, items);
    }

    public Item updateItem(Item item) {
        ItemUpdate strategy = factory.getStrategy(item.getName());
        if (strategy == null) {
            throw new IllegalStateException("No update rule for processed item: " + item.getName());
        }

        return strategy.updateItemInventory(item);
    }
}
