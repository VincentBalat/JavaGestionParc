/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */
package net.stri.batm.gestionparc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * La classe Batiment définit un batiment qui comportera des salles.
 * @author Vince
 */
public class Batiment {

	private int id;
	private String name;
	private int num;
	private ArrayList<Salle> salles;

    /**
     * Un batiment est définit par son id, son nom et son numéro
     * @param id
     * @param name
     * @param num
     */
    public Batiment(int id, String name, int num) {
		this.id = id;
		this.name = name;
		this.num = num;
		salles = new ArrayList<Salle>();
	}

    Batiment() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
    * La méthode importe depuis la base de données les salles du batiment.
    * Elles seront placées dans l'arrayList salles.
    * @author GasparMeyerfeld
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
    */
    
    public void importSalles() throws SQLException, ClassNotFoundException{
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/gestionpark";
        String user = "postgres";
        String passwd = "postgres";
         
        Connection conn = DriverManager.getConnection(url, user, passwd);
         
        //Création d'un objet Statement
        Statement state = conn.createStatement();
                
        ResultSet element = state.executeQuery("SELECT * FROM salles WHERE idbat = "+this.getId()+";");
            
        int i = 0;
        salles.clear();
        
        while(element.next()) {
            Salle s = new Salle(this, (int)element.getInt("idsalle"), (String)element.getString("noms"), (int)element.getInt("nums"), (int)element.getInt("etage"));
            salles.add(i,s);
            i++;
        }
        state.close();
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
    public ArrayList<Salle> getSalles() {
		return salles;
	}

    /**
     *
     * @param salle
     */
    public void addSalle(Salle salle) {
		salles.add(salle);
	}

    /**
     *
     * @param salle
     */
    public void removeSalle(Salle salle) {
		if (!salles.contains(salle)) {
			System.out.println("This salle is not in this batiment...");
		} else {
			salles.remove(salle);
		}
	}

	public String toString() {
		return (num +" : "+ name);
	}
}
