/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;

/**
 * Cette classe hérite de la classe equipement, et rajoute les méthodes 
 * et attributs spécifiques à un ordinateur ou serveur (Ram, processeur, Disque dur...)
 * @author VincentBalat
 */
public class Ordinateur extends Equipement{
 
    /**
     *Nom du processeur
     */
    public String processeur;

    /**
     *Capacité du disque dur (en Go)
     */
    public int DD;

    /**
     *Capacité de la RAM (en Go)
     */
    public int RAM;

    /**
     * L'ordinateur est un équipement spécifique auquel on rajoute des attributs. 
     * Il caractérisé par la salle où il se trouve, son nom, sa marque, 
     * son modele, son numéro de série, son processeur, sa capacité de disque dur et de RAM. De plus il possède
     * un attribut boolean afin de savoir son état actif/passif.
     * @param salle
     * @param nom
     * @param marque
     * @param modele
     * @param SN
     * @param actif
     * @param processeur
     * @param DD
     * @param RAM
     */
    public Ordinateur(Salle salle, String nom, String marque, String modele, String SN, boolean actif, String processeur, int DD, int RAM){
        super(salle, nom, marque, modele, SN, actif, "Ordinateur");
        this.processeur = processeur;
        this.DD = DD;
        this.RAM = RAM;
    }

    /**
     * Retourne le nom du processeur
     * @return processeur
     */
    public String getProcesseur() {
        return processeur;
    }

    /**
     * Permet de modifier le nom du processeur.
     * @param processeur
     */
    public void setProcesseur(String processeur) {
        this.processeur = processeur;
    }

    /**
     * Retourne la capacité du disque dur en Go
     * @return DD
     */
    public int getDD() {
        return DD;
    }

    /**
     * Permet de paramétrer la capacité du disque dur en Go
     * @param DD
     */
    public void setDD(int DD) {
        this.DD = DD;
    }

    /**
     * Retourne la capacité de la RAM en Go
     * @return RAM
     */
    public int getRAM() {
        return RAM;
    }

    /**
     * Permet de modifier la capacité de la RAM en Go
     * @param RAM
     */
    public void setRAM(int RAM) {
        this.RAM = RAM;
    }
}
