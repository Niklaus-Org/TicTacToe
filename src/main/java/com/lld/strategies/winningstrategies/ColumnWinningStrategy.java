package com.lld.strategies.winningstrategies;

import com.lld.models.Board;
import com.lld.models.Move;
import com.lld.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    // map of col -> map(symbols count)
    //ex: for 0 row -> map(X-1, O-1);
    private Map<Integer, Map<Symbol, Integer>> colCounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getColumn();
        Symbol symbol = move.getPlayer().getSymbol();

        colCounts.putIfAbsent(col, new HashMap<>());
        Map<Symbol, Integer> symbolCount = colCounts.get(col);

        symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);

        if(symbolCount.get(symbol) == board.getDimension()) {
            System.out.println("Player has won by column strategy by crossing: "+col+" column");
        }

        return symbolCount.get(symbol) == board.getDimension();
    }
}

