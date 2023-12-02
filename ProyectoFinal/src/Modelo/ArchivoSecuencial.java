package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class ArchivoSecuencial 

    (){
    private String cliente;
    private String fecha;
    private String hora;
    private String estado;
    private String detalles;
    private String subtotal;
    private String total;
    private File directorio;
    private File fl;
    private File archivo;

    // Constructor de la clase RAF
    public ArchivoSecuencial() {
        // Configura un JFileChooser para que el usuario seleccione una carpeta donde guardar el archivo
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setDialogTitle("Selecciona una carpeta para guardar el archivo");

        // Configura el JFileChooser para que solo permita seleccionar directorios (carpetas)
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Muestra el diálogo de selección de archivo y espera a que el usuario interactúe con él
        int returnValue = fileChooser.showSaveDialog(null);

        // Verifica si el usuario hizo clic en "Guardar" en el diálogo de selección de carpeta
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            // El usuario ha seleccionado un directorio y ha hecho clic en "Guardar"
            directorio = fileChooser.getSelectedFile();

            // Crea un archivo dentro del directorio seleccionado con el nombre "ArchivoSecuencial.txt"
            archivo = new File(directorio, "ArchivoSecuencial.txt");
        }
    }

    // Método para escribir datos en el archivo RAF
    public void escribir(VentaVO venta) {

        // Obtener y formatear los atributos de la instancia VentaVO
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            // Realiza la escritura secuencial en el archivo
            writer.write("APLHA OMEGA");
            writer.newLine();  // Agrega un salto de línea

            writer.write("Cliente: "+venta.getCliente());
            writer.newLine();  // Agrega un salto de línea

            writer.write(venta.getFolio());
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(venta.getFecha());
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(venta.getHora());
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(venta.getDetalles());
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(String.valueOf(venta.getSubtotal()));
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(String.valueOf(venta.getTotal()));
            writer.newLine();  // Agrega un salto de línea
            
            writer.write(venta.getEstado());
            writer.newLine();  // Agrega un salto de línea
            // Puedes seguir escribiendo más líneas según sea necesario
        } catch (IOException e) {
            // Maneja la excepción si ocurre un error durante la escritura
            e.printStackTrace();
        }
    }

   public void limpiarArchivo() {
    try {
        // Verifica si el archivo existe
        if (archivo != null) {
            // Abre el archivo en modo "append" para escribir al principio
            archivo = new File(fl, "rw");
            
            // Escribe una cadena vacía para "limpiar" el contenido del archivo
            archivo.writeBytes("");
            
            // Coloca el puntero de acceso al principio del archivo
            archivo.seek(0);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    //Método para cerrar el archivo

    public void cerrar() {
        try {
            archivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
