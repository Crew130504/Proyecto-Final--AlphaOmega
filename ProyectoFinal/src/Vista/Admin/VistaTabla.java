package Vista.Admin;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class VistaTabla {

    public VistaTabla() {
    }
    
    public void mostrarVentana(DefaultTableModel modelo,String titulo) {
        JTable jTable = crearJTableConEncabezadoPersonalizado(modelo);
 
        JFrame frame = new JFrame(titulo);
        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(jTable));
        //dimensiones
        frame.setSize(1328, 760);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JTable crearJTableConEncabezadoPersonalizado(DefaultTableModel modelo) {
        // Crear la JTable con el modelo proporcionado
        JTable jTable = new JTable(modelo);
        jTable.setEnabled(false);

        // Obtener el encabezado de la tabla
        JTableHeader encabezado = jTable.getTableHeader();

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        jTable.setDefaultRenderer(Object.class, centerRenderer);
        // Deshabilitar la redimension de columnas
        jTable.getTableHeader().setResizingAllowed(false);

        // Deshabilitar la redimension de filas
        jTable.getTableHeader().setReorderingAllowed(false);
        // Obtener los nombres de las columnas del modelo
        int columnCount = modelo.getColumnCount();
        String[] nombresColumnas = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            nombresColumnas[i] = modelo.getColumnName(i);
        }

        // Configurar el encabezado con nombres de columnas personalizados
        encabezado.setColumnModel(new DefaultTableColumnModel() {
            @Override
            public TableColumn getColumn(int columnIndex) {
                TableColumn columna = super.getColumn(columnIndex);
                columna.setHeaderValue(nombresColumnas[columnIndex]);
                return columna;
            }
        });
        encabezado.setDefaultRenderer(new DefaultTableCellRenderer() {
            {
                setHorizontalAlignment(JLabel.CENTER);
                setForeground(Color.WHITE);
                setBackground(Color.DARK_GRAY);
                setFont(getFont().deriveFont(Font.BOLD));
            }
        });

        // Establecer el encabezado personalizado en la tabla
        jTable.setTableHeader(encabezado);

        return jTable;
    }

}
