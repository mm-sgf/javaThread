package com.cfox.thread;

import java.util.concurrent.*;

/**
 * <br/>************************************************
 * <br/>MSG : 线程中的 Callable 和 Future
 * <br/>************************************************
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                return 15;
            }
        });

        try {
            /**
             * 使用 future.get() 获取数据，是阻塞式的，要等到 Callable 中返回数据才能继续运行
             */
            System.out.println("integer:" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        ExecutorService service1 = Executors.newFixedThreadPool(10);
        /**
         * CompletionService 可以将多个线程同一管理，然后通过 completionService.take().get() 获取先执行完的线程返回的数据
         * 在completionService.take().get() 方法处依然是阻塞式的。
         */
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service1);
        for(int i = 0 ; i < 10 ; i ++){
            final int task = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(/*new Random().nextInt(500)*/8000);
                    return task;
                }
            });
        }

        for(int i = 0 ; i < 10; i ++){
            try {
                try {
                    System.out.println(completionService.take().get(2,TimeUnit.SECONDS));
                    System.out.println("==========================");
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
