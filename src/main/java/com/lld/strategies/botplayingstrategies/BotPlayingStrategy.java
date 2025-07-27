package com.lld.strategies.botplayingstrategies;

import com.lld.models.Board;
import com.lld.models.Move;

public interface BotPlayingStrategy {

    public Move makeMove(Board board);
}