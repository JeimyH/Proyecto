package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroAguaRepository extends JpaRepository<RegistroAgua, Long> {
    // Registrar cantidad de agua
    @Modifying
    @Query(value = "INSERT INTO RegistroAgua (id_usuario, Cantidadml, registradoEn) VALUES (:id_usuario, :Cantidadml, :registradoEn)", nativeQuery = true)
    void registrarCantidadAgua(@Param("id_usuario") Integer id_usuario,
                               @Param("Cantidadml") Float cantidad,
                               @Param("registradoEn") String registradoEn);

    // Obtener historial de agua por fecha
    @Query(value = "SELECT * FROM RegistroAgua WHERE id_usuario = :id_usuario AND DATE(registradoEn) = :fecha", nativeQuery = true)
    List<RegistroAgua> obtenerHistorialPorFecha(@Param("id_usuario") Integer id_usuario, @Param("fecha") String fecha);

    // Obtener cantidad total consumida por día
    @Query(value = "SELECT SUM(cantidad) FROM RegistroAgua WHERE id_usuario = :id_usuario AND DATE(registradoEn) = :fecha", nativeQuery = true)
    Float obtenerTotalConsumidoPorDia(@Param("id_usuario") Integer id_usuario, @Param("fecha") String fecha);
}
