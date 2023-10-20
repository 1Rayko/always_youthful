package com.sudoreboot.always;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.*;
//import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class KTD extends AppCompatActivity implements View.OnClickListener {

    String title = "";
    String data = "";

    Integer agep;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getIntent().getExtras();

        setContentView(R.layout.activity_games);


        LinearLayout layout = (LinearLayout)findViewById(R.id.linlay);
        try {
            String json_string = arguments.getString("json");
            JSONObject Arr = new JSONObject(json_string);
            JSONArray KTD = Arr.getJSONArray("KTD");
//            agep = Integer.parseInt(arguments.getString("age"));
            data = KTD.toString();
            agep = Integer.valueOf(arguments.getString("age"));
            Log.d("------>>",agep.toString());

            for (int i = 0; i<KTD.length();i++)
            {

                Integer agejson = Integer.parseInt(KTD.getJSONObject(i).getString("age"));
//                Log.d("adadadada jsonnn",agejson.toString());
                if (agep!=0){
                    if ((agep == agejson) || (agejson == 0))
                    {
                        //


                        Button btn = new Button(this);
                        title = KTD.getJSONObject(i).getString("title").toString();


                        btn.setText(title);
                        btn.setOnClickListener(this);
                        btn.setBackgroundColor(0xFFBB86FC);
                        btn.setTextColor(Color.WHITE);
                        btn.setId(i);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                        );
                        params.setMargins(20, 20, 20, 20);
                        btn.setLayoutParams(params);
                        layout.addView(btn);
                    } else {
                        continue;
                    }
                }else{

                    Button btn = new Button(this);
                    title = KTD.getJSONObject(i).getString("title").toString();


                    btn.setText(title);
                    btn.setOnClickListener(this);
                    btn.setBackgroundColor(0xFFBB86FC);
                    btn.setTextColor(Color.WHITE);
                    btn.setId(i);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    params.setMargins(20, 20, 20, 20);
                    btn.setLayoutParams(params);
                    layout.addView(btn);

                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void onClick(View view) {
        Intent i;
        int id = view.getId();
        i = new Intent(this, Dyn.class);
        i.putExtra("title",id);
        i.putExtra("data",data);
        startActivity(i);
    }
}