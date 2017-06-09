package com.lwy.helloJava;

import com.lwy.helloJava.HelloProducerConsumer.ExecuteProducerConsumer;

/**
 * Created by lige on 2017/5/28.
 */
public class Launcher {
    public static void main(String[] args) {
        System.out.println("Here is beginning");

        ExecuteProducerConsumer launcher = new ExecuteProducerConsumer();
        launcher.execute();
    }
}

