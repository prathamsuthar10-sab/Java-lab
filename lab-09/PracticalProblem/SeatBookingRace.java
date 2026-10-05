interface BookingSystem {
    boolean book(String customer);
    int getSeatsLeft();
}

class UnsafeBooking implements BookingSystem {
    private int seatsLeft = 5;

    public boolean book(String customer) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            seatsLeft--;
            System.out.println(customer + " booked a seat");
            return true;
        }

        System.out.println(customer + " failed to book");
        return false;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }
}

class SafeBooking implements BookingSystem {
    private int seatsLeft = 5;

    public synchronized boolean book(String customer) {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            seatsLeft--;
            System.out.println(customer + " booked a seat");
            return true;
        }

        System.out.println(customer + " failed to book");
        return false;
    }

    public int getSeatsLeft() {
        return seatsLeft;
    }
}

public class SeatBookingRace {
    static final int CUSTOMERS = 10;

    static void runBooking(BookingSystem booking)
            throws InterruptedException {

        Thread[] threads = new Thread[CUSTOMERS];
        boolean[] results = new boolean[CUSTOMERS];

        for (int i = 0; i < CUSTOMERS; i++) {
            int index = i;
            String customer = "Customer " + (i + 1);

            threads[i] = new Thread(() ->
                    results[index] = booking.book(customer)
            );

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int successfulBookings = 0;

        for (boolean result : results) {
            if (result) {
                successfulBookings++;
            }
        }

        System.out.println(
                "Successful bookings: " + successfulBookings
        );
        System.out.println(
                "Seats left: " + booking.getSeatsLeft()
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        System.out.println("Without synchronization:");
        runBooking(new UnsafeBooking());

        System.out.println("\nWith synchronization:");
        runBooking(new SafeBooking());
    }
}