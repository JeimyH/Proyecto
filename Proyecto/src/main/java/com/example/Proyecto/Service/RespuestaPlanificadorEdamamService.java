package com.example.Proyecto.Service;

import com.example.Proyecto.Model.RespuestaPlanificadorEdamam;
import com.example.Proyecto.Repository.RespuestaPlanificadorEdamamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RespuestaPlanificadorEdamamService {
    @Autowired
    public RespuestaPlanificadorEdamamRepository planificadorEdamamRepository;

    public List<RespuestaPlanificadorEdamam> listarRespuestasPlanificadorEdamam(){
        // Validacion para intentar obtener la lista de las Respuesta de la API Planificador Edamam
        try {
            List<RespuestaPlanificadorEdamam> respuestaPlanificadorEdamams = planificadorEdamamRepository.findAll();
            // Validar que la lista no sea nula
            if (respuestaPlanificadorEdamams == null) {
                throw new IllegalStateException("No se encontraron Respuestas del PlanificadorEdamam.");
            }
            return respuestaPlanificadorEdamams;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar las Respuestas del PlanificadorEdamam: " + e.getMessage(), e);
        }
    }

    public Optional<RespuestaPlanificadorEdamam> listarPorIdRespuestaPlanificadorEdamam(long id_planificador){
        try {
            Optional<RespuestaPlanificadorEdamam> respuestaPlanificadorEdamam = planificadorEdamamRepository.findById(id_planificador);
            if (respuestaPlanificadorEdamam.isPresent()) {
                return respuestaPlanificadorEdamam;
            } else {
                throw new IllegalStateException("No se encontraron Respuestas del PlanificadorEdamam.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar la Respuesta del PlanificadorEdamam " + id_planificador +": "+ e.getMessage(), e);
        }
    }

    public RespuestaPlanificadorEdamam guardarRespuestaPlanificadorEdamam(RespuestaPlanificadorEdamam respuestaPlanificadorEdamam){
        try{
            if(respuestaPlanificadorEdamam==null){
                throw new IllegalArgumentException("La Respuesta del Planificador Edamam no puede ser nulo");

            }else{
                if (respuestaPlanificadorEdamam.getFechaGeneracion() == null) {
                    throw new IllegalArgumentException("La fecha de la Respuesta del Planificador Edamam es obligatoria.");
                }else if(respuestaPlanificadorEdamam.getContenido() == null || respuestaPlanificadorEdamam.getContenido().isEmpty()){
                    throw new IllegalArgumentException("El contenido de la Respuesta del Planificador Edamam es obligatorio.");
                }else if(respuestaPlanificadorEdamam.getTipo() == null) {
                    throw new IllegalArgumentException("El tipo de Rutina de la Respuesta del Planificador Edamam es obligatorio.");
                } else if (respuestaPlanificadorEdamam.getCreadoEn() == null) {
                    throw new IllegalArgumentException("La fecha de creacion de la Respuesta del Planificador Edamam es obligatoria.");
                }
                return  planificadorEdamamRepository.save(respuestaPlanificadorEdamam);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar la Respuesta del PlanificadorEdamam" + e.getMessage(), e);
        }
    }

    public void eliminarRespuestaPlanificadorEdamam(long id_planificador){
        try {
            if (id_planificador<=0) {
                throw new IllegalArgumentException("El ID de la Respuesta del Planificador Edamam debe ser un número positivo.");
            }
            if (!planificadorEdamamRepository.existsById(id_planificador)) {
                throw new NoSuchElementException("No se encontró una Respuesta del Planificador Edamam con el ID: " + id_planificador);
            }
            planificadorEdamamRepository.deleteById(id_planificador);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la Respuesta del Planificador Edamam "+ id_planificador +": "+ e.getMessage(), e);
        }
    }

    public RespuestaPlanificadorEdamam actualizarRespuestaPlanificadorEdamam(long id_planificador, RespuestaPlanificadorEdamam planificadorEdamamActualizado){
        Optional<RespuestaPlanificadorEdamam> planificadorEdamamOpt = planificadorEdamamRepository.findById(id_planificador);
        if(planificadorEdamamOpt.isPresent()){
            RespuestaPlanificadorEdamam planificadorEdamamExistente = planificadorEdamamOpt.get();
            planificadorEdamamExistente.setFechaGeneracion(planificadorEdamamActualizado.getFechaGeneracion());
            planificadorEdamamExistente.setContenido(planificadorEdamamActualizado.getContenido());
            planificadorEdamamExistente.setTipo(planificadorEdamamActualizado.getTipo());
            planificadorEdamamExistente.setCreadoEn(planificadorEdamamActualizado.getCreadoEn());
            return planificadorEdamamRepository.save(planificadorEdamamExistente);
        }else{
            return null;
        }
    }

    public void obtenerRespuesta(@Param("id_usuario") Integer id_usuario, @Param("respuesta") String respuesta){
        planificadorEdamamRepository.guardarRespuesta(id_usuario,respuesta);
    }

    public List<RespuestaPlanificadorEdamam> obtenerPlanAlimenticioPorFecha(@Param("fechaRegistro") String fechaRegistro, @Param("id_usuario") Integer id_usuario){
        return planificadorEdamamRepository.consultarPlanAlimenticioPorFecha(fechaRegistro,id_usuario);
    }

    public List<RespuestaPlanificadorEdamam> obtenerRespuestaPorTipoYUsuario(@Param("tipoRespuesta") String tipoRespuesta, @Param("id_usuario") Integer id_usuario){
        return planificadorEdamamRepository.buscarRespuestaPorTipoYUsuario(tipoRespuesta,id_usuario);
    }
}
