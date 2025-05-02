package com.example.Proyecto.Controller;

import com.example.Proyecto.Model.APINutricional;
import com.example.Proyecto.Service.APINutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/APINutricional")
public class APINutricionalController {
    @Autowired
    public APINutricionalService apiNutricionalService;

    @GetMapping("/listar")
    public ResponseEntity<List<APINutricional>> listarAPINutricional() {
        List<APINutricional> apiNutricionals = apiNutricionalService.listarAPIs();
        // Verificar si la lista está vacía
        if (apiNutricionals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(apiNutricionals, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_api}")
    public ResponseEntity<APINutricional> listarPorIdAPINutricional(@PathVariable long id_api){
        try {
            Optional<APINutricional> apiNutricionalOpt = apiNutricionalService.listarPorIdAPI(id_api);
            return apiNutricionalOpt.map(apiNutricional -> new ResponseEntity<>(apiNutricional, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<APINutricional> guardarAPINutricional(@RequestBody APINutricional apiNutricional){
        try {
            APINutricional nuevoApi = apiNutricionalService.guardarAPI(apiNutricional);
            return new ResponseEntity<>(nuevoApi, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_api}")
    public ResponseEntity<Void> eliminarAPINutricional(@PathVariable long id_api){
        try {
            apiNutricionalService.eliminarAPI(id_api);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_api}")
    public ResponseEntity<APINutricional> actualizarAPINutricional(@PathVariable long id_api, @RequestBody APINutricional apiActualizado){
        try {
            APINutricional apiNutricional = apiNutricionalService.actualizarAPI(id_api, apiActualizado);
            return new ResponseEntity<>(apiNutricional, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
