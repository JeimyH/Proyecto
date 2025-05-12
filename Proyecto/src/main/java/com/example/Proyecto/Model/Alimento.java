package com.example.Proyecto.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "Alimento", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombreAlimento", "origen"})
})
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_alimento;

    @Column(name = "Nombre_Alimento", nullable = false, length = 100)
    private String nombreAlimento;

    @Column(name = "Calorias", nullable = false)
    private float calorias;

    @Column(name = "Proteinas", nullable = false)
    private float proteinas;

    @Column(name = "Carbohidratos", nullable = false)
    private float carbohidratos;

    @Column(name = "Grasas", nullable = false)
    private float grasas;

    @Column(name = "Azucares", nullable = false)
    private float azucares;

    @Column(name = "Fibra", nullable = false)
    private float fibra;

    @Column(name = "Sodio", nullable = false)
    private float sodio;

    @Column(name = "Grasas_Saturadas", nullable = false)
    private float grasasSaturadas;

    @Column(name = "Categoria", length = 50)
    private String categoria;

    @Column(name = "URL_Imagen")
    private String urlImagen;

   /* @Column(name = "Marca_Producto", length = 100, nullable = false)
    private String marcaProducto;

    @Column(name = "Codigo_Barras", length = 100, nullable = false)
    private String codigoBarras;

    @Column(name = "Porcion", length = 50, nullable = false)
    private String porcion;

    @Column(name = "Unidad_Medida", length = 100, nullable = false)
    private String unidadMedida;

    @Column(name = "Origen")
    @Enumerated(EnumType.STRING)
    private Origen origen;

    public enum Origen{
        Ingresado,
        API_Interna
    }
    */
    //Relaciones uno a muchos
    /*
    @ManyToOne
    @JoinColumn(name="creado_Por", referencedColumnName = "id_usuario", nullable = false)
    @JsonIgnore
    private Usuario usuario;
     */
    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroAlimento> registroAlimentos;
}
