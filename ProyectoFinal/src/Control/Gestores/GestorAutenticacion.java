package Control.Gestores;

import Control.DAO.ClientesDAO;
//import Control.DAO.ComprarDAO;
import Control.DAO.HistorialInventarioDAO;
import Modelo.Carrito;
import Modelo.ClienteVO;
import Modelo.ProductoVO;
import Vista.Admin.VistaTabla;
import Vista.Clientes.VistaAutenticacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class GestorAutenticacion implements ActionListener {

    private ArrayList<ClienteVO> listaClientesAU;
    private ClienteVO objCliente = new ClienteVO();
    private ClientesDAO objClienteDAO = new ClientesDAO();
    private GestorAutenticacion objAutenticacion;
    private GestorComprar gestorComprar;
    private VistaAutenticacion vista;
    private VistaTabla vistaTabla = new VistaTabla();
    private ArrayList<ProductoVO> listaProductos = new ArrayList<>();
    private ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private String horaFormateada,fechaFormateada;
//    private CompDAO miComprarDAO = new ComprarDAO();
    private HistorialInventarioDAO miHistorialDAO = new HistorialInventarioDAO();
    public String strId;
    public int cantidad;

    public GestorAutenticacion() {
        this.vista = new VistaAutenticacion();
        //Botones
        this.vista.btnAutenticar.addActionListener(this);
    }

    public void iniciar() {

    }

    public void iniciar(GestorComprar vuelta) {
        //Insercion de logica de las funciones necesaria
        gestorComprar = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }
    public void garantia(){
//        this.vistaTabla.mostrarVentana(this.miComprarDAO.cargarDatosTabla( listaCarrito,listaProductos), "PRODUCTOS");
    }
    public void datosCompra( ArrayList<Carrito> listaCarrito,ArrayList<ProductoVO> listaProductos,int cantidad ){
        this.cantidad= cantidad;
        this.listaCarrito=listaCarrito;
        this.listaProductos=listaProductos;
        // Obtener la fecha y hora actual
        LocalDateTime fechaYHoraActual = LocalDateTime.now();

        // Formatear la fecha y hora según tus necesidades
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Obtener la fecha y hora formateadas
       fechaFormateada = fechaYHoraActual.format(formatoFecha);
       horaFormateada= fechaYHoraActual.format(formatoHora);

    }
    public String generarFolio() {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio de 6 dígitos
        String Folio = ("1000" + random.nextInt(9000));

        return Folio;
    }
    public String generarIdCliente() {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio de 6 dígitos
        String Id = ("1000" + random.nextInt(9000));

        return Id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAutenticar) {
            String strNombre = vista.txtNombre.getText();
            String strDireccion = vista.txtDireccion.getText();
            String strTelefono = vista.txtTelefono.getText();
            String strCorreo = vista.txtCorreo.getText();
            listaClientesAU = objClienteDAO.listaDeClientes();
            for (ClienteVO cliente : listaClientesAU) {
                if (strNombre.equals(cliente.getNombre())) {
                    if (strDireccion.equals(cliente.getDireccion()) && strTelefono.equals(cliente.getTelefono()) && strCorreo.equals(cliente.getCorreo())) {
                        vista.msg("AUTENTICACION EXITOSA");
                        vista.msg("EL USUARIO YA REGISTRABA EN LA BASE DE DATOS");
                    } else {
                        objClienteDAO.actualizarDatos(cliente);
                        vista.msg("AUTENTICACION EXITOSA");
                        vista.msg("USUARIO AÑADIDO A LA BASE DE DATOS");
                    }
                }
            }
            objCliente.setId(generarIdCliente());
            objCliente.setNombre(strNombre);
            objCliente.setDireccion(strDireccion);
            objCliente.setTelefono(strTelefono);
            objCliente.setCorreo(strCorreo);
            objClienteDAO.insertarDatos(objCliente);
            vista.msg("AUTENTICACION EXITOSA");
            vista.msg("USUARIO AÑADIDO A LA BASE DE DATOS");
            garantia();
//            comprobante();
//            importacion();
            vista.setVisible(false);
//            gestorComprar.iniciarC();
        }
    }
}
