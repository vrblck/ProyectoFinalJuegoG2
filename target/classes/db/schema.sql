CREATE TABLE IF NOT EXISTS scores (
    id IDENTITY PRIMARY KEY,
    game_id VARCHAR(64),
    player_name VARCHAR(64),
    score INT,
    moves_count INT,
    board_size INT,
    player_won BOOLEAN,
    game_duration_seconds INT,
    created_at TIMESTAMP
);
