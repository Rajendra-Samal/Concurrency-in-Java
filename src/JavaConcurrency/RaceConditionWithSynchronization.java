package JavaConcurrency;

class Counter1 {
    private int count = 0;

    // Synchronized method: only one thread can execute this at a time
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        // Also synchronized to ensure consistent read
        return count;
    }
}

public class RaceConditionWithSynchronization {
    public static void main(String[] args) throws InterruptedException {
        Counter1 c = new Counter1();

        // Thread 1 increments count 1000 times
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });

        // Thread 2 increments count 1000 times
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                c.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        // Now this will always be 2000
        System.out.println("Final Count: " + c.getCount());
    }
}

//public synchronized void increment() ensures that only one thread at a time can execute increment()
// on the same Counter object.
//The count++ operation becomes effectively atomic with respect to other threads calling increment().
//
//No race condition: each increment is guaranteed to be applied, so 1000 + 1000 = 2000.