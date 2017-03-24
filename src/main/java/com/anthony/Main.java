package com.anthony;

import com.anthony.net.HttpRequest;

/**
 * Created by chendong239 on 2017-03-24.
 */
public class Main {
    public static void main(String[] args) {
        HttpRequest httpRequest=new HttpRequest();
        System.out.println(httpRequest.get());
    }
}
