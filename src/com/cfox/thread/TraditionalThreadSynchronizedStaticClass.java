package com.cfox.thread;

/**
 * <br/>************************************************
 * <br/>MSG : 传统的线程互斥（静态类中实现,使用类的字简码实现互斥）
 * <br/>************************************************
 */
public class TraditionalThreadSynchronizedStaticClass {
    public static void main(String[] args) {
        new TraditionalThreadSynchronizedStaticClass().init();
    }

    public void init() {
        threadFactory(new OutPuter());
    }

    /**
     * synchronized (this) this 要指向同一对象才可以对该对象进行互斥处理
     */
    static class OutPuter{
        public void outputs(String str){
            /**
             * synchronized(this) 在静态累中不可以使用 this 实现互斥，要使用 className.class（类的字简码） 实现互斥
             */
            synchronized (OutPuter.class){
                char[] charStr = str.toCharArray();
                for(Character c : charStr){
                    System.out.print(c);
                }
                System.out.println();
                System.out.println();
            }
        }

        /**
         * synchronized 在方法上，也是指向该方法的对象
         * @param str
         */
        public static synchronized void outputs1(String str){
            char[] charStr = str.toCharArray();
            for(Character c : charStr){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    /**
     * 创建线程
     * @param outPuter
     */
    public void threadFactory(OutPuter outPuter){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(400);
                        outPuter.outputs("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(400);
                        outPuter.outputs1("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }
}
