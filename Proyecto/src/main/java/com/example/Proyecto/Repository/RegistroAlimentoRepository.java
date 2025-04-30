package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAlimentoRepository extends JpaRepository<RegistroAlimento, Long> {
}
