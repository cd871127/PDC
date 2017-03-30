package com.anthony;

import com.anthony.net.HttpRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chendong239 on 2017-03-24.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("C:\\Users\\chend\\Desktop\\2.sql",false);

        String template="insert into test_index(noindex_int,index_int,index_text,noindex_text) values";

        fw.write(template);

        String values="(%d,%d,\'%s\',\'%s\')";

        final int max=100000;

        for(int i=max+1;i<=max+max;++i) {
            String tmp = String.format(values, i, i, i, i);
            if(max+max>i)
                tmp+=",";
            else
                tmp+=";";
            fw.write(tmp);
        }
        fw.close();
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
