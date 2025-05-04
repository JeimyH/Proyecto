package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.ModificacionRutinaChatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModificacionRutinaChatbotRepository extends JpaRepository<ModificacionRutinaChatbot, Long> {
}
