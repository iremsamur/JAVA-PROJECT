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
public class Doktor extends User{//Doktor alt classı User üst classından miras alır
   
    Connection con = conn.conDb();//Üst class olan user aracılığıyla veritabanına bağlantı sağlar
    Statement statement = null;
    Statement statement2 = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    

    public Doktor(int id, String ad, String soyad, String tcno, String parola, String tur,String mail,String cepTelNo,String adres,String cinsiyet,String unvan) {
        super(id, ad, soyad, tcno, parola, tur,mail,cepTelNo,adres,cinsiyet,unvan);//super ile bu miras özellikleri constructorda yazılır
        
    }
    
    public Doktor(){//override constructor
        
    }

   
    
    public String randevuSaatiEkle(int doktor_id,String doktor_ad,String doktor_soyad,String unvan,String tarih){
       int key=0;
       int sayac=0;
       String sorgu = "INSERT INTO calisma_saatleri (doktor_id,doktor_adi,doktor_soyadi,unvan,calisma_zamani) VALUES (?,?,?,?,?)";
    
        try {
            statement = con.createStatement();
            
            
            rs = statement.executeQuery("SELECT * FROM calisma_saatleri WHERE  musaitlik_durumu= 'a' AND doktor_id = "+doktor_id+" AND calisma_zamani = '"+tarih+"'");
            while(rs.next()){
                sayac++;
                break;
            }
            if(sayac==0){
                preparedStatement = con.prepareStatement(sorgu);
                preparedStatement.setInt(1, doktor_id);
                preparedStatement.setString(2, doktor_ad);
                preparedStatement.setString(3,doktor_soyad);
                preparedStatement.setString(4,unvan);
                preparedStatement.setString(5,tarih);
                
                preparedStatement.executeUpdate();
                 key=1;
                
            }
            else{
                key=0;
            }

            
           
        } catch (SQLException ex) {
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key==1)
            return "true";
        else if(key==0)
            return "var";
        else
            return "false";
            
        
       
   }
    public ArrayList<CalismaSaatleri> calismaSaatleriListele(int doktor_id) throws SQLException{
        ArrayList<CalismaSaatleri> liste = new ArrayList<>();
        CalismaSaatleri object;
        String sorgu = "SELECT * FROM calisma_saatleri WHERE musaitlik_durumu = 'a' AND doktor_id = "+doktor_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.

        
        
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.
            //Ve bu gelen tüm doktorları rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new CalismaSaatleri();
                
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                object.setId(rs.getInt("id"));
                object.setDoktor_id(rs.getInt("doktor_id"));
                object.setDoktor_adi(rs.getString("doktor_adi"));
                object.setDoktor_soyadi(rs.getString("doktor_soyadi"));
                object.setMusaitlik_durumu(rs.getString("musaitlik_durumu"));
                object.setTarih(rs.getString("calisma_zamani"));
                
                
                
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return liste;
        
    }
    public boolean calismaSaatiSil(int id){
         //id'ye göre doktorun calisma saatini silme işlemi yapar
        String sorgu = "DELETE FROM calisma_saatleri where id =?";
        boolean key =false;//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key)
            return true;
        
        else
            return false;
        
    }
      
     public String parolaGetir(int id){
            String sorgu = "Select parola  from user where id ="+id;
            String parola="";
                    
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu ile sadece type 'ı doktor olanları bize getirir.
            while(rs.next()){
                parola = rs.getString("parola");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parola;
             
            
            
        }
     public boolean doktorBilgileriniGuncelle(int id,String yeniAd,String yeniSoyad,String yeniTc,String yeniParola,String yeniMail,String yeniCepTel,String yeniAdres,String yeniUnvan){
            String sorgu = "Update user set   ad = ? ,soyad = ?, tcno =?,parola = ?,mail_adresi=?,ceptel_no=?,adres=?,unvan=?  where id = ?";
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
               preparedStatement.setString(8, yeniUnvan);
               
               
               
         
               preparedStatement.setInt(9, id);
               preparedStatement.executeUpdate();
               key=true;
        }   catch (SQLException ex) {
               Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
            
        }
     public boolean doktorGeciciSifreDegisimi(String hastatc,String yeniParola){
         //doktor şifresini unuttuğunda geçici olarak şifresinin değişmesini sağlar
            String sorgu = "Update user set   parola =?  where tcno = ?";
            boolean key=false;
            try {
               preparedStatement = con.prepareStatement(sorgu);
            
               preparedStatement.setString(1, yeniParola);
            
               
               
               
               
               
         
               preparedStatement.setString(2, hastatc);
               preparedStatement.executeUpdate();
               key=true;
        }   catch (SQLException ex) {
               Logger.getLogger(Doktor.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(key){
               return true;
        }
            else{
               return false;
        }
            
        }
    
}
