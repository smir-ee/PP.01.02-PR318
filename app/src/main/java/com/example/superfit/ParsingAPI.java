package com.example.superfit;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

class ParsingAPI  {

    public static ArrayList<Recipe> parse(String search, String diet){
        ArrayList<Recipe> recipesArray = new ArrayList<>();
        String link;
        String data = "";
        String appId = "ad859d04";
        String appKey = "b870e5682e6fb9ec059b8adb3ad7745d";

        if (search.equals(""))
            search = "chicken";

        if (diet.equals(""))
             link = "https://api.edamam.com/search?q=" + search +"&to=30&app_id=" + appId +"&app_key=" + appKey;
        else
             link = "https://api.edamam.com/search?q=" + search +"&to=30&app_id=" + appId +"&app_key=" + appKey + "&diet=" + diet;

        URL url;
        HttpURLConnection connection;
        try{
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200){
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while (line != null) {
                    line = bufferedReader.readLine();
                    data = data + line;
                }

                JSONObject jsonObject = new JSONObject(data);
                JSONArray recipes = (JSONArray) jsonObject.getJSONArray("hits");


                for (int i = 0; i < recipes.length(); i++){
                    JSONObject curRecipeWrap = recipes.getJSONObject(i);
                    JSONObject curRecipe = curRecipeWrap.getJSONObject("recipe");

                    recipesArray.add(new Recipe(curRecipe.getString("label"),
                            curRecipe.getString("calories"),
                            curRecipe.getJSONArray("digest").getJSONObject(0).getString("total"),
                            curRecipe.getJSONArray("digest").getJSONObject(1).getString("total"),
                            curRecipe.getJSONArray("digest").getJSONObject(2).getString("total"),
                            curRecipe.getString("image"),
                            curRecipe.getJSONArray("ingredientLines")));

                }


            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return recipesArray;
    }
}
