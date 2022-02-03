// package epsi.template;

// import epsi.model.Agent;
// import epsi.services.FileReader;

// public class AgentPageBuilder {
//     private static final String PART1 = "template/agent/part1.txt";
//     private static final String PART3 = "template/agent/part3.txt";
//     private static final String PART4 = "template/agent/part4.txt";
//     private static final String PART6 = "template/agent/part6.txt";

//     public String build(Agent agent) {
//         String path = "src/newSite/agents/" + agent.unique ;

//         File D = new File(path);
//         D.mkdir();

//         System.setOut(new PrintStream(path + "/index.html"));

//         StringBuilder content = new StringBuilder(Files.readString(Path.of("src/template/agent/1.txt")));
//         content.append(agent.prenom).append(" ").append(agent.nom);
//         content.append(Files.readString(Path.of("src/template/agent/3.txt")));

//         for (String key: materialsMap.keySet()){
//             String checked = "";
//             if(agent.materiaux.contains(key)){
//                 checked = "checked";
//             }
//             content.append("<div class=\"form-check\">\n"
//                     + "<input class=\"form-check-input\" type=\"checkbox\" value=\"\" id=\"" + key + "\" "
//                     + checked + ">\n"
//                     + "<label class=\"form-check-label\" for=\"" + key + "\">\n"
//                     + "" + materialsMap.get(key) + "\n"
//                     + "</label>\n"
//                     + "</div>");
//         }

//         content.append(Files.readString(Path.of("src/template/agent/4.txt")));
//         content.append(agent.IDimage);
//         content.append(Files.readString(Path.of("src/template/agent/6.txt")));

//         out.println(content);
//         return null;
//     }

//     private String part1 = FileReader.getAsStringFromResourcesPath(PART1);
//     private String part3 = FileReader.getAsStringFromResourcesPath(PART3);
//     private String part4 = FileReader.getAsStringFromResourcesPath(PART4);
//     private String part6 = FileReader.getAsStringFromResourcesPath(PART6);
// }
