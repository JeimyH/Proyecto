package com.example.Proyecto.Service;

import com.example.Proyecto.Model.EstadisticasNutricionales;
import com.example.Proyecto.Model.Usuario;
import com.example.Proyecto.Repository.EstadisticasNutricionalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EstadisticasNutricionalesService {
    @Autowired
    public EstadisticasNutricionalesRepository estadisticasNutricionalesRepository;

    public List<EstadisticasNutricionales> listarEstadisticasNutricionales(){
        // Validacion para intentar obtener la lista de Estadisticas Nutricionales
        try {
            List<EstadisticasNutricionales> estadisticasNutricionales = estadisticasNutricionalesRepository.findAll();
            // Validar que la lista no sea nula
            if (estadisticasNutricionales == null) {
                throw new IllegalStateException("No se encontraron Estadisticas Nutricionales.");
            }
            return estadisticasNutricionales;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar las Estadisticas Nutricionales: " + e.getMessage(), e);
        }
    }

    public Optional<EstadisticasNutricionales> listarPorIdEstadisticasNutricionales(long id_estadistica){
        try {
            Optional<EstadisticasNutricionales> estadisticasNutricionales = estadisticasNutricionalesRepository.findById(id_estadistica);
            if (estadisticasNutricionales.isPresent()) {
                return estadisticasNutricionales;
            } else {
                throw new IllegalStateException("No se encontraron Estadisticas Nutricionales.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar la Estadistica Nutricional " + id_estadistica +": "+ e.getMessage(), e);
        }
    }


    public void eliminarEstadisticasNutricionales(long id_estadistica){
        try {
            if (id_estadistica<=0) {
                throw new IllegalArgumentException("El ID de la Estadistica Nutricional debe ser un número positivo.");
            }
            if (!estadisticasNutricionalesRepository.existsById(id_estadistica)) {
                throw new NoSuchElementException("No se encontró una Estadistica Nutricional con el ID: " + id_estadistica);
            }
            estadisticasNutricionalesRepository.deleteById(id_estadistica);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la Estadistica Nutricional "+ id_estadistica +": "+ e.getMessage(), e);
        }
    }


}
