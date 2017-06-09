package com.lwy.helloJava;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
*同步辅助类CountDownLatch:一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行
* 适用场景：5个人比赛，知道5个人都到达终点后，才算比赛结束
* */

public class HelloCountDownLatch {
    public HelloCountDownLatch execute()
    {
        final ExecutorService exec = Executors.newFixedThreadPool(5);
        final CountDownLatch latch = new CountDownLatch(5);

        System.out.println("Game Start");

        for (int i = 0; i < 5; ++i)
        {
            Runnable run = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("选手： "+Thread.currentThread().getName()+"到达");
                        Thread.sleep((long) (Math.random() * 10000));

                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                };
            };

            exec.submit(run);
        }

        try {
            latch.await();

            System.out.println("Game Over");
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return this;
    }

}
