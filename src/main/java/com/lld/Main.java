package com.lld;

import com.lld.controllers.GameController;
import com.lld.exceptions.InvalidBotCountException;
import com.lld.exceptions.InvalidPlayerCountException;
import com.lld.exceptions.PlayersNotUniqueException;
import com.lld.models.*;
import com.lld.strategies.winningstrategies.ColumnWinningStrategy;
import com.lld.strategies.winningstrategies.DiagonalWinningStrategy;
import com.lld.strategies.winningstrategies.RowWinningStrategy;
import com.lld.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

// this is the client class
public class Main {
    public static void main(String[] args) throws InvalidBotCountException, InvalidPlayerCountException, PlayersNotUniqueException {
       int dimensions = 3;
        List<Player> players = new ArrayList<>();

        players.add(new Player("nikki",1, new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("bot",2, new Symbol('Y'), PlayerType.BOT, BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();

        winningStrategies.add(new RowWinningStrategy());
        winningStrategies.add(new ColumnWinningStrategy());
        winningStrategies.add(new DiagonalWinningStrategy());

        int nextPlayerMoveIndex = 0;

        GameController gameController = new GameController();

        Game game = gameController.startGame(dimensions, players, nextPlayerMoveIndex, winningStrategies);

        while(game.getGameState().equals(GameState.IN_PROGRESS)) {
            //state of game
            gameController.printGame(game);
            //make a move
            gameController.makeMove(game);

        }

        gameController.printGame(game);

    }
}
