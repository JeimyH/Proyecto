package com.example.Proyecto.Controller;

import com.example.Proyecto.Model.Usuario;
import com.example.Proyecto.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/Usuario")
public class UsuarioController {
    @Autowired
    public UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        // Verificar si la lista está vacía
        if (usuarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        }
        return new ResponseEntity<>(usuarios, HttpStatus.OK); // 200 OK
    }

    @GetMapping("/buscar/{id_usuario}")
    public ResponseEntity<Usuario> listarPorIdUsuario(@PathVariable long id_usuario){
        try {
            Optional<Usuario> usuarioOpt = usuarioService.listarPorIdUsuario(id_usuario);
            return usuarioOpt.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND)); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        try {
            Usuario nuevoUsuario = usuarioService.guardarUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED); // 201 Created
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @DeleteMapping("/eliminar/{id_usuario}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable long id_usuario){
        try {
            usuarioService.eliminarUsuario(id_usuario);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }

    @PutMapping("/actualizar/{id_usuario}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id_usuario, @RequestBody Usuario usuarioActualizado){
        try {
            Usuario usuario = usuarioService.actualizarUsuario(id_usuario, usuarioActualizado);
            return new ResponseEntity<>(usuario, HttpStatus.OK); // 200 OK
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
    }
}
