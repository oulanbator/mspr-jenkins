package epsi.services;

import epsi.utils.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public static String getAsStringFromRelativePath(String filePath) {
        String fullFilePath = Utils.getJarAbsolutePath() + "/" + filePath;
        // TODO : surement moyen de simplifier cette méthode
        String fileContent = "";
        try {
            File file = new File(fullFilePath);
            FileInputStream fileStream = new FileInputStream(file);
            byte data[] = new byte[fileStream.available()];
            fileStream.read(data);
            fileStream.close();
            fileContent = new String(data);
        } catch(Exception e) {
            e.printStackTrace();
        }
        fileContent = fileContent.replace("\r", "");
        return fileContent;
    }

    /**
     * Récupère le contenu du fichier passé en paramètre pour pouvoir être lu
     * même une fois que le .jar est buildé.
     * Renvoie le contenu du fichier dans une variable de type String.
     * 
     * @param relativeFilePath chemin relatif ver le fichier depuis "src/main/resources/"
     * @return Un String avec le contenu du fichier
     */
    public static String getAsStringFromResourcesPath(String relativeFilePath) {
        // Get the input stream
        FileReader reader = new FileReader();
        InputStream inputStream = reader.getClass().getClassLoader().getResourceAsStream(relativeFilePath);
        // Throw error if stream = null
        if (inputStream == null) throw new IllegalArgumentException("file not found! " + relativeFilePath);

        // Transforms the stream into a String.
        String fileContent = "";
        // The try-with-resources statement ensures that each resource is closed at the end of the statement.
        try (InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader buffer = new BufferedReader(streamReader)) 
            {
            // Ajoute chaque ligne à la string
            String line;
            while ((line = buffer.readLine()) != null) {
                fileContent = fileContent + line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileContent = fileContent.replace("\r", "");
        return fileContent;
    }
    
    /**
     * Méthode utilitaire pour débug. Permet de print un fichier et de vérifier que le chemin
     * passé en paramètre est valide.
     * 
     * @param filePath chemin absolu vers le fichier
     */
    public static void printFromRelativePath(String filePath) {
        String file = getAsStringFromRelativePath(filePath);
        System.out.println(file);
    }
    
    /**
     * Méthode utilitaire pour débug. Permet de print un fichier et de vérifier que le chemin
     * passé en paramètre est valide.
     * 
     * @param relativeFilePath chemin relatif vers le fichier depuis "src/main/resources/"
     */
    public static void printFromResourcesPath(String relativeFilePath) {
        String file = getAsStringFromResourcesPath(relativeFilePath);
        System.out.println(file);
    }
}
