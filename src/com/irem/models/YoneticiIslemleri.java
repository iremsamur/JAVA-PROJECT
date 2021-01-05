/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import com.irem.veritabani.VeriTabaniBaglanti;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author İREM SAMUR
 */
public class YoneticiIslemleri extends YoneticiIslemleriAbstract {//Bu class YoneticiIslemlerAbstract classını extends eder
    //Böylece bu abstract classdaki gövdesiz methotların gövdeleri burada yazılarak.  Bu class içindeki methotlar işlemlerde kullanılır
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();
    Connection con = conn.conDb();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    //Abstract class dan override edilen methotlar
    public boolean bashekimEkle(String tcNo, String parola, String ad,String soyad, String mail, String cepTelNo, String adres, String cinsiyet, String unvan) {
        
        String sorgu = "INSERT INTO user (ad,soyad,tcno,parola,kullanici_turu,mail_adresi,ceptel_no,adres,cinsiyet,unvan) VALUES (?,?,?,?,?,?,?,?,?,?)";
        boolean durum =false;//durum ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, tcNo);
            preparedStatement.setString(4, parola);
            preparedStatement.setString(5, "bashekim");
            preparedStatement.setString(6, mail);
            preparedStatement.setString(7, cepTelNo);
            preparedStatement.setString(8, adres);
            preparedStatement.setString(9, cinsiyet);
            preparedStatement.setString(10, unvan);
          
            preparedStatement.executeUpdate();
            durum=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(durum)
            return true;
        
        else
            return false;
    }

    @Override
    public ArrayList<User> BashekimBilgileriGetir() {
        ArrayList<User> liste = new ArrayList<>();
        User object;
        String sorgu = "SELECT * FROM user WHERE kullanici_turu = 'bashekim'";//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı bashekim olanları bize getirir.
            //Ve bu gelen tüm bashekimler rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new User(rs.getInt("id"),rs.getString("ad"),rs.getString("soyad"),rs.getString("tcno"),rs.getString("parola"),rs.getString("kullanici_turu"),rs.getString("mail_adresi"),rs.getString("ceptel_no"),rs.getString("adres"),rs.getString("cinsiyet"),rs.getString("unvan"));
                //Veritabanından çektiğim tüm bashekim bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return liste;
        
    }

    @Override
    //Bu methot başka bir classdan ovveride edildi
    public boolean bashekimGuncelle(String yeniAd, String yeniSoyad, String yeniTcNo, String yeniParola, String yeniMail, String yeniCepTel, String yeniAdres, String yeniUnvan) {
        
        String sorgu = "Update user set   ad = ? ,soyad=?, tcno =?,parola = ?, mail_adresi=?,ceptel_no=?,adres=?,unvan=?  where kullanici_turu = 'bashekim'";
         boolean key=false;
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniAd);
            preparedStatement.setString(2, yeniSoyad);
            
            preparedStatement.setString(3, yeniTcNo);
            preparedStatement.setString(4, yeniParola);
            preparedStatement.setString(5, yeniMail);
            preparedStatement.setString(6, yeniCepTel);
            preparedStatement.setString(7, yeniAdres);
         
            preparedStatement.setString(8, yeniUnvan);
                
         
            
            preparedStatement.executeUpdate();
            key=true;
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key){
            return true;
        }
        else{
            return false;
        }
         
    }

    @Override
    public boolean bilgiGuncelle(String yeniAd, String yeniSoyad, String yeniTcNo, String yeniParola, String yeniMail, String yeniCepTel, String yeniAdres,int id) {
        
        String sorgu = "Update user set   ad = ? ,soyad = ?, tcno =?,parola = ? ,mail_adresi =?,ceptel_no=?,adres=? where id = ?";
            boolean key=false;
            try {
               preparedStatement = con.prepareStatement(sorgu);
            
               preparedStatement.setString(1, yeniAd);
            
               preparedStatement.setString(2, yeniSoyad);
               preparedStatement.setString(3, yeniTcNo);
               preparedStatement.setString(4, yeniParola);
               preparedStatement.setString(5, yeniMail);
               preparedStatement.setString(6, yeniCepTel);
               preparedStatement.setString(7, yeniAdres);
               
         
               preparedStatement.setInt(8, id);
               preparedStatement.executeUpdate();
               key=true;
        }   catch (SQLException ex) {
               Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
    }

    @Override
    public boolean bashekimDegistir(String ad, String soyad, String tcno, String sifre, String mail, String cepTel, String adres, String cinsiyet, String unvan) {
        String sorgu = "Update user set   ad = ? ,soyad=?, tcno =?,parola = ?, mail_adresi=?,ceptel_no=?,adres=?,cinsiyet=?,unvan=?  where kullanici_turu = 'bashekim'";
         boolean key=false;
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            
            preparedStatement.setString(3, tcno);
            preparedStatement.setString(4, sifre);
            preparedStatement.setString(5, mail);
            preparedStatement.setString(6, cepTel);
            preparedStatement.setString(7, adres);
            preparedStatement.setString(8, cinsiyet);
            
         
            preparedStatement.setString(9, unvan);
                
         
            
            preparedStatement.executeUpdate();
            key=true;
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key){
            return true;
        }
        else{
            return false;
        }
         
        
    }

    @Override
    public boolean resepsiyonistEkle(String tcNo, String parola, String ad, String soyad, String mail, String telNo, String adres, String cinsiyet) {
        
        String sorgu = "INSERT INTO user (ad,soyad,tcno,parola,kullanici_turu,mail_adresi,ceptel_no,adres,cinsiyet) VALUES (?,?,?,?,?,?,?,?,?)";
        boolean durum =false;//durum ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, tcNo);
            preparedStatement.setString(4, parola);
            preparedStatement.setString(5, "resepsiyonist");
            preparedStatement.setString(6, mail);
            preparedStatement.setString(7, telNo);
            preparedStatement.setString(8, adres);
            preparedStatement.setString(9, cinsiyet);
           
          
            preparedStatement.executeUpdate();
            durum=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(YoneticiIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(durum)
            return true;
        
        else
            return false;
        
    }
    
    

}