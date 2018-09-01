package com.example.kuhas.smartfarm_04.FirebaseClient;

import android.content.Context;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kuhas.smartfarm_04.Adapter.aDetail_Data;
import com.example.kuhas.smartfarm_04.models.Load_Data_;
import com.example.kuhas.smartfarm_04.models.TypeMushroom;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fView_Data {
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;

    String key;

    public fView_Data(Context context, String key) {
        this.context = context;

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TypeMushroom");
        reference.child(key);
    }
    public void testData(String key) {

    }

    public void refreshData() {

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
