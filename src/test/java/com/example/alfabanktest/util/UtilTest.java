package com.example.alfabanktest.util;

import com.example.alfabanktest.utils.Util;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;


import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
public class UtilTest {
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
    void getCurrentUri() throws URISyntaxException {
        URI currentUri = Util.getCurrentUri(exchangeserver, appId);
        URI uri = URI.create(exchangeserver + "latest.json?app_id=" + appId);
        Assertions.assertEquals(currentUri, uri);
    }

    @Test
    void getYesterdayUri() {
    }

    @Test
    void getYesterdayDate() {
    }

    @Test
    void getGiphyUrl() {
    }
}
