package uniandes.edu.co.proyecto.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import uniandes.edu.co.proyecto.modelo.OrdenCompra;
import uniandes.edu.co.proyecto.repositorio.OrdenCompraRepository;

@RestController
public class OrdenCompraController {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @GetMapping("/ordencompra")
    public Collection<OrdenCompra> ordenesCompra() {
        return ordenCompraRepository.darOrdenesCompra();
    }

    @PostMapping("/ordencompra/new/save")
    public ResponseEntity<String> guardarOrdenCompra(@RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.insertarOrdenCompra(
                    ordenCompra.getFechaEsperada(),
                    ordenCompra.getFechaCreacion(),
                    ordenCompra.getEstado()
            );
            return new ResponseEntity<>("Orden de compra creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la orden de compra", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ordencompra/{id}/edit/save")
    public ResponseEntity<String> editarGuardarOrdenCompra(@PathVariable("id") int id, @RequestBody OrdenCompra ordenCompra) {
        try {
            ordenCompraRepository.actualizarOrdenCompra(
                    id,
                    ordenCompra.getFechaEsperada(),
                    ordenCompra.getFechaCreacion(),
                    ordenCompra.getEstado()
            );
            return new ResponseEntity<>("Orden de compra actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La orden de compra no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ordencompra/{id}/delete")
    public ResponseEntity<String> eliminarOrdenCompra(@PathVariable("id") int id) {
        try {
            ordenCompraRepository.eliminarOrdenCompra(id);
            return new ResponseEntity<>("Orden de compra eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La orden de compra no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}