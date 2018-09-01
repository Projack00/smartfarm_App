package com.example.kuhas.smartfarm_04.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kuhas.smartfarm_04.R;
import com.example.kuhas.smartfarm_04.models.TypeMushroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Update_Data_Mashroom extends AppCompatActivity {
    Spinner spin_temp_max, spin_temp_min, spin_hum_max, spin_hum_min;
    Button Insert;
    EditText editMode;
    Context context;
    String key;
    DatabaseReference reference;
    FirebaseDatabase database;
    private TypeMushroom data;

    private TextView textHumidMin, textHumidMax, textTemMin, textTemMax, mode;

    List<String> arrList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__data__mashroom);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TypeMushroom");

        Intent intent = getIntent();
        key = intent.getStringExtra("View");
        mode = findViewById(R.id.mode);
///////////////////-- START findViewById --///////////////////////////////////////////////////////////////

        // Spinner
        spin_temp_min = findViewById(R.id.Tem_min);
        spin_temp_max = findViewById(R.id.Tem_Max);
        spin_hum_max = findViewById(R.id.Humid_Min);
        spin_hum_min = findViewById(R.id.Humid_max);
        editMode = findViewById(R.id.Edit_Name_Mashroom);

        // Button
        Insert = (Button) findViewById(R.id.Insert_Name);

        // TEXTVIEW
        textHumidMin = findViewById(R.id.textHumidMin);
        textHumidMax = findViewById(R.id.textHumidMax);
        textTemMin = findViewById(R.id.textTemMin);
        textTemMax = findViewById(R.id.textTemMax);
///////////////////-- END findViewById --///////////////////////////////////////////////////////////////


        ArrayAdapter<String> spin_adapper = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, arrList);

        spin_temp_min.setAdapter(spin_adapper);
        spin_temp_max.setAdapter(spin_adapper);
        spin_hum_max.setAdapter(spin_adapper);
        spin_hum_min.setAdapter(spin_adapper);
        //////////////////////////////////////

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("TAG", " key => " + dataSnapshot.getValue());
                mode.setText(dataSnapshot.child("mode").getValue().toString());
                textHumidMin.setText(dataSnapshot.child("hummidMin").getValue().toString());
                textHumidMax.setText(dataSnapshot.child("hummidMax").getValue().toString());
                textTemMin.setText(dataSnapshot.child("temMin").getValue().toString());
                textTemMax.setText(dataSnapshot.child("temMax").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}