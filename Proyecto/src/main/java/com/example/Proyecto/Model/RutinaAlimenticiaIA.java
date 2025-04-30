package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Rutina_Alimentia_IA")
public class RutinaAlimenticiaIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_rutina;

    @Column(name = "Fecha_Inicio")
    private LocalDate fechaInicio;

    @Column(name = "Objetivo_Calorico_Dia")
    private float objetivoCaloricoDia;

    @Column(name = "Parametros_IA")
    private String parametrosIA;

    @Column(name = "Creado_En")
    private Timestamp creadoEn;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    //@JsonIgnore
    private Usuario usuario;

    @OneToMany(mappedBy = "rutina", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<ComidaRutinaIA> comidaRutinaIAS;
}
