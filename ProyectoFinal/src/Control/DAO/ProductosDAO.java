package Control.DAO;

import Modelo.Conexion.Conexion;
import Modelo.DiscosDuros;
import Modelo.LectoresdeTarjeta;
import Modelo.MemoriasRAM;
import Modelo.Monitores;
import Modelo.Mouses;
import Modelo.ProductoVO;
import Modelo.TarjetasdeSonido;
import Modelo.TarjetasdeVideo;
import Modelo.Teclados;
import Vista.Admin.VistaMenu;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ProductosDAO {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    private VistaMenu vista = new VistaMenu();
    public ProductosDAO() {
        // Inicializa las variables de conexión y resultados a null
        this.con = null;
        this.st = null;
        this.rs = null;
    }
    public ProductoVO comprobarTipo(String tipo, String id, String nombre, String serie,
            String precio, String cantidad, String cantidadMin, String encargo, String imagen,
            String descripcion, String pais, String fabricante, String peso, String medidas,
            String garantia, String proveedor) {
        double dprecio = Double.parseDouble(precio);
        int dcantidad = Integer.parseInt(cantidad);
        int dcantidadMin = Integer.parseInt(cantidadMin);
        int iGarantia = Integer.parseInt(garantia);
        if (null == tipo) {
            return null;
        } else {
            return switch (tipo) {
                case "Discos Duros" ->
                    new DiscosDuros(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Lectores de Tarjeta" ->
                    new LectoresdeTarjeta(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Memorias RAM" ->
                    new MemoriasRAM(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Monitores" ->
                    new Monitores(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Mouses" ->
                    new Mouses(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Tarjetas de Sonido" ->
                    new TarjetasdeSonido(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Tarjetas de Video" ->
                    new TarjetasdeVideo(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                case "Teclados" ->
                    new Teclados(id, nombre, serie, dprecio, dcantidad, dcantidadMin, encargo, imagen, descripcion,
                    pais, fabricante, peso, medidas, iGarantia, proveedor, tipo);
                default ->
                    null;
            };
        }

    }
    public ArrayList<ProductoVO> listaDeProductos() {
        ArrayList<ProductoVO> listaProductos = new ArrayList<>();
        String consulta = "SELECT * FROM producto";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                // Crea objetos ProductoVO y los agrega a la lista
                listaProductos.add(comprobarTipo(tipo, rs.getString("id"), rs.getString("nombre"),
                        rs.getString("serie"), rs.getString("precio"), rs.getString("cantidad"),
                        rs.getString("cantidadMin"), rs.getString("encargo"), rs.getString("imagen"),
                        rs.getString("descripcion"), rs.getString("pais"), rs.getString("fabricante"),
                        rs.getString("peso"), rs.getString("medidas"), rs.getString("garantia"),
                        rs.getString("proveedor")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            vista.errorConsola("No se pudo");
        }
        return listaProductos;
    }
    public ArrayList<ProductoVO> listaDeProductosTipo(String miTipo) {
        ArrayList<ProductoVO> listaProductos = new ArrayList<>();
        String consulta = "SELECT * FROM producto WHERE `tipo`='"+miTipo+"'";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                // Crea objetos ProductoVO y los agrega a la lista
                listaProductos.add(comprobarTipo(miTipo, rs.getString("id"), rs.getString("nombre"),
                        rs.getString("serie"), rs.getString("precio"), rs.getString("cantidad"),
                        rs.getString("cantidadMin"), rs.getString("encargo"), rs.getString("imagen"),
                        rs.getString("descripcion"), rs.getString("pais"), rs.getString("fabricante"),
                        rs.getString("peso"), rs.getString("medidas"), rs.getString("garantia"),
                        rs.getString("proveedor")));
            }
            // Cierra la declaración y desconecta de la base de datos
            st.close();
            Conexion.desconectar();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejo de excepciones en caso de error
            vista.errorConsola("No se pudo");
        }
        return listaProductos;
    }
    public void insertarDatos(ProductoVO producto) {
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Consulta de inserción con PreparedStatement
            String insercion = "INSERT INTO producto VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // Crea una consulta preparada
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(insercion);

            // Establece los parámetros de la consulta
            pst.setString(1, producto.getId());
            pst.setString(2, producto.getNombre());
            pst.setString(3, producto.getSerie());
            pst.setDouble(4, producto.getPrecio());
            pst.setInt(5, producto.getStock());
            pst.setInt(6, producto.getMinStock());
            pst.setString(7, producto.getEncargo());
            pst.setString(8, producto.getImagen());
            pst.setString(9, producto.getDescripcion());
            pst.setString(10, producto.getPais());
            pst.setString(11, producto.getFabricante());
            pst.setString(12, producto.getPeso());
            pst.setString(13, producto.getMedidas());
            pst.setInt(14, producto.getGarantia());
            pst.setString(15, producto.getProveedor());
            pst.setString(16, producto.getTipo());

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
    public void actualizarDatos(ProductoVO producto) {
        // Consulta de actualización para un objeto ProductosVO
        String modificacion = "UPDATE `producto` SET `id`='" + producto.getId() + "',`nombre`='" + producto.getNombre()
                + "',`serie`='" + producto.getSerie() + "',`precio`='" + producto.getPrecio() + "',`cantidad`='" + producto.getStock()
                + "',`cantidadMin`='" + producto.getMinStock() + "',`encargo`='" + producto.getEncargo() + "',`imagen`='" + producto.getImagen()
                + "',`descripcion`='" + producto.getDescripcion() + "',`pais`='" + producto.getPais() + "',`fabricante`='" + producto.getFabricante()
                + "',`peso`='" + producto.getPeso() + "',`medidas`='" + producto.getMedidas() + "',`garantia`='" + producto.getGarantia()
                + "',`proveedor`='" + producto.getProveedor() + "',`tipo`='" + producto.getTipo() + "' WHERE id='" + producto.getId() + "'";
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
    public void eliminarProducto(String id) {
        String consulta = "DELETE FROM producto where id='" + id + "'";
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
            String consulta = "SELECT * FROM producto";
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
    public DefaultTableModel consultarProductosBajos(ArrayList<ProductoVO> listaProductos) {
        String[][] productosBajos = new String[400][2]; // 2 columnas: nombre y cantidad
        DefaultTableModel modelo = new DefaultTableModel();

        // Agregar las columnas al modelo (opcional, pero puede ser útil)
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");

        try {
            con = Conexion.getConexion();
            st = con.createStatement();

            // Iterar sobre la lista de productos
            String consulta = "SELECT `nombre`,`cantidad` FROM producto WHERE cantidadMin > cantidad";
            rs = st.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos al array bidimensional
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String cantidad = rs.getString("cantidad");

                // Buscar la primera fila vacía en el array bidimensional para agregar los datos
                for (String[] productosBajo : productosBajos) {
                    if (productosBajo[0] == null) {
                        productosBajo[0] = nombre;
                        productosBajo[1] = cantidad;
                        break;
                    }
                }
            }

            // Llenar el modelo con los datos del array bidimensional
            for (String[] producto : productosBajos) {
                if (producto[0] != null) {
                    modelo.addRow(producto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return modelo;
    }
    public ArrayList<ProductoVO> listadePendientesporStock() {
        ArrayList<ProductoVO> listaPendientes = new ArrayList<>();
        String consulta = "SELECT * FROM producto WHERE cantidadMin > cantidad AND (`encargo`='no' OR `encargo`='NO' )";
        try {
            // Obtiene una conexión a la base de datos
            con = Conexion.getConexion();
            // Crea una declaración SQL
            st = (Statement) con.createStatement();
            // Ejecuta la consulta
            rs = st.executeQuery(consulta);

            while (rs.next()) {
                String tipo = rs.getString("tipo");
                // Crea objetos ProductoVO y los agrega a la lista
                listaPendientes.add(comprobarTipo(tipo, rs.getString("id"), rs.getString("nombre"),
                        rs.getString("serie"), rs.getString("precio"), rs.getString("cantidad"),
                        rs.getString("cantidadMin"), rs.getString("encargo"), rs.getString("imagen"),
                        rs.getString("descripcion"), rs.getString("pais"), rs.getString("fabricante"),
                        rs.getString("peso"), rs.getString("medidas"), rs.getString("garantia"),
                        rs.getString("proveedor")));
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
    public void consultarStock() {
        int total = 0;
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            
            String consulta = "SELECT `cantidad` FROM producto";
            rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                int cantidad = Integer.parseInt(rs.getString("cantidad"));
                total += cantidad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.vista.msg("EL TOTAL DE SU INVENTARIO ES: " + total);
    }
    public DefaultTableModel consultarCantidadInventario() {
        String[][] inventario = new String[400][2]; // 2 columnas: nombre y cantidad
        DefaultTableModel modelo = new DefaultTableModel();

        // Agregar las columnas al modelo (opcional, pero puede ser útil)
        modelo.addColumn("Nombre");
        modelo.addColumn("Cantidad");

        try {
            con = Conexion.getConexion();
            st = con.createStatement();

            // Iterar sobre la lista de productos
            String consulta = "SELECT `nombre`,`cantidad` FROM producto";
            rs = st.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos al array bidimensional
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String cantidad = rs.getString("cantidad");

                // Buscar la primera fila vacía en el array bidimensional para agregar los datos
                for (String[] productosBajo : inventario) {
                    if (productosBajo[0] == null) {
                        productosBajo[0] = nombre;
                        productosBajo[1] = cantidad;
                        break;
                    }
                }
            }

            // Llenar el modelo con los datos del array bidimensional
            for (String[] producto : inventario) {
                if (producto[0] != null) {
                    modelo.addRow(producto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return modelo;
    }
    public void consultarTotal() {
        double total = 0;
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            
            String consulta = "SELECT `precio` FROM producto";
            rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                double cantidad = Double.parseDouble(rs.getString("precio"));
                total += cantidad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.vista.msg("EL VALOR TOTAL DE SU INVENTARIO ES: " + total);
    }
    public DefaultTableModel consultarPrecioInventario() {
        String[][] inventario = new String[400][2]; // 2 columnas: nombre y cantidad
        DefaultTableModel modelo = new DefaultTableModel();

        // Agregar las columnas al modelo (opcional, pero puede ser útil)
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");

        try {
            con = Conexion.getConexion();
            st = con.createStatement();

            // Iterar sobre la lista de productos
            String consulta = "SELECT `nombre`,`precio` FROM producto";
            rs = st.executeQuery(consulta);

            // Iterar sobre los resultados y agregarlos al array bidimensional
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String precio = rs.getString("precio");

                // Buscar la primera fila vacía en el array bidimensional para agregar los datos
                for (String[] productosBajo : inventario) {
                    if (productosBajo[0] == null) {
                        productosBajo[0] = nombre;
                        productosBajo[1] = precio;
                        break;
                    }
                }
            }

            // Llenar el modelo con los datos del array bidimensional
            for (String[] producto : inventario) {
                if (producto[0] != null) {
                    modelo.addRow(producto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return modelo;
    }
    public String consultarStockProducto(ProductoVO producto) {
        try {
            con = Conexion.getConexion();
            st = con.createStatement();
            
            String consulta = "SELECT `cantidad` FROM producto WHERE `id`="+producto.getId();
            rs = st.executeQuery(consulta);
            
            while (rs.next()) {
                String cantidad = rs.getString("cantidad");
                return cantidad;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
                Conexion.desconectar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null; 
    }
}