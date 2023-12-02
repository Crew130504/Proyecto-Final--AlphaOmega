package Control.DAO;

import Modelo.Carrito;
import Modelo.Conexion.Conexion;
import Modelo.ProductoVO;
import Modelo.VentaVO;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
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

    
}
