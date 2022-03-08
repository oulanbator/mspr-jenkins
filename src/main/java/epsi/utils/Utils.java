package epsi.utils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.*;
import java.security.CodeSource;

import epsi.Main;
import epsi.model.Agent;
import epsi.services.FileReader;

import static epsi.utils.Constants.*;
import static java.io.FileDescriptor.out;

public class Utils {

    /**
     * Le programme compilé en jar va travailler ave les documents avec les documents qui se situent
     * "autour" de lui. Cette méthode permet de récupérer le path absolu du répertoire du jar.
     *
     * @return le chemin du jar
     */
    public static String getJarAbsolutePath() {
        String absoluteDecodedPath = "";
        try {
            CodeSource source = Main.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(source.getLocation().toURI().getPath());
            String directoryPath = jarFile.getParentFile().getPath();
            absoluteDecodedPath = URLDecoder.decode(directoryPath, "UTF-8");
            return absoluteDecodedPath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absoluteDecodedPath;
    }

    public static void copyLogo() {
        InputStream stream = Utils.class.getResourceAsStream("/logo.png");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        File targetFile = Paths.get(getJarAbsolutePath(), ROOT, IMG, "logo.png").toFile();
        try {
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(stream.readAllBytes());
            outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyAgentImage(Agent agent) {
        Path sourcePath = Paths.get(getJarAbsolutePath(), agent.getImageId());
        File targetFile = Paths.get(getJarAbsolutePath(), ROOT, AGENTS, agent.getAgentUniqueId(), agent.getImageId()).toFile();
        try {
            copyImage(sourcePath, targetFile);
        } catch (Exception e) {
//            System.out.println("\nErreur lors de la copie de l'image : " + agent.getImageId());
//            System.out.println("Vérifier que le fichier est correctement nommé.");
            logError(agent.getImageId());
//            e.printStackTrace();
        }
    }

    public static void buildAgentDir(Agent agent) {
        File agentDir = Paths.get(getJarAbsolutePath(), ROOT, AGENTS, agent.getAgentUniqueId()).toFile();
        agentDir.mkdirs();
    }

    private static void logError(String imageId) {
        try {
            File logs = Paths.get(getJarAbsolutePath(),  "logs.txt").toFile();
            FileWriter fileWriter = new FileWriter(logs, true);
            fileWriter.write("<p>Erreur lors de la copie de l'image : " + imageId);
            fileWriter.write("<br> Vérifier que le fichier est correctement nommé.<p>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyImage(Path sourcePath, File targetFile) throws IOException {
        byte[] buffer = Files.readAllBytes(sourcePath);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
        outStream.close();
    }

    public static void redirectStdout(File target) {
        try {
            System.setOut(new PrintStream(target));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
