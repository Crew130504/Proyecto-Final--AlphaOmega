package Control.Gestores;

import Control.DAO.ClientesDAO;
import Control.DAO.VentasDAO;
import Modelo.ClienteVO;
import Vista.Admin.VistaClientes;
import Vista.Admin.VistaTabla;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestorClientes implements ActionListener {

    private ArrayList<ClienteVO> listaClientes;
    private VistaTabla vistaTabla = new VistaTabla();
    private ClientesDAO miClienteDAO = new ClientesDAO();
    private VentasDAO miVentaDAO = new VentasDAO();
    private ClienteVO objCliente = new ClienteVO();
    private VistaClientes vista;
    private GestorMenu gestorMenu;

    public GestorClientes() {
        this.vista = new VistaClientes();
        this.vista.btnVolver.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnLista.addActionListener(this);
        this.vista.btnHistorial.addActionListener(this);
    }

    public void iniciar(GestorMenu vuelta) {
        //Insercion de logica de las funciones necesaria
        gestorMenu = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        llenarCombo();
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);

    }

    private void llenarCombo() {
        vista.comboxClientes.removeAllItems(); // Limpia los elementos existentes en el ComboBox
        // Agregar la opción nula o predeterminada al principio del ComboBox
        vista.comboxClientes.addItem("Seleccionar");
        listaClientes = miClienteDAO.listaDeClientes();
        //Funcion que guarda en el ArrayList todos los clientes
        for (int i = 0; i < listaClientes.size(); i++) {
            vista.comboxClientes.addItem(listaClientes.get(i).getNombre());
        }
    }

    //Funcion de condicionales de vista
    @Override
    public void actionPerformed(ActionEvent e) {

        //Boton volver
        if (e.getSource() == vista.btnVolver) {
            vista.setVisible(false);
            gestorMenu.iniciar();
        }

        //Boton limpiar
        if (e.getSource() == vista.btnLimpiar) {
            vista.txtNombre.setText("");
            vista.txtDireccion.setText("");
            vista.txtTelefono.setText("");
            vista.txtCorreo.setText("");
        }

        //Funcion para desplegar el usuario seleccionado en los textfield
        if (e.getSource() == vista.btnSeleccionar) {
            listaClientes = miClienteDAO.listaDeClientes();
            String strNombre = (String) vista.comboxClientes.getSelectedItem();
            for (ClienteVO objCliente : listaClientes) {
                if (strNombre.equals(objCliente.getNombre())) {
                    vista.txtNombre.setText(objCliente.getNombre());
                    vista.txtDireccion.setText(objCliente.getDireccion());
                    vista.txtCorreo.setText(objCliente.getCorreo());
                    vista.txtTelefono.setText(objCliente.getTelefono());
                }
            }
        }
        //Modificar cliente
        if (e.getSource() == this.vista.btnModificar) {
            listaClientes = miClienteDAO.listaDeClientes();
            String nombre = (String) this.vista.comboxClientes.getSelectedItem();
            if(vista.txtNombre.getText().isEmpty() || vista.txtDireccion.getText().isEmpty()||vista.txtCorreo.getText().isEmpty()||vista.txtTelefono.getText().isEmpty()){
            vista.error("NO SE PERMITEN ESPACIOS VACIOS");
            }else{
            for (ClienteVO objCliente : miClienteDAO.listaDeClientes()) {
                if (objCliente.getNombre().equals(nombre)) {
                    objCliente.setNombre(this.vista.txtNombre.getText());
                    objCliente.setDireccion(this.vista.txtDireccion.getText());
                    objCliente.setCorreo(this.vista.txtCorreo.getText());
                    objCliente.setTelefono(this.vista.txtTelefono.getText());
                    this.miClienteDAO.actualizarDatos(objCliente);
                    this.vista.msg("CLIENTE MODIFICADO");
                    this.vista.limpiar();
                    llenarCombo();
                    break;  // Se detiene después de encontrar la primera coincidencia
                }
            }
            }
        }
        
        if(e.getSource() == this.vista.btnEliminar){
            listaClientes = miClienteDAO.listaDeClientes();
            String nombre = (String) this.vista.comboxClientes.getSelectedItem();
            for (ClienteVO objCliente : miClienteDAO.listaDeClientes()){
                if(objCliente.getNombre().equals(nombre)){
                String Id = objCliente.getId();
                this.miClienteDAO.eliminarCliente(Id);
                this.vista.msg("CLIENTE ELIMINADO");
                this.vista.limpiar();
                llenarCombo();
                break;
                }
            }            
        }
        
        if(e.getSource() == this.vista.btnLista){
            this.vistaTabla.mostrarVentana(this.miClienteDAO.cargarDatosTabla(), "CLIENTES");
        }
        if(e.getSource() == this.vista.btnHistorial){
            this.vistaTabla.mostrarVentana(this.miVentaDAO.cargarDatosTablaCliente(this.vista.txtNombre.getText()), "HISTORIAL");
        }
    }
}