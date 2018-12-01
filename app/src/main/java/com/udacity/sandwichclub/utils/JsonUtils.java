package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich res= null ;
        JSONObject data = null;
        try {
            data = new JSONObject(json);
            JSONObject name = data.getJSONObject("name");
            String mainName = name.getString("mainName");

//            String alsoKnownAs = name.getString("alsoKnownAs");

            List<String> alsoKnownAs_Arr = new ArrayList<String>();

            JSONArray alsoKnownAs =name.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnownAs.length(); i++) {
                alsoKnownAs_Arr.add(alsoKnownAs.getString(i));
            }


            String placeOfOrigin = data.getString("placeOfOrigin");

            String description = data.getString("description");

            String image = data.getString("image");


            List<String> ingredient = new ArrayList<String>();

            JSONArray ingredients =data.getJSONArray("ingredients");
            for (int i = 0; i < ingredients.length(); i++) {
                ingredient.add(ingredients.getString(i));
            }

            res = new Sandwich(mainName , alsoKnownAs_Arr ,placeOfOrigin , description ,image ,ingredient);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return res;
    }
}