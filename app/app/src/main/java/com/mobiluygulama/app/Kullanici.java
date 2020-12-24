package com.mobiluygulama.app;


public class Kullanici {
    private int kullaniciID;
    private String ad;
    private String soyad;
    private String telefon;
    private String email;
    private String sifre;

    public Kullanici() {

    }

    public Kullanici(int kullaniciID, String email, String sifre) {
        this.kullaniciID = kullaniciID;
        this.email=email;
        this.sifre=sifre;
    }

    public Kullanici(String ad, String soyad, String telefon, String email, String sifre) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
        this.email = email;
        this.sifre = sifre;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }
    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getTelefon() {
        return telefon;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSifre() {
        return sifre;
    }
    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}

