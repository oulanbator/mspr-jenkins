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
        Generator app = new Generator();

        List<Agent> agents = parser.getAgents();
        Map<String, String> materials = parser.getMaterials();

        // Build app
        app.buildDirectories();
        app.buildIndex(agents);
        app.buildCss();
        Utils.copyLogo();

        for(Agent agent : agents) {
            app.buildFicheAgent(agent, materials);
            app.buildHtaccess(agent);
            app.buildHtpasswd(agent);
            Utils.copyAgentImage(agent);
        }

    }

}

