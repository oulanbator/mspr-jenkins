package epsi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import epsi.model.Agent;

import static epsi.utils.Constants.*;

public class DataParser {

    /**
     * Contruit une Map (key: value) du matériel à partir du fichier "materials.txt";
     *  
     * @return la Map du matériel
     */
    public Map<String, String> getMaterials() {
        String content = FileReader.getAsStringFromRelativePath(MATERIALS_FILE);
        
        Map<String, String> materialsMap = new HashMap<>();
        String[] lines = content.split("\n");
        
        // Pour chaque ligne...
        for (String line: lines) {
            // Récupère l'index du premier espace dans la ligne
            int blankIndex = line.indexOf(' ');
            // Contruit une entrée dans materialsMap
            String key = line.substring(0,blankIndex);
            String value = line.substring(blankIndex);
            materialsMap.put(key, value);
        }
        return materialsMap;
    }

    /**
     * Construit une liste avec le nom des agents à partir du fichier "staff.txt"
     * 
     * @return
     */
    public List<String> getStaffList() {
        String content = FileReader.getAsStringFromRelativePath(STAFF_FILE);
        
        List<String> staff = new ArrayList<>();
        String[] lines = content.split("\n");
        
        for (String line: lines) {
            staff.add(line);
        }
        return staff;
    }

    /**
     * Construit une liste d'Agents à partir des agents récupérés dans getStaffList()
     * 
     * @return la liste des agents
     */
    public List<Agent> getAgents() {
        List<String> staff = getStaffList();
        Map<String, String> materialMap = getMaterials();

        List<Agent> agents = new ArrayList<>();
        for (String name : staff) {
            Agent agent = buildAgent(name, materialMap);
            agents.add(agent);
        }
        return agents;
    }

    /**
     * Construit un objet Agent à partir du nom de l'agent et des fichiers individuels.
     * Se sert de materialMap pour contruire la liste du materiel emprunté.
     * 
     * @param name nom de l'agent
     * @param materialMap map avec la liste de tout le matériel de l'agence
     * @return un Agent
     */
    private Agent buildAgent(String name, Map<String, String> materialMap) {
        String filePath = REPO_PATH + name + ".txt";
        // Récupère le fichier individuel de l'agent
        String content = FileReader.getAsStringFromRelativePath(filePath);
        String[] lines = content.split("\n");

        // Parse les informations de l'agent
        String nom = lines[0];
        String prenom = lines[1];
        String profession = lines[2];
        String mdp = lines[3];
        // Construit la liste de matériel emprunté
        List<String> usedMaterial = new ArrayList<>();
        for (String material : materialMap.keySet()){
            if(content.contains(material)){
                String value = materialMap.get(material);
                usedMaterial.add(value);
            }
        }
        return new Agent(nom, prenom, profession, mdp, usedMaterial);
    }
}
