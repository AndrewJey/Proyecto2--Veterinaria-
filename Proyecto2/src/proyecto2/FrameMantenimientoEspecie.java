/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Marcos
 */
public class FrameMantenimientoEspecie extends javax.swing.JFrame {

    /**
     * Creates new form FrameMantenimientoDueño
     */
    private int tipo;
    ConexionPostgres CP;
    DefaultTableModel modelo;

    public FrameMantenimientoEspecie() throws ClassNotFoundException, SQLException {
        initComponents();
        this.setLocationRelativeTo(null);
        tipo = 0;
        CP = new ConexionPostgres("dbProyecto", "usrUTN", "12345");
        correr();
        //llenar();
    }

    public void correr() {
        String principal[] = {"Código de Especie", "Descripción"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, principal);
        tablaDueño.setModel(modelo);
        this.Select();
    }

    public void llenar() {
        codEspecie.setText((String) modelo.getValueAt(0, 0));
        descripcion.setText((String) modelo.getValueAt(0, 1));
    }

    public void Select() {
        String cod = "";
        String descrip = "";
        try {
            ResultSet datos = CP.select("*", "\"schProyecto\".\"especie\"", "");
            while (datos.next()) {
                cod = datos.getString(1);
                descrip = datos.getString(2);
                Object datostabla[] = {cod, descrip};
                modelo.addRow(datostabla);
            }
        } catch (Exception e) {
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
        tablaDueño = new javax.swing.JTable();
        codEspecie = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        descripcion = new javax.swing.JTextField();
        ok = new javax.swing.JButton();
        regresar = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        insertar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        modificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaDueño.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Código Especie", "Descripción"
            }
        ));
        tablaDueño.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDueñoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDueño);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 360, 150));

        codEspecie.setEditable(false);
        getContentPane().add(codEspecie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 70, -1));

        jLabel1.setText("Código de Especie");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        jLabel2.setText("Descripción");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, -1, -1));

        descripcion.setEditable(false);
        getContentPane().add(descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 130, -1));

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });
        getContentPane().add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, -1, -1));

        regresar.setText("Regresar");
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        getContentPane().add(regresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 280, -1, -1));

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        insertar.setText("Insertar");
        insertar.setFocusable(false);
        insertar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        insertar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertarActionPerformed(evt);
            }
        });
        jToolBar1.add(insertar);

        eliminar.setText("Eliminar");
        eliminar.setFocusable(false);
        eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jToolBar1.add(eliminar);

        modificar.setText("Modificar");
        modificar.setFocusable(false);
        modificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jToolBar1.add(modificar);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 210, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertarActionPerformed
        codEspecie.setText("");
        descripcion.setText("");
        codEspecie.setEditable(true);
        descripcion.setEditable(true);
        tipo = 1;
    }//GEN-LAST:event_insertarActionPerformed

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        if (tipo == 1) {
            String datos = "";
            datos = "('" + this.codEspecie.getText() + "','"
                    + this.descripcion.getText() + "')";
            this.CP.insertar("\"schProyecto\".especie", datos);
            codEspecie.setEditable(false);
            descripcion.setEditable(false);
            correr();
        } else if (tipo == 2) {
            String estado;
            String datos;
            datos = "set \"descripcion\" = '" + this.descripcion.getText()
                    + "' where \"cod_especie\" = '" + this.codEspecie.getText() + "'";
            estado = this.CP.modificar("\"schProyecto\".especie", datos);
            codEspecie.setEditable(false);
            descripcion.setEditable(false);
            this.correr();
        } else if (tipo == 3) {
            String estado;
            String datos;
            datos = " where cod_especie = " + "'" + this.codEspecie.getText() + "'";
            estado = this.CP.borrar("\"schProyecto\".especie", datos);
            correr();
            codEspecie.setText("");
            descripcion.setText("");
        }
        tipo = 0;
    }//GEN-LAST:event_okActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        tipo = 3;
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        codEspecie.setEditable(true);
        descripcion.setEditable(true);
        tipo = 2;
    }//GEN-LAST:event_modificarActionPerformed

    private void tablaDueñoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDueñoMouseClicked
        codEspecie.setText((String) modelo.getValueAt(tablaDueño.getSelectedRow(), 0));
        descripcion.setText((String) modelo.getValueAt(tablaDueño.getSelectedRow(), 1));
    }//GEN-LAST:event_tablaDueñoMouseClicked

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        this.dispose();
        FramePrincipal frame = new FramePrincipal();
        frame.setVisible(true);
    }//GEN-LAST:event_regresarActionPerformed

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
            java.util.logging.Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new FrameMantenimientoEspecie().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(FrameMantenimientoEspecie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codEspecie;
    private javax.swing.JTextField descripcion;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton insertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton modificar;
    private javax.swing.JButton ok;
    private javax.swing.JButton regresar;
    private javax.swing.JTable tablaDueño;
    // End of variables declaration//GEN-END:variables
}
