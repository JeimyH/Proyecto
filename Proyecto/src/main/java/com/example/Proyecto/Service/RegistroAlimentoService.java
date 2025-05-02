package com.example.Proyecto.Service;

import com.example.Proyecto.Model.RegistroAlimento;
import com.example.Proyecto.Repository.RegistroAlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RegistroAlimentoService {
    @Autowired
    public RegistroAlimentoRepository registroAlimentoRepository;

    public List<RegistroAlimento> listarRegistroAlimento(){
        // Validacion para intentar obtener la lista de Registros de Alimento
        try {
            List<RegistroAlimento> registroAlimentos = registroAlimentoRepository.findAll();
            // Validar que la lista no sea nula
            if (registroAlimentos == null) {
                throw new IllegalStateException("No se encontraron Registros de Alimento.");
            }
            return registroAlimentos;
        } catch (Exception e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al listar los Registros de Alimento: " + e.getMessage(), e);
        }
    }

    public Optional<RegistroAlimento> listarPorIdRegistroAlimento(long id_registroAlimento){
        try {
            Optional<RegistroAlimento> registroAlimento = registroAlimentoRepository.findById(id_registroAlimento);
            if (registroAlimento.isPresent()) {
                return registroAlimento;
            } else {
                throw new IllegalStateException("No se encontraron Registros de Alimentos.");
            }
        }catch (Exception e){
            throw new RuntimeException("Error al listar el Registro del Alimento " + id_registroAlimento +": "+ e.getMessage(), e);
        }
    }

    public RegistroAlimento guardarRegistroAlimento(RegistroAlimento registroAlimento){
        try{
            if(registroAlimento==null){
                throw new IllegalArgumentException("El Registro del Alimento no puede ser nulo");

            }else{
                if (registroAlimento.getTamanoPorcion() == 0) {
                    throw new IllegalArgumentException("El Tamaño de la porcion del Registro del Alimento es obligatorio.");
                }else if(registroAlimento.getConsumidoEn() == null){
                    throw new IllegalArgumentException("El timestamp de consumido en del Registro del Alimento es obligatorio.");
                }
                return  registroAlimentoRepository.save(registroAlimento);
            }
        }catch (Exception e){
            throw new RuntimeException("Error al intentar guardar el entrenador" + e.getMessage(), e);
        }
    }

    public void eliminarRegistroAlimento(long id_registroAlimento){
        try {
            if (id_registroAlimento<=0) {
                throw new IllegalArgumentException("El ID del Registro del Alimento debe ser un número positivo.");
            }
            if (!registroAlimentoRepository.existsById(id_registroAlimento)) {
                throw new NoSuchElementException("No se encontró un Registro del Alimento con el ID: " + id_registroAlimento);
            }
            registroAlimentoRepository.deleteById(id_registroAlimento);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar el Registro del Alimento "+ id_registroAlimento +": "+ e.getMessage(), e);
        }
    }

    public RegistroAlimento actualizarRegistroAlimento(long id_registroAlimento, RegistroAlimento registroAlimentoActualizado){
        Optional<RegistroAlimento> registroAlimentoOpt = registroAlimentoRepository.findById(id_registroAlimento);
        if(registroAlimentoOpt.isPresent()){
            RegistroAlimento registroAlimentoExistente = registroAlimentoOpt.get();
            registroAlimentoExistente.setTamanoPorcion(registroAlimentoActualizado.getTamanoPorcion());
            registroAlimentoExistente.setUrlImagen(registroAlimentoActualizado.getUrlImagen());
            registroAlimentoExistente.setConsumidoEn(registroAlimentoActualizado.getConsumidoEn());
            return registroAlimentoRepository.save(registroAlimentoExistente);
        }else{
            return null;
        }
    }
}
