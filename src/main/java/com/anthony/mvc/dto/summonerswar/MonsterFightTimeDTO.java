package com.anthony.mvc.dto.summonerswar;

/**
 * Created by chend on 2017/4/16.
 */
public class MonsterFightTimeDTO {
    private int star;
    private int curExp;
    private int curLevel;
    private int fightExp;

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCurExp() {
        return curExp;
    }

    public void setCurExp(int curExp) {
        this.curExp = curExp;
    }

    public int getCurLevel() {
        return curLevel;
    }

    public void setCurLevel(int curLevel) {
        this.curLevel = curLevel;
    }

    public int getFightExp() {
        return fightExp;
    }

    public void setFightExp(int fightExp) {
        this.fightExp = fightExp;
    }

    @Override
    public String toString() {
        return "MonsterFightTimeDTO{" +
                "star=" + star +
                ", curExp=" + curExp +
                ", curLevel=" + curLevel +
                ", fightExp=" + fightExp +
                '}';
    }
}
