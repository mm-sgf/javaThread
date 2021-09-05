package com.cfox.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <br/>************************************************
 * <br/>MSG :CyclicBarrier
 表示大家彼此等待，大家集合好后才开始出发，分散活动后又在指定地点集合碰面，这就好比整个公司的人员利用周末时间集体郊游一样，先各自从家出发到公司集合后，再同时出发到公园游玩，在指定地点集合后再同时开始就餐，…。
 * <br/>************************************************
 */
public class ThreadCyclicBarrier {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(3);
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 10; i ++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点1，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
                        cb.await();//只有线程到达设定值时，才会向下执行

//                        Thread.sleep((long)(Math.random()*10000));
//                        System.out.println("线程" + Thread.currentThread().getName() +
//                                "即将到达集合地点2，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
//                        cb.await();
//                        Thread.sleep((long)(Math.random()*10000));
//                        System.out.println("线程" + Thread.currentThread().getName() +
//                                "即将到达集合地点3，当前已有" + cb.getNumberWaiting() + "个已经到达，正在等候");
//                        cb.await();

                        System.out.println("线程" + Thread.currentThread().getName() +
                                "即将到达集合地点3，当前已有" + cb.getNumberWaiting() + "个已经到达，出发");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}
