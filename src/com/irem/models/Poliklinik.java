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
public class Poliklinik {
    private int id;
    private String klinik_adi;
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();//Database i tüm alt classlara veritabanına bağlanmasını sağlaması için burada tanımladık
    Connection con = conn.conDb();//Üst class olan user aracılığıyla veritabanına bağlantı sağlar
    Statement statement = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public Poliklinik(int id, String klinik_adi) {
        this.id = id;
        this.klinik_adi = klinik_adi;
    }
    public Poliklinik(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKlinik_adi() {
        return klinik_adi;
    }

    public void setKlinik_adi(String klinik_adi) {
        this.klinik_adi = klinik_adi;
    }
    public ArrayList<Poliklinik> poliklinikListele() throws SQLException{//Poliklinik classı içinde clinicden doğan nesneneleri tutan bir bir arraylisti oluştururum
        ArrayList<Poliklinik> liste = new ArrayList<>();
        Poliklinik object;
        String sorgu = "SELECT * FROM poliklinik ";//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//Bu sorgu poliklinikleri veritabanından poliklinik tablosundan getirir
            //Ve bu gelen tüm poliklinikleri rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new Poliklinik();
                object.setId(rs.getInt("id"));
                object.setKlinik_adi(rs.getString("poliklinikAdi"));
                
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Poliklinik.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
      
       return liste;
        
    }
     public boolean poliklinikEkle(String klinikAdi){
        String sorgu = "INSERT INTO poliklinik (poliklinikAdi) VALUES (?)";
        boolean key =false;//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setString(1, klinikAdi);
            
            preparedStatement.executeUpdate();
            key=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Poliklinik.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key)
            return true;
        
        else
            return false;
        
    }
     public boolean poliklinikSil(int id){
         //id'ye göre poliklinik silme işlemi yapar
        String sorgu = "DELETE FROM poliklinik where id =?";
        boolean key =false;//key ile veritabanına eklemenin olup olmadığını anlarız true ve false ile
        try {
           
            preparedStatement = con.prepareStatement(sorgu);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            key=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(Poliklinik.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key)
            return true;
        
        else
            return false;
        
    }
      public boolean poliklinikGuncelle(int id,String yeniPoliklinik){
         String sorgu = "Update poliklinik set poliklinikAdi = ?  where id = ?";
         boolean key=false;
        try {
            preparedStatement = con.prepareStatement(sorgu);
            
            preparedStatement.setString(1, yeniPoliklinik);
            
            
         
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            key=true;
        } catch (SQLException ex) {
            Logger.getLogger(Poliklinik.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(key){
            return true;
        }
        else{
            return false;
        }
         
     }
       
      public ArrayList<User> poliklinikDoktorlariListele(int poliklinik_id) throws SQLException{
        ArrayList<User> liste = new ArrayList<>();
        User object;
        String sorgu = "SELECT u.id,u.tcno,u.kullanici_turu,u.ad,u.soyad,u.parola,u.unvan FROM hastane_doktorlari d LEFT JOIN user u ON d.doktor_id = u.id WHERE poliklinik_id = "+poliklinik_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        //hastane_doktorlari ve user tabloları arasında id lerine göre ilişki kurar
        
        
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);
            //Ve bu gelen tüm doktor bilgilerini rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new User(rs.getInt("u.id"),rs.getString("u.ad"),rs.getString("u.soyad"),rs.getString("u.tcno"),rs.getString("u.parola"),rs.getString("u.kullanici_turu"),rs.getString("u.unvan"));
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Poliklinik.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return liste;
        
    }
      
     
    
    
    
}
