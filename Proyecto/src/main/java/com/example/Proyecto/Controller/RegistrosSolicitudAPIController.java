package com.example.Proyecto.Controller;

import com.example.Proyecto.Model.RegistrosSolicitudAPI;
import com.example.Proyecto.Service.RegistrosSolicitudAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/RegistrosSolicitudAPI")
public class RegistrosSolicitudAPIController {
    @Autowired
    public RegistrosSolicitudAPIService solicitudAPIService;

    @GetMapping("/listar")
    public ResponseEntity<List<RegistrosSolicitudAPI>> listarRegistrosSolicitudAPI() {
        List<RegistrosSolicitudAPI> registrosSolicitudAPIS = solicitudAPIService.listarRegistrosSolicitudAPI();
        // Verificar si la lista está vacía
        if (registrosSolicitudAPIS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(registrosSolicitudAPIS, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_solicitud}")
    public ResponseEntity<RegistrosSolicitudAPI> listarPorIdRegistrosSolicitudAPI(@PathVariable long id_solicitud){
        try {
            Optional<RegistrosSolicitudAPI> registrosSolicitudAPIOpt = solicitudAPIService.listarPorIdRegistrosSolicitudAPI(id_solicitud);
            return registrosSolicitudAPIOpt.map(registrosSolicitudAPI -> new ResponseEntity<>(registrosSolicitudAPI, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<RegistrosSolicitudAPI> guardarRegistrosSolicitudAPI(@RequestBody RegistrosSolicitudAPI registrosSolicitudAPI){
        try {
            RegistrosSolicitudAPI nuevoRegistrosSolicitudAPI = solicitudAPIService.guardarRegistrosSolicitudAPI(registrosSolicitudAPI);
            return new ResponseEntity<>(nuevoRegistrosSolicitudAPI, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_solicitud}")
    public ResponseEntity<Void> eliminarRegistrosSolicitudAPI(@PathVariable long id_solicitud){
        try {
            solicitudAPIService.eliminarRegistrosSolicitudAPI(id_solicitud);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_solicitud}")
    public ResponseEntity<RegistrosSolicitudAPI> actualizarRegistrosSolicitudAPI(@PathVariable long id_solicitud, @RequestBody RegistrosSolicitudAPI registrosSolicitudAPIActualizado){
        try {
            RegistrosSolicitudAPI registrosSolicitudAPI = solicitudAPIService.actualizarRegistrosSolicitudAPI(id_solicitud, registrosSolicitudAPIActualizado);
            return new ResponseEntity<>(registrosSolicitudAPI, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
