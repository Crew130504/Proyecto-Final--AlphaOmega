package Control.DAO;

import Modelo.Conexion.Conexion;
import Modelo.ProductoVO;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class HistorialInventarioDAO {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private VistaMenu vista = new VistaMenu();
    public HistorialInventarioDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }
    public DefaultTableModel cargarDatosTabla() {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM historialinventario";
            con = Conexion.getConexion();
            st = con.createStatement();
            rs = st.executeQuery(consulta);

            // Crear un modelo de tabla
            DefaultTableModel modelo = new DefaultTableModel();

            // Obtener los nombres de las columnas de metadatos
            int columnCount = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                modelo.addColumn(rs.getMetaData().getColumnName(i));
            }

            // Rellenar el modelo de tabla con los datos
            while (rs.next()) {
                Object[] fila = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    fila[i - 1] = rs.getObject(i);
                }
                modelo.addRow(fila);
            }
            // Agregar una fila adicional al principio con los nombres de las columnas
            Object[] nombresColumnas = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                nombresColumnas[i - 1] = modelo.getColumnName(i - 1);
            }
            modelo.insertRow(0, nombresColumnas);

            // Crear el JTable y asignarle el modelo
            return (modelo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void ingreso(ProductoVO producto){
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement
            String insercion = "INSERT INTO historialinventario VALUES (?, ?, ?)";
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);

            // Establece los parámetros de la consulta
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getStock());
            pst.setString(3, "INGRESO");

            // Ejecuta la consulta de inserción
            pst.executeUpdate();

            // Cierra la declaración y desconecta de la base de datos
            pst.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            // Manejo de excepciones en caso de error
            ex.printStackTrace();
            vista.errorConsola("NO INSERCION");
        }
        
    }
}
