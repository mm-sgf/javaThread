package com.cfox.thread;

public class TraditionaT {

    public static void main(String[] args) {
        TraditionaT t = new TraditionaT();
        t.runThread();
    }

    private static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("is run Runnable");
        }
    }

    public void runThread() {
        new Thread(new MyRunnable()){
            @Override
            public void run() {
                System.out.println("is run thread run");
            }
        }.start();
    }
}
