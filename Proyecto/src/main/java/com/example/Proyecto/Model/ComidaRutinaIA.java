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

    @Column(name = "Tipo_Comida", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoComida tipoComida;

    @Column(name = "Alimentos_Sugeridos")
    private String alimentosSugeridos;

    @Column(name = "ValoresNutricionales")
    private String valoresNutricionales;

    @Column(name = "Tamano_Porciones")
    private String tamanoPorciones;

    @Column(name = "Dia_Numero", nullable = false)
    private int diaNumero;

    public enum TipoComida{
        Desayuno,
        Almuerzo,
        Cena,
        Snack
    }

    // Relaciones

    @ManyToOne
    @JoinColumn(name="id_rutina", nullable = false)
    @JsonIgnore
    private RutinaAlimenticiaIA rutina;
}
