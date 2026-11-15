package com.gildedrose.go.inventorymgnt.service;

import com.gildedrose.go.inventorymgnt.model.InvUpdateRequest;
import com.gildedrose.go.inventorymgnt.model.InvUpdateResponse;
import com.gildedrose.go.inventorymgnt.model.Item;
import com.gildedrose.go.inventorymgnt.service.updatestrategy.InventoryUpdateStrategyFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InventoryUpdateServiceTest {

    private final InventoryUpdateStrategyFactory factory = new InventoryUpdateStrategyFactory();
    private final InventoryUpdateService service = new InventoryUpdateService(factory);


    @Test
    void sulfurasNeverChange() {
        Item item = updateItem("Sulfuras", -1, 80);
        assertEquals(-1, item.getSellIn());
        assertEquals(80, item.getQuality());
    }

    @Test
    void updateNormalItem_NotExpired() {
        Item item = updateItem("Normal Item", 5, 10);
        assertEquals(4, item.getSellIn());
        assertEquals(9, item.getQuality());
    }

    @Test
    void updateNormalItem_Expired() {
        Item item = updateItem("Normal Item", 0, 10);
        assertEquals(-1, item.getSellIn());
        assertEquals(8, item.getQuality());
    }

    @Test
    void qualityIsNotNegative() {
        assertEquals(0, updateItem("Normal Item", 5, 0).getQuality());
        assertEquals(0, updateItem("Conjured", -1, 1).getQuality());
    }

    @Test
    void qualityIncreaseCap() {
        assertEquals(12, updateItem("Aged Brie", 5, 11).getQuality());
        assertEquals(13, updateItem("Aged Brie", -5, 12).getQuality());
        assertEquals(50, updateItem("Aged Brie", 5, 50).getQuality());
        assertEquals(50, updateItem("Aged Brie", 5, 50).getQuality());
    }

    @Test
    void conjuredDegradationTest() {
        assertEquals(4, updateItem("Conjured", 3, 6).getQuality());
        assertEquals(2, updateItem("Conjured", 0, 6).getQuality());
    }

    @Test
    void backstagePassesDegradation() {
        assertEquals(11, updateItem("Backstage passes", 11, 10).getQuality());
        assertEquals(12, updateItem("Backstage passes", 10, 10).getQuality());
        assertEquals(12, updateItem("Backstage passes", 6, 10).getQuality());
        assertEquals(13, updateItem("Backstage passes", 5, 10).getQuality());
        assertEquals(50, updateItem("Backstage passes", 5, 49).getQuality());
        assertEquals(0, updateItem("Backstage passes", 0, 30).getQuality());
        assertEquals(0, updateItem("Backstage passes", -1, 2).getQuality());
    }

    @Test
    void multipleDaysExecution() {
        InvUpdateResponse response = service.updateInventory(new InvUpdateRequest(List.of(new Item("Normal Item", 10, 20)), LocalDate.parse("2026-11-11"), 4));
        assertEquals(LocalDate.parse("2026-11-15"), response.inventoryDate());
        assertEquals(6, response.items().getFirst().getSellIn());
        assertEquals(16, response.items().getFirst().getQuality());
    }

    private Item updateItem(String name, int sellIn, int quality) {
        return service.updateItem(new Item(name, sellIn, quality));
    }
}