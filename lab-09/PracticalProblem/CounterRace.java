class UnsafeCounter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

class SafeCounter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class CounterRace {
    static final int THREAD_COUNT = 8;
    static final int INCREMENTS = 500000;

    static void runThreads(Runnable task)
            throws InterruptedException {

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS; j++) {
                    task.run();
                }
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    static int runWithoutSynchronization()
            throws InterruptedException {

        UnsafeCounter counter = new UnsafeCounter();
        runThreads(counter::increment);
        return counter.getCount();
    }

    static int runWithSynchronization()
            throws InterruptedException {

        SafeCounter counter = new SafeCounter();
        runThreads(counter::increment);
        return counter.getCount();
    }

    public static void main(String[] args)
            throws InterruptedException {

        int expected = THREAD_COUNT * INCREMENTS;

        System.out.println("Expected total: " + expected);

        System.out.println("\nWithout synchronization:");

        for (int i = 1; i <= 3; i++) {
            int result = runWithoutSynchronization();
            System.out.println("Run " + i + ": " + result);
        }

        System.out.println("\nWith synchronization:");

        for (int i = 1; i <= 3; i++) {
            int result = runWithSynchronization();
            System.out.println("Run " + i + ": " + result);
        }
    }
}