import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

interface TotalCounter {
    void add(int number);
    long getTotal();
}

class UnsafeTotal implements TotalCounter {
    private long total = 0;

    public void add(int number) {
        total += number;
    }

    public long getTotal() {
        return total;
    }
}

class SynchronizedTotal implements TotalCounter {
    private long total = 0;

    public synchronized void add(int number) {
        total += number;
    }

    public long getTotal() {
        return total;
    }
}

class AtomicTotal implements TotalCounter {
    private AtomicLong total = new AtomicLong(0);

    public void add(int number) {
        total.addAndGet(number);
    }

    public long getTotal() {
        return total.get();
    }
}

class TestResult {
    long total;
    long time;

    TestResult(long total, long time) {
        this.total = total;
        this.time = time;
    }
}

public class ArraySumRace {
    static final int THREADS = 8;

    static TestResult calculate(
            int[] numbers,
            TotalCounter counter) throws InterruptedException {

        Thread[] workers = new Thread[THREADS];
        CountDownLatch start = new CountDownLatch(1);
        int partSize = numbers.length / THREADS;

        for (int i = 0; i < THREADS; i++) {
            int startIndex = i * partSize;
            int endIndex;

            if (i == THREADS - 1) {
                endIndex = numbers.length;
            } else {
                endIndex = startIndex + partSize;
            }

            workers[i] = new Thread(() -> {
                try {
                    start.await();

                    for (int j = startIndex; j < endIndex; j++) {
                        counter.add(numbers[j]);

                        if (j % 10000 == 0) {
                            Thread.yield();
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            workers[i].start();
        }

        long startTime = System.nanoTime();
        start.countDown();

        for (Thread worker : workers) {
            worker.join();
        }

        long endTime = System.nanoTime();

        return new TestResult(
                counter.getTotal(),
                endTime - startTime
        );
    }

    static void display(
            String method,
            TestResult result,
            long expected) {

        long error = Math.abs(expected - result.total);
        double milliseconds = result.time / 1_000_000.0;

        System.out.println("\nMethod: " + method);
        System.out.println("Calculated total: " + result.total);
        System.out.println("Difference: " + error);
        System.out.printf("Time: %.3f ms%n", milliseconds);
    }

    public static void main(String[] args)
            throws InterruptedException {

        int[] numbers = new int[1000000];
        long expected = 0;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = (i % 10) + 1;
            expected += numbers[i];
        }

        TestResult unsafeResult =
                calculate(numbers, new UnsafeTotal());

        TestResult synchronizedResult =
                calculate(numbers, new SynchronizedTotal());

        TestResult atomicResult =
                calculate(numbers, new AtomicTotal());

        System.out.println("Expected total: " + expected);

        display(
                "Without synchronization",
                unsafeResult,
                expected
        );

        display(
                "Synchronized method",
                synchronizedResult,
                expected
        );

        display(
                "AtomicLong",
                atomicResult,
                expected
        );
    }
}