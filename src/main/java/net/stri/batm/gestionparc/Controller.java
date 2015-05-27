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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Le controller centralise toutes les méthodes d'accès aux différentes classes.
 * Il permet de faire le lien entre l'interface utilisateur et les fonctionnalités. 
 * C'est également lui qui contrôle les données.
 * @author Vince
 */
public class Controller {
	private ArrayList<Batiment> batiments;
        private ArrayList<Salle> salles;

    /**
     *Constructeur (batiments, salles)
     */
    public Controller() {
		batiments = new ArrayList<>();
                salles = new ArrayList<>();
                salles = listAllSalles();
	}
        
        /**
        * La méthode importe depuis la base de données tout les batiments.
        * Elles seront placées dans l'arrayList batiments.
        * @author GasparMeyerfeld
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
        */
        
        public void importBatiments() throws ClassNotFoundException, SQLException {
        
                Class.forName("org.postgresql.Driver");
                String url = "jdbc:postgresql://localhost:5432/gestionpark";
                String user = "postgres";
                String passwd = "postgres";
         
                Connection conn = DriverManager.getConnection(url, user, passwd);
         
                //Création d'un objet Statement
                Statement state = conn.createStatement();
                
                ResultSet element = state.executeQuery("SELECT * FROM batiments;");
            
                int i = 0;
                batiments.clear();
        
                while(element.next()){
                    Batiment b = new Batiment((int)element.getInt("idbatiment"), 
                            (String)element.getString("nomb"), (int)element.getInt("numb"));
                    batiments.add(i,b);
                    i++;
                }
                state.close();
        }

    /**
     * Retourne la liste des batiments existants
     * @return batiments
     */
    public ArrayList<Batiment> getBatiments() {
            return batiments;
        }

    /**
     * Retourne la liste des salles existantes
     * @return salles
     */
    public ArrayList<Salle> getSalles() {
        return salles;
    }

    /**
     * Permet d'afficher la liste des batiments existants
     */
    public void printBatiments() {
		for (Batiment batiment : batiments) {
			System.out.println(batiment.toString());
		}
	}

    /**
     * Permet d'afficher la liste des salles existantes
     */
    public void printSalles() {
		for (Batiment batiment : batiments) {
			for (Salle salle : batiment.getSalles()) {
				System.out.println(salle.toString());
			}
		}
	}
        
        /**
         * La méthode ajoute un batiment et l'insert dans la base de donnée
         * @param id
         * @param name
         * @param num 
     * @throws java.sql.SQLException 
     * @throws java.lang.ClassNotFoundException 
         */

	public void addBatiment(String name, int num) throws SQLException, ClassNotFoundException {
                BD bd = new BD();	
                int id = bd.getId("batiment");
                Batiment batiment = new Batiment(id, name, num);
		batiments.add(batiment);
                bd.requete("INSERT INTO batiments VALUES ("+id+",'"+name+"',"+num+");");
	}
        
        /**
         * La méthode ajoute une salle et l'insert dans la base de donnée
         * @param batiment
         * @param name
         * @param num
         * @param etage 
     * @throws java.sql.SQLException 
     * @throws java.lang.ClassNotFoundException 
         */

	public void addSalle(Batiment batiment, String name, int num,
			int etage) throws SQLException, ClassNotFoundException {
                BD bd = new BD();
                int id = bd.getId("salle");
		Salle salle = new Salle(batiment, id, name, num, etage);
		batiment.addSalle(salle);
                
                bd.requete("INSERT INTO salles VALUES ("+id+", '"+name+"',"+num+","+etage+","+batiment.getId()+");");
                
	}
        
        /**
         * La méthode ajoute un équipement et l'insert dans la base de donnée
         * @param salle
         * @param SN
         * @param name
         * @param marque
         * @param modele
         * @param active
         * @param type 
         */
        
        public void addEquipement(Salle salle, String SN, String name, 
                        String marque, String modele, boolean active, String type) {
		Equipement equipement = new Equipement(salle, name, marque, modele, SN, active, type);
		salle.addEquipement(equipement);
                BD bd = new BD();
                bd.requete("INSERT INTO equipements VALUES ('"+SN+"','"+name+"',"
                        + "'"+marque+"','"+modele+"',"+active+","+null+","
                        + null+","+null+",'"+type+"',"+salle.getId()+");");
	}
        
        /**
         * La méthode ajoute un ordinateur et l'insert dans la base de donnée
         * @param salle
         * @param SN
         * @param name
         * @param marque
         * @param modele
         * @param num
         * @param active
         * @param process
         * @param ram
         * @param dd
         * @param type 
         */
        
        public void addOrdinateur(Salle salle, String SN, String name, 
                        String marque, String modele, int num, boolean active, 
                        String process, int ram, int dd, String type) {
		Ordinateur ordinateur = new Ordinateur(salle, name, marque, modele, SN, active, process, ram, dd);
		salle.addOrdinateur(ordinateur);
                BD bd = new BD();
                bd.requete("INSERT INTO ordinateurs VALUES ('"+SN+"','"+name+"',"
                        + "'"+marque+"','"+modele+"',"+num+","+active+","+process+","
                        + ram+","+dd+",'"+type+"',"+salle.getId()+");");
	}
        
        /**
         * La méthode ajoute une interface et l'insert dans la base de donnée
         * @param objet
         * @param mac
         * @param ip
         * @param name
         * @param speed 
         */
        
        public void addInterface(Object objet, String mac, String ip, String name, int speed) {
		Interface inter = new Interface(objet, mac, ip, name, speed);
		BD bd = new BD();
                if (objet instanceof Equipement){
                    Equipement eq = (Equipement)objet;
                    eq.addInterface(inter);
                    bd.requete("INSERT INTO interfaces VALUES ('"+mac+"','"+ip+"',"
                                + "'"+name+"', "+speed+", '"+eq.getSN()+"');");
                } else if (objet instanceof Ordinateur){
                    Ordinateur or = (Ordinateur)objet;
                    or.addInterface(inter);
                    bd.requete("INSERT INTO interfaces VALUES ('"+mac+"','"+ip+"',"
                                + "'"+name+"',"+speed+","+or.getSN()+");");
                }
                
	}
        
        /**
         * Supprime un batiment, le supprime de sa base de donnée, et met la valeure null dans idbat des salles de ce batiment
         * @param id
         * @throws SQLException 
     * @throws java.lang.ClassNotFoundException 
         */

	public void removeBatiment(int id) throws SQLException, ClassNotFoundException {
		Batiment bat = null;
		for(Batiment batiment:batiments){
			if (batiment.getId()==id){
				bat = batiment;
				break;
			}
		}
		if(bat!=null){
                        BD bd = new BD();
                        for (Salle s : bat.getSalles()){
                            if(bd.requete("SELECT *  FROM equipements WHERE idsalle = "+s.getId()+";") != null){
                                bd.requete("UPDATE equipements SET idsalle = 0 WHERE idsalle = "+s.getId()+";");
                                s.importequipements();
                            }
                        }
                        
                        if(bd.requete("SELECT *  FROM salles WHERE idbat = "+bat.getId()+";") != null){
                            bd.requete("DELETE FROM salles WHERE idbat = "+bat.getId()+";");
                            bat.importSalles();
                        }
                        bd.requete("DELETE FROM batiments where idbatiment = "+bat.getId()+";");
			batiments.remove(bat);
                        
		}
	}

        /**
         * Supprime une salle, la supprime de sa base de donnée, et met la valeure null dans idsalle des equipements de cette salles
         * @param id
         * @throws SQLException 
     * @throws java.lang.ClassNotFoundException 
         */
        
	public void removeSalle(int id) throws SQLException, ClassNotFoundException {
		ArrayList<Salle> salles = listAllSalles();
		Salle sal = null;
		for(Salle salle:salles){
			if (salle.getId()==id){
				sal = salle;
				break;
			}
		}
		if(sal!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE equipements SET idsalle = 0 where idsalle = "+sal.getId()+";");
                        sal.importequipements();
                        bd.requete("DELETE FROM salles where idsalle = "+sal.getId()+";");
			sal.getBatiment().removeSalle(sal);
		}
	}
        
        /**
         * Supprime un equipement, le supprime de sa base de donnée, et met la valeure null dans ideq des interfaces de cet equipement
         * @param sn
         * @throws SQLException 
     * @throws java.lang.ClassNotFoundException 
         */
        
        public void removeEquipement(String sn) throws SQLException, ClassNotFoundException {
		ArrayList<Equipement> equipements = listAllEquipements();
		Equipement eq = null;
		for(Equipement equipement:equipements){
			if (equipement.getSN() == null ? sn == null : equipement.getSN().equals(sn)){
				eq = equipement;
				break;
			}
		}
		if(eq!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE interfaces SET ideq ="+null+"where ideq = '"+eq.getSN()+"';");
                        eq.importInterfaces();
                        bd.requete("DELETE FROM equipements where ideq = '"+eq.getSN()+"';");
			eq.getSalle().removeEquipement(eq);
		}
	}
        
        /**
         * Supprime un ordinateur, le supprime de sa base de donnée, et met la valeure null dans ideq des interfaces de cet equipement
         * @param sn
         * @throws SQLException 
     * @throws java.lang.ClassNotFoundException 
         */
        
        public void removeOrdinateur(String sn) throws SQLException, ClassNotFoundException {
		ArrayList<Ordinateur> ordinateurs = listAllOrdinateurs();
		Ordinateur or = null;
		for(Ordinateur ordinateur:ordinateurs){
			if (ordinateur.getSN() == null ? sn == null : ordinateur.getSN().equals(sn)){
				or = ordinateur;
				break;
			}
		}
		if(or!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE interfaces SET ideq ="+null+"where ideq = '"+or.getSN()+"';");
                        or.importInterfaces();
                        bd.requete("DELETE FROM equipements where sn = '"+or.getSN()+"';");
			or.getSalle().removeOrdinateur(or);
		}
	}
        
        /**
         * Supprime une interface, la supprime de sa base de donnée
     * @param mac
         * @throws SQLException 
         */
        
        public void removeInterface(String mac) throws SQLException {
		ArrayList<Interface> interfaces = listAllInterfaces();
		Interface in = null;
		for(Interface inter:interfaces){
			if (inter.getMAC()== null ? mac == null : inter.getMAC().equals(mac)){
				in = inter;
				break;
			}
		}
		if(in!=null){
                        BD bd = new BD();
                        bd.requete("DELETE FROM interfaces where mac = '"+in.getMAC()+"';");
			
                        Object obj = in.getEquipement();
                        if (obj instanceof Ordinateur){
                            Ordinateur ord = (Ordinateur)obj;
                            ord.removeInterface(in);
                        }
                        else if (obj instanceof Equipement){
                            Equipement equ = (Equipement)obj;
                            equ.removeInterface(in);
                        }
		}
	}      
        
    /**
     * Permet de modifier un ou plusieurs attributs d'un batiment désigné par son id. 
     * Effectue la requete sql afin de mettre à jour la base de données
     * @param id
     * @param name
     * @param num
     * @throws SQLException
     */
    public void modifyBatiment(int id, String name, int num) throws SQLException{
            Batiment batiment = null;
            for(Batiment b : batiments){
                if (b.getId()==id){
                    batiment = b;
                    break;
                }
            }
            if(batiment != null){
                BD bd = new BD();
                bd.requete("UPDATE batiments SET nomb ='"+name+"', numb = "+num+"where idbatiment = '"+id+"';");
                
            }
            
        }
        
    /**
     * Permet de modifier un ou plusieurs attributs d'une salle désigné par son id. 
     * Effectue la requete sql afin de mettre à jour la base de données
     * @param id
     * @param batiment
     * @param name
     * @param num
     * @param etage
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void modifySalle(int id, Batiment batiment, String name, int num, int etage) throws SQLException, ClassNotFoundException{
            ArrayList<Salle> salles = listAllSalles();
		Salle sal = null;
		for(Salle salle:salles){
			if (salle.getId()==id){
				sal = salle;
				break;
			}
		}
		if(sal!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE salles SET nomS ='"+name+"', nums = "+num+", etage = "+etage+", "
                                + "idbat= "+batiment.getId()+"where idsalle = "+id+";");
                        batiment.importSalles();
		}
        }
        
    /**
     * Permet de modifier un ou plusieurs attributs d'un équipement désigné par son numéro de série (sn). 
     * Effectue la requete sql afin de mettre à jour la base de données
     * @param sn
     * @param salle
     * @param name
     * @param marque
     * @param modele
     * @param actif
     * @param type
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void modifyEquipement(String sn, Salle salle, String name, String marque, String modele, boolean actif, String type) throws SQLException, ClassNotFoundException{
            ArrayList<Equipement> equipements = listAllEquipements();
		Equipement eq = null;
		for(Equipement equipement:equipements){
			if (equipement.getSN() == null ? sn == null : equipement.getSN().equals(sn)){
				eq = equipement;
				break;
			}
		}
		if(eq!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE equipements SET NomEq ='"+name+"',marque ='"+marque+"',modele ='"+modele+"',"
                                + "active ='"+actif+"',type = '"+type+"',idsalle = "+salle.getId()
                                + " where ideq = '"+sn+"';");
                        salle.importequipements();
		}
        }
        
    /**
     * Permet de modifier un ou plusieurs attributs d'un équipement désigné par son numéro de série (sn). 
     * Effectue la requete sql afin de mettre à jour la base de données
     * @param sn
     * @param salle
     * @param name
     * @param marque
     * @param modele
     * @param actif
     * @param process
     * @param ram
     * @param dd
     * @param type
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void modifyOrdinateur(String sn, Salle salle, String name, String marque, 
                String modele, boolean actif, String process, int ram, int dd, String type) throws SQLException, ClassNotFoundException{
            ArrayList<Equipement> equipements = listAllEquipements();
		Equipement eq = null;
		for(Equipement equipement:equipements){
			if (equipement.getSN() == null ? sn == null : equipement.getSN().equals(sn)){
				eq = equipement;
				break;
			}
		}
		if(eq!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE equipements SET NomEq ='"+name+"',marque ='"+marque+"',modele ='"+modele+"',"
                                + "active ='"+actif+"', processeur = "+process+", ram = "+ram+", "
                                + "disque = "+dd+",type = '"+type+"',idsalle = "+salle.getId()
                                + " where ideq = '"+sn+"';");
                        salle.importequipements();
		}
        }
        
    /**
     * Permet de modifier un ou plusieurs attributs d'un équipement désigné par son adresse mac. 
     * Effectue la requete sql afin de mettre à jour la base de données
     * @param mac
     * @param eq
     * @param ip
     * @param name
     * @param speed
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void modifyInterface(String mac, Object eq, String ip, String name, int speed) throws SQLException, ClassNotFoundException {
		ArrayList<Interface> interfaces = listAllInterfaces();
		Interface in = null;
		for(Interface inter:interfaces){
			if (inter.getMAC()== null ? mac == null : inter.getMAC().equals(mac)){
				in = inter;
				break;
			}
		}
		if(in!=null){
                        BD bd = new BD();
                        			
                        Object obj = in.getEquipement();
                        if (obj instanceof Ordinateur){
                            Ordinateur ord = (Ordinateur)obj;
                            bd.requete("UPDATE interface SET ip ='"+ip+"',nomint ='"+name+"',vitesse ="+speed
                                + "ideq = '"+ord.getSN()+"' where mac = '"+mac+"';");
                            ord.importInterfaces();
                        }
                        else if (obj instanceof Equipement){
                            Equipement equ = (Equipement)obj;
                            bd.requete("UPDATE interface SET ip ='"+ip+"',nomint ='"+name+"',vitesse ="+speed
                                + "ideq = '"+equ.getSN()+"' where mac = '"+mac+"';");
                            equ.importInterfaces();
                        }
		}
	} 
        

	private ArrayList<Salle> listAllSalles() {
		ArrayList<Salle> salles = new ArrayList<>();
		for (Batiment batiment : batiments) {
			for (Salle salle : batiment.getSalles()) {
				salles.add(salle);
			}
		}
		return salles;
	}
        
        public ArrayList<Equipement> listAllEquipements() {
		ArrayList<Equipement> equipements = new ArrayList<>();
                ArrayList<Salle> salles = listAllSalles();
		for (Salle s : salles) {
			for (Equipement equipement : s.getEquipements()) {
				equipements.add(equipement);
			}
		}
		return equipements;
	}
        
        private ArrayList<Ordinateur> listAllOrdinateurs() {
		ArrayList<Ordinateur> ordinateurs = new ArrayList<>();
                ArrayList<Salle> salles = listAllSalles();
		for (Salle s : salles) {
			for (Ordinateur ordinateur : s.getOrdinateurs()) {
				ordinateurs.add(ordinateur);
			}
		}
		return ordinateurs;
	}
        
         private ArrayList<Interface> listAllInterfaces() {
                ArrayList<Interface> interfaces = new ArrayList<>();
                ArrayList<Equipement> equipements = listAllEquipements();
                ArrayList<Ordinateur> ordinateurs = listAllOrdinateurs();
                
		for (Equipement e : equipements) {
			for (Interface inter : e.getInterfaces()) {
				interfaces.add(inter);
			}
		}
                for (Ordinateur o : ordinateurs) {
			for (Interface inter : o.getInterfaces()) {
				interfaces.add(inter);
			}
		}
		return interfaces;
	}

}
