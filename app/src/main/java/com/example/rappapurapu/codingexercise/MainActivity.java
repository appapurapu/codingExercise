package com.example.rappapurapu.codingexercise;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final projectMgr pMgr = new projectMgr("Jerry", 1234, null);

        // Read Projects info from assets and add to Project Manager
        try {
            JSONObject obj      = new JSONObject(loadJSONFromAsset("info.json"));
            JSONArray projList  = obj.getJSONArray("projects");


            for(int i = 0; i < projList.length(); ++i){
                JSONObject proj   = projList.getJSONObject(i);
                project p = new project();
                p.setTitle(proj.getString("title"));

                // Read project works and update
                JSONArray wkrList = proj.getJSONArray("workers");
                for(int j = 0; j < wkrList.length(); ++j){
                    JSONObject wObj = wkrList.getJSONObject(j);
                    worker w = new worker();
                    w.setName(wObj.getString("name"));
                    w.setEmpNumber(wObj.getInt("employee number"));
                    p.addWrker(w);
                }

                // Add the project to project Manager object
//                p.save();
                pMgr.addProject(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //pMgr.save();

        // Create Buttons
        Button btnA = (Button)findViewById(R.id.btnA);
        btnA.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                displayProject(pMgr.getProject("Project A"));
            }
        });

        Button btnB = (Button)findViewById(R.id.btnB);
        btnB.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                displayProject(pMgr.getProject("Project B"));

//                project prj = pMgr.getProject("Project B");
//                ListView lView = (ListView) findViewById(R.id.listview);
//
//                String[] from   = new String[] {"EmployeeName", "EmployeeID"};
//                int[] to        = new int[] { R.id.item1, R.id.item2 };
//
//                List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
//                ArrayList<worker> wList = prj.getWrkers();
//                for(int i = 0; i < wList.size(); i++){
//                    worker w = wList.get(i);
//                    HashMap<String, String> map = new HashMap<String, String>();
//                    map.put("EmployeeName", "" + w.getName());
//                    map.put("EmployeeID", "" + w.getEmpNumber());
//                    fillMaps.add(map);
//                }
//                // fill in the grid_item layout
//                SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
//                lView.setAdapter(adapter);
            }
        });
        btnB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                // Cancel the project B
                pMgr.cancelProject(pMgr.getProject("Project B"));

                Button btnB = (Button)findViewById(R.id.btnB);
                btnB.setVisibility(View.GONE);
                //TextView tView = (TextView)findViewById(R.id.textView);
                //tView.setText("2");

                return true;
            }
        });

        Button btnC = (Button)findViewById(R.id.btnC);
        btnC.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                displayProject(pMgr.getProject("Project C"));
            }
        });



    }



    void displayProject(project proj){
//        ListView lView = (ListView) findViewById(R.id.ListView);

//        project prj = pMgr.getProject("Project B");
        ListView lView = (ListView) findViewById(R.id.listview);

        String[] from   = new String[] {"EmployeeName", "EmployeeID"};
        int[] to        = new int[] { R.id.item1, R.id.item2 };

        List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
        ArrayList<worker> wList = proj.getWrkers();
        for(int i = 0; i < wList.size(); i++){
            worker w = wList.get(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("EmployeeName", "" + w.getName());
            map.put("EmployeeID", "" + w.getEmpNumber());
            fillMaps.add(map);
        }
        // fill in the grid_item layout
        SimpleAdapter adapter = new SimpleAdapter(this, fillMaps, R.layout.grid_item, from, to);
        lView.setAdapter(adapter);
    }

    // Load from Assets
    public String loadJSONFromAsset(String jsonFile) {
        String json = null;
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream is = assetManager.open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
