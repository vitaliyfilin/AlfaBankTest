package com.example.alfabanktest.entity;


import lombok.AllArgsConstructor;

@lombok.Data
@AllArgsConstructor
public class Data {
    private String id;
    private String title;
    private String url;
    private String embed_url;
    private String rating;
    private Images images;
}
