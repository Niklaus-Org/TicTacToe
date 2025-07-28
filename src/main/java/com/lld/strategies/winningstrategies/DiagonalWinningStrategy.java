package com.lld.strategies.winningstrategies;

import com.lld.models.Board;
import com.lld.models.Move;
import com.lld.models.Symbol;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{

    private HashMap<Symbol, Integer> lDiagonal = new HashMap<>();
    private HashMap<Symbol, Integer> rDiagonal = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Symbol symbol = move.getPlayer().getSymbol();

        if(row==col) { //left diagonal
            if (!lDiagonal.containsKey(symbol)) {
                lDiagonal.put(symbol, 1);
            } else {
                lDiagonal.put(symbol, lDiagonal.get(symbol) + 1);
            }

            if (lDiagonal.get(symbol) == board.getDimension()) {
                System.out.println(lDiagonal.toString());
                System.out.println("Player has won by left diagonal strategy by crossing");
                return true;
            }

        }

         if (row + col == board.getDimension() - 1) {
            if (!rDiagonal.containsKey(symbol)) {
                rDiagonal.put(symbol, 1);
            } else {
                rDiagonal.put(symbol, rDiagonal.get(symbol) + 1);
            }

            if (rDiagonal.get(symbol) == board.getDimension()) {
                System.out.println(rDiagonal.toString());
                System.out.println("Player has won by right diagonal strategy");
                return true;
            }

        }

        return false;
    }

    @Override
    public void handleundo(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        if(row==col) {
            lDiagonal.put(symbol, lDiagonal.get(symbol)-1);
        }
        if(row+col==board.getDimension()) {
            rDiagonal.put(symbol, rDiagonal.get(symbol)-1);
        }
    }
}
