package com.example.Proyecto.Repository;

import com.example.Proyecto.Model.ConfiguracionAplicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfiguracionAplicacionRepository extends JpaRepository<ConfiguracionAplicacion, Long> {
}
