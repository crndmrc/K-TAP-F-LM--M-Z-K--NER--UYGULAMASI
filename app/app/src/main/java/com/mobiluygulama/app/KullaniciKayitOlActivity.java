package com.mobiluygulama.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class KullaniciKayitOlActivity extends AppCompatActivity {

    private Toolbar actionbarKayit;
    private TextView txtViewUyeGirisi, txtLogo, hataTelefon, hataEmail, hataSifre;
    private ImageView logo;
    private Button uyelikOlustur;
    private EditText txtad,txtsoyad,txttelefon,txtemail,txtsifre1,txtsifre2;

    Helper1 Helper1 = new Helper1(this);

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ArrayList<Kullanici> kullanicikayit=new ArrayList<>();

    public void Baglanti(){

        logo=findViewById(R.id.logo);
        txtLogo=findViewById(R.id.txtLogo);
        txtad=findViewById(R.id.Ad);
        txtsoyad=findViewById(R.id.Soyad);
        txttelefon=findViewById(R.id.Telefon);
        txtemail=findViewById(R.id.Email);
        txtsifre1=findViewById(R.id.Sifre1);
        txtsifre2=findViewById(R.id.Sifre2);
        txtViewUyeGirisi=(TextView)findViewById(R.id.txtViewUyeGirisi);
        actionbarKayit=(Toolbar) findViewById(R.id.actionbarKayit);
        uyelikOlustur=findViewById(R.id.uyelikOlustur);
        hataEmail=findViewById(R.id.hataEmail);
        hataSifre=findViewById(R.id.hataSifre);
        hataTelefon=findViewById(R.id.hataTelefon);

        setSupportActionBar(actionbarKayit);
        getSupportActionBar().setTitle("KAYIT OL");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kullanici_kayit_ol);

        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preferences.edit();

        Baglanti();

        txtViewUyeGirisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGiris = new Intent(KullaniciKayitOlActivity.this,KullaniciGirisActivity.class);
                startActivity(intentGiris);
            }
        });

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(KullaniciKayitOlActivity.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });

        txtLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(KullaniciKayitOlActivity.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });
    }

    public void kaydet(View view) {

        boolean kayitDogru, sifreKontrol, emailKontrol, telefonKontrol;

        String ad = txtad.getText().toString();
        String soyad = txtsoyad.getText().toString();
        String telefon = txttelefon.getText().toString();
        String email = txtemail.getText().toString();
        String sifre1 = txtsifre1.getText().toString();
        String sifre2 = txtsifre2.getText().toString();

        if(!sifre1.equals("") && !telefon.equals("") && !email.equals("") && !ad.equals("") && !soyad.equals("")) {

            String sifreKarakteri=sifre1;

            String telefonKarakter = telefon;

            String emailKarakteri=email;

            String karakterler="0123456789_qwertyuopasdfghjklizxcvbnm";
            String hataliBaslangic="_";
            int ilkkarakter=hataliBaslangic.indexOf(emailKarakteri.charAt(0));
            txtad.setText(" "+ilkkarakter);


            int say=0;

            for(int i=0;i<emailKarakteri.length();i++){
                int index = karakterler.indexOf(emailKarakteri.charAt(i));
                if(index<0){
                }
                else{
                    say++;
                }
            }

            if(say==emailKarakteri.length() && ilkkarakter<0){
                hataEmail.setText("");
                emailKontrol=true;
            }
            else if(say!=emailKarakteri.length()){
                hataEmail.setText("Email adresi sadece sayı, harf veya '_' içermelidir!");
                emailKontrol=false;
            }
            else {
                hataEmail.setText("Email adresi '_' ile başlamaz (0-9)sayı veya (a-z)harf ile başlamalıdır!");
                emailKontrol=false;
            }


            /*************/

            if (!sifre1.equals(sifre2)) {
                hataSifre.setText("Şifreler birbiriyle uyuşmuyor!");
                txtsifre1.setText("");
                txtsifre2.setText("");
                sifreKontrol=false;
            }

            else if(sifreKarakteri.length()<8){
                hataSifre.setText("Şifre en az 8 karakterden oluşmalıdır!");
                sifreKontrol=false;
            }
            else {
                hataSifre.setText("");
                sifreKontrol=true;
            }

            /****************************/

            String sifir="0";
            int ilkrakam=sifir.indexOf(telefonKarakter.charAt(0));

            if(telefonKarakter.length()==11 && ilkrakam>-1){
                hataTelefon.setText("");
                telefonKontrol=true;
            }
            else if(telefonKarakter.length()!=11){
                hataTelefon.setText("Telefon numarası 11 rakamdan oluşmalı!");
                telefonKontrol=false;
            }
            else{
                hataTelefon.setText("Telefon numarası 0(sıfır) ile başlamalı!");
                telefonKontrol=false;
            }
            /*******************************/

            if (telefonKontrol==true && emailKontrol==true && sifreKontrol==true) {
                kayitDogru = true;


            } else {
                kayitDogru = false;

            }

            if (kayitDogru == true) {

                Kullanici kullanici = new Kullanici(ad, soyad, telefon, email, sifre1);
                kullanicikayit = (ArrayList<Kullanici>) Helper1.kullaniciid();

                int sonuc = 0;
                for (int i = 0; i < kullanicikayit.size(); i++) {
                    if (kullanicikayit.get(i).getEmail().equals(email)) {
                        hataEmail.setText("Girmiş olduğunuz email adresi başkası tarafından kullanılmaktadır." +
                                "Lütfen başka bir email adresi girin!");
                        sonuc++;
                    }
                }


                if (sonuc == 0) {
                    editor.putInt("isLogin", 10);
                    editor.commit();
                    Helper1.kullaniciEkle(kullanici);
                    Intent intentkayit = new Intent(KullaniciKayitOlActivity.this, KullaniciGirisActivity.class);
                    intentkayit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentkayit);
                    Toast.makeText(KullaniciKayitOlActivity.this, "Kaydınız Başarıyla kaydedildi",
                            Toast.LENGTH_LONG).show();
                    sonuc++;
                    finish();
                }

            }
        }

        else{
            Toast.makeText(KullaniciKayitOlActivity.this,"Lütfen boş alanları doldurunuz!!",
                    Toast.LENGTH_LONG).show();
        }

    }
}
