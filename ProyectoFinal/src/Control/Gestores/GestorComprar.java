package Control.Gestores;

import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Modelo.ProductoVO;
import Vista.Clientes.VistaComprar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GestorComprar implements ActionListener {
    private VistaComprar vista;
    private Gestor vuelta;
    private ProductosDAO miProductoDAO = new ProductosDAO();;
    private HistorialInventarioDAO miHistorialDAO= new HistorialInventarioDAO();;
    private ArrayList<ProductoVO> listaProductos = miProductoDAO.listaDeProductos();;
    
    public GestorComprar(){
        this.vista = new VistaComprar();
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnCarrito.addActionListener(this);
        this.vista.btnSeleccionarProducto.addActionListener(this);
        this.vista.btnSeleccionarTipo.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
    }

    public void iniciar(){
        //Llenar el combo de Tipos
        llenarComboTipo();
        //Traerse la BD de productos
        this.listaProductos = miProductoDAO.listaDeProductos();
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciar(Gestor vuelta) {
        //Llenar en combo de Tipos
        llenarComboTipo();
        //Traerse la BD de productos
        this.listaProductos = miProductoDAO.listaDeProductos();
        //Insercion de logica de las funciones necesaria
        this.vuelta = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }
    public void llenarComboTipo(){

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
    public void llenarComboProductos(String nombre){            
    } 
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== this.vista.btnVolver){
            this.vista.setVisible(false);
            this.vuelta.iniciar();
        }
        if(e.getSource()== this.vista.btnBuscar){
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
        if(e.getSource()== this.vista.btnSeleccionarTipo){
            String nombre = (String) this.vista.comboxTipo.getSelectedItem();
            llenarComboProductos(nombre);
        }
    }

}
