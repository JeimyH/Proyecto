package com.example.Proyecto.Service;

import com.example.Proyecto.Model.PreferenciasUsuario;
import com.example.Proyecto.Model.Usuario;
import com.example.Proyecto.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    public UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios(){
        // Validacion para intentar obtener la lista de los usuarios
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            // Validar que la lista no sea nula
            if (usuarios == null) {
                throw new IllegalStateException("No se encontraron usuarios.");
            }
            return usuarios;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar usuarios: " + e.getMessage(), e);
        }
    }

    public Optional<Usuario> listarPorIdUsuario(long id_usuario){
        try {
            Optional<Usuario> usuario = usuarioRepository.findById(id_usuario);
            if (usuario.isPresent()) {
                return usuario;
            } else {
                throw new IllegalStateException("No se encontraron usuarios.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el usuario " + id_usuario +": "+ e.getMessage(), e);
        }
    }

    public Usuario guardarUsuario(Usuario usuario){
        // Inicializa el campo creadoEn
        usuario.setCreadoEn(new Timestamp(System.currentTimeMillis()));
        try{
            if(usuario==null){
                throw new IllegalArgumentException("El usuario no puede ser nulo");

            }else{
                if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del usuario es obligatorio.");
                }else if(usuario.getContrasena()== null || usuario.getContrasena().isEmpty()){
                    throw new IllegalArgumentException("La contraseña del usuario es obligatorio.");
                }else if(usuario.getCorreo()==null || usuario.getCorreo().isEmpty()) {
                    throw new IllegalArgumentException("El correo del usuario es obligatorio.");
                } else if (usuario.getAltura()==0) {
                    throw new IllegalArgumentException("La altura del usuario es obligatorio.");
                } else if (usuario.getPeso()==0) {
                    throw new IllegalArgumentException("El peso del usuario es obligatorio.");
                }else if (usuario.getCreadoEn() == null) {
                    throw new IllegalArgumentException("La fecha de creacion del usuario es obligatoria.");
                }
                return  usuarioRepository.save(usuario);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el usuario " + e.getMessage(), e);
        }
    }

    public void eliminarUsuario(long id_usuario){
        try {
            if (id_usuario<=0) {
                throw new IllegalArgumentException("El ID del usuario debe ser un número positivo.");
            }
            if (!usuarioRepository.existsById(id_usuario)) {
                throw new NoSuchElementException("No se encontró un usuario con el ID: " + id_usuario);
            }
            usuarioRepository.deleteById(id_usuario);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el usuario "+ id_usuario +": "+ e.getMessage(), e);
        }
    }

    public Usuario actualizarUsuario(long id_usuario, Usuario usuarioActualizado){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id_usuario);
        if(usuarioOpt.isPresent()){
            Usuario usuarioExistente = usuarioOpt.get();
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
            usuarioExistente.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuarioExistente.setAltura(usuarioActualizado.getAltura());
            usuarioExistente.setPeso(usuarioActualizado.getPeso());
            usuarioExistente.setRestriccionesDieta(usuarioActualizado.getRestriccionesDieta());
            usuarioExistente.setObjetivosSalud(usuarioActualizado.getObjetivosSalud());
            //usuarioExistente.setCreadoEn(usuarioActualizado.getCreadoEn());
            usuarioExistente.setActualizadoEn(new Timestamp(System.currentTimeMillis()));
            return usuarioRepository.save(usuarioExistente);
        }else{
            return null;
        }
    }

    public Usuario obtenerPorCorreo(@Param("correo") String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public Usuario obtenerLogin(@Param("correo") String correo, @Param("contrasena") String contrasena){
        return usuarioRepository.validateLogin(correo,contrasena);
    }

    public PreferenciasUsuario obtenerPreferenciasPorUsuarioId(@Param("id_usuario") Integer id_usuario){
        return usuarioRepository.findPreferenciasByUsuarioId(id_usuario);
    }

    public Usuario obtenerPorCorreoYContrasena(@Param("correo") String correo, @Param("contrasena") String contrasena){
        return usuarioRepository.findByCorreoAndContrasena(correo,contrasena);
    }

    public int siExisteCorreo(@Param("correo") String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public void obtenerDatosPersonales(@Param("id_usuario") Integer id_usuario,@Param("nombre") String nombre,@Param("peso") Float peso,@Param("altura") Float altura,@Param("fechaNacimiento") String fechaNacimiento){
        usuarioRepository.updateDatosPersonales(id_usuario,nombre,peso,altura,fechaNacimiento);
    }

    public PreferenciasUsuario obtenerPreferenciasYRestriccionesPorUsuarioId(@Param("id_usuario") Integer id_usuario){
        return usuarioRepository.findPreferenciasAndRestriccionesByUsuarioId(id_usuario);
    }
}
