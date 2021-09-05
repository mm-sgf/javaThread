package com.cfox.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/>************************************************
 * <br/>MSG : 线程中lock 和 synchronized 互斥操作
 * <br/>************************************************
 */
public class ThreadLock {
    public static void main(String[] args) {
        OutPut outPut = new OutPut();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    outPut.outPuter("aaaaaaaaaaaaaaaaaaaaaaaaa");
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 100; i ++){
                    outPut.outPuter("bbbbbbbbbbbbbbbbbbbb");
                }
            }
        }).start();
    }
}
class OutPut{
    Lock lock = new ReentrantLock();
    public /*synchronized*/ void outPuter(String value){
//        Lock lock = new ReentrantLock(); //不能把 lock 放在这里，每次运行都会产生一个新的 lock 对象，所以无法达到 lock 的要求
        lock.lock();
        try {
            for(int i = 0 ; i < value.length(); i ++){
                System.out.print(value.charAt(i));
            }
            System.out.println();
        }finally {
            lock.unlock();
        }
    }
}