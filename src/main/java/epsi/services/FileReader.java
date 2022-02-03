package epsi.services;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileReader {

    public static String getAsString(String fullFilePath) {
        String str = "";
        try {
            File file = new File(fullFilePath);
            FileInputStream fileStream = new FileInputStream(file);
            byte data[] = new byte[fileStream.available()];
            fileStream.read(data);
            fileStream.close();
            str = new String(data);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * Méthode utilitaire pour débug. Permet de print un fichier et de vérifier que le chemin
     * passé en paramètre est valide.
     * 
     * @param fullFilePath chemin relatif ver le fichier depuis "src/main/resources/"
     */
    public static void print(String fullFilePath) {
        String file = getAsString(fullFilePath);
        System.out.println(file);
    }
}
