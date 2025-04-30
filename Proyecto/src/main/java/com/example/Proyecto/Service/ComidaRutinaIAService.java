package com.example.Proyecto.Service;

import com.example.Proyecto.Model.ComidaRutinaIA;
import com.example.Proyecto.Repository.ComidaRutinaIARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComidaRutinaIAService {
    @Autowired
    public ComidaRutinaIARepository comidaRutinaIARepository;

    public List<ComidaRutinaIA> listarComidasRutinas(){
        // Validacion para intentar obtener la lista de las comidas de la rutina
        try {
            List<ComidaRutinaIA> comidaRutinaIAS = comidaRutinaIARepository.findAll();
            // Validar que la lista no sea nula
            if (comidaRutinaIAS == null) {
                throw new IllegalStateException("No se encontraron comidas.");
            }
            return comidaRutinaIAS;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar las comidas: " + e.getMessage(), e);
        }
    }

    public Optional<ComidaRutinaIA> listarPorIdComidaRutinaIA(long id_comida){
        try {
            Optional<ComidaRutinaIA> comidaRutinaIA = comidaRutinaIARepository.findById(id_comida);
            if (comidaRutinaIA.isPresent()) {
                return comidaRutinaIA;
            } else {
                throw new IllegalStateException("No se encontraron comidas.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar la comida " + id_comida +": "+ e.getMessage(), e);
        }
    }

    public ComidaRutinaIA guardarComidaRutina(ComidaRutinaIA comidaRutinaIA){
        try{
            if(comidaRutinaIA==null){
                throw new IllegalArgumentException("El equipo no puede ser nulo");

            }else{
                if (equipo.getNombre() == null || equipo.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del equipo es obligatorio.");
                }else if(equipo.getCiudad()== null || equipo.getCiudad().isEmpty()){
                    throw new IllegalArgumentException("La ciudad del equipo es obligatorio.");
                }else if(equipo.getFundacion()==null) {
                    throw new IllegalArgumentException("La fecha de fundacion del equipo es obligatorio.");
                }
                return  equipoRepository.save(equipo);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el equipo" + e.getMessage(), e);
        }
    }

    public void eliminarEquipo(long id_equipo){
        try {
            if (id_equipo<=0) {
                throw new IllegalArgumentException("El ID del equipo debe ser un número positivo.");
            }
            if (!equipoRepository.existsById(id_equipo)) {
                throw new NoSuchElementException("No se encontró un equipo con el ID: " + id_equipo);
            }
            equipoRepository.deleteById(id_equipo);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el equipo "+ id_equipo +": "+ e.getMessage(), e);
        }
    }

    public Equipo actualizarEquipo(long id_equipo, Equipo equipoActualizado){
        Optional<Equipo> equipoOpt = equipoRepository.findById(id_equipo);
        if(equipoOpt.isPresent()){
            Equipo equipoExistente = equipoOpt.get();
            equipoExistente.setNombre(equipoActualizado.getNombre());
            equipoExistente.setCiudad(equipoActualizado.getCiudad());
            equipoExistente.setFundacion(equipoActualizado.getFundacion());
            return equipoRepository.save(equipoExistente);
        }else{
            return null;
        }
    }
}
