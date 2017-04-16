package com.anthony.mvc.util.summonerswar;

/**
 * Created by chend on 2017/4/16.
 */
public class MaxLevelException extends Exception {
    public MaxLevelException() {
        super("current level is out of range!");
    }
}
