package epsi.services;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import epsi.model.Agent;
import epsi.utils.Utils;

public class AppBuilder {
    private String jarCurrentPath;
    
    private final String ROOT = "gosecuri";
    private final String AGENTS = "agents";
    private final String IMG = "img";
    private final String CSS = "css";

    public AppBuilder() {
        this.jarCurrentPath = Utils.getJarAbsolutePath();
    }

    public void buildDirectories(List<Agent> agents) {
        // build root and agents dirs
        for (Agent agent : agents) {
            File agentDir = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId()).toFile();
            agentDir.mkdirs();
        }

        // build img dir
        File imgDir = Paths.get(jarCurrentPath, ROOT, IMG).toFile();
        imgDir.mkdirs();

        // build css dir
        File cssDir = Paths.get(jarCurrentPath, ROOT, CSS).toFile();
        cssDir.mkdirs();

        System.out.println("Build directories success !");
    }
}
