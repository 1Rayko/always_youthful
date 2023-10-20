package com.sudoreboot.always;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

public class Dyn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn);
        Bundle arguments = getIntent().getExtras();

        TextView textView = findViewById(R.id.dynHead);
        TextView textView1 = findViewById(R.id.dyntext);

        if(arguments!=null){
            int id  =  Integer.parseInt(arguments.get("title").toString());
            String data = arguments.get("data").toString();
            try {
                JSONArray arr = new JSONArray(data);
                String title = arr.getJSONObject(id).getString("title");
                String text = arr.getJSONObject(id).getString("body");
                textView.setText(title);
                textView1.setText(text);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


        }

    }
}