package Vista.Admin;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class VistaProductos extends javax.swing.JFrame {

    public VistaProductos() {
        initComponents();
        setIconImage(getIconImage()); 
    }
    

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Fondos/IconoVentana2.png"));
        return retValue;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboxPendientes = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        btnRecibir = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        txtPrecio = new javax.swing.JTextField();
        txtSerie = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMinStock = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEncargo = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtImagen = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPais = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtGarantia = new javax.swing.JTextField();
        txtGarantiaMeses = new javax.swing.JTextField();
        txtGarantiaAnos = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMedidas = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtFabricante = new javax.swing.JTextField();
        comboxProductos = new javax.swing.JComboBox<>();
        btnSeleccionar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtFondo = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboxPendientes.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        comboxPendientes.setForeground(new java.awt.Color(51, 51, 51));
        comboxPendientes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxPendientesItemStateChanged(evt);
            }
        });
        comboxPendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxPendientesActionPerformed(evt);
            }
        });
        getContentPane().add(comboxPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 130, -1));

        btnModificar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(51, 51, 51));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 130, -1));

        btnListar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnListar.setForeground(new java.awt.Color(51, 51, 51));
        btnListar.setText("Listar");
        btnListar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        getContentPane().add(btnListar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 130, -1));

        btnRecibir.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnRecibir.setForeground(new java.awt.Color(51, 51, 51));
        btnRecibir.setText("Recibir");
        btnRecibir.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        btnRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecibir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 130, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(51, 51, 51));
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 250, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel4.setText("Serie");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel3.setText("Precio");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel5.setText("Nombre");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        btnVolver.setFont(new java.awt.Font("Segoe UI Semilight", 3, 14)); // NOI18N
        btnVolver.setForeground(new java.awt.Color(51, 51, 51));
        btnVolver.setText("<--");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });
        getContentPane().add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 60, 20));

        txtPrecio.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(51, 51, 51));
        txtPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPrecioActionPerformed(evt);
            }
        });
        getContentPane().add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 250, -1));

        txtSerie.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtSerie.setForeground(new java.awt.Color(51, 51, 51));
        txtSerie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSerieActionPerformed(evt);
            }
        });
        getContentPane().add(txtSerie, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 250, -1));

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(51, 51, 51));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 550, 810, -1));

        txtId.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtId.setForeground(new java.awt.Color(51, 51, 51));
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 250, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel6.setText("ID");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 70, 20, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("PRODUCTOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 110, -1));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel7.setText("MinStock");
        jLabel7.setToolTipText("");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 320, -1, -1));

        txtMinStock.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtMinStock.setForeground(new java.awt.Color(51, 51, 51));
        txtMinStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinStockActionPerformed(evt);
            }
        });
        getContentPane().add(txtMinStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 250, -1));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel8.setText("Encargo");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, -1, -1));

        txtEncargo.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtEncargo.setForeground(new java.awt.Color(51, 51, 51));
        txtEncargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEncargoActionPerformed(evt);
            }
        });
        getContentPane().add(txtEncargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 250, -1));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel9.setText("Descripcion");
        jLabel9.setToolTipText("");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, -1, -1));

        txtStock.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtStock.setForeground(new java.awt.Color(51, 51, 51));
        txtStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockActionPerformed(evt);
            }
        });
        getContentPane().add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 250, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Segoe UI Semibold", 2, 12)); // NOI18N
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 250, 130));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel10.setText("Stock");
        jLabel10.setToolTipText("");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, -1, -1));

        txtImagen.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtImagen.setForeground(new java.awt.Color(51, 51, 51));
        txtImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtImagenActionPerformed(evt);
            }
        });
        getContentPane().add(txtImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 420, 250, -1));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel11.setText("Imagen");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, -1, -1));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel12.setText("Pais");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 470, -1, -1));

        txtPais.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtPais.setForeground(new java.awt.Color(51, 51, 51));
        txtPais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPaisActionPerformed(evt);
            }
        });
        getContentPane().add(txtPais, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 470, 250, -1));

        txtTipo.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtTipo.setForeground(new java.awt.Color(51, 51, 51));
        txtTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipoActionPerformed(evt);
            }
        });
        getContentPane().add(txtTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 470, 250, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel13.setText("Tipo");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 470, -1, -1));

        txtProveedor.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtProveedor.setForeground(new java.awt.Color(51, 51, 51));
        txtProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 420, 250, -1));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel14.setText("Proveedor");
        jLabel14.setToolTipText("");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 420, -1, -1));

        txtGarantia.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtGarantia.setForeground(new java.awt.Color(51, 51, 51));
        txtGarantia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGarantiaActionPerformed(evt);
            }
        });
        getContentPane().add(txtGarantia, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, 60, -1));

        txtGarantiaMeses.setEditable(false);
        txtGarantiaMeses.setBackground(new java.awt.Color(255, 255, 255));
        txtGarantiaMeses.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtGarantiaMeses.setForeground(new java.awt.Color(51, 51, 51));
        txtGarantiaMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGarantiaMesesActionPerformed(evt);
            }
        });
        getContentPane().add(txtGarantiaMeses, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 380, 60, -1));

        txtGarantiaAnos.setEditable(false);
        txtGarantiaAnos.setBackground(new java.awt.Color(255, 255, 255));
        txtGarantiaAnos.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtGarantiaAnos.setForeground(new java.awt.Color(51, 51, 51));
        txtGarantiaAnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGarantiaAnosActionPerformed(evt);
            }
        });
        getContentPane().add(txtGarantiaAnos, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 380, 60, -1));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("(Dias)");
        jLabel15.setToolTipText("");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 350, -1, 20));

        txtMedidas.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtMedidas.setForeground(new java.awt.Color(51, 51, 51));
        txtMedidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMedidasActionPerformed(evt);
            }
        });
        getContentPane().add(txtMedidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, 250, -1));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel16.setText("Medidas");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, -1, -1));

        txtPeso.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        txtPeso.setForeground(new java.awt.Color(51, 51, 51));
        txtPeso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPesoActionPerformed(evt);
            }
        });
        getContentPane().add(txtPeso, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 270, 250, -1));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel17.setText("Peso (gr)");
        jLabel17.setToolTipText("");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 270, -1, -1));

        txtFabricante.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        txtFabricante.setForeground(new java.awt.Color(51, 51, 51));
        txtFabricante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFabricanteActionPerformed(evt);
            }
        });
        getContentPane().add(txtFabricante, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 220, 250, -1));

        comboxProductos.setFont(new java.awt.Font("Segoe UI Semibold", 3, 14)); // NOI18N
        comboxProductos.setForeground(new java.awt.Color(51, 51, 51));
        comboxProductos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboxProductosItemStateChanged(evt);
            }
        });
        comboxProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboxProductosActionPerformed(evt);
            }
        });
        getContentPane().add(comboxProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, -1));

        btnSeleccionar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSeleccionar.setForeground(new java.awt.Color(51, 51, 51));
        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 130, -1));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel18.setText("Fabricante");
        getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, -1, -1));

        btnAgregar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(51, 51, 51));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 130, -1));

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(51, 51, 51));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, null, new java.awt.Color(102, 102, 102)));
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 130, -1));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel19.setText("Garantia ");
        jLabel19.setToolTipText("");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 370, -1, -1));

        jLabel20.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("(Años)");
        jLabel20.setToolTipText("");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 350, 60, 20));

        jLabel21.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("(Meses)");
        jLabel21.setToolTipText("");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 350, -1, 20));

        txtFondo.setBackground(new java.awt.Color(255, 255, 255));
        txtFondo.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        txtFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Fondos/Icono3.jpg"))); // NOI18N
        getContentPane().add(txtFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 650));

        jLabel22.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(51, 51, 51));
        jLabel22.setText("Serie");
        jLabel22.setToolTipText("");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 170, -1, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI Semibold", 3, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(51, 51, 51));
        jLabel23.setText("Nombre");
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboxPendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxPendientesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxPendientesActionPerformed

    private void txtPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPrecioActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRecibirActionPerformed

    private void comboxPendientesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxPendientesItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxPendientesItemStateChanged

    private void txtSerieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSerieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSerieActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

    }//GEN-LAST:event_btnVolverActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void txtMinStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinStockActionPerformed

    private void txtEncargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEncargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEncargoActionPerformed

    private void txtStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStockActionPerformed

    private void txtImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtImagenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtImagenActionPerformed

    private void txtPaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPaisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPaisActionPerformed

    private void txtTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipoActionPerformed

    private void txtProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtProveedorActionPerformed

    private void txtGarantiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGarantiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGarantiaActionPerformed

    private void txtMedidasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMedidasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMedidasActionPerformed

    private void txtPesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPesoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPesoActionPerformed

    private void txtFabricanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFabricanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFabricanteActionPerformed

    private void comboxProductosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboxProductosItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxProductosItemStateChanged

    private void comboxProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboxProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboxProductosActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void txtGarantiaAnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGarantiaAnosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGarantiaAnosActionPerformed

    private void txtGarantiaMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGarantiaMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGarantiaMesesActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnLimpiar;
    public javax.swing.JButton btnListar;
    public javax.swing.JButton btnModificar;
    public javax.swing.JButton btnRecibir;
    public javax.swing.JButton btnSeleccionar;
    public javax.swing.JButton btnVolver;
    public javax.swing.JComboBox<String> comboxPendientes;
    public javax.swing.JComboBox<String> comboxProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea txtDescripcion;
    public javax.swing.JTextField txtEncargo;
    public javax.swing.JTextField txtFabricante;
    private javax.swing.JLabel txtFondo;
    public javax.swing.JTextField txtGarantia;
    public javax.swing.JTextField txtGarantiaAnos;
    public javax.swing.JTextField txtGarantiaMeses;
    public javax.swing.JTextField txtId;
    public javax.swing.JTextField txtImagen;
    public javax.swing.JTextField txtMedidas;
    public javax.swing.JTextField txtMinStock;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtPais;
    public javax.swing.JTextField txtPeso;
    public javax.swing.JTextField txtPrecio;
    public javax.swing.JTextField txtProveedor;
    public javax.swing.JTextField txtSerie;
    public javax.swing.JTextField txtStock;
    public javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
//Imagen de fondo// End of variables declaration                   
//Imagen de fondo// End of variables declaration                   
//Imagen de fondo// End of variables declaration                   
//Imagen de fondo

    public void limpiar() {//Acción boton limpiar
        txtId.setText("");
        txtNombre.setText("");
        txtSerie.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtMinStock.setText("");
        txtEncargo.setText("");
        txtImagen.setText("");
        txtPais.setText("");
        txtDescripcion.setText("");
        txtFabricante.setText("");
        txtPeso.setText("");
        txtMedidas.setText("");
        txtGarantia.setText("");
        txtProveedor.setText("");
        txtTipo.setText("");
        txtId.setEditable(true);
        txtNombre.setEditable(true);
        txtSerie.setEditable(true);
        txtPrecio.setEditable(true);
        txtStock.setEditable(true);
        txtMinStock.setEditable(true);
        txtEncargo.setEditable(true);
        txtImagen.setEditable(true);
        txtPais.setEditable(true);
        txtDescripcion.setEditable(true);
        txtFabricante.setEditable(true);
        txtPeso.setEditable(true);
        txtMedidas.setEditable(true);
        txtGarantia.setEditable(true);
        txtProveedor.setEditable(true);
        txtTipo.setEditable(true);
    }

    public void error(String txt) {
        JOptionPane.showMessageDialog(null, txt, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void errorConsola(String txt) {
        System.out.print(txt);
    }

    public void msg(String txt) {
        JOptionPane.showMessageDialog(null, txt, "MSG", JOptionPane.INFORMATION_MESSAGE);
    }

}
