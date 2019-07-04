package com.android.smartkitchen;



import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment  implements AsyncResponse{

    private static String TAG = "MainActivity";
    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String URL = "http://crowd-multilogue.com/home/load";
    private static String[] xData = {"Remained", "Consumed"};
    Button button1;
    float Finalinput;
    private float[] yData = {Finalinput, 5000-Finalinput};

    PieChart pieChart;
    //ListView listViews;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final HomeFragment myHomeFragment = this;
        View views = inflater.inflate(R.layout.fragment_home, container, false);
        //listViews = (ListView) views.findViewById(R.id.Homelistview);
        button1 = (Button)views.findViewById(R.id.Homebutton);
        pieChart = (PieChart) views.findViewById(R.id.IdPieChart);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MyDownloadTask myDownloadTask = new MyDownloadTask();
                myDownloadTask.delegate = myHomeFragment;
                myDownloadTask.execute();
                pieChart.setHoleRadius(80f);
                pieChart.setTransparentCircleAlpha(0);
                pieChart.setCenterText("Yogurt");
                pieChart.setCenterTextSize(20);
                pieChart.setRotationEnabled(true);
                addDataSet();

                pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {

                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });


            }
        });
        //ArrayAdapter<String> listadapterview = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,myStringArray);
        //listViews.setAdapter(listadapterview);
        // Inflate the layout for this fragment
        return views;
    }

    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i=0; i<yData.length; i++){
            yEntrys.add(new PieEntry(yData[i], i));
        }

        for (int i=1; i<xData.length; i++){
            xEntrys.add(xData[i]);
        }
        PieDataSet pieDataSet =new PieDataSet(yEntrys, "Our Kitchen");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();


    }


    @Override
    public void processFinish(String output){
        String input =output.substring(0, output.length()-1);
        //ArrayAdapter<String> listadapterview = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,i);
        //listViews.setAdapter(listadapterview);
        Finalinput= Float.valueOf(input);
        yData = new float[]{Finalinput, 5000-Finalinput};
        addDataSet();

    }

}


interface AsyncResponse{
    void processFinish(String output);
}

class MyDownloadTask extends AsyncTask<Void,Void,Void>
{
    public AsyncResponse delegate = null;
    String responseMsg = "No";
    @Override
    protected void onPreExecute() {
    }
    @Override
    protected Void doInBackground(Void... params) {
        try{
            URL url = new URL("http://crowd-multilogue.com/home/load");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            try{
                con.setRequestMethod("GET");
                responseMsg = con.getResponseMessage();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line+"\n");
                }
                br.close();
                responseMsg = sb.toString();}
            catch (Exception e){}
            finally {
                con.disconnect();
            }
        }
        catch (Exception e){
            responseMsg = e.getMessage();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void output) {
        delegate.processFinish(responseMsg);
    }
}
