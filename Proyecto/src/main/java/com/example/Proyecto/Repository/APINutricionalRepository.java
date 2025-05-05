package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.APINutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface APINutricionalRepository extends JpaRepository<APINutricional, Long> {
    // Registrar API utilizada
    @Modifying
    @Query(value = "INSERT INTO ApiNutricional (nombreApi, version, fechaRegistro) VALUES (:nombreApi, :version, CURRENT_TIMESTAMP)", nativeQuery = true)
    void registrarApiUtilizada(@Param("nombreApi") String nombreApi, @Param("version") String version);

    // Verificar fecha de actualización
    @Query(value = "SELECT fechaRegistro FROM ApiNutricional WHERE idApi = :idApi", nativeQuery = true)
    String verificarFechaActualizacion(@Param("idApi") Integer idApi);

    // Consultar versión o key activa
    @Query(value = "SELECT version FROM ApiNutricional WHERE idApi = :idApi", nativeQuery = true)
    String consultarVersionOKeyActiva(@Param("idApi") Integer idApi);
}
