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

public class Salle {

	private Batiment batiment;
	private int id;
	private String name;
	private int num;
	private int etage;
	private ArrayList<Equipement> equipements;

	public Salle(Batiment batiment, int id, String name, int num, int etage) {
		this.batiment = batiment;
		this.id = id;
		this.name = name;
		this.num = num;
		this.etage = etage;
		equipements = new ArrayList<Equipement>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Batiment getBatiment() {
		return batiment;
	}

	public int getId() {
		return id;
	}

	public int getEtage() {
		return etage;
	}

	public ArrayList<Equipement> getEquipements() {
		return equipements;
	}

	public void addEquipement(Equipement equipement) {
		equipements.add(equipement);
	}

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

