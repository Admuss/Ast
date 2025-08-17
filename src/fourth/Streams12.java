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
                            lock.wait(); // Ждем, пока другой поток не выведет свое число
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Важно правильно обработать прерывание
                            return; // Выходим из потока, если нас прервали
                        }
                    }
                    System.out.print("1");
                    isFirst = false;
                    lock.notify(); // Уведомляем другой поток
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (isFirst) {
                        try {
                            lock.wait(); // Ждем, пока другой поток не выведет свое число
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt(); // Важно правильно обработать прерывание
                            return; // Выходим из потока, если нас прервали
                        }
                    }
                    System.out.print("2");
                    isFirst = true;
                    lock.notify(); // Уведомляем другой поток
                }
            }
        });

        thread1.start();
        thread2.start();

        // Дать потокам поработать какое-то время, чтобы увидеть результат
        // (необязательно, но полезно для демонстрации)
        try {
            Thread.sleep(5000); // Ждем 5 секунд
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Прерываем потоки, чтобы завершить программу корректно
        thread1.interrupt();
        thread2.interrupt();
    }
}
