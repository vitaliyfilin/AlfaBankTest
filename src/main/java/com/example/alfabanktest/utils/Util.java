package com.example.alfabanktest.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Util {

    public static URI getCurrentUri(String exchangeserver, String appId) throws URISyntaxException {
        return new URI(exchangeserver + "latest.json?app_id=" + appId);
    }

    public static URI getYesterdayUri(String exchangeserver, String appId) throws URISyntaxException {
        return new URI(exchangeserver + "historical/" + getYesterdayDate() + ".json?app_id=" + appId);
    }

    public static String getYesterdayDate() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now().minusDays(1L));
    }

    public static URI getGiphyUrl(String giphyServer, String giphyApiKey, String tag) throws URISyntaxException {
        return new URI(giphyServer + "/random?api_key=" + giphyApiKey + "&tag=" + tag);
    }
}
