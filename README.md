# 學 ListView 元件
> [2022-08-05 Android課程]
### 0. 開新的project , 取名為 "ListView_2"
### 1. strings.xml 新增郵遞區號 
```
<resources>
    <string name="app_name">ListView_2</string>
    <string-array name="city">
        <item>臺北市</item>
        <item>基隆市</item>
        <item>桃園市</item>
    </string-array>
    <string-array name="taipei">
        <item>中正區100</item>
        <item>大同區103</item>
        <item>中山區104</item>
        <item>松山區105</item>
        <item>大安區106</item>
        <item>萬華區108</item>
        <item>信義區110</item>
        <item>士林區111</item>
        <item>北投區112</item>
        <item>內湖區114</item>
        <item>南港區115</item>
        <item>文山區116</item>
    </string-array>
    <string-array name="Keelung">
        <item>仁愛區200</item>
        <item>信義區201</item>
        <item>中正區202</item>
        <item>中山區203</item>
        <item>安樂區204</item>
        <item>暖暖區205</item>
        <item>七堵區206</item>
    </string-array>
    <string-array name="tauyuan">
        <item>中壢區320</item>
        <item>平鎮區324</item>
        <item>龍潭區325</item>
        <item>楊梅區326</item>
        <item>新屋區327</item>
        <item>觀音區328</item>
        <item>桃園區330</item>
        <item>龜山區333</item>
        <item>八德區334</item>
        <item>大溪區335</item>
        <item>復興區336</item>
        <item>大園區337</item>
        <item>蘆竹區338</item>
    </string-array>
</resources>
```
### 2. Java 表示集合的資料結構 :
 + Collection interface : List 無順序 可重複資料
 + Map interface : Map 同組資料打包

### 3. MainActivity.java : 跟著 選Spinner不同選項 更改 ListView 內容
```
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
```
