package Control.DAO;

import Modelo.ClienteVO;
import Modelo.Conexion.Conexion;
import Modelo.ProveedoresVO;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProveedoresDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private ProveedoresVO objProveedor = new ProveedoresVO();
    VistaMenu vista;

    public ProveedoresDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

    public ArrayList<ProveedoresVO> listaDeProveedores() {
        ArrayList<ProveedoresVO> listaProveedores = new ArrayList<>();
        String consulta = "SELECT * FROM proveedores";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                // Crea objetos ClienteVO y los agrega a la lista
                listaProveedores.add(new ProveedoresVO(rs.getString("nombre"), rs.getString("id"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("correo")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (Exception e) {
            // Manejo de excepciones en caso de error
            vista.error("Ocurrió un error inesperado en la consulta con la Base de Datos");
        }
        return listaProveedores;
    }

    public void actualizarDatos(ProveedoresVO objProveedor) {
        // Consulta de actualización con PreparedStatement
        String modificacion = "UPDATE `proveedores` SET `Id`=?, `nombre`=?, `direccion`=?, `telefono`=? , `correo`=? WHERE `Id`=?";

        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(modificacion);

            // Establece los parámetros de la consulta
            pst.setString(1, objProveedor.getId());
            pst.setString(2, objProveedor.getNombre());
            pst.setString(3, objProveedor.getDireccion());
            pst.setString(4, objProveedor.getTelefono());
            pst.setString(5, objProveedor.getCorreo());
            pst.setString(6, objProveedor.getId());
            // Este último ? es para el WHERE

            // Ejecuta la consulta de actualización
            pst.executeUpdate();

            // Cierra la declaración y desconecta de la base de datos
            pst.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            this.vista.errorConsola("NO MODIFICADO");
        }
    }

    public void eliminarProveedor(String id) {
        String consulta = "DELETE FROM proveedores where id='" + id + "'";
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            st.executeUpdate(consulta);
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            this.vista.error("NO ELIMINADO");
        }
    }
    public void insertarDatos(ProveedoresVO objProveedor) {
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement
            String insercion = "INSERT INTO proveedores VALUES (?, ?, ?, ?, ?)";
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);

            // Establece los parámetros de la consulta
            pst.setString(1, objProveedor.getId());
            pst.setString(2, objProveedor.getNombre());
            pst.setString(3, objProveedor.getDireccion());
            pst.setString(4, objProveedor.getTelefono());
            pst.setString(5, objProveedor.getCorreo());

            // Ejecuta la consulta de inserción
            pst.executeUpdate();

            // Cierra la declaración y desconecta de la base de datos
            pst.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            // Manejo de excepciones en caso de error
            ex.printStackTrace();
            vista.errorConsola("HUBO UN ERROR DURANTE LA INSERCION");
        }
    }
}
