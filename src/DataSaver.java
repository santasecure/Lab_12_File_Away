import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class DataSaver {
    public static void main(String[] args) {
        // Pseudocode:
        // 1. Create Scanner and ArrayList<String> to hold CSV records
        // 2. Loop to collect user input for each record
        //      - Prompt for: first name, last name, ID (6 digits), email, year of birth
        //      - Format as CSV string: "First, Last, ID, Email, Year"
        //      - Add CSV string to ArrayList
        //      - Ask if user wants to enter another
        // 3. After loop ends, prompt for output file name
        // 4. Append ".csv" extension
        // 5. Use NIO to write CSV records to file

        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        boolean addMore = true;
        int idCounter = 1;

        while (addMore) {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter first name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter last name");

            // Format ID with leading zeroes
            String id = String.format("%06d", idCounter);
            idCounter++;

            String email = SafeInput.getNonZeroLenString(in, "Enter email address");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter year of birth", 1900, 2100);

            // Build CSV line
            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, id, email, yearOfBirth);
            records.add(record);

            // Ask user if they want to enter another record
            addMore = SafeInput.getYNConfirm(in, "Do you want to enter another record");
        }

        // Prompt for file name
        String fileName = SafeInput.getNonZeroLenString(in, "Enter output filename (no extension)");
        fileName += ".csv";

        // Create file path in IntelliJ project src folder
        Path file = Path.of(System.getProperty("user.dir"), "src", fileName);

        try {
            // Set up BufferedWriter chain using NIO
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    Files.newOutputStream(file))));

            // Write each record to file
            for (String rec : records) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }

            writer.close();
            System.out.println("File saved: " + file.toString());

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}