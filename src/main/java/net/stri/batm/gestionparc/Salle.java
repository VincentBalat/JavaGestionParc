/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;

/**
 *
 * @author Xavier
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
     *
     * @return
     */
    public Batiment getBatiment() {
        return batiment;
    }

    /**
     *
     * @return
     */
    public ArrayList<Ordinateur> getOrdinateurs() {
        return ordinateurs;
    }

    /**
     *
     * @param batiment
     */
    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param etage
     */
    public void setEtage(int etage) {
        this.etage = etage;
    }

    /**
     *
     * @param equipements
     */
    public void setEquipements(ArrayList<Equipement> equipements) {
        this.equipements = equipements;
    }

    /**
     *
     * @param ordinateurs
     */
    public void setOrdinateurs(ArrayList<Ordinateur> ordinateurs) {
        this.ordinateurs = ordinateurs;
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
     * @param ordinateur
     */
    public void addOrdinateur(Ordinateur ordinateur) {
		ordinateurs.add(ordinateur);
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
    
    /**
     *
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

