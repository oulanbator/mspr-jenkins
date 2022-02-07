package epsi.services;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import epsi.model.Agent;
import epsi.utils.Md5Hasher;
import epsi.utils.Utils;

import static epsi.utils.Constants.*;
import static epsi.utils.Utils.redirectStdout;
import static java.lang.System.out;

public class Generator {
    private String jarCurrentPath;

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
        // Redirect stdout to index target file
        File target = Paths.get(jarCurrentPath, ROOT, "index.html").toFile();
        redirectStdout(target);

        // build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/index/part1.txt");
        for (Agent agent : agents) {
            content.append("        <li><a href=\"agents/")
                    .append(agent.getAgentUniqueId())
                    .append("/index.html\">")
                    .append(agent.getPrenom())
                    .append(" ")
                    .append(agent.getNom())
                    .append("</a></li>\n");
        }
        content = appendFileContent(content, "template/index/part3.txt");

        // Print file
        out.println(content);
    }

    public void buildFichesAgents(Agent agent, Map<String, String> materialsMap) {
        // Build agent dir
        File agentDir = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId()).toFile();
        agentDir.mkdirs();

        // Redirect stdout to target agent index
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), "index.html").toFile();
        redirectStdout(target);

        // Build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/agent/part1.txt");
        content.append(agent.getPrenom())
                .append(" ")
                .append(agent.getNom());
        content = appendFileContent(content, "template/agent/part3.txt");

        // Loop on material
        for (String key: materialsMap.keySet()){
            String checked = "";
            if(agent.getMateriaux().contains(materialsMap.get(key))){
                checked = "checked";
            }
            content.append("<div class=\"form-check\">\n")
                    .append("<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"" + key + "\" ")
                    .append(checked).append(">\n")
                    .append("<label class=\"form-check-label\" for=\"")
                    .append(key).append("\">\n")
                    .append(materialsMap.get(key)).append("\n")
                    .append("</label>\n")
                    .append("</div>");
        }

        content = appendFileContent(content, "template/agent/part5.txt");
        content.append(agent.getImageId());
        content = appendFileContent(content, "template/agent/part7.txt");

        // Print file
        out.println(content);
    }

    public void buildCss() {
        // Redirect stdout to target css file
        File target = Paths.get(jarCurrentPath, ROOT, CSS, "style.css").toFile();
        redirectStdout(target);

        // Build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/css.txt");

        // Print file
        out.println(content);
    }

    public void buildHtaccess(Agent agent) {
        // Redirect stdout to target css file
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), ".htaccess").toFile();
        redirectStdout(target);

        // Build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/htaccess/part1.txt");
        content.append("AuthUserFile /var/www/html/agents/" + agent.getAgentUniqueId() + "/.htpasswd\n");
        content = appendFileContent(content, "template/htaccess/part3.txt");

        // Print file
        out.println(content);
    }

    public void buildHtpasswd(Agent agent) {
        // Redirect stdout to target css file
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), ".htpasswd").toFile();
        redirectStdout(target);

        // get encoded password
        String encodedPwd = "";
        try {
            encodedPwd = Md5Hasher.getHash(agent.getMdp());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Build content
        StringBuilder content = new StringBuilder();
        content.append(agent.getAgentUniqueId())
                .append(":")
                .append(encodedPwd);

        // Print file
        out.println(content);
    }

    public static StringBuilder appendFileContent(StringBuilder content, String fileResourcePath) {
        String newContent = FileReader.getAsStringFromResourcesPath(fileResourcePath);
        return content.append(newContent);
    }

}
