package Modelo;

public class ProductoVO {
    private String id;
    private String nombre;
    private String serie;
    private double precio;
    private int stock;
    private int minStock;
    private String encargo;
    private String imagen;
    private String descripcion;
    private String pais;
    private String fabricante;
    private String peso;
    private String medidas;
    private int garantia;
    private String proveedor;
    private String tipo;

    public ProductoVO() {
    }

    public ProductoVO(String id, String nombre, String serie, double precio, int stock, int minStock, String encargo, String imagen, String descripcion, String pais, String fabricante, String peso, String medidas, int garantia, String proveedor, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.serie = serie;
        this.precio = precio;
        this.stock = stock;
        this.minStock = minStock;
        this.encargo = encargo;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.pais = pais;
        this.fabricante = fabricante;
        this.peso = peso;
        this.medidas = medidas;
        this.garantia = garantia;
        this.proveedor = proveedor;
        this.tipo = tipo;
    }
    

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        this.minStock = minStock;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEncargo() {
        return encargo;
    }

    public void setEncargo(String encargo) {
        this.encargo = encargo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getMedidas() {
        return medidas;
    }

    public void setMedidas(String medidas) {
        this.medidas = medidas;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

}
