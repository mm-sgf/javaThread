package com.cfox.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <br/>************************************************
 * <br/>MSG : 倒计时器
 * <br/>************************************************
 */
public class ThreadCountdownLatch {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch cdOne = new CountDownLatch(1);
        CountDownLatch cdTwo = new CountDownLatch(3);
        for(int i = 0 ; i < 3 ; i ++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + " 出发了");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + " 到达等待处");
                    try {
                        cdOne.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("ThreadName:" + Thread.currentThread().getName() + " 继续前进");
                    cdTwo.countDown();
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + " 减掉一个门栓");
                }
            });
        }

        service.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadName:" + Thread.currentThread().getName() + " 出发了，准备开门");
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                cdOne.countDown();
                System.out.println("ThreadName:" + Thread.currentThread().getName() + " 到达等待处");
                try {
                    cdTwo.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadName:" + Thread.currentThread().getName() + " 继续前进");
            }
        });
    }
}
