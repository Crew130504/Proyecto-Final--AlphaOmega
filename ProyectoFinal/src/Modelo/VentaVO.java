package Modelo;

public class VentaVO {
    private String folio;
    private String fecha;
    private String hora;
    private String estado;
    private String detalles;
    private double subtotal;
    private double total;

    public VentaVO(String folio, String fecha, String hora, String estado, String detalles, double subtotal, double total) {
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
