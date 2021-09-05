package com.cfox.thread;

import java.util.concurrent.*;

/**
 * <br/>************************************************
 * <br/>MSG : 阻塞队列
 * <br/>************************************************
 */
public class ThreadBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(3);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "准备进入队列");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        blockingQueue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "进入队列,队列中的队列任务数" + blockingQueue.size());
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "准备进入队列");
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        blockingQueue.put(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "进入队列,队列中的队列任务数" + blockingQueue.size());
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "准备取队列");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        blockingQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("ThreadName:" + Thread.currentThread().getName() + "成功取出队列，队列中的队列任务数" + blockingQueue.size());
                }
            }
        });
    }
}
