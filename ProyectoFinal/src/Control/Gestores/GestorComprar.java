package Control.Gestores;

import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Modelo.Carrito;
import Modelo.ProductoVO;
import Vista.Clientes.VistaCarrito;
import Vista.Clientes.VistaComprar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestorComprar implements ActionListener {

    private VistaComprar vista;
    private VistaCarrito vistaC;
    private Gestor vuelta;
//    private GestorAutenticacion autenticacion = new GestorAutenticacion();
    private ProductosDAO miProductoDAO = new ProductosDAO();
    private HistorialInventarioDAO miHistorialDAO = new HistorialInventarioDAO();
    private ArrayList<ProductoVO> listaProductos = new ArrayList<>();
    private ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private double subtotal = 0;
    int cantidad = 0;

    public GestorComprar() {
        this.vista = new VistaComprar();
        this.vistaC = new VistaCarrito();
        this.vista.btnCarrito.addActionListener(this);
        this.vista.btnAñadirCarrito.addActionListener(this);
        this.vista.btnSeleccionarProducto.addActionListener(this);
        this.vista.btnSeleccionarTipo.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
        this.vistaC.btnSeleccionar.addActionListener(this);
        this.vistaC.btnComprar.addActionListener(this);
    }

    public void iniciar() {
        //Llenar el combo de Tipos
        llenarComboTipo();
        vista.comboxProductosTipo.addItem("Seleccionar");
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciar(Gestor vuelta) {
        //Llenar en combo de Tipos
        llenarComboTipo();
        vista.comboxProductosTipo.addItem("Seleccionar");
        //Insercion de logica de las funciones necesaria
        this.vuelta = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciarC() {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaC.setLocationRelativeTo(null);
        this.vistaC.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vistaC.setVisible(true);
    }

    public void llenarComboTipo() {

        vista.comboxTipo.addItem("Seleccion Tipo");
        vista.comboxTipo.addItem("Discos Duros");
        vista.comboxTipo.addItem("Lectores de Tarjeta");
        vista.comboxTipo.addItem("Memorias RAM");
        vista.comboxTipo.addItem("Monitores");
        vista.comboxTipo.addItem("Mouses");
        vista.comboxTipo.addItem("Tarjetas de Sonido");
        vista.comboxTipo.addItem("Tarjetas de Video");
        vista.comboxTipo.addItem("Teclados");
    }

    public void llenarComboProductos(String nombre) {
        if (nombre.equals("Seleccion Tipo")) {
            this.vista.error("SELECCIONE UN TIPO");
        } else {
            switch (nombre) {
                case "Discos Duros" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Discos Duros");
                    break;
                }
                case "Lectores de Tarjeta" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Lectores de Tarjeta");
                    break;
                }
                case "Memorias RAM" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Memorias RAM");
                    break;
                }
                case "Monitores" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Monitores");
                    break;
                }
                case "Mouses" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Mouses");
                    break;
                }
                case "Tarjetas de Sonido" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Tajetas de Sonido");
                    break;
                }
                case "Tarjetas de Video" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Tajetas de Video");
                    break;
                }
                case "Teclados" -> {
                    listaProductos = this.miProductoDAO.listaDeProductosTipo("Teclados");
                    break;
                }
                default -> {
                }
            }
            this.vista.comboxProductosTipo.removeAllItems(); // Limpia los elementos existentes en el ComboBox

            // Agregar la opción nula o predeterminada al principio del ComboBox
            vista.comboxProductosTipo.addItem("Seleccionar");
            for (int i = 0; i < listaProductos.size(); i++) {
                vista.comboxProductosTipo.addItem(listaProductos.get(i).getNombre());
            }
        }
    }

    private boolean nombreExistenteEnComboBox(String nombre) {
        int itemCount = this.vistaC.comboxCarrito.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (nombre.equals(this.vistaC.comboxCarrito.getItemAt(i))) {
                return true; // El nombre ya existe en el ComboBox
            }
        }
        return false; // El nombre no existe en el ComboBox
    }

    public void llenarCarrito(String nombre, int cantidad) {
        Carrito miProducto = new Carrito(nombre, cantidad);
        listaCarrito.add(miProducto);
        for (Carrito producto : listaCarrito) {
            if (producto.getNombre().equals(nombre)) {
                producto.setCantidad(cantidad);
                break;
            }
        }
        this.listaProductos = miProductoDAO.listaDeProductos();
        String id = null;
        for (ProductoVO productoSelec : listaProductos) {
            if (productoSelec.getNombre().equals(nombre)) {
                id = productoSelec.getId();
                break;  // Se detiene después de encontrar la primera coincidencia
            }
        }
        if (!nombreExistenteEnComboBox("SELECCIONAR")) {
            this.vistaC.comboxCarrito.addItem("SELECCIONAR");
        }

        ProductoVO productoCarrito = this.miProductoDAO.consultarporNombre(id);
        if (!nombreExistenteEnComboBox(productoCarrito.getNombre()) && nombreExistenteEnComboBox("SELECCIONAR")) {
            this.vistaC.comboxCarrito.addItem(productoCarrito.getNombre());
        }

    }

    public void seleccionTipo() {
        String nombre = (String) this.vista.comboxProductosTipo.getSelectedItem();
        if (nombre.equals("Seleccionar")) {
            this.vista.limpiar();
        }
        for (ProductoVO productoSelec : listaProductos) {
            if (productoSelec.getNombre().equals(nombre)) {
                this.vista.txtNombre.setText(productoSelec.getNombre());
                this.vista.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                this.vista.txtFabricante.setText(productoSelec.getFabricante());
                this.vista.txtPais.setText(productoSelec.getPais());
                this.vista.txtDescripcion.setText(productoSelec.getDescripcion());
                break;  // Se detiene después de encontrar la primera coincidencia
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnVolver) {
            this.vista.setVisible(false);
            this.vuelta.iniciar();
        }
        if (e.getSource() == this.vista.btnCarrito) {
            this.listaProductos = miProductoDAO.listaDeProductos();
            String nombre = this.vista.txtBuscar.getText();
            for (ProductoVO productoSelec : listaProductos) {
                if (productoSelec.getNombre().equals(nombre)) {
                    this.vista.txtNombre.setText(productoSelec.getNombre());
                    this.vista.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                    this.vista.txtFabricante.setText(productoSelec.getFabricante());
                    this.vista.txtPais.setText(productoSelec.getPais());
                    this.vista.txtDescripcion.setText(productoSelec.getDescripcion());
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
        }
        if (e.getSource() == this.vista.btnSeleccionarTipo) {
            String nombre = (String) this.vista.comboxTipo.getSelectedItem();
            llenarComboProductos(nombre);
        }
        if (e.getSource() == this.vista.btnSeleccionarProducto) {
            seleccionTipo();
        }
        if (e.getSource() == this.vista.btnAñadirCarrito) {
            int menu;
            boolean comprobacion, ciclo = false;
            seleccionTipo();
            cantidad += (int) this.vista.spnCantidad.getValue();
            if (cantidad > 0) {
                String nombre = this.vista.txtNombre.getText();
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(nombre)) {
                        if ((cantidad > productoSelec.getStock()) && ("NO".equals(productoSelec.getEncargo()) || "No".equals(productoSelec.getEncargo()))) {
                            this.vista.error("Sin Tanto Stock");
                        } else if (productoSelec.getEncargo().equals("Si") || productoSelec.getEncargo().equals("SI")) {
                            do {
                                try {
                                    menu = Integer.parseInt(this.vista.capturar(
                                            "ESTE PRODUCTO ES POR ENCARGO\n SU COMPRA SERA MARCADA COMO PENDIENTE\nHASTA QUE LLEGUE SU PEDIDO DE NUESTRO PROVEEDORS\n1) ACEPTO\n2) RECHAZO"));
                                    switch (menu) {
                                        case 1 -> {
                                            comprobacion = true;
                                            ciclo = true;
                                            break;
                                        }
                                        case 2 -> {
                                            comprobacion = false;
                                            ciclo = true;
                                            break;
                                        }
                                        // Puedes agregar aquí el código correspondiente para el caso de rechazo
                                        default -> {
                                            this.vista.error("DIGITE UNA OPCION VALIDA");
                                            ciclo = false;
                                            comprobacion = false; // Asegura que comprobacion se mantenga en falso si la opción no es válida.
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    this.vista.error("Por favor, ingrese un número válido.");
                                    comprobacion = false; // Asegura que comprobacion se mantenga en falso si hay una excepción.
                                }
                            } while (!ciclo);
                            if (comprobacion) {
                                this.vistaC.spnCantidad.setText(String.valueOf(cantidad));
                                llenarCarrito(nombre, Integer.parseInt(this.vistaC.spnCantidad.getText()));
                            }
                        } else {
                            this.vistaC.spnCantidad.setText(String.valueOf(cantidad));
                            llenarCarrito(nombre, Integer.parseInt(this.vistaC.spnCantidad.getText()));
                        }
                    }
                }
            } else {
                this.vista.error("SELECCIONE UNA CANTIDAD");
            }
            for (Carrito productorr : listaCarrito) {
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(productorr.getNombre())) {
                        subtotal += (productoSelec.getPrecio() * productorr.getCantidad());
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }
            this.vistaC.txtSubTotal.setText(String.valueOf(subtotal));
            this.vistaC.txtTotal.setText(String.valueOf((0.19 * subtotal)));
        }
        if (e.getSource() == this.vista.btnCarrito) {
            this.vista.setVisible(false);
            iniciarC();
        }
        if (e.getSource() == this.vistaC.btnSeleccionar) {
            String nombre = (String) this.vistaC.comboxCarrito.getSelectedItem();
            if ("SELECCIONAR".equals(nombre)) {
                this.vistaC.msg("SELECCIONE UN PRODUCTO PARA REVISAR");
            } else {
                for (Carrito producto : listaCarrito) {
                    if (producto.getNombre().equals(nombre)) {
                        for (ProductoVO productoSelec : listaProductos) {
                            if (productoSelec.getNombre().equals(producto.getNombre())) {
                                this.vistaC.txtNombre.setText(productoSelec.getNombre());
                                this.vistaC.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                                this.vistaC.spnCantidad.setText(String.valueOf(producto.getCantidad()));
                                subtotal += (productoSelec.getPrecio() * producto.getCantidad());
                                break;  // Se detiene después de encontrar la primera coincidencia
                            }
                        }
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }
            llenarCarrito(nombre, Integer.parseInt(this.vistaC.spnCantidad.getText()));
            for (Carrito productorr : listaCarrito) {
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(productorr.getNombre())) {
                        subtotal += (productoSelec.getPrecio() * productorr.getCantidad());
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }
            this.vistaC.txtSubTotal.setText(String.valueOf(subtotal));
            this.vistaC.txtTotal.setText(String.valueOf((0.19 * subtotal)));
        }
        if (e.getSource() == this.vistaC.btnComprar) {
            String nombre = (String) this.vistaC.comboxCarrito.getSelectedItem();
            this.vistaC.setVisible(false);
            for (Carrito producto : listaCarrito) {
                if (producto.getNombre().equals(nombre)) {
                    for (ProductoVO productoSelec : listaProductos) {
                        if (productoSelec.getNombre().equals(producto.getNombre())) {
                            this.vistaC.txtNombre.setText(productoSelec.getNombre());
                            this.vistaC.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                            this.vistaC.spnCantidad.setText(String.valueOf(producto.getCantidad()));
                            subtotal += (productoSelec.getPrecio() * producto.getCantidad());
                            break;  // Se detiene después de encontrar la primera coincidencia
                        }
                    }
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
//            autenticacion.datosCompra(listaCarrito,listaProductos,cantidad);
//            autenticacion.iniciar(this);
        }

    }

}
