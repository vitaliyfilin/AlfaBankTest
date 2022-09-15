package com.example.alfabanktest.services;

import com.example.alfabanktest.entity.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "${giphy.name}", url = "${giphy.url.general}")
public interface GifService {
    @GetMapping
    Gif getGiphyUrl (URI uri);
}
