package com.cfox.thread.u;

public class Mvs implements Runnable{
    private int box = -1;
    private int t = 10;
    @Override
    public void run() {
        while (t > 0){
//            t = t - 1;
            System.out.println("hteadname:" + Thread.currentThread().getName()  +  " ;T = " + t-- );
        }
    }
}