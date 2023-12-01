package Control.Gestores;

import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Modelo.DiscosDuros;
import Modelo.LectoresdeTarjeta;
import Modelo.MemoriasRAM;
import Modelo.Monitores;
import Modelo.Mouses;
import Modelo.ProductoVO;
import Modelo.TarjetasdeSonido;
import Modelo.TarjetasdeVideo;
import Modelo.Teclados;
import Vista.Admin.VistaInventario;
import Vista.Admin.VistaProductos;
import Vista.Admin.VistaTabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GestorProductos implements ActionListener {

    private VistaProductos vista;
    private VistaInventario vistaI;
    private VistaInventario vistaInvi = new VistaInventario();
    private DecimalFormat df = new DecimalFormat("#.##");
    private VistaTabla vistaTabla = new VistaTabla();
    private GestorMenu vuelta;
    private GestorMenu vueltaI;
    private ProductosDAO miProductoDAO = new ProductosDAO();
    private HistorialInventarioDAO miHistorialDAO = new HistorialInventarioDAO();
    private ArrayList<ProductoVO> listaProductos = miProductoDAO.listaDeProductos();
    private ArrayList<ProductoVO> listadePendientesporStock = miProductoDAO.listadePendientesporStock();

    public GestorProductos() {
        this.vista = new VistaProductos();
        this.vistaI = new VistaInventario();
        this.vistaI.btnHistorial.addActionListener(this);
        this.vistaI.btnReporte.addActionListener(this);
        this.vistaI.btnInventario.addActionListener(this);
        this.vistaI.btnSolicitar.addActionListener(this);
        this.vistaI.btnTotal.addActionListener(this);
        this.vistaI.btnVolver.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnListar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnRecibir.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
        this.vistaInvi.btnSolicitar.addActionListener(this);
    }

    public void iniciar(GestorMenu vuelta) {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vuelta = vuelta;
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        llenarComboProductos();

        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciar() {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        llenarComboRecibir();
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciarI(GestorMenu vuelta) {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vueltaI = vuelta;
        this.vistaI.setLocationRelativeTo(null);
        this.vistaI.setResizable(false);
        // Hace visible la vista
        this.vistaI.setVisible(true);
    }

    public void iniciarI() {

        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        // Hace visible la vista
        this.vista.setVisible(true);
    }

    public void llenarComboRecibir() {
        this.vista.comboxPendientes.removeAllItems(); // Limpia los elementos existentes en el ComboBox

        listadePendientesporStock = miProductoDAO.listadePendientesporStock();
        // Agregar la opción nula o predeterminada al principio del ComboBox
        vista.comboxPendientes.addItem("Pendientes");
        for (int i = 0; i < listadePendientesporStock.size(); i++) {
            vista.comboxPendientes.addItem(listadePendientesporStock.get(i).getNombre());
        }
        if (vista.comboxPendientes.getItemCount() == 1 && "Pendientes".equals(vista.comboxPendientes.getItemAt(0))) {
            vista.comboxPendientes.removeItem("Pendientes");
        }

    }

    private void llenarComboProductos() {
        this.vista.comboxProductos.removeAllItems(); // Limpia los elementos existentes en el ComboBox

        miProductoDAO = new ProductosDAO();
        listaProductos = miProductoDAO.listaDeProductos();
        // Agregar la opción nula o predeterminada al principio del ComboBox
        vista.comboxProductos.addItem("Seleccionar");

        for (int i = 0; i < listaProductos.size(); i++) {
            vista.comboxProductos.addItem(listaProductos.get(i).getNombre());
        }

    }

    public ProductoVO comprobarTipo(String tipo) {
        if (null == tipo) {
            return null;
        } else {
            return switch (tipo) {
                case "Discos Duros" ->
                    new DiscosDuros();
                case "Lectores de Tarjeta" ->
                    new LectoresdeTarjeta();
                case "Memorias RAM" ->
                    new MemoriasRAM();
                case "Monitores" ->
                    new Monitores();
                case "Mouses" ->
                    new Mouses();
                case "Tarjetas de Sonido" ->
                    new TarjetasdeSonido();
                case "Tarjetas de Video" ->
                    new TarjetasdeVideo();
                case "Teclados" ->
                    new Teclados();
                default ->
                    null;
            };
        }
    }

    private boolean camposTextoVacios() {
        return this.vista.txtId.getText().isEmpty()
                || this.vista.txtNombre.getText().isEmpty()
                || this.vista.txtSerie.getText().isEmpty()
                || this.vista.txtPrecio.getText().isEmpty()
                || this.vista.txtStock.getText().isEmpty()
                || this.vista.txtMinStock.getText().isEmpty()
                || this.vista.txtEncargo.getText().isEmpty()
                || this.vista.txtImagen.getText().isEmpty()
                || this.vista.txtDescripcion.getText().isEmpty()
                || this.vista.txtPais.getText().isEmpty()
                || this.vista.txtFabricante.getText().isEmpty()
                || this.vista.txtPeso.getText().isEmpty()
                || this.vista.txtMedidas.getText().isEmpty()
                || this.vista.txtGarantia.getText().isEmpty()
                || this.vista.txtProveedor.getText().isEmpty()
                || this.vista.txtTipo.getText().isEmpty();
    }

    private boolean nombreExistenteEnComboBox(String nombre) {
        int itemCount = this.vista.comboxProductos.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (nombre.equals(this.vista.comboxProductos.getItemAt(i))) {
                return true; // El nombre ya existe en el ComboBox
            }
        }
        return false; // El nombre no existe en el ComboBox
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnAgregar) {
            listaProductos = miProductoDAO.listaDeProductos();
            String id = this.vista.txtId.getText();
            String nombre = this.vista.txtNombre.getText();
            String serie = this.vista.txtSerie.getText();
            String precio = this.vista.txtPrecio.getText();
            String stock = this.vista.txtStock.getText();
            String minStock = this.vista.txtMinStock.getText();
            String encargo = this.vista.txtEncargo.getText();
            String imagen = this.vista.txtImagen.getText();
            String pais = this.vista.txtPais.getText();
            String descripcion = this.vista.txtDescripcion.getText();
            String fabricante = this.vista.txtFabricante.getText();
            String peso = this.vista.txtId.getText();
            String medidas = this.vista.txtMedidas.getText();
            String garantia = this.vista.txtGarantia.getText();
            String proveedor = this.vista.txtProveedor.getText();
            String tipo = this.vista.txtTipo.getText();
            if (camposTextoVacios()) {
                this.vista.error("TODOS LOS CAMPOS DEBEN ESTAR LLENOS");
            } else {
                ProductoVO miProducto = comprobarTipo(tipo);
                if (miProducto == null) {
                    this.vista.error("NO ES UN TIPO VALIDO");
                } else {
                    // Verificar si el nombre ya está en el ComboBox
                    if (!nombreExistenteEnComboBox(nombre)) {
                        // El nombre no existe en el ComboBox, proceder con la inserción
                        double dprecio = Double.parseDouble(precio);
                        int iStock = Integer.parseInt(stock);
                        int iMinStock = Integer.parseInt(minStock);
                        int iGarantia = Integer.parseInt(garantia);
                        miProducto.setId(id);
                        miProducto.setNombre(nombre);
                        miProducto.setSerie(serie);
                        miProducto.setPrecio(dprecio);
                        if ((this.vista.txtEncargo.getText().equals("Si")
                                || this.vista.txtEncargo.getText().equals("SI") && Integer.parseInt(this.vista.txtStock.getText()) > 0)) {
                            this.vista.error("UN PRODUCTO POR ENCARGO NO PUEDE TENER STOCK EN TIENDA");
                        } else {
                            miProducto.setStock(iStock);
                            miProducto.setMinStock(iMinStock);
                            miProducto.setEncargo(encargo);
                            miProducto.setImagen(imagen);
                            miProducto.setDescripcion(descripcion);
                            miProducto.setPais(pais);
                            miProducto.setFabricante(fabricante);
                            miProducto.setPeso(peso);
                            miProducto.setMedidas(medidas);
                            miProducto.setGarantia(iGarantia);
                            miProducto.setProveedor(proveedor);
                            miProducto.setTipo(tipo);
                            // Inserta los datos en la base de datos
                            miProductoDAO.insertarDatos(miProducto);
                            miHistorialDAO.ingreso(miProducto);
                            llenarComboProductos();
                            this.vista.msg("PRODUCTO AGREGADO");
                            this.vista.limpiar();
                        }
                    } else {
                        // Mostrar un mensaje de error si el nombre ya existe en el ComboBox
                        this.vista.error("YA EXISTE");
                    }
                }

            }
        }
        if (e.getSource() == this.vista.btnSeleccionar) {
            this.vista.txtStock.setEditable(false);
            this.vista.txtTipo.setEditable(false);
            this.vista.txtId.setEditable(false);

            this.vista.txtNombre.setEditable(true);
            this.vista.txtSerie.setEditable(true);
            this.vista.txtPrecio.setEditable(true);
            this.vista.txtMinStock.setEditable(true);
            this.vista.txtEncargo.setEditable(true);
            this.vista.txtImagen.setEditable(true);
            this.vista.txtPais.setEditable(true);
            this.vista.txtDescripcion.setEditable(true);
            this.vista.txtFabricante.setEditable(true);
            this.vista.txtPeso.setEditable(true);
            this.vista.txtMedidas.setEditable(true);
            this.vista.txtGarantia.setEditable(true);
            this.vista.txtProveedor.setEditable(true);
            listaProductos = miProductoDAO.listaDeProductos();
            String nombre = (String) this.vista.comboxProductos.getSelectedItem();
            if ("Seleccionar".equals(nombre)) {
                this.vista.msg("SELECCIONE UN PRODUCTO");
            } else {
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(nombre)) {
                        this.vista.txtId.setText(productoSelec.getId());
                        this.vista.txtNombre.setText(productoSelec.getNombre());
                        this.vista.txtSerie.setText(productoSelec.getSerie());
                        this.vista.txtSerie.setText(productoSelec.getSerie());
                        this.vista.txtPrecio.setText(Double.toString(productoSelec.getPrecio()));
                        this.vista.txtStock.setText(Integer.toString(productoSelec.getStock()));
                        this.vista.txtMinStock.setText(Integer.toString(productoSelec.getMinStock()));
                        this.vista.txtEncargo.setText(productoSelec.getEncargo());
                        this.vista.txtImagen.setText(productoSelec.getImagen());
                        this.vista.txtDescripcion.setText(productoSelec.getDescripcion());
                        this.vista.txtPais.setText(productoSelec.getPais());
                        this.vista.txtFabricante.setText(productoSelec.getFabricante());
                        this.vista.txtPeso.setText(productoSelec.getPeso());
                        this.vista.txtMedidas.setText(productoSelec.getMedidas());
                        this.vista.txtGarantia.setText(Integer.toString(productoSelec.getGarantia()));
                        double garantiaMeses = productoSelec.getGarantia() / (30.44);
                        double garantiaAnos = productoSelec.getGarantia() / (365.25);
                        this.vista.txtGarantiaMeses.setText(df.format(garantiaMeses));
                        this.vista.txtGarantiaAnos.setText(df.format(garantiaAnos));
                        this.vista.txtProveedor.setText(productoSelec.getProveedor());
                        this.vista.txtTipo.setText(productoSelec.getTipo());
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }

        }
        if (e.getSource() == this.vista.btnModificar) {
            listaProductos = miProductoDAO.listaDeProductos();
            String nombre = (String) this.vista.comboxProductos.getSelectedItem();
            if (camposTextoVacios()) {
                this.vista.msg("Por favor, completa todos los campos antes de modificar el producto.");
                return; // Detener la ejecución si hay campos vacíos
            }
            for (ProductoVO productoSelec : listaProductos) {
                if (productoSelec.getNombre().equals(nombre)) {

                    productoSelec.setId(this.vista.txtId.getText());
                    productoSelec.setNombre(this.vista.txtNombre.getText());
                    productoSelec.setSerie(this.vista.txtSerie.getText());
                    productoSelec.setPrecio(Double.parseDouble(this.vista.txtPrecio.getText()));
                    productoSelec.setMinStock(Integer.parseInt(this.vista.txtMinStock.getText()));

                    productoSelec.setStock(Integer.parseInt(this.vista.txtStock.getText()));
                    productoSelec.setEncargo(this.vista.txtEncargo.getText());
                    productoSelec.setImagen(this.vista.txtImagen.getText());
                    productoSelec.setDescripcion(this.vista.txtDescripcion.getText());
                    productoSelec.setPais(this.vista.txtPais.getText());
                    productoSelec.setFabricante(this.vista.txtFabricante.getText());
                    productoSelec.setPeso(this.vista.txtPeso.getText());
                    productoSelec.setMedidas(this.vista.txtMedidas.getText());
                    productoSelec.setGarantia(Integer.parseInt(this.vista.txtGarantia.getText()));
                    productoSelec.setProveedor(this.vista.txtProveedor.getText());
                    productoSelec.setTipo(this.vista.txtTipo.getText());
                    this.miProductoDAO.actualizarDatos(productoSelec);
                    this.vista.msg("PRODUCTO MODIFICADO");
                    this.vista.limpiar();
                    llenarComboProductos();
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
        }
        if (e.getSource() == this.vista.btnEliminar) {
            listaProductos = miProductoDAO.listaDeProductos();
            String nombre = (String) this.vista.comboxProductos.getSelectedItem();
            for (ProductoVO productoSelec : listaProductos) {
                if (productoSelec.getNombre().equals(nombre)) {
                    this.listaProductos.remove(productoSelec);
                    this.miProductoDAO.eliminarProducto(productoSelec.getId());
                    this.vista.msg("PRODUCTO ELIMINADO");
                    this.vista.limpiar();
                    llenarComboProductos();
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
        }
        if (e.getSource() == this.vista.btnListar) {
            this.vistaTabla.mostrarVentana(this.miProductoDAO.cargarDatosTabla(), "PRODUCTOS");
        }
        if (e.getSource() == this.vista.btnVolver) {
            this.vista.setVisible(false);
            this.vuelta.iniciar();
        }
        if (e.getSource() == this.vista.btnRecibir) {
            listadePendientesporStock = this.miProductoDAO.listadePendientesporStock();
            String nombre = (String) this.vista.comboxPendientes.getSelectedItem();
            for (ProductoVO miProducto : listaProductos) {
                if (miProducto.getNombre().equals(nombre)) {
                    miProducto.setStock(miProducto.getMinStock() * 2);
                    this.miProductoDAO.actualizarDatos(miProducto);
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
            this.vista.msg("PRODUCTO RECIBIDO");
            this.vista.txtId.setEditable(false);
            this.vista.txtNombre.setEditable(false);
            this.vista.txtSerie.setEditable(false);
            this.vista.txtPrecio.setEditable(false);
            this.vista.txtStock.setEditable(false);
            this.vista.txtMinStock.setEditable(false);
            this.vista.txtEncargo.setEditable(false);
            this.vista.txtImagen.setEditable(false);
            this.vista.txtPais.setEditable(false);
            this.vista.txtDescripcion.setEditable(false);
            this.vista.txtFabricante.setEditable(false);
            this.vista.txtPeso.setEditable(false);
            this.vista.txtMedidas.setEditable(false);
            this.vista.txtGarantia.setEditable(false);
            this.vista.txtProveedor.setEditable(false);
            this.vista.txtTipo.setEditable(false);

            if ("Seleccionar".equals(nombre)) {
                this.vista.msg("SELECCIONE UN PRODUCTO");
            } else {
                for (ProductoVO productoSelec : listadePendientesporStock) {
                    if (productoSelec.getNombre().equals(nombre)) {
                        this.vista.txtId.setText(productoSelec.getId());
                        this.vista.txtNombre.setText(productoSelec.getNombre());
                        this.vista.txtSerie.setText(productoSelec.getSerie());
                        this.vista.txtSerie.setText(productoSelec.getSerie());
                        this.vista.txtPrecio.setText(Double.toString(productoSelec.getPrecio()));
                        this.vista.txtStock.setText(miProductoDAO.consultarStockProducto(productoSelec));
                        this.vista.txtMinStock.setText(Integer.toString(productoSelec.getMinStock()));
                        this.vista.txtEncargo.setText(productoSelec.getEncargo());
                        this.vista.txtImagen.setText(productoSelec.getImagen());
                        this.vista.txtDescripcion.setText(productoSelec.getDescripcion());
                        this.vista.txtPais.setText(productoSelec.getPais());
                        this.vista.txtFabricante.setText(productoSelec.getFabricante());
                        this.vista.txtPeso.setText(productoSelec.getPeso());
                        this.vista.txtMedidas.setText(productoSelec.getMedidas());
                        this.vista.txtGarantia.setText(Integer.toString(productoSelec.getGarantia()));
                        double garantiaMeses = productoSelec.getGarantia() / (30.44);
                        double garantiaAnos = productoSelec.getGarantia() / (365.25);
                        this.vista.txtGarantiaMeses.setText(df.format(garantiaMeses));
                        this.vista.txtGarantiaAnos.setText(df.format(garantiaAnos));
                        this.vista.txtProveedor.setText(productoSelec.getProveedor());
                        this.vista.txtTipo.setText(productoSelec.getTipo());
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }
            llenarComboRecibir();
            this.vista.comboxPendientes.removeItem("Pendientes");
        }
        if (e.getSource() == this.vistaI.btnVolver) {
            this.vistaI.setVisible(false);
            this.vueltaI.iniciar();
        }
        if (e.getSource() == this.vistaI.btnReporte) {
            listaProductos = miProductoDAO.listaDeProductos();
            this.vistaTabla.mostrarVentana(this.miProductoDAO.consultarProductosBajos(listaProductos), "PRODUCTOS BAJOS EN STOCK");
        }
        if (e.getSource() == this.vistaI.btnHistorial) {
            listaProductos = miProductoDAO.listaDeProductos();
            this.vistaTabla.mostrarVentana(this.miHistorialDAO.cargarDatosTabla(), "HISTORIAL DE MOVIMIENTOS");
        }
        if (e.getSource() == this.vistaI.btnInventario) {
            listaProductos = miProductoDAO.listaDeProductos();
            this.vistaTabla.mostrarVentana(this.miProductoDAO.consultarCantidadInventario(), "INVENTARIO");
            this.miProductoDAO.consultarStock();
        }
        if (e.getSource() == this.vistaI.btnTotal) {
            listaProductos = miProductoDAO.listaDeProductos();
            this.vistaTabla.mostrarVentana(this.miProductoDAO.consultarPrecioInventario(), "PRECIO DEL INVENTARIO");
            this.miProductoDAO.consultarTotal();
        }

        if (e.getSource() == this.vistaI.btnSolicitar) {
            llenarComboRecibir();
            this.vista.msg("SOLICITANDO FALTANTES");
            this.vistaTabla.mostrarVentana(this.miProductoDAO.cargarDatosTabla(), "PRODUCTOS");
        }

    }

}
