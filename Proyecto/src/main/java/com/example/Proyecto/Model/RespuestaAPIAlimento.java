package com.example.Proyecto.Model;

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
@Table(name = "Respuesta_API_Alimentos")
public class RespuestaAPIAlimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_respuesta;

    @Column(name = "Datos_Alimentos", length = 100)
    private String datosAlimentos;

    @Column(name = "Creado_En")
    private Timestamp creadoEn;

    @ManyToOne
    @JoinColumn(name="id_solicitud", nullable = false)
    //@JsonIgnore
    private RegistrosSolicitudAPI registrosSolicitudAPI;
}
