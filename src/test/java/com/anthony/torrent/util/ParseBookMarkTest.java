package com.anthony.torrent.util;

import com.anthony.torrent.util.parse.ParseBookMark;
import com.anthony.torrent.util.parse.ParseFileUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Anthony on 2017/5/19.
 */
public class ParseBookMarkTest {
    private InputStream in;

    @Before
    public void setUp() throws Exception {
        File file= new File("C:\\Users\\Anthony\\Desktop\\Bookmark.html");

        in = new FileInputStream(file);

    }

    @After
    public void tearDown() throws Exception {
        in.close();
    }

    @Test
    public void streamToList() throws Exception {
        ParseFileUtil p=new ParseBookMark("wuhaha");
        List a=p.streamToList(in);
        System.out.println(a.size());
//    String t="<DT><H3 FOLDED ADD_DATE=\"-372450185370\">vps</H3>";
//
//        System.out.println(t.indexOf("<DT>"));
//        System.out.println(t.lastIndexOf("</H3>"));
//        System.out.println(t.length()-"</H3>".length());

    }

}