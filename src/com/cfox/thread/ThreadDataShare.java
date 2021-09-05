package com.cfox.thread;

import java.util.Random;

/**
 * <br/>************************************************
 * <br/>MSG : 线程内数据共享，两个线程操作同一数据，会出现线程安全问题
 * <br/>************************************************
 */
public class ThreadDataShare {
    private int data = 0;
    private ThreadLocal<Integer> localData = new ThreadLocal<Integer>();
    public static void main(String[] args) {
        ThreadDataShare share = new ThreadDataShare();
        share.initThread();
    }

    private void initThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                localData.set(data);
                System.out.println("ThreadName:" + Thread.currentThread() + ";Data:" + data);
                OutPuter puter = new OutPuter();
                puter.outPutA();
                puter.outPutB();
                String str;

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                localData.set(data);
                System.out.println("ThreadName:" + Thread.currentThread() + ";Data:" + data);
                OutPuter puter = new OutPuter();
                puter.outPutA();
                puter.outPutB();
            }
        }).start();
    }

    class OutPuter{
        public void outPutA(){
            int data = localData.get();
            System.out.println("put--A ---ThreadName:" + Thread.currentThread() + ";Data:" + data);
        }

        public void outPutB(){
            int data = localData.get();
            System.out.println("put--B ---ThreadName:" + Thread.currentThread() + ";Data:" + data);
        }
    }
}
