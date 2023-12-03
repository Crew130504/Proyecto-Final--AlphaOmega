package Modelo;

import java.util.ArrayList;

public class VentaVO {
    private String cliente;
    private String folio;
    private String fecha;
    private String hora;
    private String estado;
    private String detalles;
    private ArrayList<ProductoVO> producto;
    private ArrayList<Carrito> carrito;
    private double subtotal;
    private double total;

    public VentaVO(String cliente, String folio, String fecha, 
            String hora, String estado, String detalles, 
            ArrayList<ProductoVO> producto, ArrayList<Carrito> carrito, 
            double subtotal, double total) {
        this.cliente = cliente;
        this.folio = folio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.detalles = detalles;
        this.producto = producto;
        this.carrito = carrito;
        this.subtotal = subtotal;
        this.total = total;
    }

    public VentaVO(String cliente, String folio, String fecha, String hora, String estado, String detalles, double subtotal, double total) {
        this.cliente = cliente;
        this.folio = folio;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.total = total;
    }
    
    public VentaVO() {
    } 

    public ArrayList<ProductoVO> getProducto() {
        return producto;
    }

    public void setProducto(ArrayList<ProductoVO> producto) {
        this.producto = producto;
    }

    public ArrayList<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(ArrayList<Carrito> carrito) {
        this.carrito = carrito;
    }
    

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    

}
