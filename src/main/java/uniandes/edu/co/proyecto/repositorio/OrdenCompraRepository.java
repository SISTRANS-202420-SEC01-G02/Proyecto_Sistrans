package uniandes.edu.co.proyecto.repositorio;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.OrdenCompra;

@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Integer> {

    @Query(value = "SELECT * FROM OrdenCompra", nativeQuery = true)
    Collection<OrdenCompra> darOrdenesCompra();
    
    @Query(value = "SELECT * FROM OrdenCompra WHERE id = :id", nativeQuery = true)
    OrdenCompra darOrdenCompra(@Param("id") int id);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OrdenCompra (id, fechaesperada, fechacreacion, estado, sucursal_id, proveedor_nit) VALUES(ordencompra_sequence.NEXTVAL, :fechaEsperada, :fechaCreacion, :estado, :sucursal_id, :proveedor_nit)", nativeQuery = true)
    void insertarOrdenCompra(@Param("fechaEsperada") Date fechaEsperada, @Param("fechaCreacion") Date fechaCreacion, @Param("estado") String estado, @Param("sucursal_id") Integer sucursal_id, @Param("proveedor_nit") Integer proveedor_nit);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OrdenCompra SET estado=:estado WHERE id=:id", nativeQuery = true)
    void actualizarOrdenCompra(@Param("id") int id, @Param("estado") String estado);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM OrdenCompra WHERE id=:id", nativeQuery = true)
    void eliminarOrdenCompra(@Param("id") int id);
}
