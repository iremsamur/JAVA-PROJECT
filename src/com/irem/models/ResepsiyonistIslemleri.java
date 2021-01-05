/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import com.irem.veritabani.VeriTabaniBaglanti;
import com.irem.veritabani.YardimciMethotlar;
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
public class ResepsiyonistIslemleri implements InterfaceResepsiyonist {//InterfaceResepsiyonist interface'îni
    //implement eder. Böylece interface içindeki gövdesiz methotların gövdeleri,işlemleri burada yazılır
    //Resepsiyonistin yapabileceği işlemlerin methotlarını içerir
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();
    Connection con = conn.conDb();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    @Override
    public String hastaKaydet(String tcno, String parola, String ad,String soyad, String mail, String cepTel, String adres, String cinsiyet,String dogumTarihi,String yas) {
        
        //interface ile bu methodu implement ederiz
       int key=0;
       String durum="yok";
       String sorgu = "INSERT INTO user (tcno,parola,ad,soyad,kullanici_turu,mail_adresi,ceptel_no,adres,cinsiyet,dogumTarihi,hasta_yasi) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
        try {
            statement = con.createStatement();
             
            
            
            rs = statement.executeQuery("SELECT * FROM user WHERE tcno ='"+tcno+"'");
            
            while(rs.next()){
                durum="var";
                YardimciMethotlar.mesajGoster("Bu tc kimlik numarasına ait bir kayıt bulunmaktadır!!");
                key=-1;
               
                break;
            }

            if(durum.equals("yok")){
                 preparedStatement = con.prepareStatement(sorgu);
                 preparedStatement.setString(1, tcno);
                 preparedStatement.setString(2,parola);
                 preparedStatement.setString(3,ad);
                 preparedStatement.setString(4,soyad);
                 preparedStatement.setString(5,"hasta");
                 preparedStatement.setString(6,mail);
                 preparedStatement.setString(7,cepTel);
                 preparedStatement.setString(8,adres);
                 preparedStatement.setString(9,cinsiyet);
                 preparedStatement.setString(10,dogumTarihi);
                 preparedStatement.setString(11,yas);
                 preparedStatement.executeUpdate();
                 key=1;
                 
                
            }
            else if(durum.equals("var")){
                key =0;
            }
            else{
                key=-1;
            }
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonistIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return "true";
        else if(key==0){
            return "var";
        }
        else{
            return "false";
        }
            
            
        
    }
    @Override
     public ArrayList<User> hastaBilgileriGetir(String tcNo){
         ArrayList<User> cikti = new ArrayList<User>();
          try {
            
            statement = con.createStatement();//Veritabanından bilgi alabilmek için statement oluşturduk
            
            ResultSet rs = statement.executeQuery("SELECT id,ad,soyad,tcno,hasta_yasi FROM user WHERE tcno ='"+tcNo+"'");//Bu sorguyu ResultSet e dönen sonuçları atarız
            while(rs.next()){
                int id = rs.getInt("id");//getInt ile veritabanından id leri alır id ye atar
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                String hastaTc = rs.getString("tcno");
                String yas = rs.getString("hasta_yasi");
                
                cikti.add(new User(id,ad,soyad,hastaTc,yas));//Tüm id ad soyad gibi bilgileri add ile cikti arraylistine ekledim
                //Her yeni user bu cikti ya eklenir
                
                
            }
            return cikti;
            
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonistIslemleri.class.getName()).log(Level.SEVERE, null, ex);
            return null;//Hatayı gidermek için null döndüm
        }
        
    }
    @Override
    public boolean randevuOlustur2(int doktor_id,int hasta_id,String doktor_adi,String doktor_soyadi,String doktor_unvani,String hasta_adi,String hasta_soyadi,String tcKimlik,String yas,String randevu_tarihi){
       int key=0;
       //randevuları bu methot ile ekleriz
       
       String sorgu = "INSERT INTO randevular (doktor_id,doktor_adi,doktor_soyadi,unvan,hasta_id,hasta_adi,hasta_soyadi,hastaTc,hasta_yasi,randevu_tarihi) VALUES (?,?,?,?,?,?,?,?,?,?)";
    
        try {
             
            
            
                
           
            
                 preparedStatement = con.prepareStatement(sorgu);
                 preparedStatement.setInt(1, doktor_id);
                 preparedStatement.setString(2,doktor_adi);
                 preparedStatement.setString(3,doktor_soyadi);
                 preparedStatement.setString(4,doktor_unvani);
                 preparedStatement.setInt(5, hasta_id);
                 preparedStatement.setString(6,hasta_adi);
                 preparedStatement.setString(7,hasta_soyadi);
                 preparedStatement.setString(8,tcKimlik);
                 preparedStatement.setString(9,yas);
                 
                 preparedStatement.setString(10,randevu_tarihi);
                 
                 
                 preparedStatement.executeUpdate();
                 key=1;
                
                 
                
            
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonistIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return true;
        else
            return false;
            
        
       
   }
       //Hasta bir randevu tarihi seçince o seçili tarihi aktiften pasif yapmak için şu methodu yazarız
    @Override
      public boolean calismaZamaniniGuncelle2(int doktor_id,String calisma_zamani){
       int key=0;
       //alınan randevuya göre calisma_saatleri tablosunun müsaitlik durumu sütununu günceller 
       //randevu alındığında p yani pasif bir daha alınamaz yapar.
       
       String sorgu = "UPDATE calisma_saatleri SET musaitlik_durumu = ? WHERE doktor_id=? AND calisma_zamani =?";//Burada hasta tarafından gönderilen seçtiği
       //doktora ve tarihe göre seçili tarihin aktifliğinin kaybedilmesi böylece bir daha seçilememesi sağlanır
       
    
        try {
             
            
            
                
           
            
                 preparedStatement = con.prepareStatement(sorgu);
                 preparedStatement.setString(1, "p");
                 preparedStatement.setInt(2,doktor_id);
                 preparedStatement.setString(3, calisma_zamani);
                
                 
                 
                 preparedStatement.executeUpdate();
                 key=1;
                
                 
                
            
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(ResepsiyonistIslemleri.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return true;
        else
            return false;
            
        
       
   }
    
}
