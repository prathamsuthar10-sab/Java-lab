import java.util.concurrent.CountDownLatch;

class BankAccount {
    private int id;
    private String name;
    private int balance;

    BankAccount(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }
}

public class DeadlockDemo {
    static void unsafeTransfer(
            BankAccount from,
            BankAccount to,
            int amount,
            CountDownLatch firstLocks) {

        synchronized (from) {
            System.out.println(
                    Thread.currentThread().getName()
                    + " locked " + from.getName()
            );

            firstLocks.countDown();

            try {
                firstLocks.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println(
                    Thread.currentThread().getName()
                    + " waiting for " + to.getName()
            );

            synchronized (to) {
                from.withdraw(amount);
                to.deposit(amount);
            }
        }
    }

    static void safeTransfer(
            BankAccount from,
            BankAccount to,
            int amount) {

        BankAccount first;
        BankAccount second;

        if (from.getId() < to.getId()) {
            first = from;
            second = to;
        } else {
            first = to;
            second = from;
        }

        synchronized (first) {
            synchronized (second) {
                from.withdraw(amount);
                to.deposit(amount);

                System.out.println(
                        Thread.currentThread().getName()
                        + " transferred Rs." + amount
                        + " from " + from.getName()
                        + " to " + to.getName()
                );
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        BankAccount accountA =
                new BankAccount(1, "Account A", 1000);

        BankAccount accountB =
                new BankAccount(2, "Account B", 1000);

        CountDownLatch firstLocks = new CountDownLatch(2);

        Thread thread1 = new Thread(
                () -> unsafeTransfer(
                        accountA, accountB, 100, firstLocks
                ),
                "Thread 1"
        );

        Thread thread2 = new Thread(
                () -> unsafeTransfer(
                        accountB, accountA, 100, firstLocks
                ),
                "Thread 2"
        );

        thread1.setDaemon(true);
        thread2.setDaemon(true);

        System.out.println("Opposite lock order:");

        thread1.start();
        thread2.start();

        thread1.join(1000);
        thread2.join(1000);

        if (thread1.isAlive() && thread2.isAlive()) {
            System.out.println("Deadlock detected");
        }

        BankAccount safeA =
                new BankAccount(1, "Account A", 1000);

        BankAccount safeB =
                new BankAccount(2, "Account B", 1000);

        Thread safeThread1 = new Thread(
                () -> safeTransfer(safeA, safeB, 100),
                "Safe Thread 1"
        );

        Thread safeThread2 = new Thread(
                () -> safeTransfer(safeB, safeA, 100),
                "Safe Thread 2"
        );

        System.out.println("\nConsistent lock order:");

        safeThread1.start();
        safeThread2.start();

        safeThread1.join();
        safeThread2.join();

        System.out.println("Both transfers completed");
        System.out.println(
                "Account A balance: Rs." + safeA.getBalance()
        );
        System.out.println(
                "Account B balance: Rs." + safeB.getBalance()
        );
    }
}