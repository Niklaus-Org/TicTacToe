package com.lld.factory;

import com.lld.models.BotDifficultyLevel;
import com.lld.strategies.botplayingstrategies.BotPlayingStrategy;
import com.lld.strategies.botplayingstrategies.EasyBotPlayingStrategy;
import com.lld.strategies.botplayingstrategies.HardBotPlayingStrategy;
import com.lld.strategies.botplayingstrategies.MediumBotPlayingStrategy;

public class BotFactory {

    public static BotPlayingStrategy getFactory(BotDifficultyLevel botDifficultyLevel) {
        if(BotDifficultyLevel.EASY.equals(botDifficultyLevel)) {
            System.out.println("Easy level Bot created");
            return new EasyBotPlayingStrategy();
        } else if(BotDifficultyLevel.MEDIUM.equals(botDifficultyLevel)) {
            System.out.println("Medium level Bot created");
            return new MediumBotPlayingStrategy();
        } else {
            System.out.println("Hard level Bot created");
            return new HardBotPlayingStrategy();
        }
    }
}
