package com.example.alfabanktest.controller;

import com.example.alfabanktest.entity.Exchange;
import com.example.alfabanktest.entity.Gif;
import com.example.alfabanktest.services.ExchangeService;
import com.example.alfabanktest.services.GifService;
import com.example.alfabanktest.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URISyntaxException;

@Controller
public class MainController {
    @Autowired
    ExchangeService exchangeService;
    @Autowired
    GifService gifService;
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

    @PostMapping("/code")
    public String getGifForExchange(@RequestParam(value = "code") String code, Model model) throws URISyntaxException {
        Exchange firstExchange = exchangeService.getExchangeRates(Util.getCurrentUri(exchangeserver, appId));
        Exchange yesterdayExchange = exchangeService.getExchangeRates(Util.getYesterdayUri(exchangeserver, appId));

        if (firstExchange.getRates().get(code).compareTo(yesterdayExchange.getRates().get(code)) < 0) {
            Gif a = gifService.getGiphyUrl(Util.getGiphyUrl(giphyServer, giphyApiKey, rich));

            model.addAttribute("title", a.getData().getTitle());
            model.addAttribute("rich", a.getData().getImages().getOriginal().getUrl());
        } else {
            Gif b = gifService.getGiphyUrl(Util.getGiphyUrl(giphyServer, giphyApiKey, broke));
            model.addAttribute("title", b.getData().getTitle());
            model.addAttribute("broke", b.getData().getImages().getOriginal().getUrl());

        }
        return "code";
    }

    @GetMapping("/index")
    public String getListOfCurrencies(Model model) throws URISyntaxException {
        model.addAttribute("currencyList", exchangeService.getExchangeRates(Util.getCurrentUri(exchangeserver, appId)).getRates()
                .keySet().stream().toList());
        return "index";
    }

}


