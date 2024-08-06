package com.example.stockquote;

import com.example.stockquote.controller.stockController;
import com.example.stockquote.services.stockServices;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(stockController.class)
public class stockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private stockServices stockServices;

    @Test
    public void testCallEndPoint() throws Exception {
        String mockResponse = "[ { \"symbol\": \"AAPL\", \"companyName\": \"Apple Inc.\" }, { \"symbol\": \"GOOGL\", \"companyName\": \"Alphabet Inc.\" } ]";

        when(stockServices.getStockData()).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/getStock")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mockResponse));
    }

    @Test
    public void testGetStockData() throws Exception {
        String symbol = "AAPL";
        String mockResponse = "{ \"symbol\": \"IBM\" }";

        when(stockServices.getStockDataBySymbol(symbol)).thenReturn(mockResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/getSingleStock")
                        .param("symbol", symbol)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mockResponse));
    }
}

