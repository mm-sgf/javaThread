package com.cfox.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <br/>************************************************
 * <br/>MSG : 读锁和写锁
 * <br/>************************************************
 */
public class ThreadCacheLock {
    public static void main(String[] args) {
        CacheUtils utils = new CacheUtils();
        ExecutorService service = Executors.newFixedThreadPool(4);
        service.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    System.out.println("is reading cache ....");
                    System.out.println("cache value :" + utils.readValue());
                    System.out.println("is readed cache");
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    System.out.println("is reading cache ....");
                    System.out.println("cache value :" + utils.readValue());
                    System.out.println("is readed cache .");
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    System.out.println("is reading cache ....");
                    System.out.println("cache value :" + utils.readValue());
                    System.out.println("is readed cache ....");
                }
            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    System.out.println("is write cache ....");
                    utils.setValue("cache :" + i );
                }
            }
        });
    }
}

class CacheUtils{
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private String value = null;
    private boolean isCache = false;

    public String readValue(){
        lock.readLock().lock();
        if(!isCache){
            lock.readLock().unlock();
            lock.writeLock().lock();
            if(!isCache){
                value = "123456";
                isCache = true;
            }

            /**
             * 下面这两种写法是更新锁，会对写锁降级
             */
            lock.readLock().lock();
            lock.writeLock().unlock();
        }
        lock.readLock().unlock();
        return value;
    }

    public void setValue(String value){
        lock.writeLock().lock();
        try {
            this.value = value;
        } finally {
            lock.writeLock().unlock();
        }
    }
}
