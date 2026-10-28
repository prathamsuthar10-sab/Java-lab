import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> loginNames = Arrays.asList(
                "maya", "arjun", "maya", "riya", "arjun", "dev", "riya"
        );

        Set<String> uniqueUsers = new HashSet<>(loginNames);
        Set<String> firstSeenOrder = new LinkedHashSet<>(loginNames);

        System.out.println("Unique users: " + uniqueUsers.size());

        System.out.println("First three distinct names:");
        int count = 0;
        for (String name : firstSeenOrder) {
            System.out.println(name);
            count++;
            if (count == 3) {
                break;
            }
        }
    }
}