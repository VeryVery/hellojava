package com.lwy.helloJava;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
* 同步辅助工具类CyclicBarrier
*它允许一组线程相互等待，直到到达一个公共的栏栅点，后再全部同时执行
* 适用场景：5个人跑步比赛，必须5个人准备好了，才能一起出发；
* */

public class HelloCyclicBarrier {

    public HelloCyclicBarrier execute()
    {
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(5);//如果这里改成6，或者更大，则永远不会起跑

        CyclicBarrierTest barrier1 = new CyclicBarrierTest(barrier, 1);
        CyclicBarrierTest barrier2 = new CyclicBarrierTest(barrier, 2);
        CyclicBarrierTest barrier3 = new CyclicBarrierTest(barrier, 3);
        CyclicBarrierTest barrier4 = new CyclicBarrierTest(barrier, 4);
        CyclicBarrierTest barrier5 = new CyclicBarrierTest(barrier, 5);

        service.execute(barrier1);
        service.execute(barrier2);
        service.execute(barrier3);
        service.execute(barrier4);
        service.execute(barrier5);

        service.shutdown();
        return this;
    }


    class CyclicBarrierTest implements Runnable {
        private final CyclicBarrier barrier;
        private int number;

        public CyclicBarrierTest(CyclicBarrier barrier, int number) {
            this.barrier = barrier;
            this.number = number;
        }

        @Override
        public void run() {

            try {
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(String.format("number: %d 准备好了", number));
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(String.format("number: %d 出发", number));
        }
    }

}
