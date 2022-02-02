package epsi.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReader {

    /**
     * Récupère le contenu du fichier passé en paramètre dans une variable de type String
     * @param fileName chemin relatif ver le fichier depuis "src/main/resources/"
     * @return Un String avec le contenu du fichier
     */
    public static String getAsString(String fileName) {
        InputStream stream = loadStream(fileName);
        return getFileFromStreamAsString(stream);
    }

    /**
     * Charge le contenu du fichier passé en paramètre dans un stream pour pouvoir être lu
     * même une fois que le .jar est buildé.
     *  
     * @param fileName chemin relatif ver le fichier depuis "src/main/resources/"
     * @return Un stream avec le contenu du fichier
     */
    private static InputStream loadStream(String fileName) {
        // Get the input stream
        FileReader reader = new FileReader();
        InputStream inputStream = reader.getClass().getClassLoader().getResourceAsStream(fileName);
        // return the stream holding the file content or throw error
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    /**
     * Transforme le stream en une variable de type String.
     * 
     * @param stream le stream chargé dans loadStream()
     * @return Un String avec le contenu du fichier.
     */
    private static String getFileFromStreamAsString(InputStream stream) {
        String fileContent = "";
        //The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            // Ajoute chaque ligne à la string
            String line;
            while ((line = reader.readLine()) != null) {
                fileContent = fileContent + line + "\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
    
    /**
     * Méthode utilitaire pour débug. Permet de print un fichier et de vérifier que le chemin
     * passé en paramètre est valide.
     * 
     * @param fileName chemin relatif ver le fichier depuis "src/main/resources/"
     */
    public static void print(String fileName) {
        InputStream stream = loadStream(fileName);

        //The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (InputStreamReader streamReader = new InputStreamReader(stream, StandardCharsets.UTF_8);
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
