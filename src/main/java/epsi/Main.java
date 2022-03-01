package epsi;
import java.util.List;
import java.util.Map;

import epsi.model.Agent;
import epsi.services.Generator;
import epsi.services.DataParser;
import epsi.utils.Utils;

public class Main {
    public static void main(String[] args) {
        DataParser parser = new DataParser();

        List<Agent> agents = parser.getAgents();
        Map<String, String> materials = parser.getMaterials();

        generateWebFiles(agents, materials);
    }

    private static void generateWebFiles(List<Agent> agents, Map<String, String> materials){
        Generator generator = new Generator();

        // Build app
        generator.buildDirectories();
        generator.buildIndex(agents);
        generator.buildCss();
        generator.build401();
        Utils.copyLogo();

        // Build agents folders (threaded)
        for(Agent agent : agents) {
            Thread thread = new Thread(() -> buildAgentFolder(agent, materials));
            thread.start();
        }
    }

    private static void buildAgentFolder(Agent agent, Map<String, String> materials) {
        Generator generator = new Generator();
        generator.buildFicheAgent(agent, materials);
        generator.buildHtaccess(agent);
        generator.buildHtpasswd(agent);
        Utils.copyAgentImage(agent);
    }

}

