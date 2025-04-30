package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.RegistroAgua;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAguaRepository extends JpaRepository<RegistroAgua, Long> {
}
