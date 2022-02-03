package epsi;
import java.util.List;

import epsi.services.FileReader;
import epsi.model.Agent;
import epsi.services.AppBuilder;
import epsi.services.DataParser;
import epsi.utils.Utils;

public class Main {
    public static void main(String[] args) {
        DataParser parser = new DataParser();
        AppBuilder app = new AppBuilder();

        FileReader.printFromAbsolutePath(Utils.getJarAbsolutePath() + "/material.txt");

        System.out.println("\nMATERIALS : ");
        System.out.println(parser.getMaterials());

        System.out.println("\nSTAFF : ");
        System.out.println(parser.getStaffList());

        System.out.println("\nAGENTS : ");
        List<Agent> agents = parser.getAgents();
        agents.forEach(System.out::println);

        Utils.copyAgentImage(agents.get(0));

        app.buildDirectories(agents);

        // COPIE D'IMAGE TEST
//         Path currentPath = Paths.get("").toAbsolutePath(); // current user path (~) in bash
//         System.out.println("Current Path : " + currentPath.toString());
//         Path filePath = Paths.get(currentPath.toString(), "target", "test.png");
//         Path targetPath = Paths.get(currentPath.toString(), "test2.png");
//         System.out.println("File Path : " + filePath.toString());

//         System.out.println("\nCOPY IMAGE : ");
//         boolean copied = false;
//         try {
//             File sourceFile = new File(filePath.toUri());
//             copied = sourceFile.renameTo(new File(targetPath.toUri()));
// //            File tempFile = File.createTempFile("Durand", ".png");
// //            Utils.copyResource(Paths.get(sourceFile), sourceFile.getAbsolutePath(), Main.class);
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         System.out.println(copied);


        // COMMANDES SHELL : POUR REDIRIGER LA SORTIE STANDARD
        //echo "test.txt:Hello" | (read string; path=$(echo $string | cut -d: -f1) ; echo $string | cut -d: -f2 >> $path)
        //echo "test.txt~Hello" | (read string; path=$(echo $string | cut --delimiter=~ -f1) ; echo $string | cut --delimiter=~ -f2 >> $path)

        // DEBUG : Print durand.txt
        // FileReader.print("agents/durand.txt");
        // String file = FileReader.getAsString(fileName);
        // System.out.println(file);

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

