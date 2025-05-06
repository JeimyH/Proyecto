package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroAlimentoRepository extends JpaRepository<RegistroAlimento, Long> {
    // Registrar alimento consumido
    @Modifying
    @Query(value = "INSERT INTO RegistroAlimento (id_usuario, id_alimento, tamanoPorcion, consumidoEn) VALUES (:id_usuario, :id_alimento, :tamanoPorcion, :consumidoEn)", nativeQuery = true)
    void registrarAlimentoConsumido(@Param("id_usuario") Integer id_usuario,
                                    @Param("id_alimento") Integer id_alimento,
                                    @Param("tamanoPorcion") Float tamanoPorcion,
                                    @Param("consumidoEn") String consumidoEn);

    // Obtener historial de alimentos por fecha
    @Query(value = "SELECT * FROM RegistroAlimento WHERE id_usuario = :id_usuario AND DATE(consumidoEn) = :fecha", nativeQuery = true)
    List<RegistroAlimento> obtenerHistorialPorFecha(@Param("id_usuario") Integer id_usuario, @Param("fecha") String fecha);

    // Calcular totales diarios por macronutriente
    @Query(value = "SELECT SUM(A.proteinas) AS totalProteinas, SUM(A.carbohidratos) AS totalCarbohidratos, SUM(A.grasas) AS totalGrasas " +
            "FROM RegistroAlimento RA " +
            "JOIN Alimento A ON RA.id_alimento = A.id_alimento " +
            "WHERE RA.id_usuario = :id_usuario AND DATE(RA.consumidoEn) = :fecha " +
            "GROUP BY DATE(RA.consumidoEn)", nativeQuery = true)
    List<Object[]> calcularTotalesDiarios(@Param("id_usuario") Integer id_usuario, @Param("fecha") String fecha);

    // Eliminar registro de un alimento
    @Modifying
    @Query(value = "DELETE FROM RegistroAlimento WHERE id_registroAlimento = :id_registroAlimento", nativeQuery = true)
    void eliminarRegistroAlimento(@Param("id_registroAlimento") Integer idRegid_registroAlimentoistro);
}
