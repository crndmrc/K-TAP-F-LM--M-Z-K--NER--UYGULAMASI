package com.mobiluygulama.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class profil extends AppCompatActivity {

    private EditText txtad,txtsoyad,txttelefon,txtemail,txtsifre1,txtsifre2;
    private ImageView logo;
    private TextView txtLogo;
    private TextView sifre2,hataTelefon, hataEmail, hataSifre;
    private Button btnGuncelle,btniptal;
    private Toolbar actionbar_profil;

    ArrayList<Kullanici> kullanicikayit=new ArrayList<>();

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    Helper1 Helper1=new Helper1(this);
    ArrayList<Kullanici> kullaniciBilgileri=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        btnGuncelle=findViewById(R.id.btnGuncelle);
        btniptal=findViewById(R.id.btniptal);
        txtad=findViewById(R.id.Ad);
        txtsoyad=findViewById(R.id.Soyad);
        txttelefon=findViewById(R.id.Telefon);
        txtemail=findViewById(R.id.Email);
        txtsifre1=findViewById(R.id.Sifre1);
        txtsifre2=findViewById(R.id.Sifre2);
        sifre2=findViewById(R.id.txtSifre2);
        hataEmail=findViewById(R.id.hataEmail);
        hataSifre=findViewById(R.id.hataSifre);
        hataTelefon=findViewById(R.id.hataTelefon);

        actionbar_profil=(Toolbar)findViewById(R.id.actionbarProfil);
        setSupportActionBar(actionbar_profil);
        getSupportActionBar().setTitle("Profil");
        kullaniciBilgileri=(ArrayList<Kullanici>)Helper1.ProfilListele();

        for(int i=0;i<kullaniciBilgileri.size();i++){
            txtad.setText(kullaniciBilgileri.get(i).getAd());
            txtsoyad.setText(kullaniciBilgileri.get(i).getSoyad());
            txttelefon.setText(kullaniciBilgileri.get(i).getTelefon());
            txtemail.setText(kullaniciBilgileri.get(i).getEmail());
            txtsifre1.setText(kullaniciBilgileri.get(i).getSifre());
        }

        btniptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profil.this,HosgeldinActivity.class);
                startActivity(intent);
            }
        });

        logo=findViewById(R.id.logo);
        txtLogo=findViewById(R.id.txtLogo);

        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(profil.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });
        txtLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAnaSayfa = new Intent(profil.this,HosgeldinActivity.class);
                startActivity(intentAnaSayfa);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_cubugu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_remove:
                AlertDialog.Builder alert= Helper2.alertBuilder(profil.this);
                alert.setMessage("Öneri siliinsin mi?");
                alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KullaniciGirisActivity kullaniciGirisActivity = new KullaniciGirisActivity();
                        int kullaniciid=kullaniciGirisActivity.getKullanici_id();
                        Helper1.kullaniciSil(kullaniciid);
                        Intent intent=new Intent(profil.this,KullaniciGirisActivity.class);
                        startActivity(intent);
                        editor.putInt("isLogin",10);
                        editor.commit();
                    }
                });
                alert.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
                return true;

            case R.id.action_edit:
                txtad.setEnabled(true);
                txtsoyad.setEnabled(true);
                txttelefon.setEnabled(true);
                txtemail.setEnabled(true);
                txtsifre1.setEnabled(true);
                txtsifre2.setEnabled(true);
                sifre2.setVisibility(View.VISIBLE);
                txtsifre2.setVisibility(View.VISIBLE);
                btnGuncelle.setEnabled(true);
                btnGuncelle.setVisibility(View.VISIBLE);
                btniptal.setEnabled(true);
                btniptal.setVisibility(View.VISIBLE);

                btnGuncelle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean kayitDogru, sifreKontrol, emailKontrol, telefonKontrol;
                        String ad=txtad.getText().toString();
                        String soyad=txtsoyad.getText().toString();
                        String telefon = txttelefon.getText().toString();
                        String email = txtemail.getText().toString();
                        String sifre1 = txtsifre1.getText().toString();
                        String sifre2 = txtsifre2.getText().toString();

                        if(!sifre1.equals("") && !telefon.equals("") && !email.equals("") && !ad.equals("") && !soyad.equals("")){

                            String sifreKarakteri=sifre1;
                            String telefonKarakter=telefon;
                            String emailKarakteri=email;

                            String karakterler="0123456789_qwertyuopasdfghjklizxcvbnm";
                            String hataliBaslangic="_";
                            int ilkkarakter=hataliBaslangic.indexOf(emailKarakteri.charAt(0));

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

                            if (telefonKontrol==true && emailKontrol==true && sifreKontrol==true ) {
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
                                    Helper1.kullaniciGuncelle(kullanici);
                                    Intent intentkayit = new Intent(profil.this, KullaniciGirisActivity.class);
                                    intentkayit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intentkayit);
                                    Toast.makeText(profil.this, "Bilgileriniz güncellendi", Toast.LENGTH_LONG).show();
                                    sonuc++;
                                    finish();
                                }
                            }
                        }
                        else{
                            Toast.makeText(profil.this,"Lütfen boş alanları doldurunuz!!",
                                    Toast.LENGTH_LONG).show();
                        }

                    }

                });
                return true;


            case R.id.cik_item:
                editor.putInt("isLogin",10);
                editor.commit();
                Intent intent1 = new Intent(getApplicationContext(),KullaniciGirisActivity.class);
                startActivity(intent1);
                finish();
                return true;
        }

        return true;
    }
}