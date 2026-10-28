import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class WordFrequency {
    public static void main(String[] args) {
        String paragraph = "Java is simple and Java is powerful. Java is widely used.";

        Map<String, Integer> frequency = new HashMap<>();

        for (String word : paragraph.toLowerCase().replaceAll("[^a-z0-9\\s]", "").split("\\s+")) {
            if (!word.isEmpty()) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }

        frequency.entrySet().stream()
                .sorted(Comparator.comparingInt(
                        (Map.Entry<String, Integer> entry) -> entry.getValue()
                ).reversed().thenComparing(Map.Entry::getKey))
                .forEach(entry -> System.out.println(
                        entry.getKey() + " : " + entry.getValue()
                ));
    }
}