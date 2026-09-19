@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

public class NotificationSenders {
    public static void main(String[] args) {
        Notifier email = message ->
                System.out.println("Email sent: " + message);

        Notifier sms = (Notifier & Urgent) message ->
                System.out.println("SMS sent: " + message);

        Notifier[] senders = {email, sms};
        String message = "Lab practical starts at 10 AM.";

        for (Notifier sender : senders) {
            sender.send(message);

            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}