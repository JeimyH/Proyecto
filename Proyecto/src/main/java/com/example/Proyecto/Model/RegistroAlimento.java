package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Registro_Alimento")
public class RegistroAlimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_registroAlimento;

    @Column(name = "Tamano_Porcion")
    private float tamanoPorcion;

    @Column(name = "Consumido_En")
    private Timestamp consumidoEn;

    @Column(name = "URL_Imagen")
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="id_alimento", nullable = false)
    @JsonIgnore
    private Alimento alimento;

}
