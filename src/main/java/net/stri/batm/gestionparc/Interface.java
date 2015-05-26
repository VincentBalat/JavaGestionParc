/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;

/**
 * Cette classe définit les interfaces réseaux présentes sur les différents équipements.
 * Ces interfaces sont ensuite stockées dans des arrayList des instances de Ordinateur ou d'Equipement
 * @author VincentBalat
 */
public class Interface {
    private Object Equipement;
    private String Nom;
    private String IP;
    private String MAC;
    private int Vitesse;
        
    /**
     * Constructeur.
     * Une interface est définie par l'équipement auquel elle appartient, ainsi que par son adresse MAC, 
     * son adresse IP, son nom et sa vitesse.
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
     * Retourne le nom de l'equipement sur lequel est connecté l'interface
     * @return equipement
     */
    public Object getEquipement() {
        return Equipement;
    }

    
    /**
     * Retourne le nom de l'interface
     * @return nom
     */
    public String getNom() {
        return Nom;
    }

    /**
     * Permet de modifier le nom de l'interface
     * @param Nom
     */
    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    /**
     * Retourne l'adresse IP associée à l'interface
     * @return IP
     */
    public String getIP() {
        return IP;
    }

    /**
     * Permet de modifier l'adresse IP de l'interface
     * @param IP
     */
    public void setIP(String IP) {
        this.IP = IP;
    }

    /**
     * Retourne l'adresse MAC associée à l'interface
     * @return MAC
     */
    public String getMAC() {
        return MAC;
    }

    /**
     * Permet de modifier l'adresse MAC de l'interface
     * @param MAC
     */
    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    /**
     * Retourne la vitesse réseau maximale de l'interface
     * @return Vitesse
     */
    public int getVitesse() {
        return Vitesse;
    }

    /**
     * Permet de modifier la vitesse réseau de l'interface
     * @param Vitesse
     */
    public void setVitesse(int Vitesse) {
        this.Vitesse = Vitesse;
    }
    
    
}
