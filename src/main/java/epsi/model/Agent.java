package epsi.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Agent {
    private String nom;
    private String prenom;
    private String profession;
    private String mdp;
    private String agentUniqueId;
    private String imageId;
    private List<String> materiaux;

    public Agent(String nom, String prenom, String profession, String mdp, List<String> materiaux) {
        this.profession = profession;
        this.mdp = mdp;
        this.materiaux = materiaux;
        this.prenom = StringUtils.capitalize(prenom.toLowerCase());
        this.nom = StringUtils.capitalize(nom.toLowerCase());
        this.agentUniqueId = this.prenom.substring(0,1).toLowerCase() + this.nom ;
        this.imageId = this.agentUniqueId + ".png";
    }

    // GETTERS & SETTERS

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getAgentUniqueId() {
        return agentUniqueId;
    }

    public void setAgentUniqueId(String agentUniqueId) {
        this.agentUniqueId = agentUniqueId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<String> getMateriaux() {
        return materiaux;
    }

    public void setMateriaux(List<String> materiaux) {
        this.materiaux = materiaux;
    }

    @Override
    public String toString() {
        return "Agent [agentUniqueId=" + agentUniqueId + ", imageId=" + imageId + ", materiaux=" + materiaux + ", mdp="
                + mdp + ", nom=" + nom + ", prenom=" + prenom + ", profession=" + profession + "]";
    }

    
}
