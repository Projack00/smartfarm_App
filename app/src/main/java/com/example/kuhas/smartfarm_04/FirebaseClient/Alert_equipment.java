package com.example.kuhas.smartfarm_04.FirebaseClient;

import android.content.Context;
import android.widget.TextView;

import com.example.kuhas.smartfarm_04.models.Mushroom_Data_interface;
import com.example.kuhas.smartfarm_04.page.AlertNotification;
import com.example.kuhas.smartfarm_04.page.NotificationService;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Alert_equipment {
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;

    int alarm_Value_count;
    String equipment, time;
    TextView alarm_Value_count_Text, time_Text, equipment_Text;

    public Alert_equipment(Context context, TextView alarm_Value_count_Text, TextView time_Text, TextView equipment_Text) {
        this.context = context;
        this.alarm_Value_count_Text = alarm_Value_count_Text;
        this.time_Text = time_Text;
        this.equipment_Text = equipment_Text;
    }

    public void refreshData() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Alert_equipment");
//        alarm_Value_count = 1;
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alarm_Value_count_Text.setText(dataSnapshot.child("alarm_Value_count").getValue().toString());
                equipment_Text.setText(dataSnapshot.child("equipment").getValue().toString());
                time_Text.setText(dataSnapshot.child("time").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
