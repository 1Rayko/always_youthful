package com.sudoreboot.always;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.FormBody;
//import okhttp3.MediaType;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;

public class SendF extends AppCompatActivity {
    private EditText editText;
    private Button sendbtn;

    private Document doc;
    private  Thread secThread;
    private Runnable runnable;

//    OkHttpClient client;
    String postUrl = "https://dadadada123.pythonanywhere.com/vk?msg=";
    String res = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_f);
        editText = findViewById(R.id.editTextTextMultiLine);
        sendbtn = findViewById(R.id.sndbtn);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                get();
                    postUrl+=editText.getText();
//                    init();
                    if (editText.getText().toString().replace(" ","").length()>0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    postWeb();
                                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                                    editText.setText("");
                                    postUrl = "https://dadadada123.pythonanywhere.com/vk?msg=";
                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), res, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }else{
                        Toast.makeText(getApplicationContext(), "Введите текст", Toast.LENGTH_SHORT).show();
                    }
                    editText.setText("");


            }
        });
    }
//    public void get(){
//        Request request = new Request.Builder().url(postUrl+editText.getText()).build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            editText.setText("");
//                            res = response.body().string();
//                            Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
//
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
////                        return null;
//                    }
//                });
//            }
//        });
//
//        }

    private void postWeb(){

        try {
            doc = Jsoup.connect(postUrl).post();
            res = doc.body().text();
            Log.d("1233333333333333",res);
        } catch (IOException e) {
            res = "Нет подключения к интернету";
//            res = "Нет подключения к интернету:"+e.toString();
            throw new RuntimeException(e);
        }

    }


}


