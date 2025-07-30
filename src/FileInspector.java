import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.File;
import javax.swing.JFileChooser;

public class FileInspector {
    public static void main(String[] args) {
        // Pseudocode:
        // 1. Create JFileChooser to let user pick a file
        // 2. Use NIO classes to read the file
        // 3. Count lines, words, and characters
        // 4. Display a summary report

        try {
            // Set up JFileChooser to open in IntelliJ's working directory
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                // Set up BufferedReader chain using NIO
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        Files.newInputStream(file))));

                // Initialize counters
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;
                String line;

                // Read and process file line by line
                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    wordCount += line.split("\\s+").length; // split on whitespace
                    charCount += line.length();
                }

                reader.close();

                // Output report
                System.out.println("\n=== File Summary ===");
                System.out.println("File name: " + selectedFile.getName());
                System.out.println("Lines: " + lineCount);
                System.out.println("Words: " + wordCount);
                System.out.println("Characters: " + charCount);
            } else {
                System.out.println("No file selected. Program exiting.");
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}