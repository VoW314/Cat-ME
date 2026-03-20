/*
 * Created by Osman Balci. Adapted by Shreyas Bera for Cat API
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.pojo;

// This class provides a Plain Old Java Object (POJO) representing a Book returned from the API
public class CatApi {
    /*
    ===============================
    Instance Variables (Properties)
    ===============================
     */

    /*
     A unique 'id' is required by the <p:dataTable> attribute rowKey="#{Cat.id}"
     for listing and sorting the books found as a result of the API search.
     */

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
}
