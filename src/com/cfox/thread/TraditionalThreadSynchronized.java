package com.cfox.thread;

/**
 * <br/>************************************************
 * <br/>MSG : 传统的线程互斥
 * <br/>************************************************
 */
public class TraditionalThreadSynchronized {
    public static void main(String[] args) {
        new TraditionalThreadSynchronized().init();
    }

    public void init() {
        threadFactory(new OutPuter());
    }

    class OutPuter {
        public void output(String str) {
            char[] charStr = str.toCharArray();
            for (Character c : charStr) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    /**
     * 创建线程
     *
     * @param outPuter
     */
    public void threadFactory(OutPuter outPuter) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        outPuter.output("aaaaaaaaaaaaaaaa");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        outPuter.output("bbbbbbbbbbbbbbb");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
