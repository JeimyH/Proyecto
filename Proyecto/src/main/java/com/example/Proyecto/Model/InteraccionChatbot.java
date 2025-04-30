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
@Table(name = "Interaccion_Chatbot")
public class InteraccionChatbot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_interaccion;

    @Column(name = "Consulta_Usuario")
    private String consultaUsuario;

    @Column(name = "Respuesta_IA")
    private String respuestaIA;

    @Column(name = "Origen")
    @Enumerated(EnumType.STRING)
    private TipoIntento tipoIntento;

    @Column(name = "Tema")
    private String tema;

    @Column(name = "Timestamp")
    private Timestamp timestamp;

    public enum TipoIntento{
        Modificar_Rutina,
        Pregunta_Nutricional,
        Otros
    }

    //Relaciones

    @ManyToOne
    @JoinColumn(name="id_sesion", nullable = false)
    @JsonIgnore
    private SesionChatbot sesionChatbot;
}
