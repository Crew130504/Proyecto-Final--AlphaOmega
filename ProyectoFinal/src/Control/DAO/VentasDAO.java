package Control.DAO;

import Modelo.Conexion.Conexion;
import Modelo.VentaVO;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class VentasDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private VistaMenu vista = new VistaMenu();

    public VentasDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

    public ArrayList<VentaVO> listaDeVentas() {
        ArrayList<VentaVO> listaVentas = new ArrayList<>();
        String consulta = "SELECT * FROM ventas";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                // Crea objetos ProductoVO y los agrega a la lista
                listaVentas.add(new VentaVO(rs.getString("Cliente"), rs.getString("Folio"),
                        rs.getString("Fecha"), rs.getString("Hora"), rs.getString("Estado"),
                        rs.getString("Detalles"), rs.getDouble("Subtotal"), rs.getDouble("Total")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            vista.errorConsola("No se pudo");
        }
        return listaVentas;
    }

    public void insertarDatos(VentaVO venta) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm:ss");
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement
            String insercion = "INSERT INTO ventas VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);

            // Establece los parámetros de la consulta
            pst.setString(1, venta.getCliente());
            pst.setDate(2, new java.sql.Date(sdf.parse(venta.getFecha()).getTime()));
            pst.setTime(3, new java.sql.Time(sdfHora.parse(venta.getHora()).getTime()));
            pst.setString(4, venta.getFolio());
            pst.setString(5, venta.getDetalles());
            pst.setDouble(6, venta.getSubtotal());
            pst.setDouble(7, venta.getTotal());
            pst.setString(8, venta.getEstado());

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

    public VentaVO consultarPorFolio(String folio) {
        try {
            // Crea una consulta
            String consulta = "SELECT * FROM `ventas` WHERE `Folio`='" + folio + "'";
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                return new VentaVO(rs.getString("Cliente"), rs.getString("Folio"),
                        rs.getString("Fecha"), rs.getString("Hora"),
                        rs.getString("Estado"), rs.getString("Detalles"),
                        Double.parseDouble(rs.getString("Subtotal")), Double.parseDouble(rs.getString("Total")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            // Manejo de excepciones en caso de error
            ex.printStackTrace();
            vista.errorConsola("NO INSERCION");
        }
        return null;

    }
    public void actualizarDato(VentaVO venta) {
        // Consulta de actualización para un objeto ProductosVO
        String modificacion = "UPDATE `ventas` SET `Estado`='" + venta.getEstado() + "' WHERE `Folio`='" + venta.getFolio() + "'";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta de actualización
            st.executeUpdate(modificacion);
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            this.vista.errorConsola("NO MODIFICADO");
        }
    }

    public DefaultTableModel cargarDatosTabla() {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM ventas WHERE `Estado`='PENDIENTE'";
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
        public DefaultTableModel cargarDatosTablaDia(int dia,int mes,int year) {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM `ventas` WHERE DAY(`Fecha`) ="+dia+" AND MONTH(`Fecha`) ="+mes+" AND YEAR(`Fecha`) ="+year;
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
    public DefaultTableModel cargarDatosTablaMes(int mes) {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM `ventas` WHERE MONTH(`Fecha`) ="+mes;
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
    public DefaultTableModel cargarDatosTablaYear(int year) {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM `ventas` WHERE YEAR(`Fecha`) ="+year;
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
    public DefaultTableModel cargarDatosTablaCliente(String cliente) {
        try {
            // Consulta SQL para obtener los datos
            String consulta = "SELECT * FROM `ventas` WHERE `Cliente`='"+cliente+"'";
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

}
