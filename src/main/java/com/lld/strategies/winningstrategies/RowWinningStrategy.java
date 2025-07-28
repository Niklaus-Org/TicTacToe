package com.lld.strategies.winningstrategies;

import com.lld.models.Board;
import com.lld.models.Cell;
import com.lld.models.Move;
import com.lld.models.Symbol;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    // map of row -> map(symbols count)
    //ex: for 0 row -> map(X-1, O-1);
    private Map<Integer, Map<Symbol, Integer>> rowCounts = new HashMap<>();

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Symbol symbol = move.getPlayer().getSymbol();

        //checks if that row exists in the hashmap
        rowCounts.putIfAbsent(row, new HashMap<>());

        // get that row symbols count
        Map<Symbol, Integer> symbolCount = rowCounts.get(row);

        //set that row symbol count as we got the row symbols map
        symbolCount.put(symbol, symbolCount.getOrDefault(symbol, 0) + 1);

        if(symbolCount.get(symbol) == board.getDimension()) {
            System.out.println("Player has won by row strategy by crossing: "+ row+ " row");
        }

        return symbolCount.get(symbol) == board.getDimension();
    }
}

