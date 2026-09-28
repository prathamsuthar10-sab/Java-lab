class DatabaseConnection implements AutoCloseable {
    private boolean open;

    DatabaseConnection() {
        open = true;
        System.out.println("Database connection opened");
    }

    public void readData() throws Exception {
        if (!open) {
            throw new Exception("Connection is already closed");
        }

        System.out.println("Reading data from database");
        throw new Exception("Original error while reading data");
    }

    @Override
    public void close() throws Exception {
        open = false;
        System.out.println("Database connection closed");
        throw new Exception("Error while closing connection");
    }
}

public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (DatabaseConnection connection =
                     new DatabaseConnection()) {

            connection.readData();

        } catch (Exception e) {
            System.out.println("Reported error: " + e.getMessage());

            for (Throwable error : e.getSuppressed()) {
                System.out.println(
                        "Suppressed error: " + error.getMessage()
                );
            }
        }
    }
}