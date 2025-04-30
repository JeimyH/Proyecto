package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Comida_Rutina_IA")
public class ComidaRutinaIA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_comida;

    @Column(name = "Alimentos_Sugeridos", nullable = false)
    private String alimentosSugeridos;

    @Column(name = "ValoresNutricionales", nullable = false)
    private String valoresNutricionales;

    @Column(name = "Tamano_Porciones", nullable = false)
    private String tamanoPorciones;

    @Column(name = "Dia_Numero", nullable = false)
    private String diaNumero;

    @ManyToOne
    @JoinColumn(name="id_rutina", nullable = false)
    //@JsonIgnore
    private RutinaAlimenticiaIA rutina;
}
