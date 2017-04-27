package com.anthony.mvc.util.summonerswar;

import com.anthony.mvc.dto.summonerswar.MonsterFightTimeDTO;

/**
 * Created by chend on 2017/4/16.
 * 计算魔灵需要战斗次数
 */
public class FightNum {
    public static int getFightNum(int star, int curLevel, int curExp, int fightExp) {
        curExp += LevelUpExp.toCurLevelExp(star, curLevel);
        int leftExp = LevelUpExp.totalExp[star - 1] - curExp;
        return leftExp / fightExp + 1;
    }

    public static int getFightNum(MonsterFightTimeDTO monsterFightTimeDTO) {
        return getFightNum(monsterFightTimeDTO.getStar(), monsterFightTimeDTO.getCurLevel(), monsterFightTimeDTO.getCurExp(), monsterFightTimeDTO.getFightExp());
    }
}