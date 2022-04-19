package com.example.alrashed_midt2;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    TextView temperature, Clouds, datetxt;
    Button act2, act3;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        temperature = (TextView) findViewById(R.id.temp);
        Clouds = (TextView) findViewById(R.id.clouds);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        act2 = (Button) findViewById(R.id.act2);
        act3 = (Button) findViewById(R.id.act3);
        datetxt = (TextView) findViewById(R.id.datetxt);
        Calendar c = Calendar.getInstance();
        DatePicker dp = (DatePicker) findViewById(R.id.datePicker);
        DateFormat fmtDate = DateFormat.getDateInstance();
        dp.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                String day = "" + datePicker.getDayOfMonth();
                String month = "" + (datePicker.getMonth() + 1);
                String year = "" + datePicker.getYear();
                datetxt.setText(day + "-" + month + "-" + year);
            }
        });
        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity3.class));
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                String url = "https://api.openweathermap.org/data/2.5/weather?q="
                        + spinner.getSelectedItem().toString()
                        + "&appid= 29f685fac873f8f2769ba46d2c221c53&units=imperical";
                weather(url);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    public void weather(String url){
        Log.d("Nora","Succesful initialization of objects from xml");
        JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    Log.d("Nora",response.toString());
                    JSONObject jsonMain=response.getJSONObject("main");
                    JSONArray jsonWeather = response.getJSONArray("weather");
                    JSONObject jsonWeatherObject = jsonWeather.getJSONObject(0);
                    Clouds.setText("Clouds: "+ jsonMain.getDouble("Clouds"));
                    temperature.setText(jsonMain.getDouble("temp")+"°F");
                }
                catch(Exception e){
                }
            }
        }, new Response.ErrorListener() {
            @Override     public void onErrorResponse(VolleyError error) {
                Log.d("Nora",error.toString());
            }
        }
        );
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObj);
    }
}

