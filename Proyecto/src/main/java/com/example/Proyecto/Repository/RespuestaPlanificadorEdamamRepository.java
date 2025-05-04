package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RespuestaPlanificadorEdamam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaPlanificadorEdamamRepository extends JpaRepository<RespuestaPlanificadorEdamam, Long> {
}
