package JavaConcurrency;

class SharedValue {
    private int value = 0;

    public void increment() {
        value++; // Not atomic, race condition possible
    }

    public int getValue() {
        return value;
    }
}

public class RaceCondition1 {
    public static void main(String[] args) throws InterruptedException {
        SharedValue sv = new SharedValue();

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

        // May be < 2000 due to race condition
        System.out.println("Final Value: " + sv.getValue());
    }
}