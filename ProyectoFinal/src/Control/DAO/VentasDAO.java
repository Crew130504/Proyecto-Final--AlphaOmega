package Control.DAO;

import Modelo.Conexion.Conexion;
import Modelo.VentaVO;
import Vista.Admin.VistaMenu;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
                listaPendientes.add(new VentaVO( rs.getString("Folio"), rs.getString("Fecha"),
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
