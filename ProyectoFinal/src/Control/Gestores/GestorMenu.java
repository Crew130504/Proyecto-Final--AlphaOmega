package Control.Gestores;

import Vista.Admin.VistaMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GestorMenu implements ActionListener {

    private VistaMenu vista;
    private Gestor gestor;
    private GestorClientes gestorClientes = new GestorClientes();
    private GestorVentas gestorVentas = new GestorVentas();
    private GestorProductos gestorProductos = new GestorProductos();
    private GestorProveedores gestorProveedores = new GestorProveedores();
    
    public GestorMenu() {
        //Escucha botones
        this.vista = new VistaMenu();
        this.vista.btnVolver.addActionListener(this);
        this.vista.btnClientes.addActionListener(this);
        this.vista.btnVentas.addActionListener(this);
        this.vista.btnInventario.addActionListener(this);
        this.vista.btnProductos.addActionListener(this);
        this.vista.btnProveedores.addActionListener(this);
    }
    public void iniciar(){
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void iniciar(Gestor vuelta) {
        //Insercion de logica de las funciones necesaria
        gestor = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Boton volver
        if (e.getSource() == vista.btnVolver) {
            vista.setVisible(false);
            gestor.iniciar();
        }

        //Boton vista clientes
        if (e.getSource() == vista.btnClientes) {
            vista.setVisible(false);
            gestorClientes.iniciar(this);
        }

        //Boton vista ventas
        if (e.getSource() == vista.btnVentas) {
            vista.setVisible(false);
            //gestorVentas.iniciar();

        }

        //Boton vista productos
        if (e.getSource() == vista.btnProductos) {
            vista.setVisible(false);
            gestorProductos.iniciar(this);
        }

        //Boton vista inventario
        if (e.getSource() == vista.btnInventario) {
            vista.setVisible(false);
            gestorProductos.iniciarI(this);
        }

        //Boton vista proveedores
        if (e.getSource() == vista.btnProveedores) {
            vista.setVisible(false);
            gestorProveedores.iniciar(this);
        }
    }
}
