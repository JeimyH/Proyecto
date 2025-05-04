package com.example.Proyecto.Service;

import com.example.Proyecto.Model.APINutricional;
import com.example.Proyecto.Repository.APINutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class APINutricionalService {
    @Autowired
    public APINutricionalRepository apiNutricionalRepository;

    public List<APINutricional> listarAPIs(){
        // Validacion para intentar obtener la lista de los equipos
        try {
            List<APINutricional> apiNutricionals = apiNutricionalRepository.findAll();
            // Validar que la lista no sea nula
            if (apiNutricionals == null) {
                throw new IllegalStateException("No se encontraron apis.");
            }
            return apiNutricionals;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar apis: " + e.getMessage(), e);
        }
    }

    public Optional<APINutricional> listarPorIdAPI(long id_equipo){
        try {
            Optional<APINutricional> apiNutricional = apiNutricionalRepository.findById(id_equipo);
            if (apiNutricional.isPresent()) {
                return apiNutricional;
            } else {
                throw new IllegalStateException("No se encontraron apis.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar la api " + id_equipo +": "+ e.getMessage(), e);
        }
    }

    public APINutricional guardarAPI(APINutricional apiNutricional){
        try{
            if(apiNutricional==null){
                throw new IllegalArgumentException("La API no puede ser nulo");

            }else{
                if (apiNutricional.getNombreApi() == null || apiNutricional.getNombreApi().isEmpty()) {
                    throw new IllegalArgumentException("El nombre de la API es obligatorio.");
                }else if(apiNutricional.getClaveApi()== null || apiNutricional.getClaveApi().isEmpty()){
                    throw new IllegalArgumentException("La clave de la API es obligatoria.");
                }else if(apiNutricional.getUrlBase()==null || apiNutricional.getUrlBase().isEmpty()) {
                    throw new IllegalArgumentException("La url de la API es obligatoria.");
                } else if (apiNutricional.getTipoAPI()==null) {
                    throw new IllegalArgumentException("El tipo de API es obligatorio.");
                }
                return  apiNutricionalRepository.save(apiNutricional);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar la API" + e.getMessage(), e);
        }
    }

    public void eliminarAPI(long id_api){
        try {
            if (id_api<=0) {
                throw new IllegalArgumentException("El ID de la API debe ser un número positivo.");
            }
            if (!apiNutricionalRepository.existsById(id_api)) {
                throw new NoSuchElementException("No se encontró una API con el ID: " + id_api);
            }
            apiNutricionalRepository.deleteById(id_api);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la API "+ id_api +": "+ e.getMessage(), e);
        }
    }

    public APINutricional actualizarAPI(long id_api, APINutricional apiActualizado){
        Optional<APINutricional> apiOpt = apiNutricionalRepository.findById(id_api);
        if(apiOpt.isPresent()){
            APINutricional apiExistente = apiOpt.get();
            apiExistente.setNombreApi(apiActualizado.getNombreApi());
            apiExistente.setClaveApi(apiActualizado.getClaveApi());
            apiExistente.setUrlBase(apiActualizado.getUrlBase());
            apiExistente.setDescripcion(apiActualizado.getDescripcion());
            apiExistente.setUltimoAcceso(apiActualizado.getUltimoAcceso());
            apiExistente.setTipoAPI(apiActualizado.getTipoAPI());
            return apiNutricionalRepository.save(apiExistente);
        }else{
            return null;
        }
    }
}
