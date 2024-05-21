package com.sparta.spartaexam.repository;

import com.sparta.spartaexam.dto.MarketRequestDto;
import com.sparta.spartaexam.dto.MarketResponseDto;
import com.sparta.spartaexam.entity.Market;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MarketRepository {


    private JdbcTemplate jdbcTemplate;

    public MarketRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MarketResponseDto> findAll() {
        //DB 조회
        String sql = "SELECT * FROM market";

        return jdbcTemplate.query(sql, new RowMapper<MarketResponseDto>() {
            @Override
            public MarketResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String price = rs.getString("price");
                String username = rs.getString("username");
                return new MarketResponseDto(id, title, content, price, username);
            }
        });
    }


    public Market save(Market market) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO market (title, content, price, username) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, market.getTitle()); // 첫번째 ?에 넣음
                    preparedStatement.setString(2, market.getContent()); // 두번째 ?에 넣음
                    preparedStatement.setString(3, market.getPrice());
                    preparedStatement.setString(4, market.getUsername());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        market.setId(id);

        return market;


    }




    public void update(Long id, MarketRequestDto requestDto) {

        String sql = "UPDATE market SET title = ?, content =?, price = ?, username = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContent(), requestDto.getPrice(), requestDto.getUsername() ,id);

    }



    public Market findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM market WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if(resultSet.next()) {
                Market market = new Market();

                market.setTitle(resultSet.getString("title"));
                market.setContent(resultSet.getString("content"));
                market.setPrice(resultSet.getString("price"));
                market.setUsername(resultSet.getString("username"));
                return market;
            } else {
                return null;
            }
        }, id);
    }


    public void delete(Long id) {
        String sql = "DELETE FROM market WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}