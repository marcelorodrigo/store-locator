package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.entity.Store;
import com.marcelorodrigo.neareststore.service.StoreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
class GetStoreByUuidTest {

    @MockBean
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    private final static Store STORE = new Store();
    private ResultActions resultActions;

    @BeforeAll
    private static void beforeAll() {
        STORE.setUuid("uuid1");
        STORE.setAddressName("Veghel Main Store");
    }

    @Test
    void whenStoreDoesNotExist_mustReturn404() throws Exception {
        givenStoreDoesNotExist("0");
        whenGetStoreIsCalled("0");
        then404IsReturned();
    }

    @Test
    void whenStoreExists_mustReturnStore() throws Exception {
        givenStoreExists(STORE.getUuid());
        whenGetStoreIsCalled(STORE.getUuid());
        thenStoreIsReturned();
    }

    private void thenStoreIsReturned() throws Exception {
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid").value(STORE.getUuid()))
                .andExpect(jsonPath("$.addressName").value(STORE.getAddressName()))
                .andExpect(jsonPath("$.sapStoreID").doesNotExist());
    }

    private void givenStoreExists(String id) {
        when(storeService.findById(id)).thenReturn(Optional.of(STORE));
    }

    private void givenStoreDoesNotExist(String id) {
        when(storeService.findById(id)).thenReturn(Optional.empty());
    }

    private void whenGetStoreIsCalled(String id) throws Exception {
        resultActions = mockMvc.perform(get("/store/" + id));
    }

    private void then404IsReturned() throws Exception {
        resultActions.andExpect(status().isNotFound());
    }
}