package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursal")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sucursal_sequence_gen")
    @SequenceGenerator(name = "sucursal_sequence_gen", sequenceName = "sucursal_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tamanio")
    private Integer tamanio;
    @Column(name = "direccion")
    private String direccion;

    // FOREIGN KEYS

    @ManyToOne
    @JoinColumn(name = "ciudad_codigo", referencedColumnName = "codigo")
    private Ciudad ciudad;



    public Sucursal(String nombre, Integer tamanio, String direccion){
        
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.direccion = direccion;

    }

    public Sucursal(){
        ;
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

    public Integer getTamanio() {
        return tamanio;
    }

    public void setTamanio(Integer tamanio) {
        this.tamanio = tamanio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
    
}
