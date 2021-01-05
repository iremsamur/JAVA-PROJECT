/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.veritabani;

/**
 *
 * @author İREM SAMUR
 */
public class VeriTabaniBilgileri {
    //Veritabanına yani mysql e erişim için gerekli kullanici_adi ve parola bilgilerini static -final olarak tutar
    //Çünkü bu bilgiler değişmez bilgilerdir. Bu yüzden static olur
     //Bu bilgiler static ve final tanımlanmalı ki başka yerde değiştirilmesin.
    public static final String kullanici_adi="root";//Her yerden ulaşabilelim ama bilgiler değiştirilemesin diye static final yaparız.
    public static final String  parola = "apple46";
    
}
