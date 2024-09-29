package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Ciudad;

@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {

    @Query(value = "SELECT * FROM ciudad", nativeQuery = true)
    Collection<Ciudad> darCiudades();
    
    @Query(value = "SELECT * FROM Ciudad WHERE codigo = :codigo", nativeQuery = true)
    Ciudad darCiudad(@Param("codigo") int codigo);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Ciudad (nombre) VALUES(:nombre)", nativeQuery = true)
    void insertarCiudad(@Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Ciudad SET nombre=:nombre WHERE codigo=:codigo", nativeQuery = true)
    void actualizarCiudad(@Param("codigo") int codigo, @Param("nombre") String nombre);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Ciudad WHERE codigo=:codigo", nativeQuery = true)
    void eliminarCiudad(@Param("codigo") int codigo);
}

