package net.stri.batm.gestionparc;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import static org.eclipse.persistence.expressions.ExpressionOperator.All;

/*
 *          Utilitaire de Gestion de Parc Informatique
 * Travail réalisé dans le cadre de l'UPSSITECH spécialité STRI
 *   Promo 2017, Groupe Alessandrini, Balat, Meyerfeld, Tauran
 */

/**
 * Interface Utilisateur principale qui permet à l'utilisateur d'acceder à toutes les autres interfaces.
 * 
 * @author Brice
 */
public final class InterfaceU extends javax.swing.JFrame {
    private Object Interface;
    private Controller controller; 
    private DefaultListModel<String> bat;
    private DefaultListModel<String> sal;
    private DefaultTableModel eq;

    /**
     * Creates new form InterfaceU
     */
    public InterfaceU() {
        controller = new Controller();
        bat = new DefaultListModel<>();
        sal = new DefaultListModel<>();
        eq = new DefaultTableModel();
        eq.addColumn("Num Série");
        eq.addColumn("Nom");
        eq.addColumn("Type");
        eq.addColumn("Marque");
        eq.addColumn("Modèle");
        eq.addColumn("Actif");
        UpdateJList();
        initComponents();
    }

    /**
     * retourne le controller
     * @return controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * Retour la Jlist des salles
     * @return salle
     */
    public JList getSalle() {
        return salle;
    }

    /**
     * Retourne la JList des batiments
     * @return batiment
     */
    public JList getBatiment() {
        return batiment;
    }

    /**
     * retourne le DefaultListModel contenant les batiments.
     * le type DefaultListModel permet de stocker la liste depuis une ArrayList 
     * en vue de l'afficher dans une JList
     * @return
     */
    public DefaultListModel<String> getBat() {
        return bat;
    }
    
    /**
     * Permet d'actualiser la liste des batiments affichée sur l'interface graphique.
     */
    public void UpdateJList(){
        try {
            controller.importBatiments();
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        bat.clear();
        for(Batiment b : controller.getBatiments()){
            bat.addElement(b.toString());
            for(Salle s : b.getSalles()){
                sal.addElement(s.toString());
            }
        }  
    }
    
    /**
     * Permet l'actualisation de l'affichage des listes des salles par exemple 
     * lorsque un batiment est sélectionné.
     * Ainsi, si le batiment U3 est sélectionné dans la liste des batiments, 
     * seules les salles de ce batiment apparaitront.
     * @param selectBat
     */
    public void UpdateJListSalle(Batiment selectBat){
        sal.clear();
        try {
            selectBat.importSalles();
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        for(Salle s : selectBat.getSalles()){
                sal.addElement(s.toString());
        }
    }
    
    /**
     * Permet l'actualisation de l'affichage des équipements présent dans les salles 
     * et les batiments lorsqu'un d'eux est sélectionné.
     * Ainsi, si le batiment U3 est sélectionné dans la liste des batiments, 
     * seuls les équipements de ce batiment apparaitront.
     * @param selectSal
     */
    public void UpdateJTableEq(Salle selectSal){
        for (int i = eq.getRowCount() - 1; i > -1; i--) {
            eq.removeRow(i);
        }
        try {
            selectSal.importequipements();
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        for(Equipement e : selectSal.getEquipements()){
            Object[] obj = {e.getSN(),e.getNom(),e.getType(),e.getMarque(),e.getModele(), e.isActif()};
            eq.addRow(obj);
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        batiment = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        salle = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        alert = new javax.swing.JLabel();
        ErrAddSalle = new javax.swing.JLabel();
        ErrAddEq = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        batiment.setModel(bat);
        batiment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batimentMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(batiment);

        salle.setModel(sal);
        salle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        salle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salleMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(salle);

        jButton1.setText("Ajouter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ajouter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        Table.setModel(eq);
        Table.setToolTipText("");
        Table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Table.setDoubleBuffered(true);
        Table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TableMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(Table);

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel5.setText("GESTION DE PARCS INFORMATIQUES");

        jButton5.setText("Supprimer");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setText("Supprimer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("BATIMENTS :");

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("SALLES :");

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("EQUIPEMENTS :");

        alert.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        alert.setForeground(new java.awt.Color(255, 51, 51));

        ErrAddSalle.setForeground(new java.awt.Color(255, 0, 0));

        ErrAddEq.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(214, 214, 214))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(329, 329, 329)
                        .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ErrAddEq, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ErrAddSalle)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(alert, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ErrAddSalle)
                        .addGap(6, 6, 6))
                    .addComponent(ErrAddEq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4))
                .addGap(19, 19, 19))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void batimentMouseClicked(java.awt.event.MouseEvent evt) {                                      
        // TODO add your handling code here:
        
        Batiment bb = null;
        for (Batiment b : controller.getBatiments()){
                if (b.toString().equals((String) batiment.getSelectedValue())){
                    UpdateJListSalle(b);
                    bb = b;
                }
        }
        for (int i = eq.getRowCount() - 1; i > -1; i--) {
            eq.removeRow(i);
        }
        for (Salle s : bb.getSalles()){
            try {
                s.importequipements();
            } catch (SQLException | ClassNotFoundException ex) {
                ErreurBD err = new ErreurBD();
                err.setVisible(true);
            }
            for(Equipement e : s.getEquipements()){
                Object[] obj = {e.getNom(),e.getType(),e.getMarque(),e.getModele(), e.isActif()};
                eq.addRow(obj);
            }
        }
        
        

        if (evt.getClickCount() == 2) {
            
        String idselect = null; 
        String numselect = null;
        String nomselect = null ;
        
            for (Batiment b : controller.getBatiments()){
                if (b.toString().equals((String) batiment.getSelectedValue())){
                 nomselect = b.getName();
                 numselect = String.valueOf(b.getNum());
                 idselect = String.valueOf(b.getId());
                }
            }
        
        ModifBat j = new ModifBat(this);
        j.nombat.setText(nomselect);
        j.numbat.setText(numselect);
        j.id.setText(idselect);
        
        j.setVisible(true);
        }
         
        
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
                // TODO add your handling code here:
        ErrAddEq.setText("");
        ErrAddSalle.setText("");
        AjoutBat j = new AjoutBat(this);
        j.setVisible(true);
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        ErrAddEq.setText("");
        ErrAddSalle.setText("");
        if(controller.getBatiments().isEmpty())
            ErrAddSalle.setText(" Completez les champs ");
        else {
            AjoutSalle j = new AjoutSalle(this);
        j.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        ErrAddEq.setText("");
        ErrAddSalle.setText("");
        
        AjoutEquipement j = new AjoutEquipement(this);
        j.sall.setText((String) this.salle.getSelectedValue());
        j.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int id = 0;
        alert.setText("");
        ErrAddSalle.setText("");
        ErrAddEq.setText("");
        if(!batiment.getSelectedValue().toString().equals("")){
            for (Batiment b : controller.getBatiments()){
                if (b.toString().equals((String)batiment.getSelectedValue())){
                    id= b.getId();
                    
                }
            }  
            
            try {
                getController().removeBatiment(id);
            } catch (SQLException | ClassNotFoundException ex) {
                ErreurBD err = new ErreurBD();
                err.setVisible(true);
            }
            UpdateJList();
            sal.clear();
        }
        else{
            alert.setText(" Selectionnez un Batiment à Supprimer ");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        int Id = 0;
        Batiment Bati = null;
        Salle sa = null;
        alert.setText("");
        ErrAddSalle.setText("");
        if(!batiment.getSelectedValue().toString().equals("")){
            for(Batiment b : this.getController().getBatiments()){
                if(b.toString().equals(this.getBatiment().getSelectedValue()))
                    Bati = b;
            }
            
            for(Salle s : Bati.getSalles()){
                if(s.toString().equals(this.getSalle().getSelectedValue()))
                    sa = s;
            }
            Id = sa.getId();
            try {
               controller.removeSalle(Id);
               this.UpdateJListSalle(Bati);
            } catch (SQLException | ClassNotFoundException ex) {
                 ErreurBD err = new ErreurBD();
                err.setVisible(true);
            }
        }
        else{
            alert.setText(" Selectionnez un Batiment à Supprimer ");
        }
            
        
    }//GEN-LAST:event_jButton5ActionPerformed
/*
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void batimentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batimentMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_batimentMouseClicked
*/
    private void salleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_salleMouseClicked
        // TODO add your handling code here:
        Batiment bb = null;
        
        for (Batiment b : controller.getBatiments()){
                if (b.toString().equals((String) batiment.getSelectedValue())){
                    bb = b;
                }
        }
        
        for (Salle s : bb.getSalles()){
                if (s.toString().equals((String) salle.getSelectedValue())){
                    this.UpdateJTableEq(s);
                }
        }
        
        if (evt.getClickCount() == 2) {
            
        String idselect = null; 
        String numselect = null;
        String nomselect = null;
        String batselect = null;
        String etaselect = null;
        Batiment Bati = null;
        Salle sa = null;
            for(Batiment b : this.getController().getBatiments()){
                if(b.toString().equals(this.getBatiment().getSelectedValue()))
                    Bati = b;
            }
            
            for(Salle s : Bati.getSalles()){
                if(s.toString().equals(this.getSalle().getSelectedValue()))
                    sa = s;
            }
            
                 nomselect = sa.getName();
                 numselect = String.valueOf(sa.getNum());
                 idselect = String.valueOf(sa.getId());
                 batselect = String.valueOf(sa.getBatiment());
                 etaselect = String.valueOf(sa.getEtage());
                
            
        
        ModifSalle j = new ModifSalle(this);
        j.nom.setText(nomselect);
        j.numero.setText(numselect);
        j.id.setText(idselect);
        j.bat.setText(batselect);
        j.etage.setText(etaselect);
       
        j.setVisible(true);
        }
    }//GEN-LAST:event_salleMouseClicked

    private void TableMouseClicked(java.awt.event.MouseEvent evt) {
        
            ModifEquip j = new ModifEquip(this);
            int ligne = this.Table.getSelectedRow();
            String sn = (String)eq.getValueAt(ligne, 0);
            String nom = (String)eq.getValueAt(ligne, 1);
            String type = (String)eq.getValueAt(ligne, 2);
            String marque = (String)eq.getValueAt(ligne, 3);
            String modele = (String)eq.getValueAt(ligne, 4);
        
            j.nom.setText(nom);
            j.numserie.setText(sn);
            j.type.setText(type);
            j.marque.setText(marque);
            j.modele.setText(modele);
            j.setVisible(true);

        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceU().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ErrAddEq;
    private javax.swing.JLabel ErrAddSalle;
    public javax.swing.JTable Table;
    private javax.swing.JLabel alert;
    private javax.swing.JList batiment;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList salle;
    // End of variables declaration//GEN-END:variables
}
