package com.anthony;

import com.anthony.net.HttpRequest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chendong239 on 2017-03-24.
 */
public class Main {
    public static void main(String[] args) {
        HttpRequest httpRequest=new HttpRequest();
        String res[]=httpRequest.get().split("\\n");
        parse(res[1]);
    }

    static void parse(String line)
    {
        String tmp[]=line.split("\"");
        String code=filter("[^0-9]",tmp[0]);
        String[] datas=tmp[1].split(",");
        System.out.println(Arrays.asList(datas));
    }

    static String filter(String regex,String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.replaceAll("").trim();
    }
}
