/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

/**
 *
 * @author Xavier
 */
import java.util.ArrayList;

/**
 *
 * @author Vince
 */
public class Salle {

	private Batiment batiment;
	private int id;
	private String name;
	private int num;
	private int etage;
	private ArrayList<Equipement> equipements;

    /**
     *
     * @param batiment
     * @param id
     * @param name
     * @param num
     * @param etage
     */
    public Salle(Batiment batiment, int id, String name, int num, int etage) {
		this.batiment = batiment;
		this.id = id;
		this.name = name;
		this.num = num;
		this.etage = etage;
		equipements = new ArrayList<Equipement>();
	}

    /**
     *
     * @return
     */
    public String getName() {
		return name;
	}

    /**
     *
     * @param name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     *
     * @return
     */
    public int getNum() {
		return num;
	}

    /**
     *
     * @param num
     */
    public void setNum(int num) {
		this.num = num;
	}

    /**
     *
     * @return
     */
    public Batiment getBatiment() {
		return batiment;
	}

    /**
     *
     * @return
     */
    public int getId() {
		return id;
	}

    /**
     *
     * @return
     */
    public int getEtage() {
		return etage;
	}

    /**
     *
     * @return
     */
    public ArrayList<Equipement> getEquipements() {
		return equipements;
	}

    /**
     *
     * @param equipement
     */
    public void addEquipement(Equipement equipement) {
		equipements.add(equipement);
	}

    /**
     *
     * @param equipement
     */
    public void removeEquipement(Equipement equipement) {
		if (!equipements.contains(equipement)) {
			System.out.println("This equipement is not in this salle...");
		} else {
			equipements.remove(equipement);
		}
	}

	public String toString() {
		return ("Salle " + name + " at floor " + etage + ", id=" + id + ", num="
				+ num + ". Building is " + batiment.getName());
	}
}

