package com.sparta.spartaexam.service;

import com.sparta.spartaexam.dto.MarketRequestDto;
import com.sparta.spartaexam.dto.MarketResponseDto;
import com.sparta.spartaexam.entity.Market;
import com.sparta.spartaexam.repository.MarketRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class MarketService {

    private final MarketRepository marketRepository;

    public MarketService(JdbcTemplate jdbcTemplate) { //JDBC 템플릿 final이기때문에 초기화

        this.marketRepository = new MarketRepository(jdbcTemplate);
    }

    public List<MarketResponseDto> getmarkets() {

        // DB 조회
        return marketRepository.findAll();

    }

    // 글 작성
    public MarketResponseDto createMarket(MarketRequestDto requestDto) {

        Market memo = new Market(requestDto);

        // DB 저장
        Market saveMemo = marketRepository.save(memo);

        // Entity -> ResponseDto
        MarketResponseDto marketResponseDto = new MarketResponseDto(memo);

        return marketResponseDto;

    }

    public Long updateMarket(Long id, MarketRequestDto requestDto) {

        Market market = marketRepository.findById(id);
        if(market != null) {
            // memo 내용 수정
            marketRepository.update(id, requestDto);


            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }


    }

    public Long deleteMarket(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Market market = marketRepository.findById(id);
        if(market != null) {
            // memo 삭제
            marketRepository.delete(id);


            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
