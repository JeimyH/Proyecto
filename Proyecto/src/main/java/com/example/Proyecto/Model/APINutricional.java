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
@Table(name = "API_Nutricional")
public class APINutricional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_api;

    @Column(name = "Nombre_API", length = 100)
    private String nombreApi;

    @Column(name = "URL_Base")
    private String urlBase;

    @Column(name = "Clave_API", length = 100)
    private String claveApi;

    @Column(name = "Ultimo_Acceso")
    private Timestamp ultimoAcceso;

    @OneToMany(mappedBy = "apiNutricional", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    private List<RegistrosSolicitudAPI> registrosSolicitudAPIS;
}
