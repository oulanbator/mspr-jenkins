package epsi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public static void print(String fileName) {
        System.out.println("getResourceAsStream : " + fileName);
        FileReader app = new FileReader();

        InputStream stream = app.getFileFromResourceAsStream(fileName);
        printInputStream(stream);
    }

    private InputStream getFileFromResourceAsStream(String fileName) {
        // Get the input stream
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        // return the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    private static void printInputStream(InputStream is) {
        //The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            // Print chaque ligne
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
