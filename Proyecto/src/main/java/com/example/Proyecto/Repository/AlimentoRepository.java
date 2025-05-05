package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlimentoRepository extends JpaRepository<Alimento, Long> {
    // Buscar alimento por nombre
    @Query(value = "SELECT * FROM Alimento WHERE nombreAlimento LIKE %:nombre%", nativeQuery = true)
    List<Alimento> buscarAlimentoPorNombre(@Param("nombre") String nombre);

    // Filtrar alimentos por categoría
    @Query(value = "SELECT * FROM Alimento WHERE categoria = :categoria", nativeQuery = true)
    List<Alimento> filtrarAlimentosPorCategoria(@Param("categoria") String categoria);

    // Consultar alimentos creados por usuario
    @Query(value = "SELECT * FROM Alimento WHERE creador = :id_usuario", nativeQuery = true)
    List<Alimento> consultarAlimentosPorUsuario(@Param("id_usuario") Integer id_usuario);

    // Obtener información nutricional de un alimento
    @Query(value = "SELECT * FROM Alimento WHERE id_alimento = :id_alimento", nativeQuery = true)
    Alimento obtenerInformacionNutricional(@Param("id_alimento") Integer id_alimento);
}
