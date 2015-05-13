package net.stri.batm.gestionparc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Superclasse pour tous les équipements 
 * @author VincentBalat
 */
public class Equipement {
    private String nom;

    public Equipement(String nom, String marque, String modele, String SN, boolean actif) {
        this.nom = nom;
        this.marque = marque;
        this.modele = modele;
        this.SN = SN;
        this.actif = actif;
    }
    
    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getMarque() {
        return marque;
    }

    /**
     *
     * @param marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     *
     * @return
     */
    public String getModele() {
        return modele;
    }

    /**
     *
     * @param modele
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     *
     * @return
     */
    public String getSN() {
        return SN;
    }

    /**
     *
     * @param SN
     */
    public void setSN(String SN) {
        this.SN = SN;
    }

    /**
     *
     * @return
     */
    public boolean isActif() {
        return actif;
    }

    /**
     *
     * @param actif
     */
    public void setActif(boolean actif) {
        this.actif = actif;
    }
    private String marque;
    private String modele;
    private String SN;
    private boolean actif;
    
    
    
      
    
}
