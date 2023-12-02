package Control.DAO;

import Modelo.ClienteVO;
import Modelo.Conexion.Conexion;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ClientesDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    ClienteVO objCliente = new ClienteVO();
    VistaMenu vista;

    public ClientesDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

    public ArrayList<ClienteVO> listaDeClientes() {
        ArrayList<ClienteVO> listaClientes = new ArrayList<>();
        String consulta = "SELECT * FROM cliente";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                // Crea objetos ClienteVO y los agrega a la lista
                listaClientes.add(new ClienteVO(rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("correo"), rs.getString("id")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (Exception e) {
            // Manejo de excepciones en caso de error
            vista.error("Ocurrió un error inesperado en la consulta con la Base de Datos");
        }
        return listaClientes;
    }

    public void actualizarDatos(ClienteVO objCliente) {
        // Consulta de actualización con PreparedStatement
        String modificacion = "UPDATE `cliente` SET `Id`=?, `nombre`=?, `direccion`=?, `telefono`=? , `correo`=? WHERE `Id`=?";

        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(modificacion);

            // Establece los parámetros de la consulta
            pst.setString(1, objCliente.getId());
            pst.setString(2, objCliente.getNombre());
            pst.setString(3, objCliente.getDireccion());
            pst.setString(4, objCliente.getTelefono());
            pst.setString(5, objCliente.getCorreo());
            pst.setString(6, objCliente.getId());
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

    public void eliminarCliente(String id) {
        String consulta = "DELETE FROM cliente where id='" + id + "'";
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

    public DefaultTableModel cargarDatosTabla() {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM cliente";
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
    
    public void insertarDatos(ClienteVO objCliente) {
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement
            String insercion = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);

            // Establece los parámetros de la consulta
            pst.setString(1, objCliente.getId());
            pst.setString(2, objCliente.getNombre());
            pst.setString(3, objCliente.getDireccion());
            pst.setString(4, objCliente.getTelefono());
            pst.setString(5, objCliente.getCorreo());

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
    public ClienteVO buscarClientePorNombre(String strNombre){
        String consulta = "SELECT * FROM cliente WHERE nombre='" + strNombre + "'";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {

                // Crea objetos ProductoVO y los agrega a la lista
                ClienteVO miCliente =  new ClienteVO(rs.getString("nombre"),rs.getString("direccion"),
                        rs.getString("telefono"),rs.getString("correo"),rs.getString("id"));
                return miCliente;
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            vista.errorConsola("No se pudo");
        }
        return null;
    }
}
