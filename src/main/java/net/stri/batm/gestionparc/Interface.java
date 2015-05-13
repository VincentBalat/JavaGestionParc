/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

/**
 *
 * @author VincentBalat
 */
public class Interface {
    private String Nom;

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
    public String getVitesse() {
        return Vitesse;
    }

    /**
     *
     * @param Vitesse
     */
    public void setVitesse(String Vitesse) {
        this.Vitesse = Vitesse;
    }
    private String IP;
    private String MAC;
    private String Vitesse;
    
}
