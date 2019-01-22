package com.example.pawe.seventh;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREFS_FILE_NAME = "com.example.pawe.seventh.PREFS_FILE_KEY";
    private static final String SHARED_PREFS_COUNTER_KEY = "com.example.pawe.seventh.COUNTE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE);
        ArrayList<String> values = new ArrayList<>();
        Integer key = 1;
        String val = "";
        while (val != null) {
            val = sharedPreferences.getString(key.toString(),null);
            if (val != null){
                values.add(val);
            }
            key++;
        }
        if (values.size() > 0){
            Integer counter = sharedPreferences.getInt(SHARED_PREFS_COUNTER_KEY,0);
            refreshListView(values);
            TextView textView = findViewById(R.id.textView);
            textView.setText(counter.toString());
        } else {
            TextView textView = findViewById(R.id.textView);
            textView.setText("0");
        }
    }
    private void refreshListView(ArrayList<String> strings){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);
        ListView listView = findViewById(R.id.lv);
        listView.setAdapter(adapter);
    }

    public void addDataOnClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE);
        Integer counter = sharedPreferences.getInt(SHARED_PREFS_COUNTER_KEY,0);
        counter++;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFS_COUNTER_KEY,counter).putString(counter.toString(),new Date().toString()).apply();
        ArrayList<String> values = new ArrayList<>();
        Integer key = 1;
        String val = "";
        while (val != null) {
            val = sharedPreferences.getString(key.toString(),null);
            if (val != null){
                values.add(val);
            }
            key++;
        }
        refreshListView(values);
        TextView textView = findViewById(R.id.textView);
        textView.setText(counter.toString());
    }

    public void deleteDataOnClick(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_FILE_NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        refreshListView(new ArrayList<String>());
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.valueOf(0));
    }
}
