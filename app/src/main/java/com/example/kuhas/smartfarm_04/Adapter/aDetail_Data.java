package com.example.kuhas.smartfarm_04.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.kuhas.smartfarm_04.Holder.hDetail_Data;
import com.example.kuhas.smartfarm_04.R;
import com.example.kuhas.smartfarm_04.models.Load_Data_;
import com.example.kuhas.smartfarm_04.page.Delete_Mushroom;
import com.example.kuhas.smartfarm_04.page.Update_Data_Mashroom;
import com.example.kuhas.smartfarm_04.page.View_Mushroom_Data;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class aDetail_Data extends BaseAdapter {
    Context c;
    ArrayList<Load_Data_> data_s;
    LayoutInflater inflater;

    public aDetail_Data(Context c, ArrayList<Load_Data_> data_s) {
        this.c = c;
        this.data_s = data_s;
    }

    @Override
    public int getCount() {
        return data_s.size();
    }

    @Override
    public Object getItem(int position) {
        return data_s.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        final String KEY = data_s.get(position).getKey();

        if (inflater == null) {
            inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_model_recycleview_detail, parent, false);
        }

        hDetail_Data holder = new hDetail_Data(convertView);
        holder.tMode.setText(data_s.get(position).getMode());
        holder.bView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(c, View_Mushroom_Data.class);
                intent.putExtra("View", data_s.get(position).getKey());
                c.startActivity(intent);
            }
        });

        holder.bUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, Update_Data_Mashroom.class);
                intent.putExtra("View", KEY);
                c.startActivity(intent);
            }
        });

        holder.bDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("key","key = >"+KEY);
//
                Intent intent = new Intent(c, Delete_Mushroom.class);
                intent.putExtra("View", KEY);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
}
