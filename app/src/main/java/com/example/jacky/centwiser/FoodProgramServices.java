package com.example.jacky.centwiser;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mapbox.geojson.FeatureCollection;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.style.sources.Source;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Map;

public class FoodProgramServices extends AppCompatActivity {
    private MapView mapView;
    private MapboxMap mapboxMap;

    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    private ArrayList<Feature> features;

    // URL to get contacts JSON
    private static String SERVICE_URL = "http://opendata.newwestcity.ca/downloads/food/FOOD_PROGRAMS_AND_SERVICES.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1IjoiaWxvYyIsImEiOiJjam55dmJlbmYxOXVzM2trajM3eno5bTIxIn0.jbJIOAYamYvN8QZNAk28bg");
        setContentView(R.layout.activity_food_program_services);

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapbox) {
                mapbox.setCameraPosition(new CameraPosition.Builder()
                        .target(new LatLng(49.2057179, -122.910956))
                        .zoom(12)
                        .build());
                mapboxMap = mapbox;
                new GetFoodProgram().execute();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetFoodProgram extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(FoodProgramServices.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(SERVICE_URL);

            if (jsonStr != null) {
                try {
                    DataSetJsonParser jsonParser = new DataSetJsonParser();
                    features = jsonParser.parse(jsonStr);
                } catch (final JSONException e) {
                    Log.i("FoodProgramServices", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            for(Feature f : features) {
                String snippet = "";
                snippet += "Description: " + f.getDescription() + "\n";
                snippet += "Hours: " + f.getHours() + "\n";;
                snippet += "Location: " + f.getLocation() + "\n";;
                snippet += "Postcode: " + f.getPC() + "\n";;
                snippet += "Phone: " + f.getPhone() + "\n";;
                snippet += "Email: " + f.getEmail() + "\n";;
                snippet += "Website: " + f.getWebsite() + "\n";;
                mapboxMap.addMarker(
                        new MarkerOptions()
                            .position(new LatLng(f.getY(), f.getX()))
                            .title(f.getName())
                            .setSnippet(snippet)
                );
            }

            mapboxMap.setOnMarkerClickListener(new MapboxMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    mapboxMap.selectMarker(marker);
                    return true;
                }
            });
        }
    }
}
