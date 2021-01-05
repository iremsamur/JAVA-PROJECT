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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author İREM SAMUR
 */
public class Bashekim extends User {
   
    //Bashekimin bilgilerini tutan class User dan kalıtım alır
    Connection con = conn.conDb();//Üst class olan user aracılığıyla veritabanına bağlantı sağlar
    Statement statement = null;
    ResultSet rs = null;
    Statement statement2 = null;
    ResultSet rs2 = null;
    
            
    PreparedStatement preparedStatement = null;

    public Bashekim(int id, String tcno, String ad, String soyad, String parola, String tur,String mail,String cepTelNo,String adres,String cinsiyet,String unvan) {
        super(id, tcno, ad, soyad, parola, tur,mail,cepTelNo,adres,cinsiyet,unvan);
        //inheritance ile üst class olan user dan  özelliklerini miras alır
      
    }
    public Bashekim(){
        
    }

    
    //Bütün veritabani işlemleri bu class içinde yazılır
    public ArrayList<User> doktorlariListele() throws SQLException{
        ArrayList<User> liste = new ArrayList<>();
        User object;
        String sorgu = "SELECT * FROM user WHERE kullanici_turu = 'doktor'";//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        //Kullanıcı türü doktor olanları user tablosundan çekip getirir
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.
            //Ve bu gelen tüm doktorları rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new User(rs.getInt("id"),rs.getString("ad"),rs.getString("soyad"),rs.getString("tcno"),rs.getString("parola"),rs.getString("kullanici_turu"),rs.getString("mail_adresi"),rs.getString("ceptel_no"),rs.getString("adres"),rs.getString("cinsiyet"),rs.getString("unvan"));
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return liste;
        
    }
    public boolean doktorEkle(String ad, String soyad, String tcNo,String parola,String mail,String cepTel,String adres,String cinsiyet,String unvan){
        //Veritabanına doktor ekleme işlemi yapar
        String sorgu = "INSERT INTO user (ad,soyad,tcno,parola,kullanici_turu,mail_adresi,ceptel_no,adres,cinsiyet,unvan) VALUES (?,?,?,?,?,?,?,?,?,?)";
        boolean durum =false;//durum ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, ad);
            preparedStatement.setString(2, soyad);
            preparedStatement.setString(3, tcNo);
            preparedStatement.setString(4, parola);
            preparedStatement.setString(5, "doktor");
            preparedStatement.setString(6, mail);
            preparedStatement.setString(7, cepTel);
            preparedStatement.setString(8, adres);
            preparedStatement.setString(9, cinsiyet);
            preparedStatement.setString(10, unvan);
          
            preparedStatement.executeUpdate();
            durum=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(durum)
            return true;
        
        else
            return false;
        
    }
    public boolean doktorBilgisiGuncelle(int id,String yeniAd,String yeniSoyad,String yeniTcNo,String yeniParola,String yeniMail,String yeniCepTel_no,String yeniAdres,String yeniUnvan){
         String sorgu = "UPDATE user SET   ad = ? , soyad =?, tcno =?,parola = ?,mail_adresi=?,ceptel_no=?,adres=?,unvan=?  WHERE id = ?";
         boolean durum=false;
         //doktor bilgisi güncelleme işlemi yapar
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniAd);
            
            preparedStatement.setString(2, yeniSoyad);
            preparedStatement.setString(3, yeniTcNo);
            preparedStatement.setString(4,yeniParola);//? leri yerine gelecek değerleri veririz
            preparedStatement.setString(5,yeniMail);
            preparedStatement.setString(6,yeniCepTel_no);
            preparedStatement.setString(7,yeniAdres);
            preparedStatement.setString(8,yeniUnvan);
         
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
            durum=true;
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(durum){
            return true;
        }
        else{
            return false;
        }
         
     }
    public boolean doktorSil(int id){
         //id'ye göre doktor silme işlemi yapar
        String sorgu = "DELETE FROM user WHERE id =?";
        boolean durum =false;//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            durum=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(durum)
            return true;
        
        else
            return false;
        
    }
     public String poliklinikDoktorEkle(int doktor_id,int poliklinik_id){
            //bu methot ile hastane_doktorlari tablosuna seçilen doktor ve poliklinik id leri ekleriz
        String sorgu = "INSERT INTO hastane_doktorlari (doktor_id,poliklinik_id) VALUES (?,?)";
        
       
        String key ="false";//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        int sayac =0;
        int sayac2 =0;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM hastane_doktorlari WHERE poliklinik_id = "+poliklinik_id+" AND doktor_id = "+doktor_id);
            while(rs.next()){
                sayac++;//Veritabanında var olan bir kullanıcının tekrar eklenmesini engellemek için bu işlemi yaparız
                
            }
            statement2 = con.createStatement();
            rs2 = statement.executeQuery("SELECT doktor_id FROM hastane_doktorlari WHERE doktor_id="+doktor_id);
            while(rs2.next()){
                //Burada önceden seçilen bir doktoru tekrar seçmesi engellenir.
                sayac2++;
            }
            
            if(sayac==0 && sayac2==0){
                   preparedStatement = con.prepareStatement(sorgu);
                   preparedStatement.setInt(1, doktor_id);
                   preparedStatement.setInt(2, poliklinik_id);
            
                   preparedStatement.executeUpdate();
                   key="true";
            }
            else{
                
                key="var";
            }
           
           
           
            
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
            return "false";
        }
        if(key=="true")
            return "true";
        else if(key=="var")
            return "var";
        
        else
            return "false";
        
    }
    public ArrayList<User> poliklinikDoktorlariListele(int poliklinik_id) throws SQLException{
        ArrayList<User> list = new ArrayList<>();
        User object;
        String sorgu = "SELECT u.id,u.tcno,u.kullanici_turu,u.ad,u.soyad,u.parola,u.unvan FROM hastane_doktorlari d LEFT JOIN user u ON d.doktor_id = u.id WHERE poliklinik_id = "+poliklinik_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        //burada u.id,u.name şeklinde yazarak tabledan sadece o isimli sütunları çekmek istediğimiz anlamına gelir. Boş yere tüm sütunlar gelmez
        //Bu komut ile user ve hastane_doktorlari tabloları arasında ilişki kuruyoruz. id leri eşit olanları alıyoruz
        //Burada clinic id sine göre getirme . Ve user id sine göre hastane_doktorlari tablosuna ait  d ve user a ait u ların tablodaki istenen değerleri getir yani hastane_doktorlari ve user'ı id leri eşit olanları soluna ekle birleştir anlamına gelir
        //Bu komutla tablolar arasında yani user ve hastane_doktorlari tabloları arasında ilişki kurduk
        //id'leri eşleştirdik
        
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.
            //Ve bu gelen tüm doktorları rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new User(rs.getInt("u.id"),rs.getString("u.ad"),rs.getString("u.soyad"),rs.getString("u.tcno"),rs.getString("u.parola"),rs.getString("u.kullanici_turu"),rs.getString("u.unvan"));
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                list.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return list;
        
    }
    public boolean bilgilerimiGuncelle(int id,String yeniAd,String yeniSoyad,String yeniTc,String yeniParola,String yeniMail,String yeniCepTel,String yeniAdres){
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
               Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
            
        }
        public String parolaGetir(int id){
            //Gönderilen id'ye göre o id'li kullanıcının parola bilgisini getirir
            String sorgu = "Select parola  from user where id ="+id;
            String parola="";
                    
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.Sorgu sonucu rs döner
            while(rs.next()){
                parola = rs.getString("parola");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bashekim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parola;
             
            
            
        }
    
    
}
    