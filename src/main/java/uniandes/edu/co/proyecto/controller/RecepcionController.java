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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uniandes.edu.co.proyecto.modelo.Recepcion;
import uniandes.edu.co.proyecto.repositorio.RecepcionRepository;
import uniandes.edu.co.proyecto.servicio.RecepcionService;

@RestController
public class RecepcionController {

    @Autowired
    private RecepcionRepository recepcionRepository;

    @Autowired
    private RecepcionService recepcionService;

    @GetMapping("/recepcion")
    public Collection<Recepcion> recepciones() {
        return recepcionRepository.darRecepciones();
    }

    @GetMapping("/recepcion/{recepcion_id}")
    public Recepcion darRecepcionRF10(@PathVariable("recepcion_id") int recepcion_id) {
        return recepcionRepository.darRecepcionRF10(recepcion_id);
    }

    @PostMapping("/recepcion/{ordencompra_id}/{bodega_id}/new/save")
    public ResponseEntity<String> guardarRecepcion(@PathVariable("ordencompra_id") int ordencompra_id, @PathVariable("bodega_id") int bodega_id) {
        try {
            recepcionService.recepcionRF10(ordencompra_id, bodega_id);
            return new ResponseEntity<>("Recepción creada exitosamente", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear la Recepción", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/recepcion/{id}/edit/save")
    public ResponseEntity<String> editarGuardarRecepcion(
            @PathVariable("id") int id,
            @RequestBody Recepcion recepcion) {
        try {
            recepcionRepository.actualizarRecepcion(
                    id,
                    recepcion.getFechaRecepcion()
            );
            return new ResponseEntity<>("Recepción actualizada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La recepción no se actualizó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recepcion/{id}/delete")
    public ResponseEntity<String> eliminarRecepcion(@PathVariable("id") int id) {
        try {
            recepcionRepository.eliminarRecepcion(id);
            return new ResponseEntity<>("Recepción eliminada exitosamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("La recepción no se eliminó correctamente", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/recepcion/rfc6/{idS}/{idB}")
    public Collection<Recepcion> consultaRfc6(@PathVariable("idS") int idS, @PathVariable("idB") int idB, RedirectAttributes redirectAttributes) throws InterruptedException{
           return recepcionService.consusltarRfc6(idS, idB);
    }

    @GetMapping("/recepcion/rfc7/{idS}/{idB}")
    public Collection<Recepcion> consultaRfc7(@PathVariable("idS") int idS, @PathVariable("idB") int idB, RedirectAttributes redirectAttributes) throws InterruptedException{
            return recepcionService.consusltarRfc7(idS, idB);

    }
    
}
