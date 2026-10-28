import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Maya", 920);
        scores.put("Arjun", 850);
        scores.put("Riya", 920);
        scores.put("Dev", 780);
        scores.put("Anaya", 850);

        int topN = 3;

        System.out.println("Top " + topN + " players:");
        scores.entrySet().stream()
                .sorted(Comparator
                        .comparingInt((Map.Entry<String, Integer> entry) -> entry.getValue())
                        .reversed()
                        .thenComparing(Map.Entry::getKey))
                .limit(topN)
                .forEach(entry -> System.out.println(
                        entry.getKey() + " - " + entry.getValue()
                ));
    }
}