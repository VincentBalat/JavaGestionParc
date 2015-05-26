/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package initBD;

import java.sql.Connection;
import java.sql.DriverManager;
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

      state.execute("DROP TABLE if exists interfaces ;");
      state.execute("DROP TABLE if exists equipements ;");
      state.execute("DROP TABLE if exists salles ;");
      state.execute("DROP TABLE if exists batiments ;");
      state.execute("DROP TABLE if exists pk ;");
      state.close();
      state = conn.createStatement();
     /* ResultSet batiments = */
      state.execute("CREATE TABLE batiments\n" +
"       (idbatiment     INT CONSTRAINT PK_IdBatiment PRIMARY KEY,\n" +
"        nomb           VARCHAR(255),\n" +
"        numb           INT);\n");

      state = conn.createStatement();
      state.execute("CREATE TABLE salles\n" +
"       (idsalle    INT CONSTRAINT PK_IdSalle PRIMARY KEY,\n" + //
"        noms       VARCHAR(255),\n" +
"        nums       INT,\n" +
"        etage      INT,\n" +
"        idbat      INT CONSTRAINT FK_IdBat REFERENCES batiments(idbatiment));\n");
      
      state.execute("CREATE TABLE equipements\n" +
"       (sn         VARCHAR(255) CONSTRAINT PK_IdEquipement PRIMARY KEY,\n" +
"        nomeq      VARCHAR(255),\n" +
"        marque     VARCHAR(255),\n" +
"        modele     VARCHAR(255),\n" +
"        active     BOOLEAN,\n" +
"        processeur VARCHAR(255),\n" +
"        ram        INT,\n" +
"        disque     INT,\n" +
"        type       VARCHAR(255),\n" +
"        idsalle    INT CONSTRAINT FK_IdSalle REFERENCES salles(idsalle));\n");
      
      state.execute("CREATE TABLE interfaces\n" +
"       (mac        VARCHAR(255) CONSTRAINT PK_Mac PRIMARY KEY,\n" +
"        ip         VARCHAR(255),\n" +
"        nomint     VARCHAR(255),\n" +
"        vitesse    INT,\n" +
"        ideq       VARCHAR(255) CONSTRAINT FK_IdEq REFERENCES equipements(sn));\n");
      
      state.execute("CREATE TABLE pk\n" +
"       (id         INT CONSTRAINT PK_id PRIMARY KEY,\n" +
"        batiment   INT,\n" +
"        salle      INT);\n");
      
      state.execute("INSERT INTO pk\n"+
"       VALUES (0, 0, 0);");
         
      state.close();
         
    } catch (Exception e) {
      e.printStackTrace();
    }      
  }
}
