package epsi.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.CodeSource;

import epsi.Main;
import epsi.model.Agent;

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
        return null;
    }

    public void copyAgentImage(Agent agent) {
        String currentPath = getJarAbsolutePath();
        Path filePath = Paths.get(currentPath, agent.getImageId());
        Path targetPath = Paths.get(currentPath,  "test-image-copy.png");
//        Path originalPath = Path.of(Constants.AGENTS_PATH + agent.getImageId());
//        Path copied = Paths.get(Constants.AGENTS_PATH + agent.getAgentUniqueId() + "/" + agent.getImageId());
        try {
            Files.copy(filePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Image copied success !!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyResource(String resource, String destination, Class c) throws IOException {
        InputStream src = c.getResourceAsStream(resource);
        Files.copy(src, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
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
}
