/*
 * Created by Osman Balci. Adapted by Shreyas Bera on 2026.3.4
 * Copyright © 2026 Osman Balci. All rights reserved
 */

package edu.vt.controllers;

import edu.vt.pojo.CatApi;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.SelectItem; //used in the dropdown box for cat breeds
import jakarta.inject.Named;

import java.io.BufferedReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.InputStreamReader;
import java.net.URL;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.primefaces.shaded.json.JSONObject;

@Named("catApiController")
@SessionScoped

   //https://manta.cs.vt.edu/cs3754/StudentsOnly/CodeSnippets/JSONDirectly.html
   // https://manta.cs.vt.edu/cs3754/StudentsOnly/CodeSnippets/SelectOneMenu.html


   public class CatApiController implements Serializable{
       private String catRandomPhotoUrl;

       public String getCatRandomPhotoUrl(){
           return catRandomPhotoUrl;
       }
       private String catFact;

       public String getCatFact(){
           return catFact;
       }

        private final List<String> listOfCatBreedNamesAndIds = Arrays.asList("Abyssinian (abys)", "Aegean (aege)", "American Bobtail (abob)", "American Curl (acur)",
            "American Shorthair (asho)", "American Wirehair (awir)", "Arabian Mau (amau)", "Australian Mist (amis)", "Balinese (bali)", "Bambino (bamb)", "Bengal (beng)",
            "Birman (birm)", "Bombay (bomb)", "British Longhair (bslo)", "British Shorthair (bsho)", "Burmese (bure)", "Burmilla (buri)", "California Spangled (cspa)",
            "Chantilly-Tiffany (ctif)", "Chartreux (char)", "Chausie (chau)", "Cheetoh (chee)", "Colorpoint Shorthair (csho)", "Cornish Rex (crex)", "Cymric (cymr)", "Cyprus (cypr)",
            "Devon Rex (drex)", "Donskoy (dons)", "Dragon Li (lihu)", "Egyptian Mau (emau)", "European Burmese (ebur)", "Exotic Shorthair (esho)", "Havana Brown (hbro)",
            "Himalayan (hima)", "Japanese Bobtail (jbob)", "Javanese (java)", "Khao Manee (khao)", "Korat (kora)", "Kurilian (kuri)", "LaPerm (lape)", "Maine Coon (mcoo)",
            "Malayan (mala)", "Manx (manx)", "Munchkin (munc)", "Nebelung (nebe)", "Norwegian Forest Cat (norw)", "Ocicat (ocic)", "Oriental (orie)", "Persian (pers)",
            "Pixie-bob (pixi)", "Ragamuffin (raga)", "Ragdoll (ragd)", "Russian Blue (rblu)", "Savannah (sava)", "Scottish Fold (sfol)", "Selkirk Rex (srex)", "Siamese (siam)",
            "Siberian (sibe)", "Singapura (sing)", "Snowshoe (snow)", "Somali (soma)", "Sphynx (sphy)", "Tonkinese (tonk)", "Toyger (toyg)", "Turkish Angora (tang)",
            "Turkish Van (tvan)", "York Chocolate (ycho)");


    public void getRandomCatPhoto(){
           try {
               String apiUrl = "https://api.thecatapi.com/v1/images/search";

               URL url = new URL(apiUrl);

               JsonArray jsonArray = Json.createReader(new InputStreamReader(url.openStream())).readArray();

               JsonObject obj = jsonArray.getJsonObject(0);

               catRandomPhotoUrl = obj.getString("url");

           } catch (Exception e) {
               e.printStackTrace();
           }
       }

       /*
       The below code gets the random cat fact. Since the url will just
       return a JSON object, this will parse the JSON file to get the fact and
       will return it to the webpage
        */
       public void getRandomCatFact() {
           try {
               // the url of the cat fact
               URL url = new URL("https://catfact.ninja/fact");

               JsonObject jsonObject = Json.createReader(
                       new InputStreamReader(url.openStream())
               ).readObject();

               catFact = jsonObject.getString("fact");

               // in the case we can't get this cat fact
           } catch (Exception e) {
               e.printStackTrace();
               catFact = "Unable to get a random cat fact";
           }
       }

       // Used the following snippets for making the methods below
       // https://manta.cs.vt.edu/cs3754/StudentsOnly/CodeSnippets/SelectOneMenu.html
       // The ones given in the Cat Dev notes


    /**
     * Getters and Setters for the cat random pages. This will be used when the user clicks only a button
     * this will display any random cat photo no matter the breed
     */
       // Random cat breed photo
        private String catBreedPhotoUrl;

       public String getCatBreedPhotoUrl(){
           return  catBreedPhotoUrl;
       }

       // Method to get a random breed
       private String getRandomBreedId() {
           int index = (int) (Math.random() * listOfCatBreedNamesAndIds.size());

           String item = listOfCatBreedNamesAndIds.get(index);

           return item.substring(item.indexOf("(") + 1, item.indexOf(")"));
       }

       // get the random photo
       public void getRandomCatBreedPhoto(){
           try{
               // make sure that when nothing is in the dropdown that we don't error out
               if(selectedBreedId == null) {
                   return; //button does nothing
               }

               // get the API after the breedID is given
               String apiUrl = "https://api.thecatapi.com/v1/images/search?breed_id="
                       + selectedBreedId + "&api_key=" + edu.vt.globals.Constants.THE_CAT_API_KEY;

               URL url = new URL(apiUrl);

               JsonArray jsonArray = Json.createReader(
                       new InputStreamReader(url.openStream())
               ).readArray();

               JsonObject obj = jsonArray.getJsonObject(0);

               catBreedPhotoUrl = obj.getString("url");

           } catch (Exception e){
               e.printStackTrace();
               catBreedPhotoUrl = "";
           }
       }


    /**
     * Getters and Setters for the byBreedID cat random pages. These will be used
     * when the user selects from the dropdown menu. Then it will use that ID and only generate
     * random images of that cat breed.
     */
    private String selectedBreedId;

       public String getSelectedBreedId(){
           return selectedBreedId;
       }

       public void setSelectedBreedId(String selectedBreedId){
           this.selectedBreedId = selectedBreedId;
       }

       //get options for the breed
       public List<SelectItem> getBreedOptions() {
           List<SelectItem> options = new ArrayList<>();

           for (String item : listOfCatBreedNamesAndIds) {
               // get just the breed
               String name = item.substring(0, item.indexOf("(")).trim();
               String id = item.substring(item.indexOf("(") + 1, item.indexOf(")"));

               options.add(new SelectItem(id, name));
           }

           return options;
       }


   }


