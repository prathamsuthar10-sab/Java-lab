import java.util.Scanner;

public class TollBooth {

    record Vehicle(String number, String type) {
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int total = 0;
        int bike = 0;
        int car = 0;
        int truck = 0;

        while (true) {
            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next().toLowerCase();

            Vehicle v = new Vehicle(number, type);

            int toll = switch (v.type()) {
                case "bike" -> 20;
                case "car" -> 50;
                case "truck" -> 150;
                default -> 0;
            };

            total += toll;

            switch (v.type()) {
                case "bike":
                    bike++;
                    break;
                case "car":
                    car++;
                    break;
                case "truck":
                    truck++;
                    break;
            }
        }

        String most = "bike";

        if (car > bike && car >= truck) {
            most = "car";
        } else if (truck > bike && truck > car) {
            most = "truck";
        }

        System.out.println("Total toll: " + total);
        System.out.println("Most frequent: " + most);

        sc.close();
    }
}