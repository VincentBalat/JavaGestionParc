/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author Gaspar
 */
public class Connect {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
    try {
      Class.forName("org.postgresql.Driver");
         
      String url = "jdbc:postgresql://localhost:5432";
      String user = "postgres";
      String passwd = "postgres";
         
      Connection conn = DriverManager.getConnection(url, user, passwd);
         
      //Création d'un objet Statement
      Statement state = conn.createStatement();
      //L'objet ResultSet contient le résultat de la requête SQL
      ResultSet db = state.executeQuery("CREATE DATABASE Gestionpark\n" +
"  WITH OWNER = postgres\n" +
"       ENCODING = 'UTF8'\n" +
"       TABLESPACE = pg_default\n" +
"       LC_COLLATE = 'French_France.1252'\n" +
"       LC_CTYPE = 'French_France.1252'\n" +
"       CONNECTION LIMIT = -1;");
      //On récupère les MetaData
      ResultSetMetaData dbMeta = db.getMetaData();

      db.close();
      
      ResultSet batiments = state.executeQuery("CREATE TABLE Batiments\n" +
"       (IdBatiment INT CONSTRAINT PK_IdBatiment PRIMARY KEY,\n" +
"        NomB   VARCHAR(255),\n" +
"        NumB   INT);\n");
      
      //On récupère les MetaData
      ResultSetMetaData batimentsMeta = batiments.getMetaData();
      
      ResultSet salles = state.executeQuery("CREATE TABLE Salles\n" +
"       (IdSalle INT CONSTRAINT PK_IdSalle PRIMARY KEY,\n" +
"        NomS   VARCHAR(255),\n" +
"        NumS   INT,\n" +
"        IdBat  INT CONSTRAINT FK_IdBat REFERENCES Batiments(IdBatiment));\n");
      
      //On récupère les MetaData
      ResultSetMetaData sallesMeta = salles.getMetaData();
      
      salles.close();
      
      ResultSet equipements = state.executeQuery("CREATE TABLE Equipements\n" +
"       (IdEquipement INT CONSTRAINT PK_IdEquipement PRIMARY KEY,\n" +
"        Marque     VARCHAR(255),\n" +
"        NomEq      VARCHAR(255),\n" +
"        NumEq      INT\n" +
"        Type       VARCHAR(255),\n" +
"        Active     BOOLEAN,\n" +
"        Processeur VARCHAR(255),\n" +
"        RAM        INT,\n" +
"        Disque     INT,\n" +
"        IdSalle    INT CONSTRAINT FK_IdSalle REFERENCES Salles(IdSalle));\n");
      
      //On récupère les MetaData
      ResultSetMetaData equipementsMeta = equipements.getMetaData();
      
      equipements.close();
      
      ResultSet interfaces = state.executeQuery("CREATE TABLE Interfaces\n" +
"       (Mac VARCHAR(255) CONSTRAINT PK_Mac PRIMARY KEY,\n" +
"        Ip     VARCHAR(255),\n" +
"        NomInt      VARCHAR(255),\n" +
"        Vitesse      INT\n" +
"        IdEq    INT CONSTRAINT FK_IdEq REFERENCES Equipements(IdEquipement));\n");
      
      //On récupère les MetaData
      ResultSetMetaData interfaceMeta = equipements.getMetaData();
      
      state.close();
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}
