package com.atraparalagato.impl.repository;

import com.atraparalagato.impl.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ScoreRepository {
    @Autowired(required = false)
    private JdbcTemplate jdbcTemplate;

    public void save(Score score) {
        if (jdbcTemplate == null) return; // fallback para tests
        String sql = "INSERT INTO scores (game_id, player_name, score, moves_count, board_size, player_won, game_duration_seconds, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                score.getGameId(),
                score.getPlayerName(),
                score.getScore(),
                score.getMovesCount(),
                score.getBoardSize(),
                score.isPlayerWon(),
                score.getGameDurationSeconds(),
                score.getCreatedAt()
        );
    }

    public List<Score> findTopScores(int limit) {
        if (jdbcTemplate == null) return List.of();
        String sql = "SELECT * FROM scores ORDER BY score DESC, created_at ASC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Score.class), limit);
    }

    public List<Score> findWinningScores() {
        if (jdbcTemplate == null) return List.of();
        String sql = "SELECT * FROM scores WHERE player_won = true ORDER BY score DESC, created_at ASC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Score.class));
    }

    public List<Score> findRecentScores(int limit) {
        if (jdbcTemplate == null) return List.of();
        String sql = "SELECT * FROM scores ORDER BY created_at DESC LIMIT ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Score.class), limit);
    }
}
