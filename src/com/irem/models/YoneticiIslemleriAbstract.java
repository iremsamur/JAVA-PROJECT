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
public abstract class YoneticiIslemleriAbstract {
    //Abstract bir class oluşturduk ve Bu abstract class içinde gövdesiz abstract methotlar tanımladık
    abstract boolean bashekimEkle(String tcNo, String parola, String ad,String soyad,String mail,String telNo,String adres,String cinsiyet,String unvan);
    abstract ArrayList<User> BashekimBilgileriGetir();
    //Abstract class içinde gövdesiz method oluşturduk
    abstract boolean bashekimGuncelle(String yeniAd,String yeniSoyad,String yeniTcNo,String yeniParola,String yeniMail,String yeniCepTel,String yeniAdres,String yeniUnvan);
    abstract boolean bilgiGuncelle(String yeniAd,String yeniSoyad,String yeniTcNo,String yeniParola,String yeniMail,String yeniCepTel,String yeniAdres,int id);
    abstract boolean bashekimDegistir(String ad,String soyad,String tcno,String sifre,String mail,String cepTel,String adres,String cinsiyet,String unvan);
    abstract boolean resepsiyonistEkle(String tcNo, String parola, String ad,String soyad,String mail,String telNo,String adres,String cinsiyet);
}
