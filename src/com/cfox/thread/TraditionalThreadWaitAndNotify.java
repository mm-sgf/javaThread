package com.cfox.thread;

/**
 * <br/>************************************************
 * <br/>MSG : 子线程循环10次，主线程循环100 次，子线程再循环10次，主线程循再环100 次，如此循环50 次
 * <br/>************************************************
 */
public class TraditionalThreadWaitAndNotify {

    public static void main(String[] args) {
        RunFactory runFactory = new RunFactory();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0 ; i < 50; i ++){
                    runFactory.sub(i);
                }
            }
        }).start();
        for(int i = 0 ; i < 50; i ++){
            runFactory.main(i);
        }
    }
}

class RunFactory{
    private boolean isRunSub = true;//是否是子线程在执行

    public   void sub(int n){
        System.out.println("main sub...");
        /**
         *  使用while 防止假唤醒
         */
        while(!isRunSub){
            try {
                this.wait();//线程等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0 ; i < 10; i ++){
            System.out.println("sub is run out-->" + n + "; in ---->" + i);
        }

        isRunSub = false;
        this.notify();//唤醒一个线程等待
    }

    public synchronized void main(int n){
        System.out.println("main start...");
        /**
         *  使用while 防止假唤醒
         */
        while(isRunSub){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(int i = 0 ; i < 100; i ++){
            System.out.println("main is run out-->" + n + "; in ---->" + i);
        }
        isRunSub = true;
        this.notify();//唤醒一个线程等待
    }
}

