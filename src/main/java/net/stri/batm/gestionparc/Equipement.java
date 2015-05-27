package net.stri.batm.gestionparc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */


/**
 * Superclasse pour tous les équipements. 
 * Cette classe est utilisée pour les équipements qui ne possèdent pas de RAM ou de disque dur.
 * On utilisera la classe Ordinateur le cas échéant.
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

    /**
     * Constructeur.
     * Un équipement est constitué d'une marque, d'un nom, d'un modele, d'un numéro de série,
     * ainsi que d'un état (actif/passif) et d'un type. De plus, il est localisé dans une salle.
     * @param salle
     * @param nom
     * @param marque
     * @param modele
     * @param SN
     * @param actif
     * @param type
     */
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
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
    */
    
    public void importInterfaces() throws SQLException, ClassNotFoundException{
        
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/gestionpark";
        String user = "postgres";
        String passwd = "postgres";
         
        Connection conn = DriverManager.getConnection(url, user, passwd);
         
        //Création d'un objet Statement
        Statement state = conn.createStatement();
                
        ResultSet element = state.executeQuery("SELECT * FROM interfaces WHERE ideq = '"+SN+"';");
        
        int i = 0;
        interfaces.clear();
        
        while(element.next()) {
            
            Interface in = new Interface(this,(String)element.getString("mac"), (String)element.getString("ip"), (String)element.getString("nomint"), (int)element.getInt("vitesse"));
            interfaces.add(i,in);
            i++;
            
        }
        
    }
    
    /**
     * Retourne le nom de l'équipement
     * @return nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Attribue un nom à l'équipement
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne la marque de l'équipement
     * @return marque
     */
    public String getMarque() {
        return marque;
    }

    /**
     * Attribue une marque à l'équipement
     * @param marque
     */
    public void setMarque(String marque) {
        this.marque = marque;
    }

    /**
     * Retourne le modèle de l'équipement
     * @return modele
     */
    public String getModele() {
        return modele;
    }

    /**
     * Attribue un modèle de l'équipement
     * @param modele
     */
    public void setModele(String modele) {
        this.modele = modele;
    }

    /**
     * Retourne le numéro de série de l'équipement
     * @return sn
     */
    public String getSN() {
        return SN;
    }

    /**
     * Attribue un numéro de série à l'équipement
     * @param SN
     */
    public void setSN(String SN) {
        this.SN = SN;
    }

    /**
     * Retourne le numéro de la salle où est situé l'équipement
     * @return salle
     */
    public Salle getSalle() {
        return salle;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param inter
     */
    public void addInterface(Interface inter) {
		interfaces.add(inter);
	}
      
    /**
     *
     * @param inter
     */
    public void removeInterface(Interface inter) {
		if (!interfaces.contains(inter)) {
			System.out.println("This interface is not in this equipment...");
		} else {
			interfaces.remove(inter);
		}
	}
}
