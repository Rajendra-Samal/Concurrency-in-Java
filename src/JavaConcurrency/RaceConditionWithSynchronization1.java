package JavaConcurrency;

class SharedValue1 {
    private int value = 0;

    // Synchronized method: only one thread at a time
    public synchronized void increment() {
        value++;
    }

    public synchronized int getValue() {
        return value;
    }
}

public class RaceConditionWithSynchronization1 {
    public static void main(String[] args) throws InterruptedException {
        SharedValue1 sv = new SharedValue1();

        // Thread 1: increments 1000 times
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    sv.increment();
                }
            }
        };

        // Thread 2: increments 1000 times
        Thread t2 = new Thread() {
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    sv.increment();
                }
            }
        };

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Now this will always be 2000
        System.out.println("Final Value: " + sv.getValue());
    }
}