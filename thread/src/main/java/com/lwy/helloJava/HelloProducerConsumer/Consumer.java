package com.lwy.helloJava.HelloProducerConsumer;

/**
 * Created by lige on 2017/6/9.
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }


    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            storage.get();
        }
    }
}
