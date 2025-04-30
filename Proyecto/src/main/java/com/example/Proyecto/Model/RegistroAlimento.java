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

    @Column(name = "Nombre_Alimento", nullable = false, length = 100)
    private String nombreAlimento;

    @Column(name = "Tamano_Porcion")
    private float tamanoPorcion;

    @Column(name = "Calorias")
    private float calorias;

    @Column(name = "Proteinas")
    private float proteinas;

    @Column(name = "Carbohidratos")
    private float carbohidratos;

    @Column(name = "Grasas")
    private float grasas;

    @Column(name = "Azucares")
    private float azucares;

    @Column(name = "Fibra")
    private float fibra;

    @Column(name = "Sodio")
    private float sodio;

    @Column(name = "Grasas_Saturadas")
    private float grasasSaturadas;

    @Column(name = "Consumido_En")
    private Timestamp consumidoEn;

    @Column(name = "URL_Imagen")
    private String urlImagen;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable = false)
    //@JsonIgnore
    private Usuario usuario;
}
