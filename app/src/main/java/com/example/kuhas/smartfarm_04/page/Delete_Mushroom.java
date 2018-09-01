package com.example.kuhas.smartfarm_04.page;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.kuhas.smartfarm_04.FirebaseClient.Detail_Data;
import com.example.kuhas.smartfarm_04.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Delete_Mushroom extends AppCompatActivity {

    TextView textView2;
    Button button2,button3;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__mushroom);

        textView2 = findViewById(R.id.textView2);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        Intent intent = getIntent();
        String Child = intent.getStringExtra("View");

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TypeMushroom").child(Child);


        textView2.setText(Child);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.removeValue();

                Intent i = new Intent(Delete_Mushroom.this, Detail_Data.class); // Your list's Intent
//                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(i);
                finish();
            }
        }); button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Delete_Mushroom.this, Detail_Data.class); // Your list's Intent
//                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(i);
//                finish();
            }
        });

    }
}
