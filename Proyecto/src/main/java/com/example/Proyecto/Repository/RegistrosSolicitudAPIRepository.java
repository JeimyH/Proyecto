package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistrosSolicitudAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrosSolicitudAPIRepository extends JpaRepository<RegistrosSolicitudAPI, Long> {
    // Registrar solicitudes a Open Food Facts o Edamam
    @Modifying
    @Query(value = "INSERT INTO RegistroSolicitudApi (nombreApi, solicitud, fechaRegistro) VALUES (:nombreApi, :solicitud, CURRENT_TIMESTAMP)", nativeQuery = true)
    void registrarSolicitud(@Param("nombreApi") String nombreApi, @Param("solicitud") String solicitud);

    // Obtener historial de solicitudes por fecha
    @Query(value = "SELECT * FROM RegistroSolicitudApi WHERE fechaRegistro BETWEEN :fechaInicio AND :fechaFin ORDER BY fechaRegistro DESC", nativeQuery = true)
    List<RegistrosSolicitudAPI> obtenerHistorialPorFecha(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin);

    // Consultar respuestas recibidas (caché)
    @Query(value = "SELECT respuesta FROM RegistroSolicitudApi WHERE id_solicitud = :id_solicitud", nativeQuery = true)
    String consultarRespuestasRecibidas(@Param("id_solicitud") Integer id_solicitud);
}
