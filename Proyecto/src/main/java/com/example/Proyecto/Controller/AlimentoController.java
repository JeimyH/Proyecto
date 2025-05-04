package com.example.Proyecto.Controller;

import com.example.Proyecto.Model.Alimento;
import com.example.Proyecto.Service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Alimento")
public class AlimentoController {
    @Autowired
    public AlimentoService alimentoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Alimento>> listarAlimentos() {
        List<Alimento> alimentos = alimentoService.listarAlimentos();
        // Verificar si la lista está vacía
        if (alimentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(alimentos, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_alimento}")
    public ResponseEntity<Alimento> listarPorIdAlimento(@PathVariable long id_alimento){
        try {
            Optional<Alimento> alimentoOpt = alimentoService.listarPorIdAlimento(id_alimento);
            return alimentoOpt.map(alimento -> new ResponseEntity<>(alimento, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Alimento> guardarAlimento(@RequestBody Alimento alimento){
        try {
            Alimento nuevoAlimento = alimentoService.guardarAlimento(alimento);
            return new ResponseEntity<>(nuevoAlimento, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_alimento}")
    public ResponseEntity<Void> eliminarAlimento(@PathVariable long id_alimento){
        try {
            alimentoService.eliminarAlimento(id_alimento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_alimento}")
    public ResponseEntity<Alimento> actualizarAlimento(@PathVariable long id_alimento, @RequestBody Alimento alimentoActualizado){
        try {
            Alimento alimento = alimentoService.actualizarAlimento(id_alimento, alimentoActualizado);
            return new ResponseEntity<>(alimento, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

}
