package Modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class ArchivoSecuencial {

    private ArrayList<ProductoVO> listaProductos = new ArrayList<>();
    private ArrayList<Carrito> carrito = new ArrayList<>();
    private String cliente;
    private String fecha;
    private String hora;
    private String estado;
    private String detalles;
    private String subtotal;
    private String total;
    private File directorio;
    private File archivo;

    public void fusionarListas(ArrayList<ProductoVO> productos, ArrayList<Carrito> carrito) {
        this.carrito = carrito;
        for (ProductoVO miProducto : productos) {
            for (Carrito miCarrito : carrito) {
                if (miProducto.getNombre().equals(miCarrito.getNombre())) {
                    listaProductos.add(miProducto);
                }
            }
        }
    }

    // Constructor de la clase ArchivoSecuencial
    public ArchivoSecuencial(String nombre) {
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
            archivo = new File(directorio, nombre);
        }
    }

    // Método para escribir datos en el archivo
    public void escribirGarantia(VentaVO venta, ClienteVO cliente) {
        // Obtener y formatear los atributos de la instancia VentaVO
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            // Realiza la escritura secuencial en el archivo
            writer.write("APLHA OMEGA Alpha Omega S.A.\n"
                    + "Calle Principal #123, Ciudad Alpha, País Omega\n"
                    + "+123 456 7890\n"
                    + "info@alphaomega.com");
            writer.newLine();  // Agrega un salto de línea

            writer.write("Fecha: " + venta.getFecha());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Hora: " + venta.getHora());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Folio: " + venta.getFolio());
            writer.newLine();  // Agrega un salto de línea
            for (ProductoVO producto : listaProductos) {
                writer.write("Producto: " + producto.getNombre() + " Serie: " + producto.getSerie());
                writer.newLine();  // Agrega un salto de línea

                writer.write("Garantia: " + producto.getGarantia() + " Dias");
                writer.newLine();  // Agrega un salto de línea
            }

            writer.write("Cliente: \n" + cliente.getNombre() + "\n" + cliente.getDireccion() + "\n"
                    + cliente.getTelefono() + "\n" + cliente.getCorreo() + "\n" + cliente.getId());
            writer.newLine();  // Agrega un salto de línea
            // Puedes seguir escribiendo más líneas según sea necesario
        } catch (IOException e) {
            // Maneja la excepción si ocurre un error durante la escritura
            e.printStackTrace();
        }
    }

    public void escribirComprobante(VentaVO venta) {
        int cantidad=0;
        double iva=0;
        // Obtener y formatear los atributos de la instancia VentaVO
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            // Realiza la escritura secuencial en el archivo
            writer.write("APLHA OMEGA Alpha Omega S.A.\n"
                    + "Calle Principal #123, Ciudad Alpha, País Omega\n"
                    + "+123 456 7890\n"
                    + "info@alphaomega.com");
            writer.newLine();  // Agrega un salto de línea

            writer.write("Fecha: " + venta.getFecha());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Hora: " + venta.getHora());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Cliente: " + venta.getCliente());
            writer.newLine();     // Agrega un salto de línea

            writer.write("Folio: " + venta.getFolio());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Detalles: \n" + venta.getDetalles());
            writer.newLine();  // Agrega un salto de línea
            for (Carrito miCarrito : carrito) {
                cantidad+=miCarrito.getCantidad();                
            }
            writer.write("Cantidad de Piezas Compradas: " + cantidad);
            writer.newLine();  // Agrega un salto de línea
            
            writer.write("Subtotal: " + venta.getSubtotal());
            writer.newLine();  // Agrega un salto de línea
            iva=(venta.getTotal()-venta.getSubtotal());
            writer.write("IVA: " + iva);
            writer.newLine();  // Agrega un salto de línea

            writer.write("Total: " + venta.getTotal());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Estado: " + venta.getEstado());
            writer.newLine();  // Agrega un salto de línea
            // Puedes seguir escribiendo más líneas según sea necesario
        } catch (IOException e) {
            // Maneja la excepción si ocurre un error durante la escritura
            e.printStackTrace();
        }
    }

    public void escribirImportacion(VentaVO venta) {
        // Obtener y formatear los atributos de la instancia VentaVO
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            // Realiza la escritura secuencial en el archivo
            writer.write("APLHA OMEGA Alpha Omega S.A.\n"
                    + "Calle Principal #123, Ciudad Alpha, País Omega\n"
                    + "+123 456 7890\n"
                    + "info@alphaomega.com");
            writer.newLine();  // Agrega un salto de línea

            writer.write("Fecha: " + venta.getFecha());
            writer.newLine();  // Agrega un salto de línea

            writer.write("Folio: " + venta.getFolio());
            writer.newLine();  // Agrega un salto de línea
             for (ProductoVO producto : listaProductos) {
                writer.write("Producto: " + producto.getNombre());
                writer.newLine();  // Agrega un salto de línea
                
                writer.write("Fabricante: " + producto.getFabricante());
                writer.newLine();  // Agrega un salto de línea
                
                writer.write("Pais de Origen: " + producto.getPais());
                writer.newLine();  // Agrega un salto de línea
                
                writer.write("Peso: " + producto.getPeso()+" gr");
                writer.newLine();  // Agrega un salto de línea

                writer.write("Medidas: " + producto.getMedidas() );
                writer.newLine();  // Agrega un salto de línea
            }
            
        } catch (IOException e) {
            // Maneja la excepción si ocurre un error durante la escritura
            e.printStackTrace();
        }
    }

    public void limpiarArchivo() {
        try {
            // Verifica si el archivo existe
            if (archivo != null) {
                // Abre el archivo en modo "rw" para escritura
                FileWriter fileWriter = new FileWriter(archivo);

                // Escribe una cadena vacía para "limpiar" el contenido del archivo
                fileWriter.write("");

                // Cierra el FileWriter después de escribir la cadena vacía
                fileWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
