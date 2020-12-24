package com.mobiluygulama.app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.view.Menu;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Helper1 extends SQLiteOpenHelper {
    Kullanici kullanici=new Kullanici();

    public Helper1(@Nullable Context context) {
        super(context, "Offer", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS kullanici(kullaniciID INTEGER PRIMARY KEY,ad VARCHAR, soyad VARCHAR, telefon VARCHAR, email VARCHAR, sifre VARCHAR)");
        db.execSQL("CREATE TABLE IF NOT EXISTS offer(offerID INTEGER PRIMARY KEY,kullaniciID INTEGER," +
                "baslik VARCHAR,cikis_yili VARCHAR,fiyati VARCHAR,offer_tipi INTEGER,turu INTEGER," +
                "oneri_durumu INTEGER," +
                "a_film INTEGER,a_kitap INTEGER,filmtxt VARCHAR, kitaptxt VARCHAR,offer_tipitxt VARCHAR, turtxt VARCHAR, k_not VARCHAR,resim BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS kullanici");
        this.onCreate(db);

        db.execSQL("DROP TABLE IF EXISTS offer");
        this.onCreate(db);
    }

    public void kullaniciEkle(Kullanici kullanici){

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad", kullanici.getAd());
        contentValues.put("soyad",kullanici.getSoyad());
        contentValues.put("telefon",kullanici.getTelefon());
        contentValues.put("email",kullanici.getEmail());
        contentValues.put("sifre",kullanici.getSifre());
        database.insert("kullanici",null,contentValues);
        database.close();
    }

    public void offerEkle(offer offer1){

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("baslik",offer1.getBaslik());
        contentValues.put("cikis_yili",offer1.getCikis_yili());
        contentValues.put("fiyati",offer1.getFiyati());
        contentValues.put("offer_tipi",offer1.getOffer_tipi());
        contentValues.put("isitmasi",offer1.getTuru());
        contentValues.put("esya_durumu",offer1.getOneri_durumu());
        contentValues.put("a_ilce",offer1.getA_film());
        contentValues.put("a_il",offer1.getA_kitap());
        contentValues.put("resim",offer1.getResim());
        contentValues.put("kullaniciID",offer1.getKullaniciID());
        contentValues.put("filmtxt",offer1.getFilmtxt());
        contentValues.put("kitaptxt",offer1.getKitaptxt());
        contentValues.put("offer_tipitxt",offer1.getOffer_tipitxt());
        contentValues.put("turtxt",offer1.getTurtxt());
        contentValues.put("k_not",offer1.getK_not());
        database.insert("offer",null,contentValues);
        database.close();
    }

    public ArrayList<Kullanici> kullaniciid(){
        ArrayList<Kullanici> kullanicilar=new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM kullanici",null);
        int kullaniciidIx=cursor.getColumnIndex("kullaniciID");
        int emailIx=cursor.getColumnIndex("email");
        int sifreIx=cursor.getColumnIndex("sifre");
        if(cursor.moveToFirst()){
            do{
                int kullaniciid=cursor.getInt(kullaniciidIx);
                String email=cursor.getString(emailIx);
                String sifre=cursor.getString(sifreIx);
                kullanicilar.add(new Kullanici(kullaniciid,email,sifre));
            }while (cursor.moveToNext());
        }
        return kullanicilar;
    }

    public ArrayList<OfferListview> offerListesi(){
        ArrayList<OfferListview> offers=new ArrayList<>();
        KullaniciGirisActivity kullaniciGirisActivity=new KullaniciGirisActivity();
        int kullanici_id = kullaniciGirisActivity.getKullanici_id();
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM offer WHERE kullaniciID=?",
                new String[] {String.valueOf(kullanici_id)});

        int offeridIx=cursor.getColumnIndex("offerID");
        int baslikIx = cursor.getColumnIndex("baslik");
        int fiyatIx=cursor.getColumnIndex("fiyati");
        int filmIx = cursor.getColumnIndex("filmtxt");
        int kitapIx = cursor.getColumnIndex("kitaptxt");
        int offertipiIx = cursor.getColumnIndex("offer_tipitxt");
        int turIx = cursor.getColumnIndex("turtxt");
        int knotIx = cursor.getColumnIndex("k_not");
        int resimIx = cursor.getColumnIndex("resim");

        if(cursor.moveToFirst()){
            do{
                int offer_id=cursor.getInt(offeridIx);
                String baslik=cursor.getString(baslikIx);
                String fiyat=cursor.getString(fiyatIx);
                String film=cursor.getString(filmIx);
                String kitap=cursor.getString(kitapIx);
                String offertipi=cursor.getString(offertipiIx);
                String tur=cursor.getString(turIx);
                String knot=cursor.getString(knotIx);
                byte[] resimbyte=cursor.getBlob(resimIx);

                offers.add(new OfferListview(offer_id,baslik,fiyat,kitap,film,offertipi,tur,knot,resimbyte));

            }while (cursor.moveToNext());
        }

        return offers;
    }

    public ArrayList<offer> offerOzellikleriListele(int offerid){
        ArrayList<offer> offerOzellikleri=new ArrayList<>();
        KullaniciGirisActivity kullaniciGirisActivity = new KullaniciGirisActivity();
        int kullanici_id=kullaniciGirisActivity.getKullanici_id();
        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM offer WHERE offerID=? AND kullaniciID=?",
                new String[]{String.valueOf(offerid),String.valueOf(kullanici_id)});

        int baslikIx = cursor.getColumnIndex("baslik");
        int cikisYiliIx = cursor.getColumnIndex("cikis_yili");
        int fiyatIx = cursor.getColumnIndex("fiyati");
        int offertipiIx = cursor.getColumnIndex("offer_tipi");
        int turIx = cursor.getColumnIndex("turu");
        int oneriIx = cursor.getColumnIndex("oneri_durumu");
        int afilmIx = cursor.getColumnIndex("a_film");
        int akitapIx = cursor.getColumnIndex("a_kitap");
        int knotIx=cursor.getColumnIndex("k_not");
        int resimIx = cursor.getColumnIndex("resim");

        if(cursor.moveToFirst()){
            do{
                String baslik=cursor.getString(baslikIx);
                String cikisyili=cursor.getString(cikisYiliIx);
                String fiyat=cursor.getString(fiyatIx);
                int offertipi=cursor.getInt(offertipiIx);
                int tur=cursor.getInt(turIx);
                int oneri=cursor.getInt(oneriIx);
                int afilm=cursor.getInt(afilmIx);
                int akitap=cursor.getInt(akitapIx);
                String knot=cursor.getString(knotIx);
                byte[] resimbyte=cursor.getBlob(resimIx);
                offerOzellikleri.add(new offer(kullanici_id,baslik,cikisyili,fiyat,offertipi,tur,oneri
                        ,afilm,akitap,knot,resimbyte));
            }while (cursor.moveToNext());
        }
        return offerOzellikleri;
    }

    public ArrayList<Kullanici> ProfilListele(){
        ArrayList<Kullanici> kullaniciBilgileri=new ArrayList<>();
        KullaniciGirisActivity kullaniciGirisActivity = new KullaniciGirisActivity();
        int kullanici_id=kullaniciGirisActivity.getKullanici_id();
        SQLiteDatabase database =this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM kullanici WHERE kullaniciID=?",
                new String[]{String.valueOf(kullanici_id)});

        int adIx = cursor.getColumnIndex("ad");
        int soyadIx = cursor.getColumnIndex("soyad");
        int telefonIx = cursor.getColumnIndex("telefon");
        int emailIx = cursor.getColumnIndex("email");
        int sifreIx = cursor.getColumnIndex("sifre");

        if(cursor.moveToFirst()){
            do{
                String ad=cursor.getString(adIx);
                String soyad=cursor.getString(soyadIx);
                String telefon=cursor.getString(telefonIx);
                String email=cursor.getString(emailIx);
                String sifre=cursor.getString(sifreIx);
                kullaniciBilgileri.add(new Kullanici(ad,soyad,telefon,email,sifre));

            }while (cursor.moveToNext());
        }
        return kullaniciBilgileri;
    }

    public void offerSil(int offerid){
        KullaniciGirisActivity kullaniciGirisActivity=new KullaniciGirisActivity();
        int kullaniciid=kullaniciGirisActivity.getKullanici_id();
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("mulk","mulkID"+" = ? "+ "AND "+"kullaniciID" + " = ?",
                new String[] {String.valueOf(offerid),String.valueOf(kullaniciid)});
        database.close();
    }

    public int offerGuncelle(int offerid,offer offer1){

        KullaniciGirisActivity kullaniciGirisActivity=new KullaniciGirisActivity();
        int kullaniciid=kullaniciGirisActivity.getKullanici_id();

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("baslik",offer1.getBaslik());
        contentValues.put("cikis_yili",offer1.getCikis_yili());
        contentValues.put("fiyati",offer1.getFiyati());
        contentValues.put("offer_tipi",offer1.getOffer_tipi());
        contentValues.put("turu",offer1.getTuru());
        contentValues.put("oneri_durumu",offer1.getOneri_durumu());
        contentValues.put("a_film",offer1.getA_film());
        contentValues.put("a_kitap",offer1.getA_kitap());
        contentValues.put("resim",offer1.getResim());
        contentValues.put("kullaniciID",offer1.getKullaniciID());
        contentValues.put("filmtxt",offer1.getFilmtxt());
        contentValues.put("kitaptxt",offer1.getKitaptxt());
        contentValues.put("offer_tipitxt",offer1.getOffer_tipitxt());
        contentValues.put("turtxt",offer1.getTurtxt());
        contentValues.put("k_not",offer1.getK_not());
        int i=database.update("offer",contentValues,"offerID"+" = ?"+" AND "+ "kullaniciID"+" = ?",
                new String[]{String.valueOf(offerid),String.valueOf(kullaniciid)});
        database.close();
        return i;
    }

    public void kullaniciSil(int kullaniciid){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete("offer","kullaniciID" + " = ?",new String[] {String.valueOf(kullaniciid)});
        database.delete("kullanici","kullaniciID" + " = ?",new String[] {String.valueOf(kullaniciid)});
        database.close();
    }

    public int kullaniciGuncelle(Kullanici kullanici){
        KullaniciGirisActivity kullaniciGirisActivity=new KullaniciGirisActivity();
        int kullaniciid=kullaniciGirisActivity.getKullanici_id();

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("ad",kullanici.getAd());
        contentValues.put("soyad",kullanici.getSoyad());
        contentValues.put("telefon",kullanici.getTelefon());
        contentValues.put("email",kullanici.getEmail());
        contentValues.put("sifre",kullanici.getSifre());

        int i=database.update("kullanici",contentValues,"kullaniciID"+" = ?",
                new String[]{String.valueOf(kullaniciid)});
        database.close();
        return i;
    }
}