package com.example.kuhas.smartfarm_04.page;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.kuhas.smartfarm_04.models.DHTGraph;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.example.kuhas.smartfarm_04.R;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Grapg extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference refDHT;

    GraphView graphView;
    LineGraphSeries series;
    private List<Integer> integerList = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grapg);

        database = FirebaseDatabase.getInstance();
        refDHT = database.getReference("logDHT");
    }

    private void foSeries(){
    }

    private void ThreeSeries() {
        graphView = (GraphView) findViewById(R.id.graphView);
        series = new LineGraphSeries();
        graphView.addSeries(series);

        refDHT.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                sampleLastSeries(dataSnapshot);
                DataPoint[] dp = new DataPoint[(int) dataSnapshot.getChildrenCount()];
                int index = 0;
                for (DataSnapshot myDataSnapshot1 : dataSnapshot.getChildren()) {
                    DHTGraph pointValue = myDataSnapshot1.getValue(DHTGraph.class);
                    dp[index] = new DataPoint(pointValue.getHumidity(), pointValue.getTemperature());
                    index++;
                    Log.i("tag", "tag_-=>" + index + "getHumidity()=>" + pointValue.getHumidity());
                }
//                series.resetData(dp);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sampleLastSeries(DataSnapshot dataSnapshot) {
        List<Integer> listHumidity = new ArrayList<Integer>();
        for (DataSnapshot myDataSnapshot : dataSnapshot.getChildren()) {
            DHTGraph pointValue = myDataSnapshot.getValue(DHTGraph.class);
        }
        Integer[] DataInt = listHumidity.toArray(new Integer[]{});

        int num = (int) dataSnapshot.getChildrenCount();

        for (int i = 0; i <= num; i++) {
            integerList.add(i);

        }
        Integer[] intNum = integerList.toArray(new Integer[]{});

        for (Integer element : intNum) {
            System.out.println(element);
        }
    }

    private void sampleSeries() {
//        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphView.GraphViewData[]{
//                new GraphView.GraphViewData(1, 2.0d)
//                , new GraphView.GraphViewData(2, 1.5d)
//                , new GraphView.GraphViewData(3, 2.5d)
//                , new GraphView.GraphViewData(4, 1.0d)
//        });

//        graphView.addSeries(exampleSeries);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        layout.addView(graphView);
    }

    private void sampleMultipleSeries() {
        String[] months = {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

        int[] index = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

//        int[] incomeA = {50, 60, 70, 50, 55, 68, 35, 25, 33, 44, 44, 43, 50, 60, 70, 50, 55, 68, 35, 25, 33, 44, 44, 43};
        int[] incomeB = {35, 36, 35, 37, 38, 38, 36, 37, 36, 35, 38, 38, 36, 37, 36, 35, 38, 38, 36, 37, 36, 35, 35, 34};
//        Log.i("TAG", "incomeA=>" + incomeA);

////////////////////////////////////////////////////////////////////////////////////////////////////

        List<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i <= 50; i++) {
            intList.add(i);
        }
        Integer[] intArr = intList.toArray(new Integer[]{});
////////////////////////////////////////////////////////////////////////////////////////////////////

        List<Integer> incomeA1 = new ArrayList<Integer>();
        for (int i = 0; i <= 100; i++) {
            incomeA1.add(i);
        }
        Integer[] incomeA = incomeA1.toArray(new Integer[]{});

////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////

        int num = 100;
//        GraphView.GraphViewData[] data = new GraphView.GraphViewData[intArr.length];
        for (int i = 0; i < intArr.length; i++) {
//            data[i] = new GraphView.GraphViewData(i, incomeA[i]);
        }
//        GraphViewSeries seriesA = new GraphViewSeries("อุณหภูมิ",
//                new GraphViewSeries.GraphViewSeriesStyle(Color.RED, 5), data);

//        data = new GraphView.GraphViewData[index.length];
        for (int i = 0; i < index.length; i++) {
//            data[i] = new GraphView.GraphViewData(i, incomeB[i]);
        }
//        GraphViewSeries seriesB = new GraphViewSeries("ความชืน",
//                new GraphViewSeries.GraphViewSeriesStyle(Color.BLUE, 5), data);

//        GraphView graphView = new LineGraphView(this, "DHT Series");
//        graphView.addSeries(seriesA);
//        graphView.addSeries(seriesB);
//
//        graphView.setShowLegend(true);

        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);
        layout.addView(graphView);

    }
}
