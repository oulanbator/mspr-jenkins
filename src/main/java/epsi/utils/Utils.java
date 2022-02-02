package epsi.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import epsi.model.Agent;

public class Utils {

    public void copyImageFiles(Agent agent) {
        Path originalPath = Path.of(Constants.AGENTS_PATH + agent.getImageId());
        Path copied = Paths.get(Constants.AGENTS_PATH + agent.getAgentUniqueId() + "/" + agent.getImageId());
        try {
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
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
