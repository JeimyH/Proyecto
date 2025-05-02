package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Registros_Solicitud_API")
public class RegistrosSolicitudAPI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_solicitud;

    @Column(name = "Endpoint", nullable = false, length = 100)
    private String endpoint;

    @Column(name = "Datos_Solicitud", nullable = false)
    private String datosSolicitud;

    @Column(name = "Datos_Respuesta")
    private String datosRespuesta;

    @Column(name = "Creado_En", nullable = false)
    private Timestamp creadoEn;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_api", nullable = false)
    @JsonIgnore
    private APINutricional apiNutricional;

}

