package leetcode.multithreading.print_in_order;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Foo {
    private Lock lock = new ReentrantLock();
    private Condition firstDoneCond = lock.newCondition();
    private Condition secondDoneCond = lock.newCondition();

    private boolean firstDone = false;
    private boolean secondDone = false;

    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        lock.lock();
        try {
            printFirst.run();
            firstDone = true;
            firstDoneCond.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        try {
            while (!firstDone) {
                firstDoneCond.await();
            }
            printSecond.run();
            secondDone = true;
            secondDoneCond.signalAll();
        } finally {
            lock.unlock();;
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        try {
            while (!secondDone) {
                secondDoneCond.await();
            }
            printThird.run();
        } finally {
            lock.unlock();
        }
    }
}
