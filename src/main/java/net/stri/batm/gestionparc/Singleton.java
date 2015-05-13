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
/**
 * This class generates single ids. The ids are get by calling the getId method
 * 
 * @see Project
 */
public class Singleton {

	private static Singleton INSTANCE;
	private int compteur;

	private Singleton() {
		compteur = 0;
	}

	/**
	 * This method generate a single id
	 * 
	 * @return Returns the value of the counter, then increase it
	 */
	public static int getId() {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		return INSTANCE.compteur++;
	}

	/**
	 * Sets the offset of the Singleton, if the offset is lower to the counter
	 * value, it changes nothing
	 * 
	 * @param off
	 *            The values of the offset
	 */
	public static void setOffset(int off) {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		if (INSTANCE.compteur < off) {
			INSTANCE.compteur = off + 1;
		}
	}

	/**
	 * Returns the value of the counter
	 * 
	 * @return The current value of the counter
	 */
	public static int getCurrent() {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		return INSTANCE.compteur;
	}

	/**
	 * Resets the counter
	 * 
	 */
	public static void reset() {
		if (INSTANCE == null) {
			INSTANCE = new Singleton();
		}
		INSTANCE.compteur = 0;
	}
}
