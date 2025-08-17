package fourth;

public class Streams12 {

    private static final Object lock = new Object();
    private static volatile boolean isFirst = true;

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (!isFirst) {
                        try {
                            lock.wait(); 
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); 
                            return; 
                        }
                    }
                    System.out.print("1");
                    isFirst = false;
                    lock.notify(); 
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isFirst) {
                        try {
                            lock.wait(); 
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); 
                            return; 
                        }
                    }
                    System.out.print("2");
                    isFirst = true;
                    lock.notify(); 
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        thread1.interrupt();
        thread2.interrupt();
    }
}
