/*
 * Created by Shreyas Bera on 2026.3.19
 * Copyright © 2026 Osman Balci. All rights reserved
 */

/*
 * Created by Osman Balci, edited by Shreyas Bera
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.EntityBeans;


import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


/*
The @Entity annotation designates this class as a JPA Entity POJO class
representing the Cat table in the CatsDB database.
 */
@Entity
@Table(name = "Cat")
@NamedQueries({
        @NamedQuery(name = "Cat.findAll", query = "SELECT c FROM Cat c"),
        @NamedQuery(name = "Cat.findById", query = "SELECT c FROM Cat c WHERE c.catId = :catId"),
        @NamedQuery(name = "Cat.findByName", query = "SELECT c FROM Cat c WHERE c.name = :name")
})


public class Cat implements Serializable {

    private static final long serialVersionUID = 1L;

    //cat_id (Primary Key)
    @Id
    @Column(name = "cat_id")
    private String catId;

    //Fields
    //name
    @Size(max = 255)
    private String name;

    // url
    @Column(name = "vetstreet_url")
    @Size(max = 255)
    private String vetstreetUrl;

    // temperament
    @Size(max = 255)
    private String temperament;

    // origin
    @Size(max = 255)
    private String origin;

    // description
    @Column()
    @Size(max = 1024)
    private String description;

    // life span
    @Column(name = "life_span")
    private String lifeSpan;

    // weight
    @Size(max = 50)
    private String weight;

    // random_photo_url
    @Column(name = "random_photo_url")
    @Size(max = 255)
    private String randomPhotoUrl;



    // Constructors
    public Cat(){}

    public Cat(String catId){
        this.catId = catId;
    }

    //Getter and Setter methods

    // catID
    public String getCatId(){
        return catId;
    }
    public void setCatId(String catId) {
        this.catId = catId;
    }

    // get name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getVetstreetUrl() {
        return vetstreetUrl;
    }
    public void setVetstreetUrl(String vetstreetUrl) {
        this.vetstreetUrl = vetstreetUrl;
    }

    // temperament
    public String getTemperament() {
        return temperament;
    }
    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    // Origin
    public String getOrigin() {
        return origin;
    }
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    // description
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    // life span
    public String getLifeSpan() {
        return lifeSpan;
    }
    public void setLifeSpan(String lifeSpan) {
        this.lifeSpan = lifeSpan;
    }

    // weight
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }

    // random photo URL
    public String getRandomPhotoUrl() {
        return randomPhotoUrl;
    }
    public void setRandomPhotoUrl(String randomPhotoUrl) {
        this.randomPhotoUrl = randomPhotoUrl;
    }


    // -------------------------
    // equals, hashCode, toString
    // -------------------------

    @Override
    public int hashCode() {
        return (catId != null ? catId.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cat)) {
            return false;
        }
        Cat other = (Cat) object;
        return (this.catId != null || other.catId == null) &&
                (this.catId == null || this.catId.equals(other.catId));
    }

    @Override
    public String toString() {
        return catId != null ? catId.toString() : "null";
    }







}


