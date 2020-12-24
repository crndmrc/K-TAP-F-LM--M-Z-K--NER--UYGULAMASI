package com.mobiluygulama.app;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class HosgeldinActivity extends AppCompatActivity {

    private Button uyeGiris;
    private Button kayitOl;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public void tanimlama(){

        uyeGiris=(Button)findViewById(R.id.uyeGiris);
        kayitOl=(Button)findViewById(R.id.kayitOl);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();

        if(preferences.getInt("isLogin",0)==12){
            Intent intentGiris = new Intent(HosgeldinActivity.this,KullaniciGirisActivity.class);
            startActivity(intentGiris);
        }

        else if(preferences.getInt("isLogin",0)==10){

        }

        else{
            editor.putInt("isLogin",10).apply();
        }

        tanimlama();

        uyeGiris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentGiris = new Intent(HosgeldinActivity.this,KullaniciGirisActivity.class);
                startActivity(intentGiris);


            }

        });

        kayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentKayit = new Intent(HosgeldinActivity.this,KullaniciKayitOlActivity.class);
                startActivity(intentKayit);
            }
        });
    }
}
