package epsi.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import epsi.model.Agent;
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
    }


    public void build401(){
        // Redirect stdout to 401 error target file
        File target = Paths.get(jarCurrentPath, ROOT, "401.html").toFile();
        redirectStdout(target);

        //build content
        StringBuilder content = new StringBuilder();
        content.append("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "<meta http-equiv=\"refresh\" content=\"3;url=../../index.html\" />" +
                "    <title>Error 401</title>\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\n" +
                "</head><body>")
                .append("\n\n")
                .append("Désolé mais l'accès à cette section necessite une authentification :(\n")
                .append("Vous allez être redirigé d'ici quelques instant vers la page d'accueil");

        out.println(content);
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

    public void buildFicheAgent(Agent agent, Map<String, String> materialsMap) {
        // Build agent dir
        File agentDir = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId()).toFile();
        agentDir.mkdirs();

        // Build file path to target agent index
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), "index.html").toFile();

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
        try {
            FileWriter fileWriter = new FileWriter(target);
            fileWriter.write(content.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        // Build file path to target htaccess file
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), ".htaccess").toFile();

        // Build content
        StringBuilder content = new StringBuilder();
        content = appendFileContent(content, "template/htaccess/part1.txt");
        content.append("AuthUserFile /var/www/html/agents/" + agent.getAgentUniqueId() + "/.htpasswd\n");
        content = appendFileContent(content, "template/htaccess/part3.txt");

        // Print file
        try {
            FileWriter fileWriter = new FileWriter(target);
            fileWriter.write(content.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void buildHtpasswd(Agent agent) {
        // Build file path to target htaccess file
        File target = Paths.get(jarCurrentPath, ROOT, AGENTS, agent.getAgentUniqueId(), ".htpasswd").toFile();

        // get encoded password
        String encodedPwd = "";
        try {
            encodedPwd = "{SHA}" + Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA1").digest(agent.getMdp().getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Build content
        StringBuilder content = new StringBuilder();
        content.append(agent.getAgentUniqueId())
                .append(":")
                .append(encodedPwd);

        // Print file
        try {
            FileWriter fileWriter = new FileWriter(target);
            fileWriter.write(content.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StringBuilder appendFileContent(StringBuilder content, String fileResourcePath) {
        String newContent = FileReader.getAsStringFromResourcesPath(fileResourcePath);
        return content.append(newContent);
    }

}
