package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.Alimento;
import com.example.Proyecto.Model.ComidaRutinaIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComidaRutinaIARepository extends JpaRepository<ComidaRutinaIA, Long> {
    // Obtener alimentos sugeridos por tipo de comida y fecha
    @Query(value = "SELECT A.* FROM ComidaRutinaIA CR " +
            "JOIN ComidaRutinaIA_Alimento CRA ON CR.idComida = CRA.id_comida " +
            "JOIN Alimento A ON CRA.id_alimento = A.id_alimento " +
            "WHERE CR.tipoComida = :tipoComida AND CR.fecha = :fecha", nativeQuery = true)
    List<Alimento> obtenerAlimentosSugeridos(@Param("tipoComida") String tipoComida, @Param("fecha") String fecha);

    // Actualizar valores nutricionales de comida
    @Modifying
    @Query(value = "UPDATE ComidaRutinaIA SET valoresNutricionales = :valoresNutricionales WHERE id_comida = :id_comida", nativeQuery = true)
    void actualizarValoresNutricionales(@Param("id_comida") Integer id_comida, @Param("valoresNutricionales") String valoresNutricionales);

    // Agregar alimento a una comida en rutina
    @Modifying
    @Query(value = "INSERT INTO ComidaRutinaIA_Alimento (id_comida, id_alimento, cantidad) VALUES (:id_comida, :id_alimento, :cantidad)", nativeQuery = true)
    void agregarAlimentoAComida(@Param("id_comida") Integer id_comida, @Param("id_alimento") Integer id_alimento, @Param("cantidad") Float cantidad);

    // Eliminar alimento de una comida en rutina
    @Modifying
    @Query(value = "DELETE FROM ComidaRutinaIA_Alimento WHERE id_comida = :id_comida AND id_alimento = :id_alimento", nativeQuery = true)
    void eliminarAlimentoDeComida(@Param("id_comida") Integer id_comida, @Param("idAlimento") Integer id_alimento);
}
