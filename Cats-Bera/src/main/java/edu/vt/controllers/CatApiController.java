/*
 * Created by Shreyas Bera on 2026.3.4
 * Copyright © 2026 Osman Balci. All rights reserved
 */

package edu.vt.controllers;

import edu.vt.pojo.CatApi;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import java.io.InputStreamReader;
import java.net.URL;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

@Named("apiController")
@SessionScoped


   public class CatApiController implements Serializable{
       private String catRandomPhotoUrl;

       public String getCatRandomPhotoUrl(){
           return catRandomPhotoUrl;
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
   }


