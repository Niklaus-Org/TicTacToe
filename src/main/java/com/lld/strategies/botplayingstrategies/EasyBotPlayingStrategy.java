package com.lld.strategies.botplayingstrategies;

import com.lld.models.Board;
import com.lld.models.Cell;
import com.lld.models.CellState;
import com.lld.models.Move;

import java.util.List;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{
    @Override
    public Move makeMove(Board board) {
        for(List<Cell> row: board.getBoard()) {
            for(Cell cell: row) {
                if(cell.getCellState().equals(CellState.EMPTY)) {
                    return new Move(null, cell);
                }
            }
        }
        return null;
    }
}
