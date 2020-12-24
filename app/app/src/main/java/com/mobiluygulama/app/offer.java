package com.mobiluygulama.app;

import java.io.ByteArrayOutputStream;

public class offer {
    private int offerID;
    private int kullaniciID;
    private String baslik;
    private String cikis_yili;
    private String fiyati;
    private int offer_tipi;
    private int turu;
    private int oneri_durumu;
    private int a_film;
    private int a_kitap;
    private String filmtxt;
    private String kitaptxt;
    private String offer_tipitxt;
    private String turtxt;
    private String k_not;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] resim = outputStream.toByteArray();

    public offer(int kullaniciID, String baslik, String cikis_yili, String fiyati, int offer_tipi,
                int turu, int oneri_durumu, int a_film, int a_kitap,
                String k_not, byte[] resim) {
        this.kullaniciID = kullaniciID;
        this.baslik = baslik;
        this.cikis_yili = cikis_yili;
        this.fiyati = fiyati;
        this.offer_tipi = offer_tipi;
        this.turu = turu;
        this.oneri_durumu = oneri_durumu;
        this.a_film = a_film;
        this.a_kitap = a_kitap;
        this.k_not = k_not;
        this.resim = resim;
    }

    public offer() {

    }

    public offer(int kullaniciID, String baslik, String cikis_yili, String fiyati, int offer_tipi,
                int turu, int oneri_durumu, int a_film, int a_kitap,
                String filmtxt, String kitaptxt,String offer_tipitxt,String turtxt,String k_not, byte[] resim) {
        this.kullaniciID = kullaniciID;
        this.baslik = baslik;
        this.cikis_yili = cikis_yili;
        this.fiyati = fiyati;
        this.offer_tipi = offer_tipi;
        this.turu = turu;
        this.oneri_durumu = oneri_durumu;
        this.a_film = a_film;
        this.a_kitap = a_kitap;
        this.filmtxt=filmtxt;
        this.kitaptxt=kitaptxt;
        this.offer_tipitxt=offer_tipitxt;
        this.turtxt=turtxt;
        this.k_not=k_not;
        this.resim = resim;
    }


    public String getOffer_tipitxt() {
        return offer_tipitxt;
    }
    public void setOffer_tipitxt(String offer_tipitxt) {
        this.offer_tipitxt = offer_tipitxt;
    }

    public String getTurtxt() {
        return turtxt;
    }
    public void setTurtxt(String turtxt) {
        this.turtxt= turtxt;
    }

    public String getK_not() {
        return k_not;
    }
    public void setK_not(String k_not) {
        this.k_not = k_not;
    }

    public String getFilmtxt() {
        return filmtxt;
    }
    public void setFilmtxt(String filmtxt) {
        this.filmtxt = filmtxt;
    }

    public String getKitaptxt() {
        return kitaptxt;
    }
    public void setKitaptxt(String kitaptxt) {
        this.kitaptxt = kitaptxt;
    }

    public int getOfferID() {
        return offerID;
    }
    public void setOfferID(int offerID) {
        this.offerID = offerID;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }
    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getBaslik() {
        return baslik;
    }
    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getCikis_yili() {
        return cikis_yili;
    }
    public void setCikis_yili(String cikis_yili) {
        this.cikis_yili = cikis_yili;
    }

    public String getFiyati() {
        return fiyati;
    }
    public void setFiyati(String fiyati) {
        this.fiyati = fiyati;
    }

    public int getOffer_tipi() {
        return offer_tipi;
    }
    public void setOffer_tipi(int offer_tipi) {
        this.offer_tipi = offer_tipi;
    }

    public int getTuru() {
        return turu;
    }
    public void setTuru(int turu) {
        this.turu = turu;
    }

    public int getOneri_durumu() {
        return oneri_durumu;
    }
    public void setOneri_durumu(int oneri_durumu) {
        this.oneri_durumu = oneri_durumu;
    }

    public int getA_film() {
        return a_film;
    }
    public void setA_film(int a_film) {
        this.a_film = a_film;
    }

    public int getA_kitap() {
        return a_kitap;
    }
    public void setA_kitap(int a_kitap) {
        this.a_kitap = a_kitap;
    }

    public byte[] getResim() {
        return resim;
    }
    public void setResim(byte[] resim) {
        this.resim = resim;
    }
}
