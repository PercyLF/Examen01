package com.luna.Examen01.entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Eventos")
@EntityListeners(AuditingEntityListener.class)
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name= "nombre", length = 255, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 255, nullable = true)
    private String descripcion;

    @Column(name = "Fecha Inicio", nullable = true)
    private Date fechaInicio;

    @Column(name = "Fecha Fin", nullable = true)
    private Date fechaFin;

    @Column(nullable = false)
    private double costo;

    @Column(name="created_at")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(name="updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateAt;
}
