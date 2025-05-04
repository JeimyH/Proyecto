package com.example.Proyecto.Controller;


import com.example.Proyecto.Model.RespuestaPlanificadorEdamam;
import com.example.Proyecto.Service.RespuestaPlanificadorEdamamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Planificador")
public class RespuestaPlanificadorEdamamController {
    @Autowired
    public RespuestaPlanificadorEdamamService planificadorEdamamService;

    @GetMapping("/listar")
    public ResponseEntity<List<RespuestaPlanificadorEdamam>> listarRespuestasPlanificadorEdamamEquipos() {
        List<RespuestaPlanificadorEdamam> respuestaPlanificadorEdamams = planificadorEdamamService.listarRespuestasPlanificadorEdamam();
        // Verificar si la lista está vacía
        if (respuestaPlanificadorEdamams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(respuestaPlanificadorEdamams, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_planificador}")
    public ResponseEntity<RespuestaPlanificadorEdamam> listarPorIdRespuestaPlanificadorEdamam(@PathVariable long id_planificador){
        try {
            Optional<RespuestaPlanificadorEdamam> resPlanificadorEdamamOpt = planificadorEdamamService.listarPorIdRespuestaPlanificadorEdamam(id_planificador);
            return resPlanificadorEdamamOpt.map(respuestaPlanificadorEdamam -> new ResponseEntity<>(respuestaPlanificadorEdamam, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<RespuestaPlanificadorEdamam> guardarRespuestaPlanificadorEdamam(@RequestBody RespuestaPlanificadorEdamam resPlanificadorEdamam){
        try {
            RespuestaPlanificadorEdamam nuevoResPlanEdamam = planificadorEdamamService.guardarRespuestaPlanificadorEdamam(resPlanificadorEdamam);
            return new ResponseEntity<>(nuevoResPlanEdamam, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_planificador}")
    public ResponseEntity<Void> eliminarRespuestaPlanificadorEdamam(@PathVariable long id_planificador){
        try {
            planificadorEdamamService.eliminarRespuestaPlanificadorEdamam(id_planificador);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_planificador}")
    public ResponseEntity<RespuestaPlanificadorEdamam> actualizarRespuestaPlanificadorEdamam(@PathVariable long id_planificador, @RequestBody RespuestaPlanificadorEdamam resPlanEdamamActualizado){
        try {
            RespuestaPlanificadorEdamam respuestaPlanificadorEdamam = planificadorEdamamService.actualizarRespuestaPlanificadorEdamam(id_planificador, resPlanEdamamActualizado);
            return new ResponseEntity<>(respuestaPlanificadorEdamam, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

}
