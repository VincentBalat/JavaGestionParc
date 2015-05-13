/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

/**
 * Cette classe hérite de la classe equipement, et rajoute les méthodes 
 * et attributs spécifiques (Ram, processeur, Disque dur...)
 * @author VincentBalat
 */
public class Ordinateur extends Equipement{

    /**
     *
     */
    public String processeur;

    /**
     *
     */
    public int DD;

    /**
     *
     */
    public int RAM;

    /**
     *
     * @param nom
     * @param marque
     * @param modele
     * @param SN
     * @param actif
     * @param processeur
     * @param DD
     * @param RAM
     */
    public Ordinateur(String nom, String marque, String modele, String SN, boolean actif, String processeur, int DD, int RAM){
        super(nom, marque, modele, SN, actif);
        this.processeur = processeur;
        this.DD = DD;
        this.RAM = RAM;
    }

    /**
     *
     * @return
     */
    public String getProcesseur() {
        return processeur;
    }

    /**
     *
     * @param processeur
     */
    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    /**
     *
     * @return
     */
    public int getDD() {
        return DD;
    }

    /**
     *
     * @param DD
     */
    public void setDD(int DD) {
        this.DD = DD;
    }

    /**
     *
     * @return
     */
    public int getRAM() {
        return RAM;
    }

    /**
     *
     * @param RAM
     */
    public void setRAM(int RAM) {
        this.RAM = RAM;
    }
}
