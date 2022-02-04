package epsi.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import epsi.model.Agent;
import epsi.utils.Utils;

import static java.lang.System.out;

public class Generator {
    private String jarCurrentPath;
    
    private final String ROOT = "gosecuri";
    private final String AGENTS = "agents";
    private final String IMG = "img";
    private final String CSS = "css";

    public Generator() {
        this.jarCurrentPath = Utils.getJarAbsolutePath();
    }

    public void buildDirectories() {
        // build agents dir
        File agents = Paths.get(jarCurrentPath, ROOT, AGENTS).toFile();
        agents.mkdirs();

        // build img dir
        File imgDir = Paths.get(jarCurrentPath, ROOT, IMG).toFile();
        imgDir.mkdirs();

        // build css dir
        File cssDir = Paths.get(jarCurrentPath, ROOT, CSS).toFile();
        cssDir.mkdirs();

        System.out.println("Build directories success !");
    }

    public void buildIndex(List<Agent> agents) {
        // Get absolute path
        File index = Paths.get(jarCurrentPath, ROOT, "index.html").toFile();
        // Redirect stdout to file
        try {
            System.setOut(new PrintStream(index));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/index/part1.txt");
        for (Agent agent : agents) {
            content.append("<li><a href=\"agents/")
                    .append(agent.getAgentUniqueId())
                    .append("/index.html\">")
                    .append(agent.getNom())
                    .append(" ")
                    .append(agent.getPrenom())
                    .append("</a></li>");
        }
        content = appendFileContent(content, "template/index/part3.txt");
        // Print file
        out.println(content);
    }

    private StringBuilder appendFileContent(StringBuilder content, String fileResourcePath) {
        try {
            return content.append(Files.readString(Path.of(fileResourcePath)));
        } catch (IOException e) {
            e.printStackTrace();
            return new StringBuilder(); // est-ce qu'on veut renvoyer un new StringBuilder en cas d'erreur ?
        }
    }

    public void buildAgents(List<Agent> agents) {
        for (Agent agent : agents) {
            File agentDir = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId()).toFile();
            agentDir.mkdirs();
        }
    }
}
