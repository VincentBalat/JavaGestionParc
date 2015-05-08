/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

import java.util.ArrayList;

public class Batiment {

	private int id;
	private String name;
	private int num;
	private ArrayList<Salle> salles;

	public Batiment(int id, String name, int num) {
		this.id = id;
		this.name = name;
		this.num = num;
		salles = new ArrayList<Salle>();
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

	public int getId() {
		return id;
	}

	public ArrayList<Salle> getSalles() {
		return salles;
	}

	public void addSalle(Salle salle) {
		salles.add(salle);
	}

	public void removeSalle(Salle salle) {
		if (!salles.contains(salle)) {
			System.out.println("This salle is not in this batiment...");
		} else {
			salles.remove(salle);
		}
	}

	public String toString() {
		return ("Batiment " + name + ", id=" + id + ", num=" + num);
	}
}
