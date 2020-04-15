package com.https;

import com.alibaba.fastjson.JSONObject;

public class HttpHandler {

    public static void main(String[] args) throws Exception {
        long b = System.currentTimeMillis();
        for (int i = 0; i< 100; i++){
            new FilaShopeeeManagerImpl().shopeeIntgeration("1111");
        }
        long e = System.currentTimeMillis();
        System.out.println(e -b);

    }
}
