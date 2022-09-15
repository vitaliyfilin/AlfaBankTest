package com.example.alfabanktest;

import com.example.alfabanktest.controller.MainController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

@SpringBootTest

class AlfaBankTestApplicationTests {
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
    void contextLoads() {
        Assertions.assertNotNull(exchangeserver);
        Assertions.assertNotNull(appId);
        Assertions.assertNotNull(giphyApiKey);
        Assertions.assertNotNull(giphyServer);
        Assertions.assertNotNull(rich);
        Assertions.assertNotNull(broke);
    }

}
