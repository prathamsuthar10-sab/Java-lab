import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OutOfStockException extends Exception {
    private int shortfall;

    OutOfStockException(String message, int shortfall) {
        super(message);
        this.shortfall = shortfall;
    }

    public int getShortfall() {
        return shortfall;
    }
}

class InvalidQuantityException extends Exception {
    InvalidQuantityException(String message) {
        super(message);
    }
}

class Warehouse {
    private Map<String, Integer> stock = new HashMap<>();

    Warehouse() {
        stock.put("Pen", 10);
        stock.put("Notebook", 5);
        stock.put("Mouse", 2);
    }

    public void issue(String item, int quantity)
            throws OutOfStockException, InvalidQuantityException {

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero"
            );
        }

        int available = stock.getOrDefault(item, 0);

        if (quantity > available) {
            throw new OutOfStockException(
                    item + " has insufficient stock",
                    quantity - available
            );
        }

        stock.put(item, available - quantity);

        System.out.println(
                quantity + " " + item + " issued. Remaining: "
                + stock.get(item)
        );
    }
}

class Request {
    String item;
    int quantity;

    Request(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }
}

public class StockIssue {
    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        List<Request> requests = new ArrayList<>();

        requests.add(new Request("Pen", 4));
        requests.add(new Request("Notebook", 8));
        requests.add(new Request("Mouse", 0));
        requests.add(new Request("Pen", 7));
        requests.add(new Request("Mouse", 1));

        for (Request request : requests) {
            try {
                warehouse.issue(request.item, request.quantity);

            } catch (OutOfStockException e) {
                System.out.println(
                        "Request failed: " + e.getMessage()
                        + ". Shortfall: " + e.getShortfall()
                );

            } catch (InvalidQuantityException e) {
                System.out.println(
                        "Request failed: " + e.getMessage()
                );
            }
        }

        System.out.println("All requests processed");
    }
}