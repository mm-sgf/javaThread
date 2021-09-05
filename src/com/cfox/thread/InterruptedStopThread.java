package com.cfox.thread;

public class InterruptedStopThread {
    public static void main(String[] args) {
        InterruptedStopThread thread = new InterruptedStopThread();
        thread.runTread();
    }

    public void runTread(){
       Thread thread =  new Thread() {

           @Override
           public void run() {
               while (true) {
                   try {
                       Thread.sleep(2000);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println("isInterrupted:" + this.isInterrupted());
                   System.out.println("interrupted:" + Thread.interrupted());
               }
           }
       };

       thread.start();
        try {
            Thread.sleep(2000);
            System.out.println("------------------:");
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
