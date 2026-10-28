import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;

public class FolderTreeReport {
    public static void main(String[] args) {
        Path folder = Paths.get("sample-folder");
        Path report = Paths.get("folder-report.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(report)) {
            writer.write("File Name | Size (bytes) | Modified Time");
            writer.newLine();

            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attributes)
                        throws IOException {
                    writer.write(file.getFileName() + " | "
                            + attributes.size() + " | "
                            + Instant.ofEpochMilli(attributes.lastModifiedTime().toMillis()));
                    writer.newLine();
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exception)
                        throws IOException {
                    writer.write("Could not access: " + file + " | " + exception.getMessage());
                    writer.newLine();
                    return FileVisitResult.CONTINUE;
                }
            });

            System.out.println("Report saved to " + report.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Could not create the report: " + e.getMessage());
        }
    }
}