package Modelo;

public class ProveedoresVO {
    private String nombre;
    private String id;
    private String direccion;
    private String telefono;
    private String correo;

    public ProveedoresVO(String nombre, String id, String direccion, String telefono, String correo) {
        this.nombre = nombre;
        this.id = id;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public ProveedoresVO() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
