package ni.edu.uam.servicioweb.model;

public class Usuario {

    private Integer id;
    private String nombre;
    private String username;
    private String empresa;
    private String telefono;
    private String email;
    private String ciudad;


    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String username, String empresa, String telefono, String email, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.empresa = empresa;
        this.telefono = telefono;
        this.email = email;
        this.ciudad = ciudad;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
