/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import java.util.ArrayList;

/**
 *
 * @author İREM SAMUR
 */
public interface InterfaceResepsiyonist {
    //Burada resepsiyonist için interface oluşturduk. Ve bu interface içinde gövdesiz methotlar tanımladık
    String hastaKaydet(String tcno, String parola, String ad,String soyad, String mail, String cepTel, String adres, String cinsiyet,String dogumTarihi,String yas);
    boolean randevuOlustur2(int doktor_id,int hasta_id,String doktor_adi,String doktor_soyadi,String doktor_unvani,String hasta_adi,String hasta_soyadi,String tcKimlik,String yas,String randevu_tarihi);
    boolean calismaZamaniniGuncelle2(int doktor_id,String calisma_zamani);
    ArrayList<User> hastaBilgileriGetir(String tcNo);
    
}
