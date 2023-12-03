package Control.DAO;

import Modelo.ClienteVO;
import Modelo.Conexion.Conexion;
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

public class ClientesDAO {

    // Declaración de una variable de tipo Connection para gestionar la conexión a la base de datos.
    private Connection con;
    // Declaración de una variable de tipo Statement para ejecutar consultas SQL.
    private Statement st;
    // Declaración de una variable de tipo ResultSet para almacenar los resultados de consultas SQL.
    private ResultSet rs;
    // Declaración de una variable de tipo VistaMenu, para manejar la interfaz gráfica del menú.
    private VistaMenu vista;

    public ClientesDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

// Método que retorna una lista de objetos ClienteVO, obtenidos desde la base de datos.
    public ArrayList<ClienteVO> listaDeClientes() {
        // Creación de una nueva lista para almacenar objetos ClienteVO.
        ArrayList<ClienteVO> listaClientes = new ArrayList<>();
        // Consulta SQL para seleccionar todos los registros de la tabla "cliente".
        String consulta = "SELECT * FROM cliente";
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Crea una declaración SQL.
            st = (Statement) con.createStatement();
            // Ejecuta la consulta y obtiene un conjunto de resultados.
            rs = st.executeQuery(consulta);
            // Recorre los resultados y crea objetos ClienteVO, agregándolos a la lista.
            while (rs.next()) {
                listaClientes.add(new ClienteVO(
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("id")
                ));
            }
            // Cierra la declaración y desconecta de la base de datos.
            st.close();
            Conexion.desconectar();
        } catch (SQLException e) {
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            vista.error("Ocurrió un error inesperado en la consulta con la Base de Datos");
        }
        // Retorna la lista de clientes obtenida de la base de datos.
        return listaClientes;
    }

    // Método para actualizar datos de un cliente en la base de datos.
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
            pst.setString(6, objCliente.getId());// Este último ? es para el WHERE
            // Ejecuta la consulta de actualización
            pst.executeUpdate();
            // Cierra la declaración y desconecta de la base de datos
            pst.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Imprime la traza de la excepción en caso de error
            ex.printStackTrace();
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            this.vista.errorConsola("NO MODIFICADO");
        }
    }

    // Método para eliminar un cliente de la base de datos por su ID.
    public void eliminarCliente(String id) {
        // Consulta SQL para eliminar un cliente con un ID específico.
        String consulta = "DELETE FROM cliente where id='" + id + "'";
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Crea una declaración SQL.
            st = con.createStatement();
            // Ejecuta la consulta de eliminación.
            st.executeUpdate(consulta);
            // Cierra la declaración y desconecta de la base de datos.
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            this.vista.error("NO ELIMINADO");
        }
    }

    // Método para cargar datos desde la base de datos y construir un DefaultTableModel para una tabla Swing.
    public DefaultTableModel cargarDatosTabla() {
        try {
            // Consulta SQL para obtener los datos de la tabla cliente.
            String consulta = "SELECT * FROM cliente";
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

    // Método para insertar datos de un nuevo cliente en la base de datos.
    public void insertarDatos(ClienteVO objCliente) {
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement.
            String insercion = "INSERT INTO cliente VALUES (?, ?, ?, ?, ?)";
            // Crea una consulta preparada.
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);
            // Establece los parámetros de la consulta con los datos del objeto ClienteVO.
            pst.setString(1, objCliente.getId());
            pst.setString(2, objCliente.getNombre());
            pst.setString(3, objCliente.getDireccion());
            pst.setString(4, objCliente.getTelefono());
            pst.setString(5, objCliente.getCorreo());
            // Ejecuta la consulta de inserción.
            pst.executeUpdate();
            // Cierra la declaración y desconecta de la base de datos.
            pst.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            ex.printStackTrace();
            vista.errorConsola("HUBO UN ERROR DURANTE LA INSERCION");
        }
    }

    // Método para buscar un cliente en la base de datos por su nombre.
    public ClienteVO buscarClientePorNombre(String strNombre) {
        // Consulta SQL para seleccionar un cliente por su nombre.
        String consulta = "SELECT * FROM cliente WHERE nombre='" + strNombre + "'";
        try {
            // Obtiene una conexión a la base de datos.
            con = Conexion.getConexion();
            // Crea una declaración SQL.
            st = (Statement) con.createStatement();
            // Ejecuta la consulta y obtiene un conjunto de resultados.
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                // Crea un objeto ClienteVO con los datos obtenidos de la base de datos.
                ClienteVO miCliente = new ClienteVO(
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("correo"),
                        rs.getString("id")
                );
                // Retorna el objeto ClienteVO encontrado.
                return miCliente;
            }
            // Cierra la declaración y desconecta de la base de datos.
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            // Imprime la traza de la excepción en caso de error.
            ex.printStackTrace();
            // Manejo de excepciones en caso de error, mostrando un mensaje en la interfaz.
            vista.errorConsola("No se pudo");
        }
        // Retorna null si no se encuentra el cliente.
        return null;
    }
}