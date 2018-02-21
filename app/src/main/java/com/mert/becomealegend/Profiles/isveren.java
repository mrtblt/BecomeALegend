package com.mert.becomealegend.Profiles;

/**
 * Created by EsrefTurkok on 16.02.2018.
 */

public class isveren {
    private String isverenIsim;
    private String isverenSoyisim;
    private String isverenEmail;
    private String isverenSirketIsim;
    private String sirketAciklamasi;
    private String arananElemanAciklama;


    private isveren(){
        this.isverenIsim = isverenIsim;
        this.isverenSoyisim = isverenSoyisim;
        this.isverenEmail = isverenEmail;
        this.isverenSirketIsim = isverenSirketIsim;
        this.sirketAciklamasi = sirketAciklamasi;
    }

    public isveren(String isverenIsim, String isverenSoyisim, String isverenEmail, String isverenSirketIsim, String sirketAciklamasi) {
        this.isverenIsim = isverenIsim;
        this.isverenSoyisim = isverenSoyisim;
        this.isverenEmail = isverenEmail;
        this.isverenSirketIsim = isverenSirketIsim;
        this.sirketAciklamasi = sirketAciklamasi;
    }

    public String getIsverenIsim() {
        return isverenIsim;
    }

    public void setIsverenIsim(String isverenIsim) {
        this.isverenIsim = isverenIsim;
    }

    public String getIsverenSoyisim() {
        return isverenSoyisim;
    }

    public void setIsverenSoyisim(String isverenSoyisim) {
        this.isverenSoyisim = isverenSoyisim;
    }

    public String getIsverenEmail() {
        return isverenEmail;
    }

    public void setIsverenEmail(String isverenEmail) {
        this.isverenEmail = isverenEmail;
    }

    public String getIsverenSirketIsim() {
        return isverenSirketIsim;
    }

    public void setIsverenSirketIsim(String isverenSirketIsim) {
        this.isverenSirketIsim = isverenSirketIsim;
    }

    public String getSirketAciklamasi() {
        return sirketAciklamasi;
    }

    public void setSirketAciklamasi(String sirketAciklamasi) {
        this.sirketAciklamasi = sirketAciklamasi;
    }

    public String getArananElemanAciklama() {
        return arananElemanAciklama;
    }

    public void setArananElemanAciklama(String arananElemanAciklama) {
        this.arananElemanAciklama = arananElemanAciklama;
    }
}
