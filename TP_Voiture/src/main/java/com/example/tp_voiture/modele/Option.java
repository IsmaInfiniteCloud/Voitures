package com.example.tp_voiture.modele;

import jakarta.persistence.*;


public class Option {
    private Integer idOption;
    private String option;
    private String Model;
    private String Milage;
    private String Prix;

    public Option() {
    }

    public Option(String option, String model, String milage, String prix) {
        this.option = option;
        Model = model;
        Milage = milage;
        Prix = prix;
    }

    public Integer getIdOption() {
        return idOption;
    }

    public void setIdOption(Integer idOption) {
        this.idOption = idOption;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getMilage() {
        return Milage;
    }

    public void setMilage(String milage) {
        Milage = milage;
    }

    public String getPrix() {
        return Prix;
    }

    public void setPrix(String prix) {
        Prix = prix;
    }
}
