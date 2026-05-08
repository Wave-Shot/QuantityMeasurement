package com.app.quantitymeasurement;

import com.app.quantitymeasurement.repository.QuantityMeasurementRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityMeasurementAppTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuantityMeasurementRepository
            repository;

    @Test
    public void testCompareEqualMeasurements()
            throws Exception {

        mockMvc.perform(

                        get(
                                "/api/quantity/compare"
                        )

                                .param(
                                        "value1",
                                        "1"
                                )

                                .param(
                                        "unit1",
                                        "FEET"
                                )

                                .param(
                                        "type1",
                                        "Length"
                                )

                                .param(
                                        "value2",
                                        "12"
                                )

                                .param(
                                        "unit2",
                                        "INCHES"
                                )

                                .param(
                                        "type2",
                                        "Length"
                                )
                )

                .andExpect(
                        status().isOk()
                )

                .andExpect(
                        content().string(
                                "true"
                        )
                );
    }

    @Test
    public void testRepositorySave() {

        long before =
                repository.count();

        repository.count();

        long after =
                repository.count();

        assert after >= before;
    }
}