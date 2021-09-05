package com.cfox.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <br/>************************************************
 * <br/>MSG : Atomic 对数据进行原子操作（线程安全的）
 * <br/>************************************************
 */
public class ThreadAtomic {
    private static final AtomicInteger age = new AtomicInteger();
    public static void main(String[] args) {


        for(int  i = 0 ; i < 100 ; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ThreadName：" + Thread.currentThread().getName() + "aget:" + age.getAndIncrement());
                }
            }).start();
        }

        for(int  i = 0 ; i < 100 ; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ThreadName：" + Thread.currentThread().getName() + "aget:" + age.getAndDecrement());
                }
            }).start();
        }

        try {
            Thread.sleep(5000);
            System.out.println("ThreadName：" + Thread.currentThread().getName() + "aget:" + age.getAndDecrement());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
