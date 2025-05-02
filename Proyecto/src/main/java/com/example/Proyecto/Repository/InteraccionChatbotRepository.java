package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.InteraccionChatbot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteraccionChatbotRepository extends JpaRepository<InteraccionChatbot, Long> {
}
