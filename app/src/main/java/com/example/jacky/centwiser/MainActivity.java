package com.example.jacky.centwiser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mapbox.mapboxsdk.maps.MapView;

public class MainActivity extends AppCompatActivity {
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_dropin = findViewById(R.id.btn_dropin);
        Button btn_emp = findViewById(R.id.btn_emp_job);
        Button btn_food = findViewById(R.id.btn_food);

        btn_dropin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DropInCentreActivity.class);
                startActivity(i);
            }
        });
        btn_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, EmploymentJobTrainingActivity.class);
                startActivity(i);
            }
        });
        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FoodProgramServices.class);
                startActivity(i);
            }
        });
    }
}
