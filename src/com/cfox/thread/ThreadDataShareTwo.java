package com.cfox.thread;

import java.util.Random;

/**
 * <br/>************************************************
 * <br/>MSG : 线程内数据共享，两个线程操作同一数据，会出现线程安全问题
 * <br/>************************************************
 */
public class ThreadDataShareTwo {
    private int data = 0;
    private ThreadLocal<Integer> localData = new ThreadLocal<Integer>();
    public static void main(String[] args) {
        ThreadDataShareTwo share = new ThreadDataShareTwo();
        share.initThread();
    }

    private void initThread(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                User.getThreadLoaclUser().setName("zhangsan");
                User.getThreadLoaclUser().setAge(data);
                System.out.println("ThreadName:" + Thread.currentThread() + ";name::: zhangsan ---> age:::" + data);
                OutPuter puter = new OutPuter();
                puter.outPutA();
                puter.outPutB();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int data = new Random().nextInt();
                User.getThreadLoaclUser().setName("lisi");
                User.getThreadLoaclUser().setAge(data);
                System.out.println("ThreadName:" + Thread.currentThread() + ";name::: lisi ---> age:::" + data);
                OutPuter puter = new OutPuter();
                puter.outPutA();
                puter.outPutB();
                String strs;
            }
        }).start();
    }

    class OutPuter{
        public void outPutA(){
            User user = User.getThreadLoaclUser();
            System.out.println("put--A ---ThreadName:" + Thread.currentThread() + ";name:" + user.getName());
        }
        public void outPutB(){
            User user = User.getThreadLoaclUser();
            System.out.println("put--B ---ThreadName:" + Thread.currentThread() + "name:::;" + user.getName() +  ";Data:" + user.getAge());
        }
    }
}

class User{
    private static ThreadLocal<User> localUser = new ThreadLocal<User>();
    private String name;
    private int age;
    public User(){}
    public static User getThreadLoaclUser(){
        if(localUser.get() == null){
            localUser.set(new User());
        }
        return localUser.get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
