package com.example.tp_voiture.modele;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Integer idReservation;
    private String dateReservation;
    private String dateCirculation;
    private String dateRetour;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST },fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_fk")
    private Client client;


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @OneToOne (cascade = { CascadeType.MERGE, CascadeType.PERSIST },fetch = FetchType.LAZY)
    @JoinColumn(name = "voiture_fk")
    private Voiture voiture;

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public Reservation() {
    }

    public Reservation(String dateReservation, String dateCirculation, String dateRetour,Voiture voiture,Client client) {
        this.dateReservation = dateReservation;
        this.dateCirculation = dateCirculation;
        this.dateRetour = dateRetour;
        this.voiture = voiture;
        this.client = client;
    }

    public Integer getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getDateCirculation() {
        return dateCirculation;
    }

    public void setDateCirculation(String dateCirculation) {
        this.dateCirculation = dateCirculation;
    }

    public String getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(String dateRetour) {
        this.dateRetour = dateRetour;
    }




}
