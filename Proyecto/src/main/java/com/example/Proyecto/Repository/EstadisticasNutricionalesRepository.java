package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.EstadisticasNutricionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadisticasNutricionalesRepository extends JpaRepository<EstadisticasNutricionales, Long> {
}
