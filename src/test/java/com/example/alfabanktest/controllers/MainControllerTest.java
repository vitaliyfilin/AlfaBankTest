package com.example.alfabanktest.controllers;

import com.example.alfabanktest.controller.MainController;
import com.example.alfabanktest.entity.*;
import com.example.alfabanktest.services.ExchangeService;
import com.example.alfabanktest.services.GifService;
import com.example.alfabanktest.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.awt.*;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(controllers = MainController.class)
class MainControllerTest {
    @Value("${openexchangerates.server}")
    String exchangeserver;
    @Value("${openexchangerates.appid}")
    String appId;
    @Value("${giphy.url.general}")
    String giphyServer;
    @Value("${giphy.api.key}")
    String giphyApiKey;
    @Value("${giphy.rich}")
    String rich;
    @Value("${giphy.broke}")
    String broke;
    @MockBean
    ExchangeService exchangeService;
    @MockBean
    GifService gifService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getGifForExchange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/code").param("code", "AED")).andExpect(status().isOk()).andDo(print());
    }

    @BeforeEach
    void beforeEach() throws URISyntaxException {
        Images images = new Images();
        Data data = new Data("", "", "", "", "", images);
        Original original = new Original();
        images.setOriginal(original);
        Gif gif = new Gif();
        gif.setData(data);
        URI today = Util.getCurrentUri(exchangeserver, appId);
        URI yesterday = Util.getYesterdayUri(exchangeserver, appId);
        URI uri = Util.getGiphyUrl(giphyServer, giphyApiKey, rich);
        Map<String, BigDecimal> todayMap = new HashMap<>();
        Map<String, BigDecimal> yesterdayMap = new HashMap<>();
        todayMap.put("AED", BigDecimal.valueOf(12345));
        yesterdayMap.put("AED", BigDecimal.valueOf(54321));
        Exchange todayExchange = new Exchange("a", "a", (int) new Date().getTime(), "a", todayMap);
        Exchange yesterdayExchange = new Exchange("a", "a", (int) new Date().getTime(), "a", yesterdayMap);
        when(exchangeService.getExchangeRates(today)).thenReturn(todayExchange);
        when(exchangeService.getExchangeRates(yesterday)).thenReturn(yesterdayExchange);
        when(gifService.getGiphyUrl(uri)).thenReturn(gif);
    }

    @Test
    void getListOfCurrencies() throws URISyntaxException {
        Assertions.assertFalse(exchangeService.getExchangeRates(Util.getCurrentUri(exchangeserver, appId))
                .getRates().keySet().stream().toList().isEmpty());
        Assertions.assertTrue(exchangeService.getExchangeRates(Util.getCurrentUri(exchangeserver, appId)) != null);
    }
}