import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;

public class LogAnalyzer {
    public static void main(String[] args) {
        List<Path> files = Arrays.asList(
                Paths.get("log1.txt"),
                Paths.get("log2.txt"),
                Paths.get("log3.txt")
        );

        String keyword = "ERROR";
        int totalLines = 0;
        int matchingLines = 0;

        for (Path file : files) {
            try {
                int fileLines = 0;
                int fileMatches = 0;

                for (String line : Files.readAllLines(file)) {
                    fileLines++;
                    if (line.contains(keyword)) {
                        fileMatches++;
                    }
                }

                BasicFileAttributes attributes =
                        Files.readAttributes(file, BasicFileAttributes.class);

                totalLines += fileLines;
                matchingLines += fileMatches;

                System.out.println("File: " + file.getFileName());
                System.out.println("Size: " + attributes.size() + " bytes");
                System.out.println("Lines: " + fileLines);
                System.out.println("Lines containing \"" + keyword + "\": " + fileMatches);
                System.out.println();
            } catch (IOException e) {
                System.out.println("Could not read " + file + ": " + e.getMessage());
            }
        }

        System.out.println("Total lines across files: " + totalLines);
        System.out.println("Total lines containing \"" + keyword + "\": " + matchingLines);
    }
}