package com.lwy.helloJava.HelloProducerConsumer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lige on 2017/6/9.
 */
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage()
    {
        maxSize = 10;
        storage = new LinkedList<Date>();
    }

    public synchronized void set()
    {
        while (storage.size() == maxSize)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        storage.add(new Date());
        System.out.println(String.format("Set: %d",storage.size()));


        notifyAll();
    }

    public synchronized void get()
    {
        while (storage.size() == 0)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("Get: %d: %s",storage.size(),((LinkedList<?>)storage).poll()));
        notifyAll();
    }

}
