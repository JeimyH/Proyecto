package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.InteraccionChatbot;
import com.example.Proyecto.Model.SesionChatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SesionChatbotRepository extends JpaRepository<SesionChatbot, Long> {
    // Obtener sesiones activas del usuario
    @Query(value = "SELECT * FROM SesionChatbot WHERE id_usuario = :id_usuario AND estado = 'ACTIVA'", nativeQuery = true)
    List<SesionChatbot> obtenerSesionesActivas(@Param("id_usuario") Integer id_usuario);

    // Crear nueva sesión
    @Modifying
    @Query(value = "INSERT INTO SesionChatbot (id_usuario, estado, fechaInicio) VALUES (:id_usuario, 'ACTIVA', CURRENT_TIMESTAMP)", nativeQuery = true)
    void crearNuevaSesion(@Param("id_usuario") Integer id_usuario);

    // Finalizar sesión
    @Modifying
    @Query(value = "UPDATE SesionChatbot SET estado = 'FINALIZADA', fechaFin = CURRENT_TIMESTAMP WHERE id_sesion = :id_sesion", nativeQuery = true)
    void finalizarSesion(@Param("id_sesion") Integer id_sesion);

    // Obtener mensajes y recomendaciones anteriores
    @Query(value = "SELECT * FROM InteraccionChatbot WHERE id_sesion = :id_sesion ORDER BY fechaRegistro DESC", nativeQuery = true)
    List<InteraccionChatbot> obtenerMensajesYRecomendaciones(@Param("id_sesion") Integer id_sesion);
}
