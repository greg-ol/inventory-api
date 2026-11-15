package com.gildedrose.go.inventorymgnt.controller;

import com.gildedrose.go.inventorymgnt.model.InvUpdateRequest;
import com.gildedrose.go.inventorymgnt.model.InvUpdateResponse;
import com.gildedrose.go.inventorymgnt.service.InventoryUpdateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Endpoints for Inventory update operations.
 */
@RestController
@RequestMapping("/api/v1/inventory")
@Tag(name = "Inventory Controller") // Info for swagger
public class InventoryUpdateController {

    private final InventoryUpdateService service;

    public InventoryUpdateController(InventoryUpdateService service) {
        this.service = service;
    }

    @Operation(description = "Processes update request of Gilded Rose inventory returning updated inventory" +
            " or error payload.") // Info for swagger
    @PostMapping("/updateinventory")
    public InvUpdateResponse updateInventory(@Valid @RequestBody InvUpdateRequest request) {
        return service.updateInventory(request);
    }
}
