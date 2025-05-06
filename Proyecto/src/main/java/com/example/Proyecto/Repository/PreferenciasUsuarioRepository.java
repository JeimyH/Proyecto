package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.PreferenciasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreferenciasUsuarioRepository extends JpaRepository<PreferenciasUsuario, Long> {
    // Obtener preferencias alimenticias por usuario
    @Query(value = "SELECT * FROM PreferenciasUsuario WHERE id_usuario = :id_usuario", nativeQuery = true)
    List<PreferenciasUsuario> obtenerPreferenciasPorUsuario(@Param("id_usuario") Integer id_usuario);

    // Actualizar preferencias
    @Modifying
    @Query(value = "UPDATE PreferenciasUsuario SET tipo = :tipo, valor = :valor WHERE id_usuario = :id_usuario AND tipo = :tipoAntiguo", nativeQuery = true)
    void actualizarPreferencias(@Param("id_usuario") Integer id_usuario,
                                @Param("tipo") String tipo,
                                @Param("valor") String valor,
                                @Param("tipoAntiguo") String tipoAntiguo);

    // Insertar nueva configuración del usuario
    @Modifying
    @Query(value = "INSERT INTO PreferenciasUsuario (id_usuario, tipo, valor) VALUES (:id_usuario, :tipo, :valor)", nativeQuery = true)
    void insertarNuevaConfiguracion(@Param("id_usuario") Integer id_usuario,
                                    @Param("tipo") String tipo,
                                    @Param("valor") String valor);
}
