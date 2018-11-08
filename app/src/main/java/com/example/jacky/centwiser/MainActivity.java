package com.example.jacky.centwiser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mapbox.mapboxsdk.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView map_categories = findViewById(R.id.listview_map_categories);

        // We grab a constant array for category
        final String [] const_map_categories = getResources().getStringArray(R.array.const_map_categories);

        map_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            // Adapter view just KNOWS which thing is in the category
            System.out.println("Clicked position: " + i + ", row_id: " + l);
            Intent intent = new Intent(MainActivity.this, MapboxActivity.class);
            intent.putExtra("map_category", const_map_categories[i]);
            startActivity(intent);
            }
        });
    }
}
