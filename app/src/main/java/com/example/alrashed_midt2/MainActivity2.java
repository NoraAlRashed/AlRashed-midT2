package com.example.alrashed_midt2;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView name = (TextView) findViewById(R.id.name);
        TextView id = (TextView) findViewById(R.id.id);
        TextView nid = (TextView) findViewById(R.id.nID);
        Button insert = (Button) findViewById(R.id.insert);
        Button act1 = (Button) findViewById(R.id.act1);
        Button act3 = (Button) findViewById(R.id.act3);
        DatabaseHelper db =new DatabaseHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int id_val = Integer.parseInt(id.getText().toString());
                    int nid_val = Integer.parseInt(nid.getText().toString());
                    String name_val = name.getText().toString();
                    db.Add(id_val, nid_val, name_val);
                    finish();
                    Toast.makeText(MainActivity2.this, "Successful Add", Toast.LENGTH_LONG).show();
                    Log.d("Nora", "after adding value");
                }
                catch(Exception e){
                    Toast.makeText(MainActivity2.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                }

            }
        });
        act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
            }
        });
        act3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
            }
        });

    }
}