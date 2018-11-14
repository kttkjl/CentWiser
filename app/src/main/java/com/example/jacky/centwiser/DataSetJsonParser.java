package com.example.jacky.centwiser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataSetJsonParser {
    private ArrayList<Feature> features;

    public DataSetJsonParser() {
        features = new ArrayList<>();
    }

    public ArrayList<Feature> parse(String jsonStr) throws JSONException {
        JSONObject dropInDataSet = new JSONObject(jsonStr);
        JSONArray featuresJsonArray = dropInDataSet.getJSONArray("features");

        int featuresLength = featuresJsonArray.length();
        for (int i = 0; i < featuresLength; i++) {
            JSONObject f = featuresJsonArray.getJSONObject(i);
            String type = f.getString("type");
            JSONObject props = f.getJSONObject("properties");
            String name = props.getString("Name");
            String description = props.getString("Description");
            String category = props.getString("Category");
            String hours = props.getString("Hours");
            String location = props.getString("Location");
            String pc = props.getString("PC");
            String phone = props.getString("Phone");
            String email = props.getString("Email");
            String website = props.getString("Website");
            double x = props.getDouble("X");
            double y = props.getDouble("Y");

            Feature feature = new Feature();
            feature.setType(type);
            feature.setName(name);
            feature.setDescription(description);
            feature.setCategory(category);
            feature.setHours(hours);
            feature.setLocation(location);
            feature.setPC(pc);
            feature.setPhone(phone);
            feature.setEmail(email);
            feature.setWebsite(website);
            feature.setX(x);
            feature.setY(y);

            features.add(feature);
        }

        return features;
    }

}
