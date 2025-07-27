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
        int col = move.getCell().getRow();

        Symbol symbol = move.getPlayer().getSymbol();

        if(row==col) { //left diagonal
            if (!lDiagonal.containsKey(symbol)) {
                lDiagonal.put(symbol, 0);
            } else {
                lDiagonal.put(symbol, lDiagonal.get(symbol) + 1);
            }

            if (lDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }

        }

        if (row + col == board.getDimension() - 1) {
            if (!rDiagonal.containsKey(symbol)) {
                rDiagonal.put(symbol, 0);
            } else {
                rDiagonal.put(symbol, lDiagonal.get(symbol) + 1);
            }

            if (rDiagonal.get(symbol) == board.getDimension()) {
                return true;
            }

        }

        return false;
    }
}
