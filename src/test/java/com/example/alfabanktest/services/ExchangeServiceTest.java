package com.example.alfabanktest.services;

import com.example.alfabanktest.utils.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ExchangeServiceTest {
@Autowired
ExchangeService exchangeService;
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
    @Test
    void getExchangeRates() throws URISyntaxException {
        Assertions.assertNotNull(exchangeService.getExchangeRates(Util.getCurrentUri(exchangeserver, appId)));
        Assertions.assertNotNull(exchangeService.getExchangeRates(Util.getYesterdayUri(exchangeserver, appId)));
    }
}