/*
 * Created by Osman Balci. Adapted by Shreyas Bera for Cat API
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.pojo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// This class provides a Plain Old Java Object (POJO) representing a Book returned from the API
public class CatApi {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */
 // the apiID. Realized I needed this. Wrote apiId as everything already works with the String ID I made at the start
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apiID;


    private String id;
    private String name;
    private String description;
    private String temperament;
    private String origin;
    private String lifeSpan;
    private String weight;
    private String vetstreetUrl;
    private String imageUrl;


    /*
    ==================
    Constructor Method
    ==================
     */
    public CatApi() {
    }

    /*
    =========================
    Getter and Setter Methods for the cats
    =========================
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVetstreetUrl() {
        return vetstreetUrl;
    }

    public void setVetstreetUrl(String vetstreetUrl) {
        this.vetstreetUrl = vetstreetUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Realized I needed this when looking into this more
    public Long getApiID(){
        return apiID;
    }

    public void setApiID(Long apiID){
        this.apiID = apiID;
    }
}
