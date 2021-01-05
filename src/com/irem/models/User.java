/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import com.irem.veritabani.VeriTabaniBaglanti;
import com.irem.veritabani.YardimciMethotlar;

/**
 *
 * @author İREM SAMUR
 */
public class User {
    //Üst classımızdır . Tüm alt classların özelliklerini içerir . Alt classlara miras verir
    private int id;//Değişkenler nesne yönelimli programlama gereği private olarak , encapsulation kuralına göre tanımlanır
    private String tcno,ad,soyad,parola,tur,mail_adresi,ceptel_no,adres,cinsiyet,unvan,dogumTarihi,yas;
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();//Veritabanı'nı  tüm alt classlara veritabanına bağlanmasını sağlaması için burada tanımladık

    //1.constructor . Ortak özellikler
    public User(int id, String ad, String soyad, String tcno,String parola, String tur,String mail_adresi,String ceptel_no,String adres,String cinsiyet) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad=soyad;
        this.parola = parola;
        this.tur = tur;
        this.mail_adresi=mail_adresi;
        this.ceptel_no = ceptel_no;
        this.adres=adres;
        this.cinsiyet=cinsiyet;
        
    }
    //2.constructor hastaya ait özellikleride içerir
     public User(int id, String ad, String soyad, String tcno,String parola, String tur,String mail_adresi,String ceptel_no,String adres,String cinsiyet,String dogumTarihi,String yas) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad=soyad;
        this.parola = parola;
        this.tur = tur;
        this.mail_adresi=mail_adresi;
        this.ceptel_no = ceptel_no;
        this.adres=adres;
        this.cinsiyet=cinsiyet;
        this.dogumTarihi=dogumTarihi;
        this.yas = yas;
        
    }
     //3.constructor doktor ve başhekime ait özellikler içerir
    public User(int id, String ad, String soyad, String tcno,String parola, String tur,String mail_adresi,String ceptel_no,String adres,String cinsiyet,String unvan) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad=soyad;
        this.parola = parola;
        this.tur = tur;
        this.mail_adresi=mail_adresi;
        this.ceptel_no = ceptel_no;
        this.adres=adres;
        this.cinsiyet=cinsiyet;
        this.unvan=unvan;
    }
    public User(int id, String ad, String soyad, String tcno,String parola, String tur) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad=soyad;
        this.parola = parola;
        this.tur = tur;
        
    }
     public User(int id, String ad, String soyad, String tcno,String parola, String tur,String unvan) {
        this.id = id;
        this.tcno = tcno;
        this.ad = ad;
        this.soyad=soyad;
        this.parola = parola;
        this.tur = tur;
        this.unvan=unvan;
        
    }
     public User(int id,String ad,String soyad,String hastaTc,String yas){
         this.id=id;
         this.ad=ad;
         this.soyad=soyad;
         this.tcno = hastaTc;
         this.yas=yas;
     }
    public User(){
        
    }
    //Burada farklı kullanıcılar için farklı parametreler alan birden fazla constructor yazarak override yaptık

    //Encapsulation- get- set methotları kullanımı
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTcno() {
        return tcno;
    }

    public void setTcno(String tcno) {
        this.tcno = tcno;
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

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public VeriTabaniBaglanti getConn() {
        return conn;
    }

    public void setConn(VeriTabaniBaglanti conn) {
        this.conn = conn;
    }

    public String getMail_adresi() {
        return mail_adresi;
    }

    public void setMail_adresi(String mail_adresi) {
        this.mail_adresi = mail_adresi;
    }

    public String getCeptel_no() {
        return ceptel_no;
    }

    public void setCeptel_no(String ceptel_no) {
        this.ceptel_no = ceptel_no;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }

    public String getDogumTarihi() {
        return dogumTarihi;
    }

    public void setDogumTarihi(String dogumTarihi) {
        this.dogumTarihi = dogumTarihi;
    }

    public String getYas() {
        return yas;
    }

    public void setYas(String yas) {
        this.yas = yas;
    }
    
    public void girisMesajiYaz(String ad){
        YardimciMethotlar.mesajGoster("Sistemimize Hoşgeldiniz Sayın , "+ad);
    }
    
    
}
