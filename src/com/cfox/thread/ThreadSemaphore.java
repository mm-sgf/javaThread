package com.cfox.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <br/>************************************************
 * <br/>MSG : Semaphore可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 Semaphore实现的功能就类似厕所有5个坑，假如有十个人要上厕所，那么同时能有多少个人去上厕所呢？同时只能有5个人能够占用，当5个人中的任何一个人让开后，其中在等待的另外5个人中又有一个可以占用了。
 另外等待的5个人中可以是随机获得优先机会，也可以是按照先来后到的顺序获得机会，这取决于构造Semaphore对象时传入的参数选项。
 单个信号量的Semaphore对象可以实现互斥锁的功能，并且可以是由一个线程获得了“锁”，再由另一个线程释放“锁”，这可应用于死锁恢复的一些场合。
 * <br/>************************************************
 */
public class ThreadSemaphore {
    public static void main(String[] args) {
        Semaphore sp = new Semaphore(3);
        ExecutorService service = Executors.newCachedThreadPool();

        for(int i = 0 ; i < 10; i ++){
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        sp.acquire();//获取信号
                        System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3-sp.availablePermits()) + "个并发");
                        Thread.sleep((long)(Math.random()*10000));
                        System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                        sp.release();//释放信号
                        //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                        System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (3-sp.availablePermits()) + "个并发");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
