package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.PreferenciasUsuario;
import com.example.Proyecto.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar usuario por correo
    @Query(value = "SELECT * FROM Usuario WHERE correo = :correo", nativeQuery = true)
    Usuario findByCorreo(@Param("correo") String correo);

    // Validar login
    @Query(value = "SELECT * FROM Usuario WHERE correo = :correo AND contrasena = :contrasena", nativeQuery = true)
    Usuario validateLogin(@Param("correo") String correo, @Param("contrasena") String contrasena);

    // Obtener preferencias alimenticias
    @Query(value = "SELECT * FROM PreferenciasUsuario WHERE id_usuario = :id_usuario", nativeQuery = true)
    PreferenciasUsuario findPreferenciasByUsuarioId(@Param("id_usuario") Integer id_usuario);

    // Buscar usuario por correo electrónico y contraseña
    @Query(value = "SELECT * FROM Usuario WHERE correo = :correo AND contrasena = :contrasena", nativeQuery = true)
    Usuario findByCorreoAndContrasena(@Param("correo") String correo, @Param("contrasena") String contrasena);

    // Verificar si un correo ya está registrado
    @Query(value = "SELECT COUNT(*) FROM Usuario WHERE correo = :correo", nativeQuery = true)
    int existsByCorreo(@Param("correo") String correo);

    // Actualizar datos personales
    @Modifying
    @Query(value = "UPDATE Usuario SET nombre = :nombre, peso = :peso, altura = :altura, fechaNacimiento = :fechaNacimiento WHERE idUsuario = :idUsuario", nativeQuery = true)
    void updateDatosPersonales(@Param("idUsuario") Integer idUsuario,
                               @Param("nombre") String nombre,
                               @Param("peso") Float peso,
                               @Param("altura") Float altura,
                               @Param("fechaNacimiento") String fechaNacimiento);

    // Obtener preferencias y restricciones del usuario
    @Query(value = "SELECT * FROM PreferenciasUsuario WHERE idUsuario = :idUsuario", nativeQuery = true)
    PreferenciasUsuario findPreferenciasAndRestriccionesByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
