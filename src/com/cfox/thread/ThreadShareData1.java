package com.cfox.thread;

/**
 * <br/>************************************************
 * <br/>MSG : 多线程同时操作同一数据
 * <br/>************************************************
 */
public class ThreadShareData1 {
    public static void main(String[] args) {
        Sum sum = new Sum();
        new Thread(new MyRunnableDec(sum)).start();
        new Thread(new MyRunnableInc(sum)).start();
    }
}

class MyRunnableDec implements Runnable {
    private final Sum sum;
    public MyRunnableDec(Sum sum){
        this.sum = sum;
    }

    @Override
    public void run() {
        this.sum.dec();
    }
}


class MyRunnableInc implements Runnable {
    private final Sum sum;
    public MyRunnableInc(Sum sum){
        this.sum = sum;
    }

    @Override
    public void run() {
        this.sum.inc();
    }
}
class Sum{
    private int  j = 0;
    public synchronized void dec(){
        j--;
        System.out.println(j);
    }

    public synchronized void inc(){
        j++;
        System.out.println(j);
    }
}