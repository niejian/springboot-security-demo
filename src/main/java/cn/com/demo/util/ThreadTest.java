package cn.com.demo.util;/**
 * Created by niejian on 2018/7/31.
 */

/**
 * @author niejian
 * @date 2018/7/31
 */

public class ThreadTest implements Runnable {


    int count;
    int flag;
    public ThreadTest(int count, int flag) {
        this.count = count;
        this.flag = flag;
    }
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int k = 0; k < count; k++) {
                DistributedLock lock   = new DistributedLock("yun1:2181","lock");
                lock.lock();
            count--;
            flag--;
            System.err.println(Thread.currentThread().getId() + "---->" + count + "=====局部变量结果=====>" + flag);
                if (lock != null) {
                    lock.unlock();
                }
        }

    }
}
