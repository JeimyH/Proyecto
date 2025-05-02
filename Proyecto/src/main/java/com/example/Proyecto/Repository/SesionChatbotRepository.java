package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.SesionChatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SesionChatbotRepository extends JpaRepository<SesionChatbot, Long> {
}
