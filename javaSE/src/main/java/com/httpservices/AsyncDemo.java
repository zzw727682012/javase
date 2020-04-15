package com.httpservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

public class AsyncDemo {
    private static final Logger log = LoggerFactory.getLogger(AsyncDemo.class);

    public static void main(String[] args) {
        async1();
    }

    public static void sync() {
        long start = System.currentTimeMillis();
        Integer integer = new RpcService().getRpcResult();
        Integer integer1 = new HttpService().getHttpResult();
        long end = System.currentTimeMillis();
        System.out.println("rpc:" + integer + "\n" + "http:" + integer1 + "\n" + "总共耗时:" + (end - start));
    }

    public static void async() {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            long start = System.currentTimeMillis();
            Future<Integer> future = executorService.submit(() -> new RpcService().getRpcResult());
            Future<Integer> future1 = executorService.submit(() -> new HttpService().getHttpResult());
            Integer integer = future.get();
            Integer integer1 = future1.get();
            long end = System.currentTimeMillis();

            System.out.println("rpc:" + integer + "\n" + "http:" + integer1 + "\n" + "总共耗时:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void async1() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        try {
            long start = System.currentTimeMillis();
            AsyncService asyncService = new AsyncService();
            Future<Integer> future = executorService.submit(() -> asyncService.getAsyncResult((o, e) -> {
                if (e != null) {
                    log.error("get result error", e.getMessage());
                } else if ((Integer) o == 6324) {
                    System.out.println("背诵抽象圣经");
                }
            }));


            long end = System.currentTimeMillis();

            System.out.println("总共耗时:" + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class RpcService {


        Integer getRpcResult() {
            int value = 6324;
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return value;
        }
    }

    static class HttpService {
        Integer getHttpResult() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 4396;
        }
    }

    static class AsyncService {
        Integer getAsyncResult(CallBack callBack) {
            int value = 6324;
            try {
                Thread.sleep(2000);
                callBack.onComplete(value, null);
            } catch (Exception e) {
                e.printStackTrace();
                callBack.onComplete(null, e);
            }
            return value;
        }

    }

    interface CallBack<V> {
        void onComplete(V v, Exception e);
    }

}
