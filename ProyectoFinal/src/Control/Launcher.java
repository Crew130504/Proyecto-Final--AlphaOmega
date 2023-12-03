package Control;

// Importamos la clase Gestor del paquete Control.Gestores.
import Control.Gestores.Gestor;

// Clase Launcher, que contiene el método principal para iniciar la aplicación.
public class Launcher {
    // Método principal que se ejecuta al iniciar la aplicación.
    public static void main(String[] args) {
        // Creamos una instancia del Gestor, que gestiona el flujo principal del programa.
        Gestor gestor = new Gestor();
        
        // Llamamos al método iniciar() del gestor para comenzar la ejecución del programa.
        gestor.iniciar();
    }
}
