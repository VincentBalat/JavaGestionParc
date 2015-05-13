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

public class Controller {
	private final ArrayList<Batiment> batiments;

	public Controller() {
		batiments = new ArrayList<>();
		example();
	}

	public void printBatiments() {
		for (Batiment batiment : batiments) {
			System.out.println(batiment.toString());
		}
	}

	public void printSalles() {
		for (Batiment batiment : batiments) {
			for (Salle salle : batiment.getSalles()) {
				System.out.println(salle.toString());
			}
		}
	}

	public void addBatiment(int id, String name, int num) {
		Batiment batiment = new Batiment(id, name, num);
		batiments.add(batiment);
	}

	public void addSalle(Batiment batiment, int id, String name, int num,
			int etage) {
		Salle salle = new Salle(batiment, id, name, num, etage);
		batiment.addSalle(salle);
	}

	public void removeBatiment(int id) {
		Batiment bat = null;
		for(Batiment batiment:batiments){
			if (batiment.getId()==id){
				bat = batiment;
				break;
			}
		}
		if(bat!=null){
			batiments.remove(bat);
		}
	}

	public void removeSalle(int id) {
		ArrayList<Salle> salles = listAllSalles();
		Salle sal = null;
		for(Salle salle:salles){
			if (salle.getId()==id){
				sal = salle;
				break;
			}
		}
		if(sal!=null){
			sal.getBatiment().removeSalle(sal);
		}
	}

	private ArrayList<Salle> listAllSalles() {
		ArrayList<Salle> salles = new ArrayList<>();
		for (Batiment batiment : batiments) {
			for (Salle salle : batiment.getSalles()) {
				salles.add(salle);
			}
		}
		return salles;
	}

	// pour faire les tests
	private void example() {
		addBatiment(1, "Bat 1", 1);
		addBatiment(2, "Bat 2", 2);
		addBatiment(3, "Bat 3", 3);
		addSalle(batiments.get(0), 1, "Salle 1", 1, 0);
		addSalle(batiments.get(0), 2, "Salle 2", 2, 0);
		addSalle(batiments.get(0), 3, "Salle 3", 3, 0);
		addSalle(batiments.get(1), 4, "Salle 1", 1, 0);
		addSalle(batiments.get(1), 5, "Salle 2", 2, 0);
		addSalle(batiments.get(1), 6, "Salle 3", 3, 0);
		addSalle(batiments.get(2), 7, "Salle 1", 1, 0);
		addSalle(batiments.get(2), 8, "Salle 2", 2, 0);
		addSalle(batiments.get(2), 9, "Salle 3", 3, 0);
		System.out.println("Liste des batiments");
		printBatiments();
		System.out.println("Liste des salles");
		printSalles();
		
		removeSalle(8);
		removeSalle(7);
		removeBatiment(1);
		System.out.println("Liste des batiments");
		printBatiments();
		System.out.println("Liste des salles");
		printSalles();
	}
}
