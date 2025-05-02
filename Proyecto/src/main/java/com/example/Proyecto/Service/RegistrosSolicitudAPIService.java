package com.example.Proyecto.Service;

import com.example.Proyecto.Model.RegistrosSolicitudAPI;
import com.example.Proyecto.Repository.RegistrosSolicitudAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RegistrosSolicitudAPIService {
    @Autowired
    public RegistrosSolicitudAPIRepository solicitudAPIRepository;

    public List<RegistrosSolicitudAPI> listarRegistrosSolicitudAPI(){
        // Validacion para intentar obtener la lista de los registros con las solicitudes de la api
        try {
            List<Entrenador> entrenadores = entrenadorRepository.findAll();
            // Validar que la lista no sea nula
            if (entrenadores == null) {
                throw new IllegalStateException("No se encontraron entrenadores.");
            }
            return entrenadores;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar a los entrenadores: " + e.getMessage(), e);
        }
    }

    public Optional<Entrenador> listarPorIdEntrenador(long id_entrenador){
        try {
            Optional<Entrenador> entrenador = entrenadorRepository.findById(id_entrenador);
            if (entrenador.isPresent()) {
                return entrenador;
            } else {
                throw new IllegalStateException("No se encontraron entrenadores.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el entrenador " + id_entrenador +": "+ e.getMessage(), e);
        }
    }

    public Entrenador guardarEntrenador(Entrenador entrenador){
        try{
            if(entrenador==null){
                throw new IllegalArgumentException("El entrenador no puede ser nulo");

            }else{
                if (entrenador.getNombre() == null || entrenador.getNombre().isEmpty()) {
                    throw new IllegalArgumentException("El nombre del entrenador es obligatorio.");
                }else if(entrenador.getEspecialidad()== null || entrenador.getEspecialidad().isEmpty()){
                    throw new IllegalArgumentException("La especialidad del entrenador es obligatorio.");
                }
                return  entrenadorRepository.save(entrenador);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el entrenador" + e.getMessage(), e);
        }
    }

    public void eliminarEntrenador(long id_entrenador){
        try {
            if (id_entrenador<=0) {
                throw new IllegalArgumentException("El ID del entrenador debe ser un número positivo.");
            }
            if (!entrenadorRepository.existsById(id_entrenador)) {
                throw new NoSuchElementException("No se encontró un entrenador con el ID: " + id_entrenador);
            }
            entrenadorRepository.deleteById(id_entrenador);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el entrenador "+ id_entrenador +": "+ e.getMessage(), e);
        }
    }

    public Entrenador actualizarEntrenador(long id_entrenador, Entrenador entrenadorActualizado){
        Optional<Entrenador> entrenadorOpt = entrenadorRepository.findById(id_entrenador);
        if(entrenadorOpt.isPresent()){
            Entrenador entrenadorExistente = entrenadorOpt.get();
            entrenadorExistente.setNombre(entrenadorActualizado.getNombre());
            entrenadorExistente.setEspecialidad(entrenadorActualizado.getEspecialidad());
            return entrenadorRepository.save(entrenadorExistente);
        }else{
            return null;
        }
    }
}
