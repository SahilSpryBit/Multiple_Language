package com.example.multiplelanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView txt1, txt2, txt3, txt4;
    LinearLayout layout;
    Button btn_chng, btn2;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Language();
        setContentView(R.layout.activity_main);



//        SharedPreferences preferences = getSharedPreferences("Language", MODE_PRIVATE);
//        String language = preferences.getString("app_lang", "");
//        setLocale(language);


        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        btn_chng = findViewById(R.id.btn_chng);
        layout = findViewById(R.id.layout);
        btn2 = findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_chng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              final String language[] = {"English", "Hindi", "Gujarati", "Urdu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Choose Language");
                builder.setSingleChoiceItems(language, 5, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(i == 0){

                            setLocale("");
                            recreate();
                        }
                        else if(i == 1){

                            setLocale("hi");
                            recreate();
                        }
                        else if(i == 2){

                            setLocale("gu");
                            recreate();
                        }
                        else if(i == 3){

                            setLocale("ur");
                            recreate();
                        }

                    }
                });

                builder.create();
                builder.show();

            }
        });

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }

    private void setLocale(String language){

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;

        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());
        invalidateOptionsMenu();
        onConfigurationChanged(configuration);


        SharedPreferences.Editor editor = getSharedPreferences("Language", MODE_PRIVATE).edit();
        editor.putString("app_lang", language);
        editor.apply();
    }

    private void initializeDownloadManager() {

        downloadManager= (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

    }

    private void Language(){

        SharedPreferences preferences = getSharedPreferences("Language", MODE_PRIVATE);
        String language = preferences.getString("app_lang", "");
        setLocale(language);
    }

}