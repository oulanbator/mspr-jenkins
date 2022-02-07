package epsi;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import epsi.services.FileReader;
import epsi.model.Agent;
import epsi.services.Generator;
import epsi.services.DataParser;
import epsi.utils.Md5Hasher;
import epsi.utils.Utils;
import jdk.jshell.execution.Util;

public class Main {
    public static void main(String[] args) {
        DataParser parser = new DataParser();
        Generator app = new Generator();
        List<Agent> agents = parser.getAgents();

        app.buildDirectories();
        app.buildIndex(agents);
        app.buildCss();
        Utils.copyLogo();
        Map<String, String> materials = parser.getMaterials();
        for(Agent agent : agents) {
            app.buildFichesAgents(agent, materials);
            app.buildHtaccess(agent);
            app.buildHtpasswd(agent);
            Utils.copyAgentImage(agent);
        }

    }

}

