package com.cfox.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <br/>************************************************
 * <br/>MSG : java 5 线程池， 创建固定线程的线程池(newFixedThreadPool)，创建一个缓存线程池（newCachedThreadPool），创建一个带有计时器功能的线程池
 * <br/>************************************************
 */
public class ThreadPool1 {

    public static void main(String[] args) {
        /**
         * 创建一个有三个固定线程的线程池
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        /**
         * 创建一个缓存线程池
         */
//        ExecutorService executorService = Executors.newCachedThreadPool();

        /**
         * 创建只有一个线程的线程池，在该线程池中如果线程意外停止，该线程池会启动一个新的线程继续执行任务
         */
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 1 ; i <= 10; i ++){
            final int task = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j = 1 ; j <=10; j ++){
                        System.out.println("ThreadName:" + Thread.currentThread().getName() + " is run num :" + j + " is task num " + task);
                    }
                }
            });
        }

        /**
         * 线程池中的线程执行完毕后，结束线程池中线程
         */
//        executorService.shutdown();

        /**
         * 马上结束线程池中所有线程，包括正在运行的线程
         */
//        executorService.shutdownNow();

        /**
         *
         */
        Executors.newScheduledThreadPool(3).
                schedule(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("newScheduledThreadPool run" );
                        }},
                10,
                TimeUnit.SECONDS);


    }
}
