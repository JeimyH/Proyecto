package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordatorioRepository extends JpaRepository<Recordatorio, Long> {
    // Insertar nuevo recordatorio
    @Modifying
    @Query(value = "INSERT INTO Recordatorio (descripcion, hora, tipo, activo) VALUES (:descripcion, :hora, :tipo, true)", nativeQuery = true)
    void insertarNuevoRecordatorio(@Param("descripcion") String descripcion,
                                   @Param("hora") String hora,
                                   @Param("tipo") String tipo);

    // Activar/desactivar recordatorios
    @Modifying
    @Query(value = "UPDATE Recordatorio SET activo = :activo WHERE id_recordatorio = :id_recordatorio", nativeQuery = true)
    void activarDesactivarRecordatorio(@Param("id_recordatorio") Integer id_recordatorio, @Param("activo") boolean activo);

    // Obtener recordatorios activos
    @Query(value = "SELECT * FROM Recordatorio WHERE activo = true", nativeQuery = true)
    List<Recordatorio> obtenerRecordatoriosActivos();

    // Buscar recordatorios por hora o tipo
    @Query(value = "SELECT * FROM Recordatorio WHERE hora = :hora OR tipo = :tipo", nativeQuery = true)
    List<Recordatorio> buscarRecordatoriosPorHoraOTipo(@Param("hora") String hora, @Param("tipo") String tipo);
}
