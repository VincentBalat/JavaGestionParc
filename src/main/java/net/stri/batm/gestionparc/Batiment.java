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
public class Batiment {

	private int id;
	private String name;
	private int num;
	private ArrayList<Salle> salles;

    /**
     *
     * @param id
     * @param name
     * @param num
     */
    public Batiment(int id, String name, int num) {
		this.id = id;
		this.name = name;
		this.num = num;
		salles = new ArrayList<Salle>();
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
    public int getId() {
		return id;
	}

    /**
     *
     * @return
     */
    public ArrayList<Salle> getSalles() {
		return salles;
	}

    /**
     *
     * @param salle
     */
    public void addSalle(Salle salle) {
		salles.add(salle);
	}

    /**
     *
     * @param salle
     */
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
