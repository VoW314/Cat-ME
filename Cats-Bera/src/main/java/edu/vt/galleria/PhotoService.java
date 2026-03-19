/*
 * Created by Osman Balci
 * Copyright © 2026 Osman Balci. All rights reserved
 */
package edu.vt.galleria;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named(value = "photoService")
@ApplicationScoped
public class PhotoService {
    /*
    ============================
    Instance Variable (Property)
    ============================
     */
    private List<Photo> listOfPhotos;

    /*
    The PostConstruct annotation is used on a method that needs to be executed after
    dependency injection is done to perform any initialization. The initialization
    method init() is the first method invoked before this class is put into service.
     */
    @PostConstruct
    public void init() {
        listOfPhotos = new ArrayList<>();
        listOfPhotos.add(new Photo("/resources/images/photos/photo1.jpg", "/resources/images/photos/photo1s.jpg",
                "Description for Photo 1", "The International Cat Association recognizes 73 standardized cat breeds."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo2.jpg", "/resources/images/photos/photo2s.jpg",
                "Description for Photo 2", "Cats' whiskers are highly sensitive touch receptors that help them sense vibrations in the air."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo3.jpg", "/resources/images/photos/photo3s.jpg",
                "Description for Photo 3", "Cats can't taste sweet things."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo4.jpg", "/resources/images/photos/photo4s.jpg",
                "Description for Photo 4", "Cats have around 244 bones, while humans have 206."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo5.jpg", "/resources/images/photos/photo5s.jpg",
                "Description for Photo 5", "Adult cats have 30 teeth, while kittens have 26."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo6.jpg", "/resources/images/photos/photo6s.jpg",
                "Description for Photo 6", "An adult cat's brain is about 2 inches long and weighs 30 grams."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo7.jpg", "/resources/images/photos/photo7s.jpg",
                "Description for Photo 7", "Cats can have a dominant paw, and cats with a stronger paw preference are often more confident and friendly."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo8.jpg", "/resources/images/photos/photo8s.jpg",
                "Description for Photo 8", "Neutered males live 62% longer than unneutered cats, and spayed females live 39% longer than unspayed females."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo9.jpg", "/resources/images/photos/photo9s.jpg",
                "Description for Photo 9", "Cats can run up to 30 miles per hour."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo10.jpg", "/resources/images/photos/photo10s.jpg",
                "Description for Photo 10", "A cat's tongue has backward-facing hooks that help it clean bones."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo11.jpg", "/resources/images/photos/photo11s.jpg",
                "Description for Photo 11", "Each cat has a unique nose, similar to human fingerprints."));

        listOfPhotos.add(new Photo("/resources/images/photos/photo12.jpg", "/resources/images/photos/photo12s.jpg",
                "Description for Photo 12", "Cats sleep 12–16 hours per day and spend one-third of their awake hours grooming."));
    }
    /*
    =============
    Getter Method
    =============
     */
    public List<Photo> getListOfPhotos() {
        return listOfPhotos;
    }
}