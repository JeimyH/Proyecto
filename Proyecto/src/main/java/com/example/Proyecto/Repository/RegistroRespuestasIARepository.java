package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroRespuestasIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroRespuestasIARepository extends JpaRepository<RegistroRespuestasIA, Long> {
}
