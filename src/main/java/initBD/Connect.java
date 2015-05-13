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
 * Cette classe permet de créer les tables de la base de données.
 * La base de données doit être préalablement créée.
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
         
      String url = "jdbc:postgresql://localhost:5432/gestionpark";
      String user = "postgres";
      String passwd = "postgres";
         
      Connection conn = DriverManager.getConnection(url, user, passwd);
         
      //Création d'un objet Statement
      Statement state = conn.createStatement();

      state.execute("DROP TABLE if exists Interfaces ;");
      state.execute("DROP TABLE if exists Equipements ;");
      state.execute("DROP TABLE if exists Salles ;");
      state.execute("DROP TABLE if exists Batiments ;");
      state.close();
      state = conn.createStatement();
     /* ResultSet batiments = */
      state.execute("CREATE TABLE Batiments\n" +
"       (IdBatiment     INT CONSTRAINT PK_IdBatiment PRIMARY KEY,\n" +
"        NomB           VARCHAR(255),\n" +
"        NumB           INT);\n");

      state = conn.createStatement();
      state.execute("CREATE TABLE Salles\n" +
"       (IdSalle    INT CONSTRAINT PK_IdSalle PRIMARY KEY,\n" +
"        NomS       VARCHAR(255),\n" +
"        NumS       INT,\n" +
"        IdBat      INT CONSTRAINT FK_IdBat REFERENCES Batiments(IdBatiment));\n");
      
      state.execute("CREATE TABLE Equipements\n" +
"       (IdEquipement INT CONSTRAINT PK_IdEquipement PRIMARY KEY,\n" +
"        Marque     VARCHAR(255),\n" +
"        NomEq      VARCHAR(255),\n" +
"        NumEq      INT,\n" +
"        Type       VARCHAR(255),\n" +
"        Active     BOOLEAN,\n" +
"        Processeur VARCHAR(255),\n" +
"        RAM        INT,\n" +
"        Disque     INT,\n" +
"        IdSalle    INT CONSTRAINT FK_IdSalle REFERENCES Salles(IdSalle));\n");
      
      state.execute("CREATE TABLE Interfaces\n" +
"       (Mac VARCHAR(255) CONSTRAINT PK_Mac PRIMARY KEY,\n" +
"        Ip         VARCHAR(255),\n" +
"        NomInt     VARCHAR(255),\n" +
"        Vitesse    INT,\n" +
"        IdEq       INT CONSTRAINT FK_IdEq REFERENCES Equipements(IdEquipement));\n");

      state.close();
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}
