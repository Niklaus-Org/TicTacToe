package com.lld.models;

import com.lld.factory.BotFactory;
import com.lld.strategies.botplayingstrategies.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;


    public Bot(String name, int id, Symbol symbol, PlayerType playerType, BotDifficultyLevel botDifficultyLevel1) {
        super(name, id, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel1;
        botPlayingStrategy = BotFactory.getFactory(botDifficultyLevel1);
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

    public Move makeMove(Board board) {
        Move move = botPlayingStrategy.makeMove(board);
        //beacuse in the strategy, this field (player) will be null
        move.setPlayer(this);
        return move;
    }


}
