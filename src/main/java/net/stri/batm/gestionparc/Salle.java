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
import java.sql.ResultSet;
import java.sql.SQLException;
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
        private ArrayList<Ordinateur> ordinateurs;

    /**
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
                ordinateurs = new ArrayList<Ordinateur>();
	}
    
    public void majeq() throws SQLException{
        
        ResultSet element;
        
        BD bd = new BD();
        
        int i = 0;
        
        element = bd.selectRs("SELECT * FROM Equipements WHERE idsalle = "+id+";");
        
        while(element.next()) {
            
            if("ordinateur".equals((String)element.getString("type"))){
                Ordinateur o = new Ordinateur((String)element.getString("nomeq"), (String)element.getString("marque"), (String)element.getString("modele"),(String)element.getString("SN"), (boolean)element.getBoolean("active"), (String)element.getString("Processeur"), (int)element.getInt("disque"), (int)element.getInt("ram"));
                ordinateurs.add(i,o);
            } else {
                Equipement e = new Equipement((String)element.getString("nomeq"), (String)element.getString("marque"), (String)element.getString("modele"), (String)element.getString("SN"), (boolean)element.getBoolean("active"), (String)element.getString("type"));
                equipements.add(i,e);
            }
            i++;
           
        }
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
}

