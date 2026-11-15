package com.gildedrose.go.inventorymgnt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryUpdateControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    void acceptsValidRequests() throws Exception {
        executeCall("{\"date\":\"2026-11-11\", \"days\":2, \"items\":[{\"name\": \"Aged Brie\", \"sellIn\":2, \"quality\":0}" +
                ",{\"name\": \"Normal Item\", \"sellIn\": 3, \"quality\":3}]}")
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.inventoryDate").value("2026-11-13"))
                .andExpect(jsonPath("$.items[0].name").value("Aged Brie"))
                .andExpect(jsonPath("$.items[0].sellIn").value(0))
                .andExpect(jsonPath("$.items[0].quality").value(2))
                .andExpect(jsonPath("$.items[1].name").value("Normal Item"))
                .andExpect(jsonPath("$.items[1].sellIn").value("1"))
                .andExpect(jsonPath("$.items[1].quality").value("1"));
    }

    @Test
    void rejectsNotValidItems() throws Exception {
        executeCall("{\"date\":\"2026-11-11\", \"days\":3, \"items\":[{\"name\": \"Not valid Item\", \"sellIn\":2, \"quality\":0}" +
                ",{\"name\": \"Normal Item\", \"sellIn\": 3, \"quality\":3}]}").andExpect(status().isBadRequest());
    }

    @Test
    void rejectsNonParsablePayload() throws Exception {
        executeCall("{\"items\":\"non parsable\"}").andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
    }

    @Test
    void rejectsNotCompletePayload() throws Exception {
        executeCall("{}").andExpect(status().isBadRequest());
        executeCall("{\"items\":[{\"sellIn\":1, \"quality\":20}]}").andExpect(status().isBadRequest());
    }


    private ResultActions executeCall(String body) throws Exception {
        return mvc.perform(post("/api/v1/inventory/updateinventory").contentType(MediaType.APPLICATION_JSON).content(body));
    }
}