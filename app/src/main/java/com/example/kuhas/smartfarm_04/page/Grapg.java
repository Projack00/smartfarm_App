package com.example.kuhas.smartfarm_04.page;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kuhas.smartfarm_04.models.Graph_DHT_;
import com.example.kuhas.smartfarm_04.models.PointValue;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.example.kuhas.smartfarm_04.R;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Grapg extends AppCompatActivity {

    public FirebaseDatabase database;
    public DatabaseReference reference;

    public GraphView graph;
    public LineGraphSeries seriesTemp, seriesHumid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapg);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("logDHT");

        graph = (GraphView) findViewById(R.id.graphView);
        seriesTemp = new LineGraphSeries();
        seriesHumid = new LineGraphSeries();

        graph.addSeries(seriesTemp);
        graph.addSeries(seriesHumid);
        graph.getViewport().setScrollable(true); // enables horizontal scrolling
        graph.getViewport().setScrollableY(true); // enables vertical scrolling
        graph.getViewport().setScalable(true); // enables horizontal zooming and scrolling
        graph.getViewport().setScalableY(true); // enables vertical zooming and scrolling

        seriesHumid.setDrawDataPoints(true);
        seriesHumid.setDataPointsRadius(10);
        seriesHumid.setThickness(8);

        seriesTemp.setDrawDataPoints(true);
        seriesTemp.setDataPointsRadius(10);
        seriesTemp.setThickness(8);
//        graph.getSecondScale().setMinY(0);
//        graph.getSecondScale().setMaxY(100);
//        setList();


    }

    @Override
    protected void onStart() {
        super.onStart();
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                DataPoint[] dpX = new DataPoint[(int) dataSnapshot.getChildrenCount()];
//                DataPoint[] dpY = new DataPoint[(int) dataSnapshot.getChildrenCount()];
//
//                int index = 0;
//
//                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
//                    PointValue pointValue = myDataSnapshot.getValue(PointValue.class);
//                    dpX[index] = new DataPoint(index, pointValue.getxValue());
//                    dpY[index] = new DataPoint(index, pointValue.getyValue());
//                    index++;
//                }
//                seriesX.resetData(dpX);
//                seriesX.setColor(Color.RED);
//                seriesY.resetData(dpY);
//                seriesY.setColor(Color.BLUE);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataPoint[] dptemp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                DataPoint[] dphumid = new DataPoint[(int) dataSnapshot.getChildrenCount()];

                int index = 0;

                for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
                    Graph_DHT_ conGraphDht = myDataSnapshot.getValue(Graph_DHT_.class);
                    dptemp[index] = new DataPoint(index, conGraphDht.getTemperature());
                    dphumid[index] = new DataPoint(index, conGraphDht.getHumidity());

                    index++;
                }
                seriesTemp.setTitle("อุณหภูมิ");
                seriesTemp.resetData(dptemp);
                seriesTemp.setColor(Color.RED);

                seriesHumid.setTitle("ความชื้น");
                seriesHumid.resetData(dphumid);
                seriesHumid.setColor(Color.BLUE);

                graph.getLegendRenderer().setVisible(true);
                graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
