package com.heyx.jsoup;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: heyx
 * @create: 2019-05-31 10:13
 * @email; 1064042411@qq.com
 */
public class DeadLockTest {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "ThreadAAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "ThreadBBB").start();

        /**
         * linux ps -f| grep xxxx
         * window 下java 运行程序有类似的ps jps = java ps  jps -l
         */
    }
}


class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+ "\t 自己持有："+ lockA + "尝试获取： " + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+ "\t 自己持有："+ lockB + "尝试获取： " + lockA);
            }
        }


    }
}