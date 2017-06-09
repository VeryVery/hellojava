package com.lwy.helloJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by lige on 2017/6/8.
 */
public class HelloSemaphore {

    /*
    * 单个信号量的Semaphore对象可以实现互斥锁的功能，并且可以是由一个线程获得了 "锁"，再由另外一个线程释放"锁"，
    * 适用场景：银行存钱：20个人去银行存款，但是该银行只有两个办公柜台，有空位则上去存钱，没有空位则只能去排队等待
    * */


    public HelloSemaphore execute()
    {
        ExecutorService executorService = Executors.newCachedThreadPool(); // 线程池
        final Semaphore semaphore = new Semaphore(5); // 只能5个线程同时访问

        // 模拟20个客户端访问
        for(int i = 0; i < 20; ++i)
        {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();  // 获取许可

                        System.out.println("线程: " + Thread.currentThread().getName() + " 获取了许可");
                        Thread.sleep((long) (Math.random() * 10000));

                        System.out.println("线程: " + Thread.currentThread().getName() +
                                " 即将离开");
                        semaphore.release(); // 完成后，释放

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();

        return this;
    }

}
