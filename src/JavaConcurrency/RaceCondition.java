package JavaConcurrency;

class Counter {
    int count = 0;

    void increment() {
        count++; // Not atomic: multiple threads may interfere
    }

}
//A race condition occurs when two or more threads access shared data simultaneously,
//and the outcome depends on the order in which the threads execute.
public class RaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Counter c = new Counter();

        // Thread 1 increments count 1000 times
        Thread t1 = new Thread(() ->{
            for (int i = 0; i < 1000; i++) c.increment();
        });

        // Thread 2 increments count 1000 times
        Thread t2 = new Thread(() ->{
            for (int i = 0; i < 1000; i++) c.increment();
        });

        // Start thread 1
        t1.start();

        // Start thread 2
        t2.start();

        // Wait for thread 1 to finish
        t1.join();

        // Wait for thread 2 to finish
        t2.join();

        // May be < 2000 due to race condition
        System.out.println("Final Count: " + c.count);
    }
}
//Solution: Use synchronized blocks or AtomicInteger to ensure thread-safe updates.