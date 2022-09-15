package com.example.alfabanktest.services;

import com.example.alfabanktest.entity.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "openexchange.name", url = "${openexchangerates.server}")
public interface ExchangeService {
    @GetMapping
    Exchange getExchangeRates (URI uri);

}
