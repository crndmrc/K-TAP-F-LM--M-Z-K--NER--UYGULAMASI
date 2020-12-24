package com.mobiluygulama.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class offer_bilgileri extends AppCompatActivity {

    private Spinner spinnerOfferTipi,spinnerTur, spinnerOneri
            ,spinnerKitap,spinnerFilm;
    private EditText baslik,edtYil,k_not;
    private Toolbar actionbarOfferBilgileri;
    private Button btnGuncelle,btniptal;

    private String s_kitap, s_film,s_offer_tipi,s_tur;
    private int offerIndex,turIndex, oneriIndex,kitapIndex,filmIndex;


    Bitmap resimSecimi;
    ImageView resimEkle;
    Uri imageUri;

    Helper1 Helper1;
    ArrayList<offer> offerozellikleri = new ArrayList<offer>();

    private ArrayAdapter<String> adapter;
    private ArrayList<String> turCesitleri = new ArrayList<>();
    private ArrayList<String> oneriVarMi = new ArrayList<>();
    private ArrayList<String> offerTipi = new ArrayList<>();
    private ArrayList<String> film = new ArrayList<>();
    private ArrayList<String> kitap = new ArrayList<>();

    private static int offer_id;

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_bilgileri);

        btnGuncelle=findViewById(R.id.btnGuncelle2);
        btniptal=findViewById(R.id.btniptal);
        baslik=(EditText) findViewById(R.id.baslik);
        edtYil=(EditText) findViewById(R.id.edtYil);
        resimEkle =findViewById(R.id.resimEkle);
        registerForContextMenu(resimEkle);
        k_not=findViewById(R.id.knot);

        spinnerOfferTipi=findViewById(R.id.spinnerOfferTipi);
        offerTipi.add("belirsiz");
        offerTipi.add("Kitap");
        offerTipi.add("Film");
        offerTipi.add("Oyun");
        offerTipi.add("Müzik");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,offerTipi);
        spinnerOfferTipi.setAdapter(adapter);

        spinnerTur=findViewById(R.id.spinnerTur);
        turCesitleri.add("belirsiz");
        turCesitleri.add("Bilim Kurgu");
        turCesitleri.add("Macera");
        turCesitleri.add("Korku");
        turCesitleri.add("Biyografi");
        turCesitleri.add("Romantik Komedi");
        turCesitleri.add("Dram");
        turCesitleri.add("Gerilim");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,turCesitleri);
        spinnerTur.setAdapter(adapter);

        spinnerOneri=findViewById(R.id.spinnerOneri);
        oneriVarMi.add("Hayir");
        oneriVarMi.add("Evet");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,oneriVarMi);
        spinnerOneri.setAdapter(adapter);

        spinnerFilm=findViewById(R.id.spinnerfilm);
        film.add("film");
        film.add("ET");
        film.add("Yeşil Yol");
        film.add("My Way");
        film.add("ınception");
        film.add("Harry Potter");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,film);
        spinnerFilm.setAdapter(adapter);

        spinnerKitap=findViewById(R.id.spinnerkitap);
        kitap.add("kitap");
        kitap.add("SEFİLLER");
        kitap.add("İnsan Ne İle Yaşar");
        kitap.add("Karga Meclisi");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,kitap);
        spinnerKitap.setAdapter(adapter);

        spinnerKitap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kitapIndex=position;
                s_kitap=spinnerKitap.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerFilm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filmIndex=position;
                s_film=spinnerFilm.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerTur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                turIndex=position;
                s_tur=spinnerTur.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerOneri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                oneriIndex=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        actionbarOfferBilgileri=(Toolbar)findViewById(R.id.actionbarOfferOzellikleri);
        setSupportActionBar(actionbarOfferBilgileri);
        getSupportActionBar().setTitle("Offer Özellikleri");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent intent = getIntent();
        offer_id=intent.getIntExtra("offerID",1);
        offer offer1 = new offer();
        offer1.setOfferID(offer_id);

        Helper1 =new Helper1(this);
        this.offerozellikleri = (ArrayList<offer>) Helper1.offerOzellikleriListele(offer_id);

        baslik.setText(offerozellikleri.get(0).getBaslik());
        edtYil.setText(offerozellikleri.get(0).getCikis_yili());
        k_not.setText(offerozellikleri.get(0).getK_not());
        spinnerOfferTipi.setSelection(offerozellikleri.get(0).getOffer_tipi());
        spinnerTur.setSelection(offerozellikleri.get(0).getTuru());
        spinnerOneri.setSelection(offerozellikleri.get(0).getOneri_durumu());
        spinnerFilm.setSelection(offerozellikleri.get(0).getA_film());
        spinnerKitap.setSelection(offerozellikleri.get(0).getA_kitap());

        byte[] resimbyte=offerozellikleri.get(0).getResim();
        Bitmap bitmap = BitmapFactory.decodeByteArray(resimbyte,0,resimbyte.length);
        resimEkle.setImageBitmap(bitmap);

        btnGuncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String offerBaslik=baslik.getText().toString();
                String cikisYili=edtYil.getText().toString();
                int offerTipi=offerIndex;
                int tur=turIndex;
                int oneri=oneriIndex;
                int film=filmIndex;
                int kitap=kitapIndex;
                String filmtxt=s_film;
                String kitaptxt=s_kitap;
                String offertipitxt=s_offer_tipi;
                String turtxt=s_tur;
                String knot= k_not.getText().toString();


                KullaniciGirisActivity kullaniciGirisActivity=new KullaniciGirisActivity();
                int kullaniciid = kullaniciGirisActivity.getKullanici_id();


                if(!offerBaslik.equals("") && offerTipi!=0 && tur!=0 && film!=0 && kitap!=0 && !cikisYili.equals("") && !knot.equals("")) {
                    if (farkliresim==0) {
                        if (resimEkle.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.resim_yok4).getConstantState()) {
                            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.resim_yok4);
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
                            byte[] byteArray1 = stream.toByteArray();
                            offer offer = new offer();
                            Helper1.offerGuncelle(offer_id,offer);

                        }
                        else {
                            byte[] byteArray1 = offerozellikleri.get(0).getResim();
                            offer offer = new offer();
                            Helper1.offerGuncelle(offer_id, offer);

                        }
                    }
                    else {

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        resimSecimi.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                        byte[] byteArray2 = outputStream.toByteArray();
                        offer offer = new offer();
                        Helper1.offerGuncelle(offer_id, offer);
                    }

                    Toast.makeText(offer_bilgileri.this, "Kayıt Başarılı", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(offer_bilgileri.this, offers.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }

                else {
                    AlertDialog.Builder alert= Helper2.alertBuilder(offer_bilgileri.this);
                    alert.setMessage("Lütfen boş alanları doldurun.");
                    alert.show();
                }
            }
        });

        btniptal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(offer_bilgileri.this,offers.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }

    int farkliresim=0;
    public void resimSec(View view){

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        else{
            Intent intentGaleri = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intentGaleri.setType("image/*");
            startActivityForResult(intentGaleri,2);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Intent intentGaleri = new Intent(Intent.ACTION_PICK);
                intentGaleri.setType("image/*");
                startActivityForResult(intentGaleri,2);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==2 && resultCode==RESULT_OK){
            imageUri=data.getData();
            try{
                if(Build.VERSION.SDK_INT>=28){
                    ImageDecoder.Source source=ImageDecoder.createSource(this.getContentResolver(),imageUri);
                    resimSecimi= ImageDecoder.decodeBitmap(source);
                    resimEkle.setImageBitmap(resimSecimi);
                    farkliresim++;
                }
                else {
                    resimSecimi= MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    resimEkle.setImageBitmap(resimSecimi);
                    farkliresim++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.profil2_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profil:
                Intent profilIntent = new Intent(offer_bilgileri.this,profil.class);
                startActivity(profilIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.contex_menu2,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        final AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.sil:
                resimEkle.setImageResource(R.drawable.resim_yok4);
                return true;
        }

        return super.onContextItemSelected(item); }
}