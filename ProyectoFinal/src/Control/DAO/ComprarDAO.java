package Control.DAO;

import Modelo.Carrito;
import Modelo.Conexion.Conexion;
import Modelo.ProductoVO;
import Modelo.VentaVO;
import Vista.Admin.VistaMenu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ComprarDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private VistaMenu vista = new VistaMenu();

    public ComprarDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }

    public DefaultTableModel cargarDatosTabla(ArrayList<Carrito> listaCarrito, ArrayList<ProductoVO> listaProductos) {
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            for (Carrito productorr : listaCarrito) {
                for (ProductoVO productoSelec : listaProductos) {
                    if (productoSelec.getNombre().equals(productorr.getNombre())) {
                        String consulta = "SELECT * FROM producto WHERE `nombre`='" + productorr.getNombre() + "'";
                        // Consulta SQL para obtener los datos
                        con = Conexion.getConexion();
                        st = con.createStatement();
                        rs = st.executeQuery(consulta);
                        
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
                        
                    }
                }
            }
            return (modelo);
        }catch (SQLException e) {
            e.printStackTrace();
        }
                                return null;
        }

    public ArrayList<VentaVO> listaDePendientes() {
        ArrayList<VentaVO> listaPendientes = new ArrayList<>();
        String consulta = "SELECT * FROM ventas WHERE `Estado`= Pendiente";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                // Crea objetos ProductoVO y los agrega a la lista
                listaPendientes.add(new VentaVO(rs.getString("Folio"), rs.getString("Fecha"),
                        rs.getString("Hora"), rs.getString("Estado"), rs.getString("Detalles"),
                        Double.parseDouble(rs.getString("Subtotal")), Double.parseDouble(rs.getString("Total"))));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            vista.errorConsola("No se pudo");
        }
        return listaPendientes;
    }
}
