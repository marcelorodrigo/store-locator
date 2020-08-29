package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.entity.Store;
import com.marcelorodrigo.neareststore.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static java.util.Arrays.asList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
class GetNearestStoresByCoordinateTest {

    @MockBean
    private StoreService storeService;

    @Autowired
    private MockMvc mockMvc;

    private ResultActions resultActions;


    @Test
    void whenInvalidLatitude_mustReturn400BadRequest() throws Exception {
        whenGetNearestStoreIsCalled(-90.01, 5);
        thenBadCoordinatedIsReturned();
    }

    @Test
    void whenInvalidLongitude_mustReturn400BadRequest() throws Exception {
        whenGetNearestStoreIsCalled(9, 190.0001);
        thenBadCoordinatedIsReturned();
    }

    @Test
    void whenGetNearest_mustReturn5Stores() throws Exception {
        givenAtLeastFiveStoresExists();
        whenGetNearestStoreIsCalled(5.98721, 9.40157);
        thenFiveStoresAreReturned();
    }

    private void givenAtLeastFiveStoresExists() {
        Page<Store> stores = new PageImpl<>(asList(new Store(), new Store(), new Store(), new Store(), new Store()));
        when(storeService.findNearest(anyDouble(), anyDouble(), any(Pageable.class)))
                .thenReturn(stores);
    }

    private void thenFiveStoresAreReturned() throws Exception {
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(5)))
                .andExpect(jsonPath("$.content[0].sapStoreID").doesNotExist());
    }

    private void thenBadCoordinatedIsReturned() throws Exception {
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.dateTime").exists())
                .andExpect(jsonPath("$.message").exists());
    }

    private void whenGetNearestStoreIsCalled(double latitude, double longitude) throws Exception {
        resultActions = mockMvc.perform(get("/store/?latitude=" + latitude + "&longitude=" + longitude));
    }
}