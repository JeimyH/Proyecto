package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.ComidaRutinaIA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComidaRutinaIARepository extends JpaRepository<ComidaRutinaIA, Long> {
}
