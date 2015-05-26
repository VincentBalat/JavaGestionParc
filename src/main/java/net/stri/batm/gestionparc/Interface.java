/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;

/**
 *
 * @author VincentBalat
 */
public class Interface {
    private Object Equipement;
    private String Nom;
    private String IP;
    private String MAC;
    private int Vitesse;
        
    /**
     *
     * @param Equipement
     * @param MAC
     * @param IP
     * @param Nom
     * @param Vitesse
     */
    public Interface(Object Equipement, String MAC, String IP, String Nom, int Vitesse){
        this.Equipement = Equipement;
        this.Nom = Nom;
        this.IP = IP;
        this.MAC = MAC;
        this.Vitesse = Vitesse;
    }

    /**
     *
     * @return
     */
    public Object getEquipement() {
        return Equipement;
    }

    
    /**
     *
     * @return
     */
    public String getNom() {
        return Nom;
    }

    /**
     *
     * @param Nom
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     *
     * @return
     */
    public String getIP() {
        return IP;
    }

    /**
     *
     * @param IP
     */
    public void setIP(String IP) {
        this.IP = IP;
    }

    /**
     *
     * @return
     */
    public String getMAC() {
        return MAC;
    }

    /**
     *
     * @param MAC
     */
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    /**
     *
     * @return
     */
    public int getVitesse() {
        return Vitesse;
    }

    /**
     *
     * @param Vitesse
     */
    public void setVitesse(int Vitesse) {
        this.Vitesse = Vitesse;
    }
    
    
}
