package com.example.Proyecto.Service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RutinaAlimenticiaIAService {
    @Autowired
    public EquipoRepository equipoRepository;

    public List<Equipo> listarEquipos(){
        // Validacion para intentar obtener la lista de los equipos
        try {
            List<Equipo> equipos = equipoRepository.findAll();
            // Validar que la lista no sea nula
            if (equipos == null) {
                throw new IllegalStateException("No se encontraron equipos.");
            }
            return equipos;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar equipos: " + e.getMessage(), e);
        }
    }

    public Optional<Equipo> listarPorIdEqipo(long id_equipo){
        try {
            Optional<Equipo> equipo = equipoRepository.findById(id_equipo);
            if (equipo.isPresent()) {
                return equipo;
            } else {
                throw new IllegalStateException("No se encontraron equipos.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el equipo " + id_equipo +": "+ e.getMessage(), e);
        }
    }

    public Equipo guardarEquipo(Equipo equipo){
        try{
            if(equipo==null){
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
