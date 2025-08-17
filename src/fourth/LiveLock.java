package fourth;


import java.util.concurrent.locks.ReentrantLock;

public class LiveLock {

    private static final ReentrantLock lock1 = new ReentrantLock(true);
    private static final ReentrantLock lock2 = new ReentrantLock(true);

    public static void main(String[] args) {

        Runnable worker1 = () -> {
            String name = Thread.currentThread().getName();
            while (true) {
                try {
                    lock1.lock();
                    System.out.println(name + " acquired lock1, trying to acquire lock2...");
                    if (lock2.tryLock()) {
                        System.out.println(name + " acquired lock2.");
                        break; // Success!
                    } else {
                        System.out.println(name + " release lock1, lock2 not available.");
                        lock1.unlock();
                        Thread.sleep(1); // Small delay to avoid spinning too fast
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                }
            }
            System.out.println(name + " finished its work.");
        };

        Runnable worker2 = () -> {
            String name = Thread.currentThread().getName();
            while (true) {
                try {
                    lock2.lock();
                    System.out.println(name + " acquired lock2, trying to acquire lock1...");
                    if (lock1.tryLock()) {
                        System.out.println(name + " acquired lock1.");
                        break; // Success!
                    } else {
                        System.out.println(name + " release lock2, lock1 not available.");
                        lock2.unlock();
                        Thread.sleep(1); // Small delay to avoid spinning too fast
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock1.isHeldByCurrentThread()) {
                        lock1.unlock();
                    }
                    if (lock2.isHeldByCurrentThread()) {
                        lock2.unlock();
                    }
                }
            }
            System.out.println(name + " finished its work.");
        };

        Thread thread1 = new Thread(worker1, "Worker-1");
        Thread thread2 = new Thread(worker2, "Worker-2");

        thread1.start();
        thread2.start();
    }
}
