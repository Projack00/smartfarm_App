package com.example.kuhas.smartfarm_04.page;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kuhas.smartfarm_04.R;
import com.example.kuhas.smartfarm_04.models.TypeMushroom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Insert_Mashroom extends AppCompatActivity {

    List<String> lstSource = new ArrayList<String>();
    Spinner spin_temp_max, spin_temp_min, spin_hum_max, spin_hum_min;
    Button Insert;
    EditText editMode;
    FirebaseDatabase database;
    DatabaseReference ref;

    TypeMushroom data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert__mashroom);

        generateeData();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("TypeMushroom");
        data = new TypeMushroom();
//==================================================================================================
        spin_temp_min = findViewById(R.id.Tem_min);
        spin_temp_max = findViewById(R.id.Tem_Max);
        spin_hum_max = findViewById(R.id.Humid_Min);
        spin_hum_min = findViewById(R.id.Humid_max);
        editMode = findViewById(R.id.Edit_Name_Mashroom);
        Insert = (Button) findViewById(R.id.Insert_Name);
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        ArrayAdapter<String> spin_adapper = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, lstSource);

        spin_temp_min.setAdapter(spin_adapper);
        spin_temp_max.setAdapter(spin_adapper);
        spin_hum_max.setAdapter(spin_adapper);
        spin_hum_min.setAdapter(spin_adapper);

//==================================================================================================

        Insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                if (data.getMode().equals(null)) {
                    Snackbar.make(v, "Please Edit Name", Snackbar.LENGTH_LONG).show();

                } else {
                    ref.child(data.getMode()).setValue(data);
                    Toast.makeText(Insert_Mashroom.this,
                            "OnClickListener : " +
                                    "\n spin_temp_min  : " + String.valueOf(spin_temp_min.getSelectedItem()) +
                                    "\n spin_temp_max  : " + String.valueOf(spin_temp_max.getSelectedItem()) +
                                    "\n spin_hum_max  : " + String.valueOf(spin_hum_max.getSelectedItem()) +
                                    "\n spin_hum_min  : " + String.valueOf(spin_hum_min.getSelectedItem()),
                            Toast.LENGTH_SHORT).show();

//                    editMode.setText("");
                }
            }

        });
    }

    private void getValues() {
        data.setHummidMin(Integer.parseInt(String.valueOf(spin_hum_min.getSelectedItem())));
        data.setHummidMax(Integer.parseInt(String.valueOf(spin_hum_max.getSelectedItem())));
        data.setTemMin(Integer.parseInt(String.valueOf(spin_temp_min.getSelectedItem())));
        data.setTemMax(Integer.parseInt(String.valueOf(spin_temp_max.getSelectedItem())));
        data.setMode(editMode.getText().toString());
    }

    private void generateeData() {
        // get the selected dropdown list value

        for (int i = 0; i < 100; i++) {
            lstSource.add(String.valueOf(i));
        }
    }
}
