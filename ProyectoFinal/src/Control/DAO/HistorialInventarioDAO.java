package Control.DAO;

import Modelo.Carrito;
import Modelo.Conexion.Conexion;
import Modelo.ProductoVO;
import Vista.Admin.VistaMenu;
// PreparedStatement permite ejecutar consultas SQL precompiladas, mejorando la seguridad y rendimiento.
import com.mysql.jdbc.PreparedStatement;
// Estas clases son esenciales para interactuar con bases de datos mediante JDBC.
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// ArrayList es una colección dinámica que facilita el manejo de conjuntos de datos.
import java.util.ArrayList;
// DefaultTableModel es una implementación de TableModel para gestionar datos tabulares en componentes Swing.
import javax.swing.table.DefaultTableModel;

public class HistorialInventarioDAO {

    // Declaración de una variable de tipo Connection para gestionar la conexión a la base de datos.
    private Connection con;
    // Declaración de una variable de tipo Statement para ejecutar consultas SQL.
    private Statement st;
    // Declaración de una variable de tipo ResultSet para almacenar los resultados de consultas SQL.
    private ResultSet rs;
    // Declaración de una variable de tipo VistaMenu, instanciada como un nuevo objeto VistaMenu.
    // VistaMenu es una interfaz gráfica que representa el menú de la aplicación.
    private VistaMenu vista = new VistaMenu();
    
    public HistorialInventarioDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

    // Método para cargar datos desde la tabla "historialinventario" y construir un DefaultTableModel para una tabla Swing.
    public DefaultTableModel cargarDatosTabla() {
        try {
            // Consulta SQL para obtener los datos de la tabla "historialinventario".
            String consulta = "SELECT * FROM historialinventario";
            con = Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            // Crear un modelo de tabla para contener los datos.
            DefaultTableModel modelo = new DefaultTableModel();
            // Obtener los nombres de las columnas de metadatos y agregarlos al modelo.
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                modelo.addColumn(rs.getMetaData().getColumnName(i));
            }
            // Rellenar el modelo de tabla con los datos obtenidos de la base de datos.
            while (rs.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modelo.addRow(fila);
            }
            // Agregar una fila adicional al principio con los nombres de las columnas.
            Object[] nombresColumnas = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                nombresColumnas[i - 1] = modelo.getColumnName(i - 1);
            }
            modelo.insertRow(0, nombresColumnas);
            // Devolver el modelo de tabla creado.
            return modelo;
        } catch (SQLException e) {
            // Imprime la traza de la excepción en caso de error.
            e.printStackTrace();
        }
        // En caso de error, retorna null.
        return null;
    }

    // Método para registrar el ingreso de un producto en el historial de inventario.
    public void ingreso(ProductoVO producto) {
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement.
            String insercion = "INSERT INTO historialinventario VALUES (?, ?, ?)";
            // Crea una consulta preparada.
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);
            // Establece los parámetros de la consulta con los datos del producto y el tipo de movimiento (INGRESO).
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getStock());
            pst.setString(3, "INGRESO");
            // Ejecuta la consulta de inserción.
            pst.executeUpdate();
            // Cierra la declaración y desconecta de la base de datos.
            pst.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Imprime la traza de la excepción en caso de error.
            ex.printStackTrace();
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            vista.errorConsola("NO INSERCION");
        }
    }

    // Método para registrar el ingreso de una cantidad específica de un producto en el historial de inventario.
    public void ingresoCantidad(ProductoVO producto, int cantidad) {
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement.
            String insercion = "INSERT INTO historialinventario VALUES (?, ?, ?)";
            // Crea una consulta preparada.
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);
            // Establece los parámetros de la consulta con el nombre del producto, la cantidad y el tipo de movimiento (INGRESO).
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, cantidad);
            pst.setString(3, "INGRESO");
            // Ejecuta la consulta de inserción.
            pst.executeUpdate();
            // Cierra la declaración y desconecta de la base de datos.
            pst.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Imprime la traza de la excepción en caso de error.
            ex.printStackTrace();
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            vista.errorConsola("NO INSERCION");
        }
    }
    // Método para registrar la salida de productos de la lista de compra en el historial de inventario.
    public void salida(ArrayList<Carrito> listaCompra) {
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement.
            String insercion = "INSERT INTO historialinventario VALUES (?, ?, ?)";
            // Crea una consulta preparada.
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);
            // Itera sobre la lista de productos en el carrito para registrar la salida de cada uno.
            for (Carrito producto : listaCompra) {
                // Establece los parámetros de la consulta con el nombre del producto, la cantidad y el tipo de movimiento (SALIDA).
                pst.setString(1, producto.getNombre());
                pst.setDouble(2, producto.getCantidad());
                pst.setString(3, "SALIDA");
                // Ejecuta la consulta de inserción.
                pst.executeUpdate();
            }
            // Cierra la declaración y desconecta de la base de datos.
            pst.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Imprime la traza de la excepción en caso de error.
            ex.printStackTrace();
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            vista.errorConsola("NO INSERCION");
        }
    }
}