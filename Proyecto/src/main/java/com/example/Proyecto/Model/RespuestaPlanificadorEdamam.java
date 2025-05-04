package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Respuesta_Planificador_Edamam")
public class RespuestaPlanificadorEdamam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_planificador;

    @Column(name = "Fecha_Generacion", nullable = false)
    private Date fechaGeneracion;

    @Column(name = "Contenido", nullable = false)
    private String contenido;

    @Column(name = "Creado_En", nullable = false)
    private Timestamp creadoEn;

    @Column(name = "Tipo", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    public enum Tipo{
        Rutina_Diaria,
        Rutina_Semanal
    }

    //Relaciones

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

}
