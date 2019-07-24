package cn.peng.feigntwo.test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;


public class KodyLock implements Lock {

    /**
     * 锁拥有者
     */
    public AtomicReference<Thread> owner = new AtomicReference<>();
    /**
     * 等待队列
     */
    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();
    /**
     * 标记锁被获取了多少次
     */
    AtomicInteger count = new AtomicInteger(0);

    @Override
    public boolean tryLock() {
        int ct = count.get();
        //判断 count是否为0,若count！=0，说明锁被占用，
        if (ct!=0){
            //判断是否被当前线程占用，若被当前线程占用 count+=1
            if (owner.get() == Thread.currentThread()){
                count.set(ct + 1);
                return true;
            }else{
                //若不是被当前线程占用，抢锁失败，返回false
                return false;
            }
        }else{
            //若count=0，说明锁违被占用，通过CAS 修改count=1来抢锁
            //若修改成功，抢锁成功，设置owner后返回true
            if (count.compareAndSet(ct, ct+1)){
                owner.set(Thread.currentThread());
                return true;
            }else{
                //否则返回false
                return false;
            }
        }
    }

    @Override
    public void lock() {
        //若tryLock失败，
        if (!tryLock()){
            // 就加入队列，
            waiters.offer(Thread.currentThread());

            //自旋抢锁
            for (;;){
                //若当前线程在队列头部
                Thread head = waiters.peek();
                if (head == Thread.currentThread()){
                    //tryLock，若失败，park
                    if (!tryLock()){
                        LockSupport.park();
                    }else{
                        //若成功，出队列，退出方法
                        waiters.poll();
                        return;
                    }
                }else{
                    //若不在
                    LockSupport.park();
                }
            }
        }
        //若tryLock成功直接退出
    }


    @Override
    public void unlock() {
        if (tryUnlock()){
            Thread th =  waiters.peek();
            if (th !=null){
                LockSupport.unpark(th);
            }
        }
    }

    public boolean tryUnlock(){
        //判断 当前线程是不是owner，若不是，就抛异常
        if (owner.get()!= Thread.currentThread()){
            throw new IllegalMonitorStateException();
        }else{
            //若是，将count值-1
            int ct = count.get();
            int nextc = ct -1;
            count.set(nextc);

            //判断新的count值是否为0，若为0，讲owner设置为null 返回true
            if (nextc == 0){
                owner.set(null);
                return true;
                //若不为0，返回false
            }else{
                return false;
            }
        }

    }





    @Override
    public void lockInterruptibly() throws InterruptedException {

    }



    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
