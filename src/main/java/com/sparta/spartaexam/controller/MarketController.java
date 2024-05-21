package com.sparta.spartaexam.controller;

import com.sparta.spartaexam.dto.MarketRequestDto;
import com.sparta.spartaexam.dto.MarketResponseDto;
import com.sparta.spartaexam.service.MarketService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MarketController {

    private final MarketService marketService;

    public MarketController(JdbcTemplate jdbcTemplate) { //JDBC 템플릿 final이기때문에 초기화
        this.marketService = new MarketService(jdbcTemplate);
    }


    //전체 조회
    @GetMapping("/post")
    public List<MarketResponseDto> getMarkets() {

        return  marketService.getmarkets();


    }

    //글 작성
    @PostMapping("/post")
    public MarketResponseDto createMarket(@RequestBody MarketRequestDto requestDto) {

        return marketService.createMarket(requestDto);
    }

    //수정
    @PutMapping("/post/{id}")
    public Long updateMarket(@PathVariable Long id, @RequestBody MarketRequestDto requestDto) {

        return marketService.updateMarket(id,requestDto);

    }

    //삭제
    @DeleteMapping("/post/{id}")
    public Long deleteMarket(@PathVariable Long id) {

        return  marketService.deleteMarket(id);

    }


}
