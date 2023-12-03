// Importación de clases y paquetes necesarios
package Control.Gestores;

import Control.DAO.ProveedoresDAO;
import Modelo.ProveedoresVO;
import Vista.Admin.VistaProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Declaración de la clase que implementa la interfaz ActionListener
public class GestorProveedores implements ActionListener {

    // Declaración de variables de instancia
    private ArrayList<ProveedoresVO> listaProveedores;
    private VistaProveedores vista;
    private GestorMenu gestorMenu;
    private ProveedoresVO objProveedores = new ProveedoresVO();
    private ProveedoresDAO miProveedorDAO = new ProveedoresDAO();

    // Constructor de la clase
    public GestorProveedores() {
        // Inicialización de la vista y configuración de los listeners de los botones
        this.vista = new VistaProveedores();
        this.vista.btnVolver.addActionListener(this);
        this.vista.btnLimpiar.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        this.vista.btnModificar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
    }

    // Método para iniciar el gestor y configurar la vista
    public void iniciar(GestorMenu vuelta) {
        // Insercion de logica de las funciones necesaria
        gestorMenu = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        llenarCombo();
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    // Método para llenar el ComboBox con los proveedores disponibles
    private void llenarCombo() {
        vista.comboxProveedores.removeAllItems(); // Limpia los elementos existentes en el ComboBox
        // Agregar la opción nula o predeterminada al principio del ComboBox
        vista.comboxProveedores.addItem("Seleccionar");
        listaProveedores = miProveedorDAO.listaDeProveedores();
        // Función que guarda en el ArrayList todos los Proveedores
        for (int i = 0; i < listaProveedores.size(); i++) {
            vista.comboxProveedores.addItem(listaProveedores.get(i).getNombre());
        }
    }

    // Método para verificar si hay campos de texto vacíos
    private boolean camposTextoVacios() {
        return this.vista.txtId.getText().isEmpty()
                || this.vista.txtNombre.getText().isEmpty()
                || this.vista.txtDireccion.getText().isEmpty()
                || this.vista.txtTelefono.getText().isEmpty()
                || this.vista.txtCorreo.getText().isEmpty();
    }

    // Método para verificar si un nombre ya existe en el ComboBox
    private boolean nombreExistenteEnComboBox(String nombre) {
        int itemCount = this.vista.comboxProveedores.getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (nombre.equals(this.vista.comboxProveedores.getItemAt(i))) {
                return true; // El nombre ya existe en el ComboBox
            }
        }
        return false; // El nombre no existe en el ComboBox
    }

    // Implementación del método actionPerformed de la interfaz ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        // Manejo de eventos para los botones de la interfaz

        // Boton volver
        if (e.getSource() == vista.btnVolver) {
            vista.setVisible(false);
            gestorMenu.iniciar();
            vista.txtId.setEditable(true);
        }

        // Boton limpiar
        if (e.getSource() == vista.btnLimpiar) {
            vista.txtId.setEditable(true);
            vista.txtId.setText("");
            vista.txtNombre.setText("");
            vista.txtDireccion.setText("");
            vista.txtTelefono.setText("");
            vista.txtCorreo.setText("");
        }

        // Función para desplegar el usuario seleccionado en los textfield
        if (e.getSource() == vista.btnSeleccionar) {
            listaProveedores = miProveedorDAO.listaDeProveedores();
            String strNombre = (String) vista.comboxProveedores.getSelectedItem();
            for (ProveedoresVO objProveedor : listaProveedores) {
                if (strNombre.equals(objProveedor.getNombre())) {
                    vista.txtId.setText(objProveedor.getId());
                    vista.txtNombre.setText(objProveedor.getNombre());
                    vista.txtDireccion.setText(objProveedor.getDireccion());
                    vista.txtCorreo.setText(objProveedor.getCorreo());
                    vista.txtTelefono.setText(objProveedor.getTelefono());
                }
            }
            vista.txtId.setEditable(false);
        }

        // Modificar proveedor
        if (e.getSource() == this.vista.btnModificar) {
            vista.txtId.setEditable(true);
            listaProveedores = miProveedorDAO.listaDeProveedores();
            String nombre = (String) this.vista.comboxProveedores.getSelectedItem();
            if (vista.txtNombre.getText().isEmpty() || vista.txtDireccion.getText().isEmpty()
                    || vista.txtCorreo.getText().isEmpty() || vista.txtTelefono.getText().isEmpty()) {
                vista.error("NO SE PERMITEN ESPACIOS VACIOS");
            } else {
                for (ProveedoresVO objProveedores : miProveedorDAO.listaDeProveedores()) {
                    if (objProveedores.getNombre().equals(nombre)) {
                        objProveedores.setId(this.vista.txtId.getText());
                        objProveedores.setNombre(this.vista.txtNombre.getText());
                        objProveedores.setDireccion(this.vista.txtDireccion.getText());
                        objProveedores.setCorreo(this.vista.txtCorreo.getText());
                        objProveedores.setTelefono(this.vista.txtTelefono.getText());
                        this.miProveedorDAO.actualizarDatos(objProveedores);
                        this.vista.msg("PROVEEDOR MODIFICADO");
                        this.vista.limpiar();
                        llenarCombo();
                        break;  // Se detiene después de encontrar la primera coincidencia
                    }
                }
            }
        }

        // Eliminar proveedor
        if (e.getSource() == this.vista.btnEliminar) {
            vista.txtId.setEditable(true);
            listaProveedores = miProveedorDAO.listaDeProveedores();
            String nombre = (String) this.vista.comboxProveedores.getSelectedItem();
            for (ProveedoresVO objProveedores : miProveedorDAO.listaDeProveedores()) {
                if (objProveedores.getNombre().equals(nombre)) {
                    String Id = objProveedores.getId();
                    this.miProveedorDAO.eliminarProveedor(Id);
                    this.vista.msg("PROVEEDOR ELIMINADO");
                    this.vista.limpiar();
                    llenarCombo();
                    break;
                }
            }
        }

        // Agregar proveedor
        if (e.getSource() == this.vista.btnAgregar) {
            vista.txtId.setEditable(true);
            listaProveedores = miProveedorDAO.listaDeProveedores();
            String id = this.vista.txtId.getText();
            String nombre = this.vista.txtNombre.getText();
            String direccion = this.vista.txtDireccion.getText();
            String telefono = this.vista.txtTelefono.getText();
            String correo = this.vista.txtCorreo.getText();
            if (camposTextoVacios()) {
                this.vista.error("TODOS LOS CAMPOS DEBEN ESTAR LLENOS");
            } else {
                // Verificar si el nombre ya está en el ComboBox
                if (!nombreExistenteEnComboBox(nombre)) {
                    // El nombre no existe en el ComboBox, proceder con la inserción
                    objProveedores.setId(id);
                    objProveedores.setNombre(nombre);
                    objProveedores.setDireccion(direccion);
                    objProveedores.setTelefono(telefono);
                    objProveedores.setCorreo(correo);
                    miProveedorDAO.insertarDatos(objProveedores);
                    this.vista.msg("PROVEEDOR AGREGADO CON EXITO");
                    llenarCombo();
                    vista.limpiar();
                }
            }
        }
    }
}