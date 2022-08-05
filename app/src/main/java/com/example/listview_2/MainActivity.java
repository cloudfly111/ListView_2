package com.example.listview_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerCity;
    private ListView listViewCode;
    private ArrayAdapter<CharSequence> spinnerAdapter;
    private ArrayList<String> codeList;
    private ArrayAdapter<String> listViewAdapter;
    private int[] cityCode = {R.array.taipei,R.array.Keelung,R.array.tauyuan};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerCity = (Spinner) findViewById(R.id.spinner_city);
        listViewCode = (ListView) findViewById(R.id.listView_code);
//      Adapter : a object to control data source
//      set the Adapter of Spinner : ---------------------------------
//      before press Spinner :
        spinnerAdapter = ArrayAdapter.createFromResource(MainActivity.this,R.array.city,
                R.layout.simple_spinner_item);
//      after press Spinner :
        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnerCity.setAdapter(spinnerAdapter);
//      set the Adapter of ListView : --------------------------------
        String[] code = getResources().getStringArray(R.array.taipei);
//       Two method to put String Array to ArrayList
//        1. using asList
//        codeList = new ArrayList<String>(Arrays.asList(code));
//        2. using for-loop
        codeList = new ArrayList<String>();
        for(int i=0;i<code.length;i++){
            codeList.add(code[i]);
        }
        listViewAdapter = new ArrayAdapter<String>(MainActivity.this,
                R.layout.simple_expandable_list_item_1,codeList);
        listViewCode.setAdapter(listViewAdapter);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName = parent.getItemAtPosition(position).toString();
                Log.d("main","cityName="+cityName);
                Log.d("main","position="+position);
                String[] codeData = getResources().getStringArray(cityCode[position]);
                codeList = new ArrayList<String>();
                for(int i=0;i<codeData.length;i++){
                    codeList.add(codeData[i]);
                }
                listViewAdapter.clear();
                listViewAdapter.addAll(codeList);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        
        listViewCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
            }
        });

    }
}