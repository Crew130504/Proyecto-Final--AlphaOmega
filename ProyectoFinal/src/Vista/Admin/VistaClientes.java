package Vista.Admin;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.JOptionPane;
public class VistaClientes extends javax.swing.JFrame {

    
    public VistaClientes() {
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboxClientes = new javax.swing.JComboBox<>();
        btnLista = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnSeleccionar = new javax.swing.JButton();
        txtDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        txtCorreo = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboxClientes.setBackground(new java.awt.Color(255, 255, 204));
        comboxClientes.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        comboxClientes.setForeground(new java.awt.Color(51, 51, 51));
        comboxClientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxClientesItemStateChanged(evt);
            }
        });
        comboxClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxClientesActionPerformed(evt);
            }
        });
        getContentPane().add(comboxClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, -1));

        btnLista.setBackground(new java.awt.Color(255, 255, 204));
        btnLista.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnLista.setForeground(new java.awt.Color(51, 51, 51));
        btnLista.setText("Lista");
        btnLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListaActionPerformed(evt);
            }
        });
        getContentPane().add(btnLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 100, -1));

        btnEliminar.setBackground(new java.awt.Color(255, 255, 204));
        btnEliminar.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(51, 51, 51));
        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 100, -1));

        btnModificar.setBackground(new java.awt.Color(255, 255, 204));
        btnModificar.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(51, 51, 51));
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 100, -1));

        btnSeleccionar.setBackground(new java.awt.Color(255, 255, 204));
        btnSeleccionar.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(51, 51, 51));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        txtDireccion.setBackground(new java.awt.Color(255, 255, 204));
        txtDireccion.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(51, 51, 51));
        txtDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDireccionActionPerformed(evt);
            }
        });
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 250, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Telefono");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Correo");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 230, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Direccion");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        btnVolver.setBackground(new java.awt.Color(255, 255, 204));
        btnVolver.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(51, 51, 51));
        btnVolver.setText("<--");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        txtCorreo.setBackground(new java.awt.Color(255, 255, 204));
        txtCorreo.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(51, 51, 51));
        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 230, 250, -1));

        txtTelefono.setBackground(new java.awt.Color(255, 255, 204));
        txtTelefono.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtTelefono.setForeground(new java.awt.Color(51, 51, 51));
        txtTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 250, -1));

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 204));
        btnLimpiar.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 400, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 204));
        txtNombre.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 250, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Nombre");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("CLIENTES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        txtFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/Icono2.png"))); // NOI18N
        getContentPane().add(txtFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 380));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboxClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxClientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxClientesActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void txtDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDireccionActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
      limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void comboxClientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxClientesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxClientesItemStateChanged

    private void txtTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefonoActionPerformed

    private void btnListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnLista;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnSeleccionar;
    public javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> comboxClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    public javax.swing.JTextField txtCorreo;
    public javax.swing.JTextField txtDireccion;
    private javax.swing.JLabel txtFondo;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
//Imagen de fondo// End of variables declaration                   
//Imagen de fondo

   public void limpiar() {//Acción boton limpiar
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText(""); 
        txtCorreo.setText("");
    }
   public void error(String txt){
       JOptionPane.showMessageDialog(null, txt, "Error",JOptionPane.ERROR_MESSAGE);
   }

   public void errorConsola(String txt){
       System.out.print(txt);
   }
    public void msg(String txt){
       JOptionPane.showMessageDialog(null, txt, "Eliminada",JOptionPane.INFORMATION_MESSAGE);
   }
    
    
   
}
