package epsi;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import epsi.services.DataParser;
import epsi.utils.Utils;

public class Main {
    public static void main(String[] args) {
        String fileName = "agents/durand.txt";

        System.out.println("\nMATERIALS : ");
        System.out.println(DataParser.getMaterials());

        System.out.println("\nSTAFF : ");
        System.out.println(DataParser.getStaffList());

        System.out.println("\nAGENTS : ");
        DataParser.getAgents().forEach(System.out::println);

        System.out.println("\nCOPY IMAGE : ");
        try {
            File tempFile = File.createTempFile("Durand", ".png");
            Utils.copyResource("agents/Durand.png", tempFile.getAbsolutePath(), Main.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //echo "test.txt:Hello" | (read string; path=$(echo $string | cut -d: -f1) ; echo $string | cut -d: -f2 >> $path)
        //echo "test.txt~Hello" | (read string; path=$(echo $string | cut --delimiter=~ -f1) ; echo $string | cut --delimiter=~ -f2 >> $path)

        // FileReader.print("agents/durand.txt");
        // String file = FileReader.getAsString(fileName);
        // System.out.println(file);

        // Retriever retriever = new Retriever();
        // Map<String, String> materials = retriever.setMaterials();
        // System.out.println(materials);

//        Retriever retriever = new Retriever();
//        retriever.deletePreviousHTML(new File("src/newSite/agents"));
//        try {
//            retriever.setMaterials();
//
//            List<Agent> agents = retriever.tratAllagentes();
//            for(Agent agent : agents){
//                Generator.setFiche(agent, retriever.materialsMap);
//                retriever.uploadIDimage(agent);
//            }
//            Generator.setIndex(agents);
//            Generator.setHtaccess(agents);
//            Generator.setHtpassword(agents);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}

