package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.ConfiguracionAplicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfiguracionAplicacionRepository extends JpaRepository<ConfiguracionAplicacion, Long> {
    // Obtener configuraciones del usuario
    @Query(value = "SELECT * FROM ConfiguracionAplicacion WHERE id_usuario = :id_usuario", nativeQuery = true)
    List<ConfiguracionAplicacion> obtenerConfiguracionesDelUsuario(@Param("id_usuario") Integer id_usuario);

    // Actualizar idioma o unidades
    @Modifying
    @Query(value = "UPDATE ConfiguracionAplicacion SET idioma = :idioma, unidades = :unidades WHERE id_usuario = :id_usuario", nativeQuery = true)
    void actualizarIdiomaOUnidades(@Param("id_usuario") Integer id_usuario,
                                   @Param("idioma") String idioma,
                                   @Param("unidades") String unidades);

    // Activar/desactivar notificaciones
    @Modifying
    @Query(value = "UPDATE ConfiguracionAplicacion SET notificaciones = :activadas WHERE id_usuario = :id_usuario", nativeQuery = true)
    void activarDesactivarNotificaciones(@Param("id_usuario") Integer id_usuario, @Param("activadas") boolean activadas);

}
