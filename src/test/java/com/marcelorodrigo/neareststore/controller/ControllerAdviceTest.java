package com.marcelorodrigo.neareststore.controller;

import com.marcelorodrigo.neareststore.exception.InvalidLatitudeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WebMvcTest(ControllerAdviceTest.TestController.class)
class ControllerAdviceTest {

    private static final String TEST_ENDPOINT = "/throw-exception";
    private TestController testController;
    private ResultActions resultActions;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        testController = new TestController();
        mockMvc = standaloneSetup(testController)
                .setControllerAdvice(new ControllerAdvice())
                .build();
    }

    @Test
    void handleInvalidCoordinatesException() throws Exception {
        givenControllerThrows(new InvalidLatitudeException());
        whenRequestIsPerformed();
        thenTheResponseIsValidated();
    }

    private void thenTheResponseIsValidated() throws Exception {
        resultActions.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(testController.throwableToThrow.getMessage()));
    }

    private void whenRequestIsPerformed() throws Exception {
        resultActions = mockMvc.perform(get(TEST_ENDPOINT));
    }

    private void givenControllerThrows(Throwable throwable) {
        testController.throwableToThrow = throwable;
    }

    @RestController
    static class TestController {
        private Throwable throwableToThrow;

        @GetMapping(TEST_ENDPOINT)
        void throwException() throws Throwable {
            throw throwableToThrow;
        }
    }
}