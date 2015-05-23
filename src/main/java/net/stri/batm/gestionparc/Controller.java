/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

/**
 *
 * @author Xavier
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
	private final ArrayList<Batiment> batiments;

	public Controller() {
		batiments = new ArrayList<>();
		//example();
	}
        
        /**
        * La méthode importe depuis la base de données tout les batiments.
        * Elles seront placées dans l'arrayList batiments.
        * @author GasparMeyerfeld
        */
        
        public void importBatiments() throws SQLException {
        
                ResultSet element;
        
                BD bd = new BD();
        
                int i = 0;
        
                element = bd.requete("SELECT * FROM batiments;");
        
                while(element.next()) {
                    Batiment b = new Batiment((int)element.getInt("idbatiment"), 
                            (String)element.getString("nomb"), (int)element.getInt("numb"));
                    batiments.add(i,b);
                    i++;
                }
        }

	public void printBatiments() {
		for (Batiment batiment : batiments) {
			System.out.println(batiment.toString());
		}
	}

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
         */

	public void addBatiment(int id, String name, int num) {
		Batiment batiment = new Batiment(id, name, num);
		batiments.add(batiment);
                BD bd = new BD();
                bd.requete("INSERT INTO batiments VALUES ("+ id +",'"+name+"',"+num+");");
	}
        
        /**
         * La méthode ajoute une salle et l'insert dans la base de donnée
         * @param batiment
         * @param id
         * @param name
         * @param num
         * @param etage 
         */

	public void addSalle(Batiment batiment, int id, String name, int num,
			int etage) {
		Salle salle = new Salle(batiment, id, name, num, etage);
		batiment.addSalle(salle);
                BD bd = new BD();
                bd.requete("INSERT INTO salles VALUES ("+ id +",'"+name+"',"+num+","+etage+","+batiment.getId()+");");
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
                                + "'"+name+"',"+speed+","+eq.getSN()+");");
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
         */

	public void removeBatiment(int id) throws SQLException {
		Batiment bat = null;
		for(Batiment batiment:batiments){
			if (batiment.getId()==id){
				bat = batiment;
				break;
			}
		}
		if(bat!=null){
                        BD bd = new BD();
                        bd.requete("UPDATE salles SET idbat ="+null+"where idbat = "+bat.getId()+";");
                        bat.importSalles();
                        bd.requete("DELETE FROM batiments where idbat = "+bat.getId()+";");
			batiments.remove(bat);
                        
		}
	}

        /**
         * Supprime une salle, la supprime de sa base de donnée, et met la valeure null dans idsalle des equipements de cette salles
         * @param id
         * @throws SQLException 
         */
        
	public void removeSalle(int id) throws SQLException {
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
                        bd.requete("UPDATE equipements SET idsalle ="+null+"where idsalle = "+sal.getId()+";");
                        sal.importequipements();
                        bd.requete("DELETE FROM salles where idsalle = "+sal.getId()+";");
			sal.getBatiment().removeSalle(sal);
		}
	}
        
        /**
         * Supprime un equipement, le supprime de sa base de donnée, et met la valeure null dans ideq des interfaces de cet equipement
         * @param sn
         * @throws SQLException 
         */
        
        public void removeEquipement(String sn) throws SQLException {
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
         */
        
        public void removeOrdinateur(String sn) throws SQLException {
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
        
        public void modifySalle(int id, Batiment batiment, String name, int num, int etage) throws SQLException{
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
        
        public void modifyEquipement(String sn, Salle salle, String name, String marque, String modele, boolean actif, String type) throws SQLException{
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
        
        public void modifyOrdinateur(String sn, Salle salle, String name, String marque, 
                String modele, boolean actif, String process, int ram, int dd, String type) throws SQLException{
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
        
        public void modifyInterface(String mac, Object eq, String ip, String name, int speed) throws SQLException {
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
        
        private ArrayList<Equipement> listAllEquipements() {
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


	// pour faire les tests
	private void main() {
		addBatiment(1, "Bat 1", 1);
		addBatiment(2, "Bat 2", 2);
		addBatiment(3, "Bat 3", 3);
		addSalle(batiments.get(0), 1, "Salle 1", 1, 0);
		addSalle(batiments.get(0), 2, "Salle 2", 2, 0);
		addSalle(batiments.get(0), 3, "Salle 3", 3, 0);
		addSalle(batiments.get(1), 4, "Salle 1", 1, 0);
		addSalle(batiments.get(1), 5, "Salle 2", 2, 0);
		addSalle(batiments.get(1), 6, "Salle 3", 3, 0);
		addSalle(batiments.get(2), 7, "Salle 1", 1, 0);
		addSalle(batiments.get(2), 8, "Salle 2", 2, 0);
		addSalle(batiments.get(2), 9, "Salle 3", 3, 0);
		System.out.println("Liste des batiments");
		printBatiments();
		System.out.println("Liste des salles");
		printSalles();
		
            try {
                removeSalle(8);
                removeSalle(7);
                removeBatiment(1);
            } catch (SQLException ex) {
                System.out.println("Erreur de base de donnée");
            }
		
		
		System.out.println("Liste des batiments");
		printBatiments();
		System.out.println("Liste des salles");
		printSalles();
	}
}
