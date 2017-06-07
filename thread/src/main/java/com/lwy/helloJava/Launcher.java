package com.lwy.helloJava;

/**
 * Created by lige on 2017/5/28.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Here is beginning");

        HelloThread helloThread = new HelloThread();
        helloThread.multiThread();
    }
}

