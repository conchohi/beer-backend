package com.zipbeer.beerbackend.dto.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameState {
    private List<String> players;
    private String currentTurn;
    private String previousTurn; // 이전 출제자 추적
    private String topic;
    private Map<String, Integer> scores = new HashMap<>();
    private boolean isGameOver;
    private String winner;
    private String message;
    private String liar;
    private Map<String, Integer> votes = new HashMap<>();
    private int timeLeft; // 타이머 필드 추가

    public GameState(List<String> players) {
        this.players = players;
        for (String player : players) {
            scores.put(player, 0);
            votes.put(player, 0);
        }
        this.timeLeft = 180; // 초기 타이머 설정
    }

    public void updateScore(String player) {
        scores.put(player, scores.get(player) + 1);
    }

    public void addVote(String player) {
        votes.put(player, votes.getOrDefault(player, 0) + 1);
    }

    public String getMostVoted() {
        return votes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }

    public void reset() {
        currentTurn = players.get(0);
        previousTurn = null;
        topic = "";
        scores.clear();
        isGameOver = false;
        winner = "";
        liar = "";
        message = "";
        votes.clear();
        timeLeft = 180; // 타이머 초기화
        for (String player : players) {
            scores.put(player, 0);
            votes.put(player, 0);
        }
    }

    public void resetScores() {
        scores.clear();
        for (String player : players) {
            scores.put(player, 0);
        }
    }

    public void endGame() {
        isGameOver = true;
    }
}
