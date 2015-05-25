package net.stri.batm.gestionparc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    
    private Salle salle;
    private String nom;
    private String marque;
    private String modele;
    private String SN;
    private boolean actif;
    private String type;
    private ArrayList<Interface> interfaces;

    public Equipement(Salle salle, String nom, String marque, String modele, String SN, boolean actif, String type) {
        this.salle = salle;
        this.nom = nom;
        this.marque = marque;
        this.modele = modele;
        this.SN = SN;
        this.type = type;
        this.actif = actif;
        interfaces = new ArrayList<Interface>();
    }
    
    /**
    * La méthode importe depuis la base de données les interfaces de l'équipement.
    * Elles seront placées dans l'arrayList interfaces.
    * @author GasparMeyerfeld
    */
    
    public void importInterfaces() throws SQLException, ClassNotFoundException{
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/gestionpark";
        String user = "postgres";
        String passwd = "postgres";
         
        Connection conn = DriverManager.getConnection(url, user, passwd);
         
        //Création d'un objet Statement
        Statement state = conn.createStatement();
                
        ResultSet element = state.executeQuery("SELECT * FROM Interfaces WHERE sn = "+SN+";");
        
        int i = 0;
        interfaces.clear();
        
        while(element.next()) {
            
            Interface in = new Interface(this,(String)element.getString("mac"), (String)element.getString("ip"), (String)element.getString("nomint"), (int)element.getInt("vitesse"));
            interfaces.add(i,in);
            i++;
            
        }
        
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

    public Salle getSalle() {
        return salle;
    }

    public String getType() {
        return type;
    }

    public ArrayList<Interface> getInterfaces() {
        return interfaces;
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
    
    public void addInterface(Interface inter) {
		interfaces.add(inter);
	}
      
    public void removeInterface(Interface inter) {
		if (!interfaces.contains(inter)) {
			System.out.println("This interface is not in this equipment...");
		} else {
			interfaces.remove(inter);
		}
	}
}
