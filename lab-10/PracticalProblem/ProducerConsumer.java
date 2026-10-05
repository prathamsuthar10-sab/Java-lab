import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer {
    private Queue<Integer> buffer = new LinkedList<>();
    private int capacity;

    SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void produce(int number)
            throws InterruptedException {

        while (buffer.size() == capacity) {
            wait();
        }

        buffer.add(number);

        System.out.println(
                "Produced: " + number
                + " | Buffer: " + buffer
        );

        notifyAll();
    }

    public synchronized int consume()
            throws InterruptedException {

        while (buffer.isEmpty()) {
            wait();
        }

        int number = buffer.remove();

        System.out.println(
                "Consumed: " + number
                + " | Buffer: " + buffer
        );

        notifyAll();
        return number;
    }
}

public class ProducerConsumer {
    public static void main(String[] args)
            throws InterruptedException {

        SharedBuffer buffer = new SharedBuffer(3);
        int totalItems = 10;

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.produce(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= totalItems; i++) {
                    buffer.consume();
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("All items produced and consumed");
    }
}