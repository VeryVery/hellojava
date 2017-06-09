package com.lwy.helloJava.HelloProducerConsumer;

/**
 * Created by lige on 2017/6/9.
 */
public class ExecuteProducerConsumer {
    public ExecuteProducerConsumer execute()
    {
        EventStorage storage = new EventStorage();
        Producer producer = new Producer(storage);
        Consumer consumer = new Consumer(storage);

        Thread thread1 = new Thread(producer);
        Thread thread2 = new Thread(consumer);

        thread2.start();
        thread1.start();

        return this;
    }

}
