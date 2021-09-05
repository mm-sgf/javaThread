package com.cfox.thread;

/**
 * <br/>************************************************
 * <br/>MSG : 传统线程创建
 * <br/>************************************************
 */
public class TraditionalThread {

    public static void main(String [] arg){

        runThreadMethodOne();
        runThreadMethodTwo();
        runThreadMethodThree();
    }

    private static void runThreadMethodOne() {

        new Thread(){
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this.getName());
            }
        }.start();
    }
    private static void runThreadMethodTwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }

    private static void runThreadMethodThree() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("is run Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("is run out Run");
            }
        }.start();
    }
}
