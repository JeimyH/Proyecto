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
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_usuario;

    @Column(name = "Correo", nullable = false, length = 100)
    private String correo;

    @Column(name = "Contraseña", nullable = false, length = 25)
    private String contrasena;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Fecha_Nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "Altura", nullable = false)
    private float altura;

    @Column(name = "Peso", nullable = false)
    private float peso;

    @Column(name = "Restricciones_Dieta")
    private String restriccionesDieta;

    @Column(name = "Objetivos_Salud")
    private String objetivosSalud;

    @Column(name = "Creado_En", nullable = false)
    private Timestamp creadoEn;

    @Column(name = "Actualizado_En")
    private Timestamp actualizadoEn;

    // Relaciones entre tablas
    // Uno a muchos

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroAgua> registroAguas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroAlimento> registroAlimentos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RutinaAlimenticiaIA> rutinaAlimenticiaIAS;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Alimento> alimentos;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EstadisticasNutricionales> estadisticasNutricionales;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistrosSolicitudAPI> registrosSolicitudAPIS;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RegistroRespuestasIA> registroRespuestasIAS;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SesionChatbot> sesionChatbots;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<TokenSesion> tokenSesions;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Recordatorio> recordatorios;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<RespuestaPlanificadorEdamam> respuestaPlanificadorEdamams;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ModificacionRutinaChatbot> modificacionRutinaChatbots;

    // Uno a Uno
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private PreferenciasUsuario preferenciasUsuario;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private ConfiguracionAplicacion configuracionAplicacion;

}
