package com.cfox.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <br/>************************************************
 * <br/>MSG : 子线程1循环10次，子线程2循环20次，主线程循环100 次，子线程再循环10次，主线程循再环100 次，如此循环50 次,
 * <br/> 使用 Condition 实现多线程间互斥
 * <br/>************************************************
 */
public class ThreadCondition {
    public static void main(String[] args) {
        RunFactory runFactory = new RunFactory();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0 ; i < 50; i ++){
                    runFactory.sub1(i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0 ; i < 50; i ++){
                    runFactory.sub2(i);
                }
            }
        }).start();
        for(int i = 0 ; i < 50; i ++){
            runFactory.main(i);
        }
    }

    static class RunFactory{
        private int isRunSub = 1;//是否是子线程在执行
        /**
         * 多线程间通信使用 Condition ，使用Condition 可以通知指定线程等待启动
         */
        Lock lock = new ReentrantLock();
        Condition conditionMain = lock.newCondition();
        Condition conditionSub1 = lock.newCondition();
        Condition conditionSub2 = lock.newCondition();

        public void sub1(int n){
            lock.lock();
            try {
                /**
                 *  使用while 防止假唤醒
                 */
                while(isRunSub != 1){
                    try {
                        //等待
                        conditionSub1.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int i = 0 ; i < 10; i ++){
                    System.out.println("sub1 is run out-->" + n + "; in ---->" + i);
                }
                isRunSub = 2;
                conditionSub2.signal();//启动 conditionSub2 的等待
            } finally {
                lock.unlock();
            }
        }

        public void sub2(int n){
            lock.lock();
            try {
                /**
                 *  使用while 防止假唤醒
                 */
                while(isRunSub != 2){
                    try {
                        conditionSub2.await();//线程等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for(int i = 0 ; i < 20; i ++){
                    System.out.println("sub2 is run out-->" + n + "; in ---->" + i);
                }
                isRunSub = 3;
                conditionMain.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int n){
            lock.lock();
            try {
                /**
                 *  使用while 防止假唤醒
                 */
                while(isRunSub != 3){
                    try {
                        conditionMain.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for(int i = 0 ; i < 100; i ++){
                    System.out.println("main is run out-->" + n + "; in ---->" + i);
                }
                isRunSub = 1;
                conditionSub1.signal();//唤醒一个线程等待
            } finally {
                lock.unlock();
            }
        }
    }
}

