package com.mobiluygulama.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KullaniciGirisActivity extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private Toolbar actionbarGiris;
    private ImageView logo;
    private TextView txtLogo;
    private EditText edtEmail, edtSifre;
    private String Email,Sifre;

    Helper1 Helper1=new Helper1(this);
    ArrayList<Kullanici> kullanicigiris=new ArrayList<>();

    private static int kullanici_id;

    public static int getKullanici_id() {
        return kullanici_id;
    }

    public static void setKullanici_id(int kullanici_id) {
        KullaniciGirisActivity.kullanici_id = kullanici_id;
    }

    public void Baglanti(){

        actionbarGiris=(Toolbar) findViewById(R.id.actionbarGiris);
        setSupportActionBar(actionbarGiris);
        getSupportActionBar().setTitle("ÜYE GİRİŞİ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        logo=findViewById(R.id.logo);
        txtLogo=findViewById(R.id.txtLogo);
        edtEmail=findViewById(R.id.edtEmail);
        edtSifre=findViewById(R.id.edtSifre);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(KullaniciGirisActivity.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });
        txtLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(KullaniciGirisActivity.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_giris);

        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();


        String islogin=String.valueOf(preferences.getInt("isLogin",0));

        if(islogin.equals("12")){
            kullanici_id=preferences.getInt("kullaniciid",0);
            Intent intent = new Intent(getApplicationContext(),offers.class);
            startActivity(intent);
            finish();
            Toast.makeText(KullaniciGirisActivity.this,"Giriş Başarılı",Toast.LENGTH_LONG).show();
        }

        Baglanti();
    }

    public void girisYap(View view)
    {
        Email=edtEmail.getText().toString();
        Sifre=edtSifre.getText().toString();

        kullanicigiris=(ArrayList<Kullanici>) Helper1.kullaniciid();

        int sonuc=0;

        for(int i=0;i<kullanicigiris.size();i++){
            if(kullanicigiris.get(i).getEmail().equals(Email) && kullanicigiris.get(i).getSifre().equals(Sifre)){
                kullanici_id=kullanicigiris.get(i).getKullaniciID();

                editor.putInt("isLogin",12);
                editor.commit();
                editor.putInt("kullaniciid",kullanici_id);
                editor.commit();

                Toast.makeText(KullaniciGirisActivity.this,"Giriş başarılı ",Toast.LENGTH_LONG).show();
                Intent intentOffer = new Intent(this,offers.class);
                intentOffer.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentOffer);
                sonuc++;
                finish();
                break;
            }
        }

        if(sonuc==0){
            Toast.makeText(KullaniciGirisActivity.this,"Eposta veya Sifre yanlış!! " +
                    "Lütfen bilgilerinizi kontrol ederek tekrar giriniz.",Toast.LENGTH_LONG).show();

        }

    }

    public void kayitOl(View view){
        Intent intent = new Intent(this,KullaniciKayitOlActivity.class);
        startActivity(intent);
        finish();
    }
}
