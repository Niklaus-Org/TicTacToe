package com.lld.models;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    public Bot(String name, int id, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel1) {
        super(name, id, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel1;
    }

    @Override
    public String toString() {
        return "Bot{" +
                "botDifficultyLevel=" + botDifficultyLevel +
                '}';
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
