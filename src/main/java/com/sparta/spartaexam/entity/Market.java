package com.sparta.spartaexam.entity;

import com.sparta.spartaexam.dto.MarketRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Market {
    private Long id;
    private String title;
    private String content;
    private String price;
    private String username;

    public Market(MarketRequestDto marketDto) {

        this.title = marketDto.getTitle();
        this.content = marketDto.getContent();
        this.price = marketDto.getPrice();
        this.username = marketDto.getUsername();
    }
}