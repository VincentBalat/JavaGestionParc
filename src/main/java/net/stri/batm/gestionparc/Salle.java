/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;

/**
 * Cette classe définit les salles qui sont listées dans les Batiments.
 * @author Xavier
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La salle est contenue dans un batiment, et possède les équipements quelle contient.
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
     * Une salle est définie par le batiment auquel elle appartient, son id, son nom, son numéro et son étage.
     * @param batiment
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
    
    /**
    * La méthode importe depuis la base de données les equipements de la salle.
    * Elles seront placées dans l'arrayList ordinateur ou equipement selon le type.
    * @author GasparMeyerfeld
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
    */
    
    public void importequipements() throws SQLException, ClassNotFoundException{
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/gestionpark";
        String user = "postgres";
        String passwd = "postgres";
         
        Connection conn = DriverManager.getConnection(url, user, passwd);
         
        //Création d'un objet Statement
        Statement state = conn.createStatement();
                
        ResultSet element = state.executeQuery("SELECT * FROM Equipements WHERE idsalle = "+id+";");
        
        int i = 0;
        equipements.clear();
        
        while(element.next()) {
            
            if("ordinateur".equals((String)element.getString("type"))){
                Ordinateur o = new Ordinateur(this, (String)element.getString("nomeq"), 
                        (String)element.getString("marque"), (String)element.getString("modele"),
                        (String)element.getString("SN"), (boolean)element.getBoolean("active"),
                        (String)element.getString("Processeur"), (int)element.getInt("disque"), 
                        (int)element.getInt("ram"));
                ordinateurs.add(i,o);
            } else {
                Equipement e = new Equipement(this, (String)element.getString("nomeq"), 
                        (String)element.getString("marque"), (String)element.getString("modele"), 
                        (String)element.getString("SN"), (boolean)element.getBoolean("active"), 
                        (String)element.getString("type"));
                equipements.add(i,e);
            }
            i++;
           
        }
    }

    /**
     * Retourne le batiment dans lequel se trouve la salle
     * @return batiment
     */
    public Batiment getBatiment() {
        return batiment;
    }

    /**
     * Retourne l'Arraylist des ordinateurs présents dans la salle
     * @return ordinateurs
     */
    public ArrayList<Ordinateur> getOrdinateurs() {
        return ordinateurs;
    }

    /**
     * Permet de modifier le batiment de la salle
     * @param batiment
     */
    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    /**
     * Permet de modifier l'id de la salle
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Permet de modifier l'étage de la salle
     * @param etage
     */
    public void setEtage(int etage) {
        this.etage = etage;
    }

    /**
     * Permet de modifier l'ArrayList des équipements présents dans la salle.
     * @param equipements
     */
    public void setEquipements(ArrayList<Equipement> equipements) {
        this.equipements = equipements;
    }

    /**
     * Permet de modifier l'ArrayList des ordinateurs présents dans la salle.
     * @param ordinateurs
     */
    public void setOrdinateurs(ArrayList<Ordinateur> ordinateurs) {
        this.ordinateurs = ordinateurs;
    }

    /**
     * Retourne le nom de la salle.
     * @return name
     */
    public String getName() {
		return name;
	}

    /**
     * Permet de modifier le nom de la salle
     * @param name
     */
    public void setName(String name) {
		this.name = name;
	}

    /**
     * Retourne le numéro de la salle
     * @return num
     */
    public int getNum() {
		return num;
	}

    /**
     * Permet de mettre à jour le numéro de la salle.
     * @param num
     */
    public void setNum(int num) {
		this.num = num;
    }

    /**
     * Retourne l'id de la salle
     * @return id
     */
    public int getId() {
		return id;
	}

    /**
     * Retourne l'étage de la salle
     * @return etage
     */
    public int getEtage() {
		return etage;
	}

    /**
     * Retourne l'ArrayList contenant les équipements présent dans la salle
     * @return equipements
     */
    public ArrayList<Equipement> getEquipements() {
		return equipements;
	}

    /**
     * Permet d'ajouter un équipement dans l'ArrrayList des équipements de la salle
     * @param equipement
     */
    public void addEquipement(Equipement equipement) {
		equipements.add(equipement);
	}
    
    /**
     * Permet d'ajouter un ordinateur dans l'ArrrayList des ordinateurs de la salle
     * @param ordinateur
     */
    public void addOrdinateur(Ordinateur ordinateur) {
		ordinateurs.add(ordinateur);
	}

    /**
     * Permet de supprimer un équipement de l'ArrayList equipements de la salle
     * @param equipement
     */
    public void removeEquipement(Equipement equipement) {
		if (!equipements.contains(equipement)) {
			System.out.println("This equipement is not in this salle...");
		} else {
			equipements.remove(equipement);
		}
	}
    
    /**
     * Permet de supprimer un ordinateur de l'ArrayList ordinateurs de la salle
     * @param ordinateur
     */
    public void removeOrdinateur(Ordinateur ordinateur) {
		if (!ordinateurs.contains(ordinateur)) {
			System.out.println("This computer is not in this salle...");
		} else {
			ordinateurs.remove(ordinateur);
		}
	}

    @Override
    public String toString() {
        return  (name + " : " + num);
    }
}

