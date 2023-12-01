package Modelo;

public class DiscosDuros extends ProductoVO{

    public DiscosDuros() {
    }
    public DiscosDuros(String id, String nombre, String serie, double precio, int stock, int minStock, String encargo, String imagen, String descripcion, String pais, String fabricante, String peso, String medidas, int garantia, String proveedor, String tipo) {
        super(id, nombre, serie, precio, stock, minStock, encargo, imagen, descripcion, pais, fabricante, peso, medidas, garantia, proveedor, tipo);
    }
}
