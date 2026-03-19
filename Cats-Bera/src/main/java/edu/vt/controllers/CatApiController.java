/*
 * Created by Shreyas Bera on 2026.3.4
 * Copyright © 2026 Osman Balci. All rights reserved
 */

package edu.vt.controllers;

import edu.vt.pojo.CatApi;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.BufferedReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStreamReader;
import java.net.URL;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import org.primefaces.shaded.json.JSONObject;

@Named("CatApiController")
@SessionScoped


   public class CatApiController implements Serializable{
       private String catRandomPhotoUrl;

       public String getCatRandomPhotoUrl(){
           return catRandomPhotoUrl;
       }
       private String catFact;

       public String getCatFact(){
           return catFact;
       }

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


   }


