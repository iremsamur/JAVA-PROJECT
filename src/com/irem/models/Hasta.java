/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author İREM SAMUR
 */
public class Hasta extends User{
    Connection con = conn.conDb();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public Hasta(int id, String ad, String soyad, String tcno, String parola, String tur,String mail,String cepTelNo,String adres,String cinsiyet,String dogumTarihi,String yas) {
        super(id, ad, soyad, tcno, parola, tur,mail,cepTelNo,adres,cinsiyet,dogumTarihi,yas);
    }
    public Hasta(){
        
    }
    
     public String kaydol(String tcno,String parola,String ad,String soyad,String hastaMail,String hastaCepTel,String hastaAdres,String cinsiyet,String dogumTarihi,String yas){
       //hasta kayıt işlemini gerçekleştirir
         int key=0;
       String durum = "yok";
       String sorgu = "INSERT INTO user (tcno,parola,ad,soyad,kullanici_turu,mail_adresi,ceptel_no,adres,cinsiyet,dogumTarihi,hasta_yasi) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
        try {
             statement = con.createStatement();
            
            
            rs = statement.executeQuery("SELECT * FROM user WHERE tcno ='"+tcno+"'");
            while(rs.next()){
                durum="var";
                
               
                break;
            }
            if(durum.equals("yok")){
                 preparedStatement = con.prepareStatement(sorgu);
                 preparedStatement.setString(1, tcno);
                 preparedStatement.setString(2,parola);
                 preparedStatement.setString(3,ad);
                 preparedStatement.setString(4,soyad);
                 preparedStatement.setString(5,"hasta");
                 preparedStatement.setString(6,hastaMail);
                 preparedStatement.setString(7,hastaCepTel);
                 preparedStatement.setString(8,hastaAdres);
                 preparedStatement.setString(9,cinsiyet);
                 preparedStatement.setString(10,dogumTarihi);
                 preparedStatement.setString(11,yas);
                     
                 
                 
                 preparedStatement.executeUpdate();
                 key=1;
                 
                
            }
            else if(durum=="var"){
                key=0;
               
            }
            else{
                key=-1;
            }
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return "true";
        else if(key==0)
            
            return "var";
        
        else
            
            return "false";
            
        
       
   }
     
       public boolean randevuOlustur(int doktor_id,int hasta_id,String doktor_adi,String doktor_soyadi,String doktor_unvani,String hasta_adi,String hasta_soyadi,String tcNo,String yas,String randevu_tarihi){
       int key=0;
       // oluşturulan randevuları bu methot ile ekleriz
       
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
                 preparedStatement.setString(8,tcNo);
                 preparedStatement.setString(9,yas);
                 
                 preparedStatement.setString(10,randevu_tarihi);
                 
                 
                 preparedStatement.executeUpdate();
                 key=1;
                
                 
                
            
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return true;
        else
            return false;
            
        
       
   }
       //Hasta bir randevu tarihi seçince o seçili tarihi aktiften pasif yapmak için şu methodu yazarız
      public boolean calismaZamaniniGuncelle(int doktor_id,String calisma_zamani){
       int key=0;
       //randevuları bu methot ile ekleriz
       
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
            Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return true;
        else
            return false;
            
        
       
   }
      public boolean randevuIptalEt(int id){
         //id'ye göre randevu silme işlemi yapar
        String sorgu = "DELETE FROM randevular where id =?";
        boolean key =false;//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key)
            return true;
        
        else
            return false;
        
    }
      public boolean calismaZamaniniGuncelle2(int doktor_id,String calisma_zamani){
       int key=0;
       //randevuları bu methot ile ekleriz
       
       String sorgu = "UPDATE calisma_saatleri SET musaitlik_durumu = ? WHERE doktor_id=? AND calisma_zamani =?";//Burada hasta tarafından gönderilen seçtiği
       //doktora ve tarihe göre seçili tarihin , randevu iptali olduğu için geri a yapılması sağlanır böylece tekrar seçilebilmesi sağlanır
       
    
        try {
             
            
            
                
           
            
                 preparedStatement = con.prepareStatement(sorgu);
                 preparedStatement.setString(1, "a");
                 preparedStatement.setInt(2,doktor_id);
                 preparedStatement.setString(3, calisma_zamani);
                
                 
                 
                 preparedStatement.executeUpdate();
                 key=1;
                
                 
                
            
           
            
        
            
      

            
            
        } catch (SQLException ex) {
            Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return true;
        else
            return false;
            
        
       
   }
      public String hastaParolaGetir(int id){
            String sorgu = "Select parola  from user where id ="+id;
            String parola="";
                    
            try {
                statement = con.createStatement();
                rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.
                while(rs.next()){
                   parola = rs.getString("parola");
                
            }
        } catch (SQLException ex) {
                Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
            return parola;
             
            
            
        }
           public boolean hastaBilgileriniGuncelle(int id,String yeniAd,String yeniSoyad,String yeniTc,String yeniParola,String yeniMail,String yeniCepTel,String yeniAdres){
            String sorgu = "Update user set   ad = ? ,soyad = ?, tcno =?,parola = ?,mail_adresi=?,ceptel_no=?,adres=?  where id = ?";
            boolean key=false;
            try {
               preparedStatement = con.prepareStatement(sorgu);
            
               preparedStatement.setString(1, yeniAd);
            
               preparedStatement.setString(2, yeniSoyad);
               preparedStatement.setString(3, yeniTc);
               preparedStatement.setString(4, yeniParola);
               preparedStatement.setString(5, yeniMail);
               preparedStatement.setString(6, yeniCepTel);
               preparedStatement.setString(7, yeniAdres);
               
               
               
               
         
               preparedStatement.setInt(8, id);
               preparedStatement.executeUpdate();
               key=true;
        }   catch (SQLException ex) {
               Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
            
        }
            public boolean hastaGeciciSifreDegisimi(String hastatc,String yeniParola){
            String sorgu = "Update user set   parola =?  where tcno = ?";
            boolean key=false;
            try {
               preparedStatement = con.prepareStatement(sorgu);
            
               preparedStatement.setString(1, yeniParola);
            
               
               
               
               
               
         
               preparedStatement.setString(2, hastatc);
               preparedStatement.executeUpdate();
               key=true;
        }   catch (SQLException ex) {
               Logger.getLogger(Hasta.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
            
        }
    
    
}
