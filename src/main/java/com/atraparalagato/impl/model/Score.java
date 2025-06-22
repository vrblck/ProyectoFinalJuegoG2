package com.atraparalagato.impl.model;

import java.time.LocalDateTime;

public class Score {
    private String gameId;
    private String playerName;
    private int score;
    private int movesCount;
    private int boardSize;
    private boolean playerWon;
    private int gameDurationSeconds;
    private LocalDateTime createdAt;

    public Score(String gameId, String playerName, int score, int movesCount, int boardSize, boolean playerWon, int gameDurationSeconds, LocalDateTime createdAt) {
        this.gameId = gameId;
        this.playerName = playerName;
        this.score = score;
        this.movesCount = movesCount;
        this.boardSize = boardSize;
        this.playerWon = playerWon;
        this.gameDurationSeconds = gameDurationSeconds;
        this.createdAt = createdAt;
    }

    public Score() {}

    // Getters y setters
    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }
    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }
    public int getMovesCount() { return movesCount; }
    public void setMovesCount(int movesCount) { this.movesCount = movesCount; }
    public int getBoardSize() { return boardSize; }
    public void setBoardSize(int boardSize) { this.boardSize = boardSize; }
    public boolean isPlayerWon() { return playerWon; }
    public void setPlayerWon(boolean playerWon) { this.playerWon = playerWon; }
    public int getGameDurationSeconds() { return gameDurationSeconds; }
    public void setGameDurationSeconds(int gameDurationSeconds) { this.gameDurationSeconds = gameDurationSeconds; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
