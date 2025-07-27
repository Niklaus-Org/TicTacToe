package com.lld.controllers;

import com.lld.exceptions.InvalidBotCountException;
import com.lld.exceptions.InvalidPlayerCountException;
import com.lld.exceptions.PlayersNotUniqueException;
import com.lld.models.Game;
import com.lld.models.GameState;
import com.lld.models.Player;
import com.lld.strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {
    // responsible for maintaining and playing with the game....

    public Game startGame(int dimension, List<Player> players, int nextPlayerMoveIndex, List<WinningStrategy> winningStrategies) throws InvalidBotCountException, InvalidPlayerCountException, PlayersNotUniqueException {
        Game game = Game.getBuilder()
                .setDimension(dimension)
                .setPlayers(players)
                .setNextPlayerMoveIndex(nextPlayerMoveIndex)
                .setWinningStrategy(winningStrategies)
                .build();
        return game;
    }

    public Player getWinner() {
        return null;
    }

    public void getGameState() {

    }

    public void makeMove() {

    }

    public void undo() {

    }
}
