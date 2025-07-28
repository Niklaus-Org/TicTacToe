package com.lld.models;

import com.lld.exceptions.InvalidBotCountException;
import com.lld.exceptions.InvalidPlayerCountException;
import com.lld.exceptions.PlayersNotUniqueException;
import com.lld.strategies.winningstrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Game {

    private List<Player> players;
    private List<Move> moves;
    private Board board;
    private Player winner;
    private GameState gameState;
    private int nextMoveIndex;
    private List<WinningStrategy> winningStrategy;

    private Game(GameBuilder gameBuilder) {
        this.players = gameBuilder.players;
        this.moves = new ArrayList<>();
        this.board = new Board(gameBuilder.dimension);
        this.winner = null;
        this.gameState = GameState.IN_PROGRESS;
        this.nextMoveIndex = gameBuilder.nextPlayerMoveIndex;
        this.winningStrategy = gameBuilder.winningStrategy;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public void makeMove() {
        Player currPlayer = players.get(nextMoveIndex%players.size());

        System.out.println("Current player name is: "+currPlayer.getName());

        Move move = currPlayer.makeMove(this.board);

        System.out.println("Player wants to make a move at: " + move.getCell().toString());

        //Add a player symbol in the board.
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Cell cellToChange = board.getBoard().get(row).get(col);
        cellToChange.setPlayer(currPlayer);
        cellToChange.setCellState(CellState.FILLED);

        Move finalMove = new Move(currPlayer, cellToChange);

        moves.add(finalMove); //maintaining the list of moves

        nextMoveIndex += 1%players.size(); //nex play turn after prev final-Move happen just now

        //check if that move makes the player winner?
        if(checkWinner(finalMove)) {
            winner = currPlayer;
            gameState = GameState.ENDED;
            System.out.println(currPlayer.toString() + " has won the game");

        } else if(moves.size()==board.getDimension()*board.getDimension()) { // no valid moves left
            gameState = GameState.DRAW;
        }

    }

    public boolean checkWinner(Move move) {
        for(WinningStrategy strategy: winningStrategy) {
            if(strategy.checkWinner(board, move)) {
                return true;
            }
        }

        return false;
    }

    public void printBoard() {
        for(List<Cell> row: board.getBoard()) {
            for(Cell cell : row) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    System.out.print("|  |");
                } else {
                    System.out.print("| " + cell.getPlayer().getSymbol().getaChar() + " |");
                }
            }
            System.out.println();
        }
    }


    @Override
    public String toString() {
        return "Game{" +
                "players=" + players +
                ", moves=" + moves +
                ", board=" + board +
                ", winner=" + winner +
                ", gameState=" + gameState +
                ", nextMoveIndex=" + nextMoveIndex +
                ", winningStrategy=" + winningStrategy +
                '}';
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int getNextMoveIndex() {
        return nextMoveIndex;
    }

    public void setNextMoveIndex(int nextMoveIndex) {
        this.nextMoveIndex = nextMoveIndex;
    }

    public List<WinningStrategy> getWinningStrategy() {
        return winningStrategy;
    }

    public void setWinningStrategy(List<WinningStrategy> winningStrategy) {
        this.winningStrategy = winningStrategy;
    }

    public static class GameBuilder {

        private int dimension;
        private List<Player> players;
        private List<WinningStrategy> winningStrategy;
        private int nextPlayerMoveIndex;

        public GameBuilder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public GameBuilder setPlayers(List<Player> players) {
            this.players = players;
            return this;

        }

        public GameBuilder setWinningStrategy(List<WinningStrategy> winningStrategy) {
            this.winningStrategy = winningStrategy;
            return this;

        }

        public GameBuilder setNextPlayerMoveIndex(int nextPlayerMoveIndex) {
            this.nextPlayerMoveIndex = nextPlayerMoveIndex;
            return this;

        }

        private void checkBotCount() throws InvalidBotCountException {
            int botCount = 0;

            for(Player p: players) {
                if(p.getPlayerType().equals(PlayerType.BOT)) {
                    botCount++;
                }
            }
            if(botCount > 1) {
                //throw exception
                throw new InvalidBotCountException("Bot count should not greater than 1");
            }
        }

        public void checkPlayerCount() throws InvalidPlayerCountException {
            int playerCount = 0;

            for(Player p: players) {
                if(p.getPlayerType().equals(PlayerType.HUMAN)) {
                    playerCount++;
                }
            }

            if(playerCount>dimension-1) {
                throw new InvalidPlayerCountException("Number of players invalid");
            }
        }

        public void checkUniquePlayers() throws PlayersNotUniqueException {
            HashSet<Character> uniqueSymbols = new HashSet<>();

            for (Player p : players) {
                if(!uniqueSymbols.add(p.getSymbol().getaChar())) {
                    throw new PlayersNotUniqueException("Players in game are not unique");
                }
            }


        }

        private void validate() throws InvalidBotCountException, InvalidPlayerCountException, PlayersNotUniqueException {
           //bot count
            checkBotCount();
            checkPlayerCount();
            checkUniquePlayers();
        }

        public Game build() throws InvalidBotCountException, InvalidPlayerCountException, PlayersNotUniqueException {
            //validate
            //playerCount
            //unique player symbol
            validate();
            return new Game(this);
        }
    }
}

