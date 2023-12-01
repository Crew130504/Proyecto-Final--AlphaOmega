package Control.Gestores;

import Control.DAO.ClientesDAO;
import Modelo.ClienteVO;
import Vista.Clientes.VistaAutenticacion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class GestorAutenticacion implements ActionListener {

    private ArrayList<ClienteVO> listaClientesAU;
    private ClienteVO objCliente;
    private ClientesDAO objClienteDAO;
    private GestorAutenticacion objAutenticacion;
    private GestorComprar gestorComprar;
    private VistaAutenticacion vista;
    public String strId;

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

    public String generarIdCliente() {
        // Crear una instancia de la clase Random
        Random random = new Random();

        // Generar un número aleatorio de 6 dígitos
        String Id = ("100000" + random.nextInt(900000));

        return strId;
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
                    break;
                } else {
                    objCliente.setId(generarIdCliente());
                    objCliente.setNombre(strNombre);
                    objCliente.setDireccion(strDireccion);
                    objCliente.setTelefono(strTelefono);
                    objCliente.setCorreo(strCorreo);
                    objClienteDAO.insertarDatos(objCliente);
                }
            }
        vista.setVisible(false);
        gestorComprar.iniciarC();
        }
    }
}
