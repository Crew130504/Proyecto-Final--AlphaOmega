package Control.Gestores;

import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Control.DAO.VentasDAO;
import Modelo.Carrito;
import Modelo.ProductoVO;
import Modelo.VentaVO;
import Vista.Clientes.VistaAutenticacion;
import Vista.Clientes.VistaCarrito;
import Vista.Clientes.VistaComprar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class GestorComprar implements ActionListener {

    private VistaComprar vistaComprar;
    private VistaCarrito vistaCarrito;
    private VistaAutenticacion vistaautenticar;
    private Gestor vuelta;
//    private GestorAutenticacion autenticacion = new GestorAutenticacion();
    private ProductosDAO miProductoDAO = new ProductosDAO();
    private HistorialInventarioDAO miHistorialDAO = new HistorialInventarioDAO();
    private VentasDAO miVentaDAO = new VentasDAO();
    private VentaVO miVenta = new VentaVO();
    private ArrayList<ProductoVO> listaProductos = new ArrayList<>();
    private ArrayList<VentaVO> miventa = new ArrayList<>();
    private ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private double precio;
    private double subTotal = 0;
    private double total = 0;
    private int cantidad = 0;

    public GestorComprar() {
        this.vistaComprar = new VistaComprar();
        this.vistaCarrito = new VistaCarrito();
        this.vistaComprar.btnCarrito.addActionListener(this);
        this.vistaComprar.btnAñadirCarrito.addActionListener(this);
        this.vistaComprar.btnSeleccionarProducto.addActionListener(this);
        this.vistaComprar.btnSeleccionarTipo.addActionListener(this);
        this.vistaComprar.btnVolver.addActionListener(this);
        this.vistaCarrito.btnSeleccionar.addActionListener(this);
        this.vistaCarrito.btnComprar.addActionListener(this);
        this.vistaCarrito.btnVolver.addActionListener(this);
        this.vistaCarrito.btnEliminar.addActionListener(this);
        this.vistaCarrito.btnModificar.addActionListener(this);
    }

    public void iniciar() {
        //Llenar el combo de Tipos
        llenarComboTipo();
        vistaComprar.comboxProductosTipo.addItem("Seleccionar");
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaComprar.setLocationRelativeTo(null);
        this.vistaComprar.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vistaComprar.setVisible(true);
    }

    public void iniciar(Gestor vuelta) {
        //Llenar en combo de Tipos
        llenarComboTipo();
        vistaComprar.comboxProductosTipo.addItem("Seleccionar");
        //Insercion de logica de las funciones necesaria
        this.vuelta = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaComprar.setLocationRelativeTo(null);

        this.vistaComprar.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vistaComprar.setVisible(true);
    }

    public void iniciarCarrito() {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaCarrito.setLocationRelativeTo(null);
        this.vistaCarrito.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vistaCarrito.setVisible(true);
    }

    public void llenarComboTipo() {

        vistaComprar.comboxTipo.addItem("Seleccion Tipo");
        vistaComprar.comboxTipo.addItem("Discos Duros");
        vistaComprar.comboxTipo.addItem("Lectores de Tarjeta");
        vistaComprar.comboxTipo.addItem("Memorias RAM");
        vistaComprar.comboxTipo.addItem("Monitores");
        vistaComprar.comboxTipo.addItem("Mouses");
        vistaComprar.comboxTipo.addItem("Tarjetas de Sonido");
        vistaComprar.comboxTipo.addItem("Tarjetas de Video");
        vistaComprar.comboxTipo.addItem("Teclados");
    }

    public void llenarComboProductos(String nombre) {
        if (nombre.equals("Seleccion Tipo")) {
            this.vistaComprar.error("SELECCIONE UN TIPO");
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
            this.vistaComprar.comboxProductosTipo.removeAllItems(); // Limpia los elementos existentes en el ComboBox

            // Agregar la opción nula o predeterminada al principio del ComboBox
            vistaComprar.comboxProductosTipo.addItem("Seleccionar");
            for (int i = 0; i < listaProductos.size(); i++) {
                vistaComprar.comboxProductosTipo.addItem(listaProductos.get(i).getNombre());
            }
        }
    }

    private boolean nombreExistenteEnComboBox(String nombre) {
        int itemCount = this.vistaCarrito.comboxCarrito.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (nombre.equals(this.vistaCarrito.comboxCarrito.getItemAt(i))) {
                return true; // El nombre ya existe en el ComboBox
            }
        }
        return false; // El nombre no existe en el ComboBox
    }

    public void seleccionTipo() {
        String nombre = (String) this.vistaComprar.comboxProductosTipo.getSelectedItem();
        for (ProductoVO productoSelec : listaProductos) {
            if (productoSelec.getNombre().equals(nombre)) {
                this.vistaComprar.txtNombre.setText(productoSelec.getNombre());
                this.vistaComprar.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                this.vistaComprar.txtFabricante.setText(productoSelec.getFabricante());
                this.vistaComprar.txtPais.setText(productoSelec.getPais());
                this.vistaComprar.txtDescripcion.setText(productoSelec.getDescripcion());
                break;  // Se detiene después de encontrar la primera coincidencia
            }
        }
    }

    public void calcularCompra() {
        subTotal = 0;
        total = 0;
        double iva = 0;
        for (Carrito producto : listaCarrito) {
            subTotal += producto.getImporte();
        }
        iva = subTotal * 0.19;
        total = subTotal + iva;
        this.vistaCarrito.txtSubTotal.setText(String.valueOf(subTotal));
        this.vistaCarrito.txtTotal.setText(String.valueOf(total));
    }

    public boolean buscarCarrito(Carrito producto) {
        for (Carrito existente : listaCarrito) {
            if (producto.getNombre().equals(existente.getNombre())) {
                existente.setCantidad(producto.getCantidad());
                existente.setImporte(existente.getPrecio() * existente.getCantidad());
                return true;
            }
        }
        return false;
    }

    public void llenarComboCarrito() {
        this.vistaCarrito.comboxCarrito.removeAllItems(); // Limpia los elementos existentes en el ComboBox
        this.vistaCarrito.comboxCarrito.addItem("Revisar");
        for (Carrito existente : listaCarrito) {
            vistaCarrito.comboxCarrito.addItem(existente.getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistaComprar.btnVolver) {
            this.vistaComprar.setVisible(false);
            this.vuelta.iniciar();
        }
        if (e.getSource() == this.vistaCarrito.btnVolver) {
            this.vistaCarrito.setVisible(false);
            this.vistaCarrito.limpiar();
            this.vistaComprar.setVisible(true);
        }

        if (e.getSource() == this.vistaComprar.btnCarrito) {
            this.listaProductos = miProductoDAO.listaDeProductos();
            String nombre = this.vistaComprar.txtBuscar.getText();
            for (ProductoVO productoSelec : listaProductos) {
                if (productoSelec.getNombre().equals(nombre)) {
                    this.vistaComprar.txtNombre.setText(productoSelec.getNombre());
                    this.vistaComprar.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                    this.vistaComprar.txtFabricante.setText(productoSelec.getFabricante());
                    this.vistaComprar.txtPais.setText(productoSelec.getPais());
                    this.vistaComprar.txtDescripcion.setText(productoSelec.getDescripcion());
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
        }

        if (e.getSource() == this.vistaComprar.btnSeleccionarTipo) {
            String nombre = (String) this.vistaComprar.comboxTipo.getSelectedItem();
            llenarComboProductos(nombre);
        }
        if (e.getSource() == this.vistaComprar.btnSeleccionarProducto) {
            seleccionTipo();
        }
        if (e.getSource() == this.vistaComprar.btnAñadirCarrito) {
            int menu;
            boolean comprobacion, ciclo = false;
            seleccionTipo();
            String nombre = this.vistaComprar.txtNombre.getText();
            if ((int) this.vistaComprar.spnCantidad.getValue() > 0) {
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(nombre)) {
                        if (((int) this.vistaComprar.spnCantidad.getValue() > productoSelec.getStock()) && ("NO".equals(productoSelec.getEncargo()) || "No".equals(productoSelec.getEncargo()))) {
                            this.vistaComprar.error("Producto no disponible");
                        } else if (productoSelec.getEncargo().equals("Si") || productoSelec.getEncargo().equals("SI")) {
                            do {
                                try {
                                    menu = Integer.parseInt(this.vistaComprar.capturar(
                                            "ESTE PRODUCTO ES POR ENCARGO\nSU COMPRA SERA MARCADA COMO PENDIENTE\nHASTA QUE LLEGUE SU PEDIDO DE NUESTRO PROVEEDORS\n1) ACEPTO\n2) RECHAZO"));
                                    switch (menu) {
                                        case 1 -> {
                                            comprobacion = true;
                                            ciclo = true;
                                            break;
                                        }
                                        case 2 -> {
                                            ciclo = true;
                                            break;
                                        }
                                        // Puedes agregar aquí el código correspondiente para el caso de rechazo
                                        default -> {
                                            this.vistaComprar.error("DIGITE UNA OPCION VALIDA");
                                            ciclo = false;
                                        }
                                    }
                                } catch (NumberFormatException ex) {
                                    this.vistaComprar.error("Por favor, ingrese un número válido.");
                                    comprobacion = false; // Asegura que comprobacion se mantenga en falso si hay una excepción.
                                }
                            } while (!ciclo);
                        }
                        Carrito producto = new Carrito();
                        producto.setNombre(nombre);
                        producto.setPrecio(Double.parseDouble(this.vistaComprar.txtPrecio.getText()));
                        producto.setCantidad((int) this.vistaComprar.spnCantidad.getValue());
                        producto.setImporte(producto.getPrecio() * producto.getCantidad());
                        if (!buscarCarrito(producto)) {
                            listaCarrito.add(producto);
                        }
                        calcularCompra();
                    }
                }
            } else {
                this.vistaComprar.error("Seleccione una cantidad");
            }

        }
        if (e.getSource() == this.vistaComprar.btnCarrito) {
            this.vistaComprar.setVisible(false);
            llenarComboCarrito();
            iniciarCarrito();
        }
        if (e.getSource() == this.vistaCarrito.btnSeleccionar) {
            String nombre = (String) this.vistaCarrito.comboxCarrito.getSelectedItem();
            for (Carrito productoSelec : listaCarrito) {
                if (productoSelec.getNombre().equals(nombre)) {
                    this.vistaCarrito.txtNombre.setText(productoSelec.getNombre());
                    this.vistaCarrito.txtPrecio.setText(String.valueOf(productoSelec.getPrecio()));
                    this.vistaCarrito.spnCantidad.setText(String.valueOf(productoSelec.getCantidad()));
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
        }
        if (e.getSource() == this.vistaCarrito.btnModificar) {
            if (this.vistaCarrito.spnCantidad.getText().isEmpty()
                    || "0".equals(this.vistaCarrito.spnCantidad.getText())) {
                this.vistaCarrito.error("Error en su cantidad,seleccione un item a revisar o elimine el item");

            } else {
                Carrito producto = new Carrito();
                producto.setNombre(this.vistaCarrito.getName());
                producto.setPrecio(Double.parseDouble(this.vistaCarrito.txtPrecio.getText()));
                producto.setCantidad(Integer.parseInt(this.vistaCarrito.spnCantidad.getText()));
                producto.setImporte(producto.getPrecio() * producto.getCantidad());
                for (Carrito existente : listaCarrito) {
                    if (existente.getNombre().equals(this.vistaCarrito.txtNombre.getText())) {
                        existente.setCantidad(producto.getCantidad());
                        existente.setImporte(existente.getPrecio() * existente.getCantidad());
                    }
                }
                calcularCompra();
            }
        }
        if (e.getSource() == this.vistaCarrito.btnEliminar) {
            Carrito producto = new Carrito();
            producto.setNombre(this.vistaCarrito.getName());
            producto.setPrecio(Double.parseDouble(this.vistaCarrito.txtPrecio.getText()));
            producto.setCantidad(Integer.parseInt(this.vistaCarrito.spnCantidad.getText()));
            producto.setImporte(producto.getPrecio() * producto.getCantidad());

            Iterator<Carrito> iterador = listaCarrito.iterator();
            while (iterador.hasNext()) {
                Carrito existente = iterador.next();
                if (existente.getNombre().equals(this.vistaCarrito.txtNombre.getText())) {
                    iterador.remove();  // Utiliza el iterador para eliminar de forma segura
                }
            }

            calcularCompra();
            this.vistaCarrito.limpiar();
            llenarComboCarrito();
            this.vistaCarrito.msg("Producto Eliminado");
        }

    }
}
