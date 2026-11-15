package com.gildedrose.go.inventorymgnt.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents request body for inventory update.
 */
public record InvUpdateRequest(
        @NotNull(message = "items must not be null")
        List<@NotNull @Valid Item> items,

        @NotNull(message = "submission date must not be null")
        LocalDate date,

        @Min(value = 1, message = "min number of days must be 1")
        // Arbitrary validation for max num of days
        @Max(value = 365, message = "max value of days not higher then 365")
        Integer days
) {
}
