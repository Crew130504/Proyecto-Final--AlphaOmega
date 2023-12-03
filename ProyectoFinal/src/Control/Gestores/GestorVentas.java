package Control.Gestores;

import Control.DAO.HistorialInventarioDAO;
import Control.DAO.ProductosDAO;
import Control.DAO.VentasDAO;
import Modelo.ProductoVO;
import Modelo.VentaVO;
import Vista.Admin.VistaTabla;
import Vista.Admin.VistaVentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class GestorVentas implements ActionListener {

    private ArrayList<VentaVO> listaVentas;
    private VistaVentas vista;
    private VistaTabla vistaTabla = new VistaTabla();
    private GestorMenu gestorMenu;
    private VentaVO objVenta = new VentaVO();
    private VentasDAO dao = new VentasDAO();
    private HistorialInventarioDAO historialDao = new HistorialInventarioDAO();
    private ProductosDAO productosDao = new ProductosDAO();

    public GestorVentas() {
        this.vista = new VistaVentas();
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnVolver.addActionListener(this);
        this.vista.btnComprobar.addActionListener(this);
        this.vista.btnSeleccionar.addActionListener(this);
        this.vista.btnPendientes.addActionListener(this);
        this.vista.btnHistorial.addActionListener(this);
    }

    public void iniciar(GestorMenu vuelta) {
        llenarCombo();
        // Insercion de logica de las funciones necesaria
        gestorMenu = vuelta;
        // Centra la vistaBienvenida en el centro de la pantalla
        this.vista.setLocationRelativeTo(null);
        this.vista.setResizable(false);
        // Hace visible la vistaBienvenida
        this.vista.setVisible(true);
    }

    public void llenarCombo() {
        this.vista.comboxVentas.removeAllItems(); // Limpia los elementos existentes en el ComboBox

        listaVentas = dao.listaDeVentas();
        // Agregar la opción nula o predeterminada al principio del ComboBox
        vista.comboxVentas.addItem("Seleccionar");

        for (int i = 0; i < listaVentas.size(); i++) {
            vista.comboxVentas.addItem(listaVentas.get(i).getFolio());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vista.btnVolver) {
            this.vista.setVisible(false);
            this.gestorMenu.iniciar();
        }
        if (e.getSource() == this.vista.btnBuscar) {
            String folio = this.vista.txtBuscarFolio.getText();
            VentaVO ventaBuscada = dao.consultarPorFolio(folio);
            if (ventaBuscada == null) {
                this.vista.error("FOLIO NO ENCONTRADO");
            } else {
                this.vista.txtNombre.setText(ventaBuscada.getCliente());
                this.vista.txtFecha.setText(ventaBuscada.getFecha());
                this.vista.txtHora.setText(ventaBuscada.getHora());
                this.vista.txtFolio.setText(ventaBuscada.getFolio());
                this.vista.txtDetalles.setText(ventaBuscada.getDetalles());
                this.vista.txtEstado.setText(ventaBuscada.getEstado());
                this.vista.txtSubTotal.setText(String.valueOf(ventaBuscada.getSubtotal()));
                this.vista.txtTotal.setText(String.valueOf(ventaBuscada.getTotal()));
            }
        }
        if (e.getSource() == this.vista.btnComprobar) {
            String folio = this.vista.txtFolio.getText();
            VentaVO ventaBuscada = dao.consultarPorFolio(folio);
            // La cadena de fecha
            String fechaString = ventaBuscada.getFecha();

            // Convertir la cadena a LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaIngresada = LocalDate.parse(fechaString, formatter);

            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Calcular la diferencia en días
            long diferenciaEnDias = ChronoUnit.DAYS.between(fechaIngresada, fechaActual);

            // Verificar si la diferencia está entre 5 y 10 días
            if (diferenciaEnDias >= 5 && diferenciaEnDias <= 10) {
                if (ventaBuscada == null) {
                    this.vista.error("FOLIO NO ENCONTRADO");
                } else {
                    if (ventaBuscada.getEstado().equalsIgnoreCase("Pendiente")) {
                        String inputString = ventaBuscada.getDetalles();

                        // Dividir la cadena en líneas
                        String[] lines = inputString.split("\n");

                        // Iterar sobre las líneas en bloques de cuatro
                        for (int i = 0; i < lines.length; i += 4) {
                            // Verificar si hay suficientes líneas en el bloque
                            if (i + 3 < lines.length) {
                                String nombreProducto = lines[i];

                                // Procesar las líneas restantes del bloque
                                try {
                                    int cantidad = Integer.parseInt(lines[i + 1]);
                                    ProductoVO miProducto = productosDao.consultarEncargoporNombre(nombreProducto);
                                    if (miProducto != null) {
                                        miProducto.setStock(miProducto.getStock() + cantidad);
                                        ventaBuscada.setEstado("ENTREGADO");
                                        historialDao.ingresoCantidad(miProducto, cantidad);
                                        dao.actualizarDato(ventaBuscada);
                                        this.vista.limpiar();
                                        this.vista.msg("VENTE MARCADA COMO ENTREGADA");
                                    }
                                } catch (NumberFormatException ex) {
                                    this.vista.errorConsola("Error al convertir cadena a número: " + ex.getMessage());
                                }
                            } else {
                                this.vista.errorConsola("Error: Bloque de datos incompleto");
                            }
                        }
                    }
                }
            }
        }
        if (e.getSource() == this.vista.btnSeleccionar) {
            String folio = (String) this.vista.comboxVentas.getSelectedItem();
            VentaVO ventaBuscada = dao.consultarPorFolio(folio);
            if (ventaBuscada == null) {
                this.vista.error("SELECCIONE UN FOLIO");
            } else {
                this.vista.txtNombre.setText(ventaBuscada.getCliente());
                this.vista.txtFecha.setText(ventaBuscada.getFecha());
                this.vista.txtHora.setText(ventaBuscada.getHora());
                this.vista.txtFolio.setText(ventaBuscada.getFolio());
                this.vista.txtDetalles.setText(ventaBuscada.getDetalles());
                this.vista.txtEstado.setText(ventaBuscada.getEstado());
                this.vista.txtSubTotal.setText(String.valueOf(ventaBuscada.getSubtotal()));
                this.vista.txtTotal.setText(String.valueOf(ventaBuscada.getTotal()));
            }
        }
        if (e.getSource() == this.vista.btnPendientes) {
            this.vistaTabla.mostrarVentana(this.dao.cargarDatosTabla(), "PRODUCTOS");
        }
        if (e.getSource() == this.vista.btnHistorial) {
            String opcion = this.vista.capturar("1)DIA\n2)MES\n3)AÑO");
            switch (opcion) {
                case "1":
                    int dia = Integer.parseInt(this.vista.capturar("Digite el Dia: "));
                    this.vistaTabla.mostrarVentana(this.dao.cargarDatosTablaDia(dia), "VENTAS POR DIA");
                    break;
                case "2":
                    int mes = Integer.parseInt(this.vista.capturar("Digite el numero del Mes: "));
                    this.vistaTabla.mostrarVentana(this.dao.cargarDatosTablaMes(mes), "VENTAS POR DIA");
                    
                    
                case "3":
                    int year = Integer.parseInt(this.vista.capturar("Digite el Año: "));
                    this.vistaTabla.mostrarVentana(this.dao.cargarDatosTablaYear(year), "VENTAS POR DIA");
                    
                default:
                    break;
            }

        }
    }
}
