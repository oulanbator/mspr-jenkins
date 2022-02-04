package epsi.utils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;

import epsi.Main;
import epsi.model.Agent;
import epsi.services.FileReader;

import static epsi.utils.Constants.*;

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

    public static void copyAgentImage(Agent agent) {
        Path sourcePath = Paths.get(SRC_TO_RESOURCES_PATH, "repo", agent.getImageId());
        File targetFile = Paths.get(getJarAbsolutePath(), ROOT, AGENTS, agent.getAgentUniqueId(), agent.getImageId()).toFile();
        copyImage(sourcePath, targetFile);
    }

    public static void copyLogo() {
        Path sourcePath = Paths.get(SRC_TO_RESOURCES_PATH, "logo.png");
        File targetFile = Paths.get(getJarAbsolutePath(), ROOT, IMG, "logo.png").toFile();
        copyImage(sourcePath, targetFile);
    }

    public static void copyImage(Path sourcePath, File targetFile) {
        try {
            byte[] buffer = buffer = Files.readAllBytes(sourcePath);
            OutputStream outStream = new FileOutputStream(targetFile);
            outStream.write(buffer);
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyResource(String resource, String destination) throws IOException {
        Files.copy(Paths.get(resource), Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
    }

    public void clearHtmlFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) {
            for(File f: files) {
                if(f.isDirectory()) {
                    File[] underFiles = f.listFiles();
                    for (File underFile: underFiles){
                        underFile.delete();
                    }
                }
                f.delete();
            }
        }
    }

    // TODO : DEPLACER DANS UTILS ?
    public static void redirectStdout(File target) {
        try {
            System.setOut(new PrintStream(target));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder appendFileContent(StringBuilder content, String fileResourcePath) {
        String newContent = FileReader.getAsStringFromResourcesPath(fileResourcePath);
        return content.append(newContent);
    }
}
