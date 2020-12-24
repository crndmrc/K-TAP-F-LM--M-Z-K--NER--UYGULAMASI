package com.mobiluygulama.app;

import java.io.ByteArrayOutputStream;

public class OfferListview {
    private int offerid;
    private String baslik;
    private String fiyati;
    private String kitaptxt;
    private String filmtxt;
    private String offer_tipitxt;
    private String turtxt;
    private String k_not;
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    byte[] resimId = outputStream.toByteArray();

    public int getOfferid() {
        return offerid;
    }

    public void setOfferid(int offerid) {
        this.offerid = offerid;
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

    public void setTurtxt(String isitmatxt) {
        this.turtxt = turtxt;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getFiyati() {
        return fiyati;
    }

    public void setFiyati(String fiyati) {
        this.fiyati = fiyati;
    }

    public String getKitaptxt() {
        return kitaptxt;
    }

    public void setKitaptxt(String kitaptxt) {
        this.kitaptxt = kitaptxt;
    }

    public String getFilmtxt() {
        return filmtxt;
    }

    public void setFilmtxt(String filmtxt) {
        this.filmtxt = filmtxt;
    }

    public String getK_not() {
        return k_not;
    }

    public void setK_not(String k_not) {
        this.k_not = k_not;
    }

    public byte[] getResimId() {
        return resimId;
    }

    public void setResimId(byte[] resimId) {
        this.resimId = resimId;
    }

    public OfferListview(int offerid,String baslik, String fiyati, String kitaptxt, String filmtxt,
                         String offer_tipitxt,String turtxt, String acik_adres, byte[] resimId) {

        this.offerid=offerid;
        this.baslik = baslik;
        this.fiyati = fiyati;
        this.kitaptxt = kitaptxt;
        this.filmtxt = filmtxt;
        this.offer_tipitxt=offer_tipitxt;
        this.turtxt=turtxt;
        this.k_not = acik_adres;
        this.resimId = resimId;
    }
}
