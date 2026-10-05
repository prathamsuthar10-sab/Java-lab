import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolRunner {
    public static void main(String[] args)
            throws InterruptedException {

        ExecutorService pool =
                Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int taskId = i;

            pool.submit(() -> {
                String threadName =
                        Thread.currentThread().getName();

                System.out.println(
                        "Task " + taskId
                        + " started by " + threadName
                );

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Task " + taskId
                        + " completed by " + threadName
                );
            });
        }

        pool.shutdown();

        boolean completed =
                pool.awaitTermination(1, TimeUnit.MINUTES);

        if (completed) {
            System.out.println("All tasks completed");
        } else {
            System.out.println("Time limit exceeded");
            pool.shutdownNow();
        }
    }
}