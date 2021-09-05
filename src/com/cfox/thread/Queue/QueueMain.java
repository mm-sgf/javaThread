package com.cfox.thread.Queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * <br/>************************************************
 * <br/>MSG :LinkedBlockingDeque 是安全的阻塞的队列
 * <br/>************************************************
 */
public class QueueMain {
    public static void main(String[] args) {
        My my = new My();
        my.addQueue();
        my.startQueue();
    }

    private static class My{
        int num = 0;
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>();
        private void addQueue(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("--------add-------" + num + "------");
                        try {
                            queue.put("hello" + num);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        num ++;
                    }
                }
            }).start();
        }

        private void startQueue(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        System.out.println("run is this");
                        String qu = null;
                        try {
                            qu = queue.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("poll data" + qu);
                    }
                }
            }).start();
        }
    }
}
