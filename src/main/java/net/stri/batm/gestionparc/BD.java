/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import net.stri.batm.gestionparc.Batiment;
import net.stri.batm.gestionparc.Salle;
import net.stri.batm.gestionparc.Equipement;
import net.stri.batm.gestionparc.Interface;

/**
 * La classe BD permet la connexion et l'accès à la base de données
 * @author Gaspar
 */
public class BD {
    
    private ArrayList<Batiment> batiments;
   /*private ArrayList<Salle> salles;
    private ArrayList<Equipement> equipements;
    private ArrayList<Ordinateur> ordinateurs;
    private ArrayList<Interface> interfaces;*/
    
    /**
     * La méthode initialisation permet la connexion à la base de données.
     * Elle est utilisée seulement par les methodes d'accès ou d'insertion de la classe.
     * @return Le Statement retourné est utilisé pour la connexion
     */
    
    public BD(){
        batiments = new ArrayList<Batiment>();
    }
    
    private Statement initialisation(){
      
        try {
          Class.forName("org.postgresql.Driver");
          String url = "jdbc:postgresql://localhost:5432/gestionpark";
          String user = "postgres";
          String passwd = "postgres";
         
          Connection conn = DriverManager.getConnection(url, user, passwd);
         
          //Création d'un objet Statement
          Statement state = conn.createStatement();
          return state;
      } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public HashMap<String, Object> select(String query) {
        try{
            
            Statement init = this.initialisation();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = init.executeQuery(query);
            //On récupère les MetaData
            ResultSetMetaData resultMeta = result.getMetaData();
            
            HashMap<String, Object> retour = new HashMap<String, Object>();

            //On insère les MetaData dans le hashtable retour
                
            while(result.next()){         
                for(int i = 1; i <= resultMeta.getColumnCount(); i++)
                retour.put(resultMeta.getColumnName(i), result.getObject(i));

            }

            result.close();
            init.close();
            
            return retour;
         
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    
    
    public void majbat() {
        
        HashMap<String, Object> element = new HashMap<String, Object>();
        
        boolean fin = false;
        
        int i = 0;
                
        do {
            element = select("SELECT * FROM Batiments WHERE idbatiment = ".concat(Integer.toString(i)));
            
            Batiment b = new Batiment((int)element.get("idbatiment"), (String)element.get("nomb"), (int)element.get("numb"));
            batiments.add(i,b);
            i++;
            if(element == null)
                fin = true;
        }while(!fin);
    }
        
    public void majeq(){
        
        HashMap<String, Object> element = new HashMap<String, Object>();
        
        boolean fin = false;
        
        int i = 0;
        
        do {
            element = select("SELECT * FROM Equipements WHERE idequipement = ".concat(Integer.toString(i)));
            
            if((String)element.get("type") == "Ordinateur"){
                Ordinateur o = new Ordinateur((String)element.get("nomeq"), (String)element.get("marque"), (String)element.get("modele"),(String)element.get("SN"), (boolean)element.get("active"), (String)element.get("Processeur"), (int)element.get("disque"), (int)element.get("ram"));
                ordinateurs.add(i,o);
            } else {
                Equipement e = new Equipement((String)element.get("nomeq"), (String)element.get("marque"), (String)element.get("modele"), (String)element.get("SN"), (boolean)element.get("active")/*, (String)element.get("type")*/);
                equipements.add(i,e);
            }
            i++;
            if(element == null)
                fin = true;
        }while(!fin);
    }
    
    public void majint(){
        
        do {
            element = select("SELECT * FROM Interfaces WHERE idint = ".concat(Integer.toString(i)));
            
            Salle e = new Salle((int)element.get('idint'),(char)element.get("mac"), (char)element.get("ip"), (char)element.get("nomint"), (int)element.get("idbat"));
            equipements.add(i,b);
            i++;
            if(equipements == null)
                fin = true;
        }while(!fin);
        
    }

}
