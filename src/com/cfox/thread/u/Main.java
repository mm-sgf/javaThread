package com.cfox.thread.u;

public class Main {

    public static void main(String[] args) {

        Mvs m = new Mvs();
        new Thread(m).start();

        new Thread(m).start();
    }
}
