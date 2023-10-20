package com.sudoreboot.always;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import android.os.CountDownTimer;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private CardView Games,Ktd,Fire,Pesni,Spv,Sendm,Clicker;

    public Integer age = 0;

    private RadioButton malishi, srednie, starshie,allvozrast;
    private RadioGroup rg;
    private static final long TIMER_INTERVAL = 500;
    private CountDownTimer countDownTimer;
    String getUrl = "https://dadadada123.pythonanywhere.com/get";
    private Document doc;
    private  Thread secThread,thrThread;
    
    private Runnable runnable,nrunnable;

    private JSONObject res;
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sendm = findViewById(R.id.Sendmm);
        Games = findViewById(R.id.Gamesc);
        Ktd=findViewById(R.id.KTD);
        Fire = findViewById(R.id.Firee);
        Pesni = findViewById(R.id.Pesnii);
        Spv = findViewById(R.id.spv);

        Games.setOnClickListener(this);
        Sendm.setOnClickListener(this);
        Ktd.setOnClickListener(this);
        Fire.setOnClickListener(this);
        Pesni.setOnClickListener(this);
        Spv.setOnClickListener(this);


        malishi = findViewById(R.id.malishi);
        srednie = findViewById(R.id.sredine);
        starshie = findViewById(R.id.starshie);
        allvozrast = findViewById(R.id.allvozrast);

        rg = findViewById(R.id.radiogrr);


        init();





    }

    public void startTimer() {
        countDownTimer = new CountDownTimer(Long.MAX_VALUE, TIMER_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Здесь выполняется код проверки запроса на сервер
                boolean isRequestCompleted = checkServerRequest(); // Здесь ваш код проверки запроса

                if (isRequestCompleted) {
                    // Запрос выполнен, можно остановить таймер
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                    if (res==null){
                        Log.d("--------------------","123132131");
                        readLocal();

                    }
                    else{

                    }

//                return false;
                // Выполняется, если таймер завершается (после очень долгого времени)
                // Здесь можно выполнить дополнительные действия, если нужно
            }
        };
        countDownTimer.start(); // Запускаем таймер
    }

    public void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Остановка таймера
        }
    }

    private boolean checkServerRequest() {
        return false;

    }


    @Override
    public void onClick( View v){
        Intent i;
        if (res!=null){
            rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup arg0, int id) {
                    switch(id) {
                        case R.id.allvozrast:
                            Log.d("dadadadadada--->>>","00");
                            age = 0;
                            break;
                        case R.id.malishi:
                            Log.d("dadadadadada--->>>","11111");
                            age = 1;
                            break;
                        case R.id.sredine:
                            Log.d("dadadadadada--->>>","222");
                            age = 2;
                            break;

                        case R.id.starshie:
                            Log.d("dadadadadada--->>>","333");
                            age = 3;
                            break;
                        default:

                            break;
                    }
                }


            });

            switch (v.getId()){
                case R.id.Gamesc:i = new Intent(this, Games.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                case R.id.Firee:i=new Intent(this,Fire.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                case R.id.KTD:i=new Intent(this,KTD.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                case R.id.Pesnii:i = new Intent(this,Pesni.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                case R.id.spv:i = new Intent(this,SPV.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                case R.id.Sendmm:i=new Intent(this,SendF.class);i.putExtra("json", res.toString());i.putExtra("age",age.toString());startActivity(i);
                    break;
                default:break;
            }
        }else{
            Toast.makeText(this,"Дождитесь загрузки материалов",Toast.LENGTH_SHORT).show();
        }
    }

    private String readLocal(){

        String ret="";
        try {
            String str = "";
            // открываем поток для чтения
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput("base.json")));

            // читаем содержимое
            while ((str = br.readLine()) != null) {
                Log.d("читаем локальноу", str);
                ret+=str;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Нет файла");
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
        return ret;
    }
    private String readAssets(){
        StringBuilder sb = null;
        try {
            sb = new StringBuilder();
            InputStream is = getAssets().open("base.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String str;
            Log.d("->>>>>>>>>>>>>>>>>>","Читаем assets");
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();


        } catch (Exception e) {
            throw new RuntimeException("assets err:" + e.toString());
        }

        return sb.toString();

    }
    private void writeLocal(String text){

        try {
            // отрываем поток для записи
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("base.json", MODE_PRIVATE)));
            // пишем данные
            bw.write(text);
            // закрываем поток
            bw.close();
            Log.d("запись в файл", "Файл записан");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void getWeb(){

        try {
            doc = Jsoup.connect(getUrl).get();
            try {
                str = readLocal();
                JSONObject tmp = new JSONObject(str);
                JSONObject tmp1 = new JSONObject(doc.body().text());

                int f_ver,ver_web ;
                f_ver = Integer.parseInt(tmp.getString("version"));
                ver_web = Integer.parseInt(tmp1.getString("version"));

                if (ver_web>f_ver){
                    writeLocal(doc.body().text());
                }
                res = new JSONObject(doc.body().text());

            }catch (Exception e){
                writeLocal(doc.body().text());
                res = new JSONObject(doc.body().text());

            }
        } catch (IOException | JSONException e) {
            try {
                str = readLocal();
                res = new JSONObject(str);
                Log.d("------------->",str);
            }catch (Exception exception){

                try {
                    res = new JSONObject(readAssets());
                } catch (JSONException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

    }

    private void init(){
        runnable = new Runnable() {
            @Override
            public void run() {
                 nrunnable = new Runnable() {
                    @Override
                    public void run() {

                        startTimer();

                    }
                };

                getWeb();




            }
        };
        thrThread = new Thread(nrunnable);
        thrThread.start();
        secThread = new Thread(runnable);
        secThread.start();
    }
}