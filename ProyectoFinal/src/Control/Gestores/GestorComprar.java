package Control.Gestores;

import Control.DAO.ClientesDAO;
import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Control.DAO.VentasDAO;
import Modelo.ArchivoSecuencial;
import Modelo.Carrito;
import Modelo.ClienteVO;
import Modelo.ProductoVO;
import Modelo.VentaVO;
import Vista.Clientes.VistaAutenticacion;
import Vista.Clientes.VistaCarrito;
import Vista.Clientes.VistaComprar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorComprar implements ActionListener {

    private final VistaComprar vistaComprar;
    private final VistaCarrito vistaCarrito;
    private VistaAutenticacion vistaAutenticar;
    private Gestor vuelta;

    private final ProductosDAO miProductoDAO = new ProductosDAO();
    private final HistorialInventarioDAO miHistorialDAO = new HistorialInventarioDAO();
    private final VentasDAO miVentaDAO = new VentasDAO();
    private final ClientesDAO objClienteDAO = new ClientesDAO();

    private final ClienteVO objCliente = new ClienteVO();

    private ArrayList<ProductoVO> listaProductos = new ArrayList<>();
    private final ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private ArrayList<ClienteVO> listaClientesAU= new ArrayList<>();

    private double subTotal = 0;
    private double total = 0;

    public GestorComprar() {
        this.vistaComprar = new VistaComprar();
        this.vistaCarrito = new VistaCarrito();
        this.vistaAutenticar = new VistaAutenticacion();
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

        this.vistaAutenticar.btnAutenticar.addActionListener(this);

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

    public void iniciarAutenticar() {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vistaAutenticar.setLocationRelativeTo(null);
        this.vistaAutenticar.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vistaAutenticar.setVisible(true);
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

    public String generarIdCliente() {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio de 6 dígitos
        String Id = ("1000" + random.nextInt(9000));

        return Id;
    }
    
    public String generarFolio(){
         // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio de 6 dígitos
        String Folio = ("1000" + random.nextInt(9000));

        return Folio;
    }
    
    public String generarDetalles(){
        for(Carrito producto:listaCarrito){
            return (producto.getNombre()+"\n"+producto.getCantidad()+"\n"+producto.getPrecio()+"\n"+producto.getImporte());
        }
        return null;
    }
    
    public void generarRecibos(VentaVO venta) {
        ArchivoSecuencial garantia = new ArchivoSecuencial();
        garantia.limpiarArchivo();
        garantia.escribir(venta);
        garantia.cerrar();
        ArchivoSecuencial comprobante = new ArchivoSecuencial();
        comprobante.limpiarArchivo();
        comprobante.escribir(venta);
        comprobante.cerrar();
        ArchivoSecuencial importacion = new ArchivoSecuencial();
        importacion.limpiarArchivo();
        importacion.escribir(venta);
        importacion.cerrar();
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
        if (e.getSource() == this.vistaCarrito.btnComprar) {
            if (listaCarrito.isEmpty()) {
                this.vistaAutenticar.error("Carrito vacio");
            } else {
                iniciarAutenticar();
            }
        }
        if (e.getSource() == this.vistaAutenticar.btnAutenticar) {
            String strNombre = this.vistaAutenticar.txtNombre.getText();
            String strDireccion = this.vistaAutenticar.txtDireccion.getText();
            String strTelefono = this.vistaAutenticar.txtTelefono.getText();
            String strCorreo = this.vistaAutenticar.txtCorreo.getText();
            listaClientesAU = objClienteDAO.listaDeClientes();
            if(listaClientesAU.isEmpty()){
                objCliente.setId(generarIdCliente());
                objCliente.setNombre(strNombre);
                objCliente.setDireccion(strDireccion);
                objCliente.setTelefono(strTelefono);
                objCliente.setCorreo(strCorreo);
                objClienteDAO.insertarDatos(objCliente);
                this.vistaAutenticar.msg("AUTENTICACION EXITOSA\nUSUARIO AÑADIDO A LA BASE DE DATOS");
                this.vistaAutenticar.msg("USTED ES NUESTRO PRIMER CLIENTE");
            }else{
                for (ClienteVO cliente : listaClientesAU) {
                    if (strNombre.equals(cliente.getNombre())) {
                        if (strDireccion.equals(cliente.getDireccion()) && strTelefono.equals(cliente.getTelefono()) && strCorreo.equals(cliente.getCorreo())) {
                            this.vistaAutenticar.msg("AUTENTICACION EXITOSA");
                            this.vistaAutenticar.msg("EL USUARIO YA REGISTRABA EN LA BASE DE DATOS");
                        } else {
                            objClienteDAO.actualizarDatos(cliente);
                            this.vistaAutenticar.msg("AUTENTICACION EXITOSA");
                            this.vistaAutenticar.msg("DATOS ACTUALIZADOS\nEL USUARIO YA REGISTRABA EN LA BASE DE DATOS");
                        }
                    } else {
                        objCliente.setId(generarIdCliente());
                        objCliente.setNombre(strNombre);
                        objCliente.setDireccion(strDireccion);
                        objCliente.setTelefono(strTelefono);
                        objCliente.setCorreo(strCorreo);
                        objClienteDAO.insertarDatos(objCliente);
                        this.vistaAutenticar.msg("AUTENTICACION EXITOSA");
                        this.vistaAutenticar.msg("USUARIO AÑADIDO A LA BASE DE DATOS");
                        break;
                    }
                }
            }
            this.vistaAutenticar.limpiar();
            this.vistaAutenticar.setVisible(false);
            // Obtén la fecha y hora actual
            LocalDateTime fechaHoraActual = LocalDateTime.now();

            // Formatea la fecha como DATE (YYYY-MM-DD)
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaFormateada = fechaHoraActual.format(formatoFecha);

            // Formatea la hora como TIME (HH:MM:SS)
            DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            String horaFormateada = fechaHoraActual.format(formatoHora);
            String estado=this.miProductoDAO.salida(listaCarrito);
            VentaVO miventa = new VentaVO(generarFolio(),fechaFormateada,horaFormateada,estado,generarDetalles(),subTotal,total);
            try {
                this.miVentaDAO.insertarDatos(miventa);
            } catch (ParseException ex) {
                Logger.getLogger(GestorComprar.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.miHistorialDAO.salida(listaCarrito);
            generarRecibos(miventa);

//            generarRecibos();
        }

    }
}
