package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RespuestaPlanificadorEdamam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaPlanificadorEdamamRepository extends JpaRepository<RespuestaPlanificadorEdamam, Long> {
    // Guardar respuesta del planificador
    @Modifying
    @Query(value = "INSERT INTO RespuestaPlanificador (idUsuario, respuesta, fechaRegistro) VALUES (:idUsuario, :respuesta, CURRENT_TIMESTAMP)", nativeQuery = true)
    void guardarRespuesta(@Param("idUsuario") Integer idUsuario, @Param("respuesta") String respuesta);

    // Consultar plan alimenticio por fecha
    @Query(value = "SELECT * FROM RespuestaPlanificador WHERE fechaRegistro = :fechaRegistro AND idUsuario = :idUsuario", nativeQuery = true)
    List<RespuestaPlanificadorEdamam> consultarPlanAlimenticioPorFecha(@Param("fechaRegistro") String fechaRegistro, @Param("idUsuario") Integer idUsuario);

    // Buscar respuesta por tipo y usuario
    @Query(value = "SELECT * FROM RespuestaPlanificador WHERE tipoRespuesta = :tipoRespuesta AND idUsuario = :idUsuario", nativeQuery = true)
    List<RespuestaPlanificadorEdamam> buscarRespuestaPorTipoYUsuario(@Param("tipoRespuesta") String tipoRespuesta, @Param("idUsuario") Integer idUsuario);
}
