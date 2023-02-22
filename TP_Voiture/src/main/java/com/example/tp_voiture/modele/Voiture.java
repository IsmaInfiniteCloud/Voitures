
package com.example.tp_voiture.modele;


import jakarta.persistence.*;
import org.hibernate.annotations.Proxy;

import java.io.Serializable;

@Entity
@Table(name = "voiture")
public class Voiture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voiture_id")
    private Integer idVoiture;
    private Integer annee;
    private Float mileage;
    private boolean isRented;
    private String model;
    private String licence;
    private Float price;

    public Voiture() {
    }

    public Voiture(Integer annee, Float mileage, boolean isRented, String model, String licence, Float price) {
        this.annee = annee;
        this.mileage = mileage;
        this.isRented = isRented;
        this.model = model;
        this.licence = licence;
        this.price = price;
    }

    public Integer getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(Integer idVoiture) {
        this.idVoiture = idVoiture;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Float getMileage() {
        return mileage;
    }

    public void setMileage(Float mileage) {
        this.mileage = mileage;
    }

   public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}

