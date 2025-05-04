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
@Table(name = "API_Nutricional")
public class APINutricional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_api;

    @Column(name = "Nombre_API", nullable = false, length = 100)
    private String nombreApi;

    @Column(name = "URL_Base", nullable = false)
    private String urlBase;

    @Column(name = "Clave_API", nullable = false, length = 100)
    private String claveApi;

    @Lob
    @Column(name = "Descripcion", columnDefinition = "TEXT") // Opcional, pero recomendable
    private String descripcion;

    @Column(name = "Ultimo_Acceso")
    private Timestamp ultimoAcceso;

    @Column(name = "Tipo_API", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAPI tipoAPI;

    public enum TipoAPI{
        open_food_facts,
        edamam_mealplanner
    }

    //Relaciones
    @OneToMany(mappedBy = "apiNutricional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistrosSolicitudAPI> registrosSolicitudAPIS;
}
