package com.lld.strategies.winningstrategies;

import com.lld.models.Board;
import com.lld.models.Move;

public interface WinningStrategy {

    boolean checkWinner(Board board, Move move);

    void handleundo(Board board, Move move);
}
