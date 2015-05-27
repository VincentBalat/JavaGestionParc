/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.stri.batm.gestionparc;

import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.stri.batm.gestionparc.Equipement;
import net.stri.batm.gestionparc.ErreurBD;
import net.stri.batm.gestionparc.Interface;
import net.stri.batm.gestionparc.InterfaceU;
import net.stri.batm.gestionparc.Salle;

/**
 *
 * @author Brice
 */
public class ModifOrdi extends javax.swing.JFrame {
    private DefaultTableModel eq;
    private InterfaceU mainInt;
    private DefaultTableModel interf;
    private DefaultComboBoxModel sal = new DefaultComboBoxModel();

    /**
     * Creates new form ModifOrdi
     */
    public ModifOrdi() {
        this.mainInt = mainInt;
        interf = new DefaultTableModel();
        interf.addColumn("Nom Int");
        interf.addColumn("@MAC");
        interf.addColumn("@IP");
        interf.addColumn("Vitesse");
        UpdateJListSalle();
        initComponents();
        initComponents();
    }

    ModifOrdi(InterfaceU aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public void UpdateJListSalle(){
        
        sal.removeAllElements();
        //sal.addElement("Salle");
        sal.setSelectedItem(this.mainInt.getSalle().getSelectedValue());
              
        for(Salle s : mainInt.getController().listAllSalles())
            sal.addElement(s);
    }

    public InterfaceU getMainInt() {
        return mainInt;
    }

    public void UpdateJTableIn(Equipement selectEq){
        for (int i = interf.getRowCount() - 1; i > -1; i--) {
            interf.removeRow(i);
        }
        try {
            selectEq.importInterfaces();
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        for(Interface i : selectEq.getInterfaces()){
            Object[] obj = {i.getNom(),i.getMAC(),i.getIP(),i.getVitesse()};
            interf.addRow(obj);
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

        type = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        modifier = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        salle = new javax.swing.JComboBox();
        sup = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        supint = new javax.swing.JButton();
        marque = new javax.swing.JTextField();
        nom = new javax.swing.JTextField();
        numserie = new javax.swing.JTextField();
        modele = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        addInt = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        act = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableint = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ram = new javax.swing.JTextField();
        proces = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        disque = new javax.swing.JTextField();

        type.setEditable(false);
        type.setBackground(new java.awt.Color(204, 204, 204));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("MODIFIER ORDINATEUR");

        modifier.setText("Modifier");
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        jLabel6.setText("Type");

        jLabel3.setText("Marque");

        salle.setModel(sal);
        salle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salleActionPerformed(evt);
            }
        });

        sup.setText("Supprimer");
        sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supActionPerformed(evt);
            }
        });

        jLabel4.setText("Salle");

        supint.setText("Supprimer Interface");
        supint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supintActionPerformed(evt);
            }
        });

        nom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomActionPerformed(evt);
            }
        });

        numserie.setEditable(false);
        numserie.setBackground(new java.awt.Color(204, 204, 204));
        numserie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numserieActionPerformed(evt);
            }
        });

        modele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeleActionPerformed(evt);
            }
        });

        jLabel13.setText("Numero Série");

        lMessage.setForeground(new java.awt.Color(255, 51, 51));

        jLabel1.setText("Modele");

        addInt.setText("Ajout Interface");
        addInt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addIntMouseClicked(evt);
            }
        });
        addInt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIntActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom");

        act.setSelected(true);
        act.setText("Actif");
        act.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actActionPerformed(evt);
            }
        });

        tableint.setModel(interf);
        tableint.setEnabled(false);
        jScrollPane1.setViewportView(tableint);

        jLabel5.setText("RAM");

        jLabel8.setText("Processeur");

        ram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ramActionPerformed(evt);
            }
        });

        jLabel9.setText("Disque Dur");

        disque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disqueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(76, 76, 76)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modele, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(type, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(marque, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(proces, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(disque, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(salle, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ram, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addComponent(addInt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(supint))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 97, Short.MAX_VALUE)
                        .addComponent(numserie, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(456, 456, 456))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sup)
                                .addGap(25, 25, 25)
                                .addComponent(modifier)
                                .addGap(18, 18, 18)
                                .addComponent(act)
                                .addGap(88, 88, 88)
                                .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(126, 126, 126)
                                .addComponent(jLabel7)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(299, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(numserie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(nom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(marque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(modele, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(salle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(proces, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(disque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(act)
                            .addComponent(modifier)
                            .addComponent(sup)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addInt)
                            .addComponent(supint))
                        .addGap(77, 77, 77)
                        .addComponent(lMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(423, 423, 423))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(127, 127, 127)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(600, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        // TODO add your handling code here:

        String msn = null;
        String mnom = null;
        String mtype = null;
        String mmarque = null;
        String mmodele = null;
        Salle msalle = null ;
        int mram = 0;
        String mprocesseur= null;
        int mdd = 0;
        boolean mactif = true;
        
        msn = String.valueOf(numserie.getText());
        mnom = nom.getText();
        mtype = type.getText();
        mmarque = marque.getText();
        mmodele = modele.getText();
        mram = Integer.valueOf(ram.getText());
        mprocesseur = proces.getText();
        mdd = Integer.valueOf(disque.getText());
        
        for(Salle s : mainInt.getController().listAllSalles()){
            if(s.toString().equals(salle.getSelectedItem()))
            msalle = s;
        }
        if(act.isSelected()== false)
        mactif = false;
        lMessage.setText("");
        try {
            mainInt.getController().modifyOrdinateur(msn, msalle,mnom, mmarque, mmodele, mactif,mprocesseur, mram, mdd, mtype);
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        mainInt.UpdateJTableEq(msalle);
        this.setVisible(false);
    }//GEN-LAST:event_modifierActionPerformed

    private void salleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salleActionPerformed

    private void supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supActionPerformed
        // TODO add your handling code here:
        String idI = numserie.getText();
        lMessage.setText("");
        try {
            mainInt.getController().removeOrdinateur(idI);
        } catch (SQLException | ClassNotFoundException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
        Salle sall = null;
        for(Salle s : mainInt.getController().listAllSalles()){
            if(s.toString().equals(mainInt.getSalle().getSelectedValue()))
            sall = s;
        }

        mainInt.UpdateJTableEq(sall);
        this.setVisible(false);

    }//GEN-LAST:event_supActionPerformed

    private void supintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supintActionPerformed
        // TODO add your handling code here:
        int ligne = this.tableint.getSelectedRow();
        String mac = (String) interf.getValueAt(ligne, 1);
        try {
            mainInt.getController().removeInterface(mac);
        } catch (SQLException ex) {
            ErreurBD err = new ErreurBD();
            err.setVisible(true);
        }
    }//GEN-LAST:event_supintActionPerformed

    private void nomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomActionPerformed

    private void numserieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numserieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numserieActionPerformed

    private void modeleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modeleActionPerformed

    private void addIntMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addIntMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addIntMouseClicked

    private void addIntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIntActionPerformed
        // TODO add your handling code here:
        AjoutIntOrdi j = new AjoutIntOrdi();
        j.setVisible(true);
    }//GEN-LAST:event_addIntActionPerformed

    private void actActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actActionPerformed

    private void ramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ramActionPerformed

    private void disqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disqueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disqueActionPerformed

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
            java.util.logging.Logger.getLogger(ModifOrdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModifOrdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModifOrdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModifOrdi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModifOrdi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox act;
    private javax.swing.JButton addInt;
    public javax.swing.JTextField disque;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lMessage;
    public javax.swing.JTextField marque;
    public javax.swing.JTextField modele;
    private javax.swing.JButton modifier;
    public javax.swing.JTextField nom;
    public javax.swing.JTextField numserie;
    public javax.swing.JTextField proces;
    public javax.swing.JTextField ram;
    public javax.swing.JComboBox salle;
    private javax.swing.JButton sup;
    private javax.swing.JButton supint;
    private javax.swing.JTable tableint;
    public javax.swing.JTextField type;
    // End of variables declaration//GEN-END:variables
}