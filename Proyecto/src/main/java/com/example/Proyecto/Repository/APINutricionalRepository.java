package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.APINutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface APINutricionalRepository extends JpaRepository<APINutricional, Long> {
}
