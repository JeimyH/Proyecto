package com.example.Proyecto.Service;

import com.example.Proyecto.Model.RegistrosSolicitudAPI;
import com.example.Proyecto.Repository.RegistrosSolicitudAPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
            List<RegistrosSolicitudAPI> registrosSolicitudAPIS = solicitudAPIRepository.findAll();
            // Validar que la lista no sea nula
            if (registrosSolicitudAPIS == null) {
                throw new IllegalStateException("No se encontraron Registros con Solicitudes a la API.");
            }
            return registrosSolicitudAPIS;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar los Registros con las Solicitudes a la API: " + e.getMessage(), e);
        }
    }

    public Optional<RegistrosSolicitudAPI> listarPorIdRegistrosSolicitudAPI(long id_solicitud){
        try {
            Optional<RegistrosSolicitudAPI> registrosSolicitudAPI = solicitudAPIRepository.findById(id_solicitud);
            if (registrosSolicitudAPI.isPresent()) {
                return registrosSolicitudAPI;
            } else {
                throw new IllegalStateException("No se encontraron Registros con la Solicitud a la API.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el Registro con la Solicitud a la API " + id_solicitud +": "+ e.getMessage(), e);
        }
    }

    public RegistrosSolicitudAPI guardarRegistrosSolicitudAPI(RegistrosSolicitudAPI registrosSolicitudAPI){
        try{
            if(registrosSolicitudAPI==null){
                throw new IllegalArgumentException("El Registro de la SolicitudAPI no puede ser nulo");

            }else{
                if (registrosSolicitudAPI.getDatosSolicitud() == null || registrosSolicitudAPI.getDatosSolicitud().isEmpty()) {
                    throw new IllegalArgumentException("Los datos de la solicitud a la API son obligatorios.");
                }else if(registrosSolicitudAPI.getEndpoint() == null || registrosSolicitudAPI.getEndpoint().isEmpty()){
                    throw new IllegalArgumentException("El endpoint del registro de la solicitud a la api es obligatorio.");
                }else if (registrosSolicitudAPI.getCreadoEn() == null) {
                    throw new IllegalArgumentException("La fecha con la creacion del registro de la solicitud a la IA es obligatoria.");
                }
                return  solicitudAPIRepository.save(registrosSolicitudAPI);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el registro con la solicitud a la api" + e.getMessage(), e);
        }
    }

    public void eliminarRegistrosSolicitudAPI(long id_solicitud){
        try {
            if (id_solicitud<=0) {
                throw new IllegalArgumentException("El ID del Registro de la SolicitudAPI debe ser un número positivo.");
            }
            if (!solicitudAPIRepository.existsById(id_solicitud)) {
                throw new NoSuchElementException("No se encontró un Registro de la SolicitudAPI con el ID: " + id_solicitud);
            }
            solicitudAPIRepository.deleteById(id_solicitud);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el Registro de la SolicitudAPI con el ID: "+ id_solicitud +": "+ e.getMessage(), e);
        }
    }

    public RegistrosSolicitudAPI actualizarRegistrosSolicitudAPI(long id_solicitud, RegistrosSolicitudAPI solicitudActualizado){
        Optional<RegistrosSolicitudAPI> solicitudOpt = solicitudAPIRepository.findById(id_solicitud);
        if(solicitudOpt.isPresent()){
            RegistrosSolicitudAPI solicitudExistente = solicitudOpt.get();
            solicitudExistente.setEndpoint(solicitudActualizado.getEndpoint());
            solicitudExistente.setDatosSolicitud(solicitudActualizado.getDatosSolicitud());
            solicitudExistente.setDatosRespuesta(solicitudActualizado.getDatosRespuesta());
            solicitudExistente.setCreadoEn(solicitudActualizado.getCreadoEn());
            return solicitudAPIRepository.save(solicitudExistente);
        }else{
            return null;
        }
    }

    public void obtenerSolicitud(@Param("nombreApi") String nombreApi, @Param("solicitud") String solicitud){
        solicitudAPIRepository.registrarSolicitud(nombreApi,solicitud);
    }

    public List<RegistrosSolicitudAPI> obtenerHistorialPorFecha(@Param("fechaInicio") String fechaInicio, @Param("fechaFin") String fechaFin){
        return solicitudAPIRepository.obtenerHistorialPorFecha(fechaInicio,fechaFin);
    }

    public String obtenerRespuestasRecibidas(@Param("id_solicitud") Integer id_solicitud){
        return solicitudAPIRepository.consultarRespuestasRecibidas(id_solicitud);
    }
}
