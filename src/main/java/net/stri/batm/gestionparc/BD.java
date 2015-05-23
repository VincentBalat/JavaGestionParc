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
    private ArrayList<Salle> salles;
    private ArrayList<Equipement> equipements;
    private ArrayList<Ordinateur> ordinateurs;
    private ArrayList<Interface> interfaces;
    
    /**
     * La méthode initialisation permet la connexion à la base de données.
     * Elle est utilisée seulement par les methodes d'accès ou d'insertion de la classe.
     * @return Le Statement retourné est utilisé pour la connexion
     */
    
    public BD(){
        batiments = new ArrayList<Batiment>();
        salles = new ArrayList<Salle>(); 
        equipements = new ArrayList<Equipement>(); 
        ordinateurs = new ArrayList<Ordinateur>(); 
        interfaces = new ArrayList<Interface>(); 
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
    
    public ResultSet selectRs(String query) {
        try{
            
            Statement init = this.initialisation();
            //L'objet ResultSet contient le résultat de la requête SQL
            ResultSet result = init.executeQuery(query);
            
           init.close();
           
           return result;
         
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
    
    public void majbat() throws SQLException {
        
        ResultSet element;
        
        BD bd = new BD();
        
        int i = 0;
        
        element = bd.selectRs("SELECT * FROM batiments;");
        
        while(element.next()) {
            Batiment b = new Batiment((int)element.getInt("idbatiment"), (String)element.getString("nomb"), (int)element.getInt("numb"));
            batiments.add(i,b);
            i++;
        }
    }
    
    public void majsalle() throws SQLException{
        
        ResultSet element;
        
        BD bd = new BD();
        
        int i = 0;
        
        element = bd.selectRs("SELECT * FROM Salle;");
        
        while(element.next()) {
            Salle s = new Salle(null, (int)element.getInt("idsalle"), (String)element.getString("noms"), (int)element.getInt("nums"), (int)element.getInt("etages"));
            salles.add(i,s);
            i++;
        }
    }
        
    public void majeq() throws SQLException{
        
        ResultSet element;
        
        BD bd = new BD();
        
        int i = 0;
        
        element = bd.selectRs("SELECT * FROM Equipements;");
        
        while(element.next()) {
            
            if("Ordinateur".equals((String)element.getString("type"))){
                Ordinateur o = new Ordinateur((String)element.getString("nomeq"), (String)element.getString("marque"), (String)element.getString("modele"),(String)element.getString("SN"), (boolean)element.getBoolean("active"), (String)element.getString("Processeur"), (int)element.getInt("disque"), (int)element.getInt("ram"));
                ordinateurs.add(i,o);
            } else {
                Equipement e = new Equipement((String)element.getString("nomeq"), (String)element.getString("marque"), (String)element.getString("modele"), (String)element.getString("SN"), (boolean)element.getBoolean("active"), (String)element.getString("type"));
                equipements.add(i,e);
            }
            i++;
           
        }
    }
    
    public void majint() throws SQLException{
        
        ResultSet element;
        
        BD bd = new BD();
        
        int i = 0;
        
        element = bd.selectRs("SELECT * FROM Interfaces;");
        
        while(element.next()) {
            
            Interface in = new Interface((String)element.getString("mac"), (String)element.getString("ip"), (String)element.getString("nomint"), (int)element.getInt("vitesse"));
            interfaces.add(i,in);
            i++;
            
        }
        
    }

}
