package Control.Gestores;

import Vista.VistaBienvenida;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gestor implements ActionListener {

    private VistaBienvenida vista;
    private GestorMenu gestorMenu = new GestorMenu();
    private GestorComprar gestorComprar = new GestorComprar();
    private String credencial = "123";

    public Gestor() {
        this.vista = new VistaBienvenida();
        this.vista.btnAdmin.addActionListener(this);
        this.vista.btnUsuario.addActionListener(this);
    }

    public void iniciar() {
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);

        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnAdmin) {
            boolean ciclo = true;
            while (ciclo) {
                String credencial = this.vista.capturar("DIGITE SUS CREDENCIALES");
                if (credencial.equals(this.credencial)) {
                    ciclo = false;
                    this.vista.setVisible(false);
                    gestorMenu.iniciar(this);
                } else {
                    this.vista.error("Credenciales erroneas");
                }
            }
        }
        if(e.getSource()==this.vista.btnUsuario){
            this.vista.setVisible(false);
            gestorComprar.iniciar(this);
        }

    }

}
