package com.sparta.spartaexam.dto;

import com.sparta.spartaexam.entity.Market;
import lombok.Getter;

@Getter
public class MarketResponseDto {
    private Long id;
    private String title;
    private String content;
    private String price;
    private String username;


    public MarketResponseDto(Market market) {
        this.id = market.getId();
        this.title = market.getTitle();
        this.content = market.getContent();
        this.price = market.getPrice();
        this.username = market.getUsername();
    }


    public MarketResponseDto(Long id, String title, String content, String price, String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.username = username;

    }

}