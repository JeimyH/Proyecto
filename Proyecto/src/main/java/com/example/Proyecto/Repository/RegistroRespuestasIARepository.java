package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroRespuestasIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRespuestasIARepository extends JpaRepository<RegistroRespuestasIA, Long> {
    // Guardar respuesta generada por el chatbot
    @Modifying
    @Query(value = "INSERT INTO RegistroRespuestasIA (id_sesion, datosRespuesta, timestamp) VALUES (:id_sesion, :datosRespuesta, CURRENT_TIMESTAMP)", nativeQuery = true)
    void guardarRespuestaGenerada(@Param("id_sesion") Integer id_sesion, @Param("datosRespuesta") String datosRespuesta);

    // Consultar respuestas anteriores por sesión
    @Query(value = "SELECT * FROM RegistroRespuestasIA WHERE id_sesion = :id_sesion ORDER BY timestamp DESC", nativeQuery = true)
    List<RegistroRespuestasIA> consultarRespuestasAnteriores(@Param("id_sesion") Integer id_sesion);
}
