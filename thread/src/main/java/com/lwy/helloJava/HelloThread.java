package com.lwy.helloJava;

public class HelloThread {
    public HelloThread multiThread() {
        //multiThreadDemo1(); // 多线程
        //multiThreadJoin(); // 线程等待，即：B等待A结束后才执行
        //multiThreadSync(); // 线程交叉，即：A进行一部分，插入B，然后再回到A线程
        return this;
    }

    private HelloThread multiThreadDemo1() {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                print("A");
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                print("B");
            }
        });

        one.start();
        two.start();
        return this;
    }

    private HelloThread print(String threadName) {
        int i = 0;
        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(threadName + ": " + i);
        }

        return this;
    }

    private HelloThread multiThreadJoin() {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                print("A");
            }
        });

        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B 开始等待 A");
                try {
                    one.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print("B");
            }
        });

        one.start();
        two.start();
        return this;
    }

    private HelloThread multiThreadSync()
    {
        Object lock = new Object();

        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock)
                {
                    System.out.println("A: 1");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("A: 2");
                    System.out.println("A: 3");
                }
            }
        });


        Thread two = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock)
                {
                    System.out.println("B 1");
                    System.out.println("B 2");
                    System.out.println("B 3");

                    lock.notify();
                }
            }
        });

        one.start();
        two.start();
        return this;
    }

}
