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
public class Randevu {
    private int id,doktor_id,hasta_id;
    private String doktor_adi,doktor_soyadi,doktor_unvanı,hasta_adi,hasta_soyadi,hastaTc,hastaYas,randevu_tarihi;
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();//Database i tüm alt classlara veritabanına bağlanmasını sağlaması için burada tanımladık
    Connection con = conn.conDb();
    Statement statement = null;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public Randevu(int id, int doktor_id, int hasta_id, String doktor_adi, String doktor_soyadi,String doktor_unvanı, String hasta_adi, String hasta_soyadi,String hastaTc,String hastaYas, String randevu_tarihi) {
        this.id = id;
        this.doktor_id = doktor_id;
        this.hasta_id = hasta_id;
        this.doktor_adi = doktor_adi;
        this.doktor_soyadi = doktor_soyadi;
        this.hasta_adi = hasta_adi;
        this.hasta_soyadi = hasta_soyadi;
        this.hastaTc=hastaTc;
        this.hastaYas=hastaYas;
        this.randevu_tarihi = randevu_tarihi;
        this.doktor_unvanı=doktor_unvanı;
    }
    public Randevu(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoktor_id() {
        return doktor_id;
    }

    public void setDoktor_id(int doktor_id) {
        this.doktor_id = doktor_id;
    }

    public int getHasta_id() {
        return hasta_id;
    }

    public void setHasta_id(int hasta_id) {
        this.hasta_id = hasta_id;
    }

    public String getDoktor_adi() {
        return doktor_adi;
    }

    public void setDoktor_adi(String doktor_adi) {
        this.doktor_adi = doktor_adi;
    }

    public String getDoktor_soyadi() {
        return doktor_soyadi;
    }

    public void setDoktor_soyadi(String doktor_soyadi) {
        this.doktor_soyadi = doktor_soyadi;
    }

    public String getHasta_adi() {
        return hasta_adi;
    }

    public void setHasta_adi(String hasta_adi) {
        this.hasta_adi = hasta_adi;
    }

    public String getHasta_soyadi() {
        return hasta_soyadi;
    }

    public String getHastaTc() {
        return hastaTc;
    }

    public void setHastaTc(String hastaTc) {
        this.hastaTc = hastaTc;
    }

    public String getHastaYas() {
        return hastaYas;
    }

    public void setHastaYas(String hastaYas) {
        this.hastaYas = hastaYas;
    }
    

    public void setHasta_soyadi(String hasta_soyadi) {
        this.hasta_soyadi = hasta_soyadi;
    }

    public String getRandevu_tarihi() {
        return randevu_tarihi;
    }

 
    

    public void setRandevu_tarihi(String randevu_tarihi) {
        this.randevu_tarihi = randevu_tarihi;
    }
    public String getDoktor_unvanı() {
        return doktor_unvanı;
    }

    public void setDoktor_unvanı(String doktor_unvanı) {
        this.doktor_unvanı = doktor_unvanı;
    }
     public ArrayList<Randevu> randevularımıListele(int hasta_id) throws SQLException{//Poliklinik classı içinde clinicden doğan nesneneleri tutan bir bir arraylisti oluştururum
        //Hastaya aldığı randevuları gösterir
        ArrayList<Randevu> liste = new ArrayList<>();
        Randevu object;
        String sorgu = "SELECT * FROM randevular WHERE hasta_id =  "+hasta_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        //Gönderilen hasta_id değerine göre randevular tablosundan arama yapıp
        //sadece id değeri bu olanları hastaya getirir. Yani kendi randeuvularının listelenmesini sağlar
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);//
            //Ve bu gelen tüm bilgileri rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new Randevu();
                object.setId(rs.getInt("id"));
                object.setDoktor_id(rs.getInt("doktor_id"));
                object.setDoktor_adi(rs.getString("doktor_adi"));
                object.setDoktor_soyadi(rs.getString("doktor_soyadi"));
                object.setDoktor_unvanı(rs.getString("unvan"));
                object.setHasta_id(rs.getInt("hasta_id"));
                object.setHasta_adi(rs.getString("hasta_adi"));
                object.setHasta_soyadi(rs.getString("hasta_soyadi"));
                object.setRandevu_tarihi(rs.getString("randevu_tarihi"));
                
                
                
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Randevu.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
      
       return liste;
        
    }
     
     public ArrayList<Randevu> hastaRandevularınıListele(int doktor_id) throws SQLException{
        //Doktora randevularını gösterir
        ArrayList<Randevu> liste = new ArrayList<>();
        Randevu object;
        String sorgu = "SELECT * FROM randevular WHERE doktor_id =  "+doktor_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(sorgu);
            //Ve bu gelen tüm bilgileri rs'ye atar.
            while(rs.next()){
                //tüm rs elemanları üzerinde dolaşılır
                object = new Randevu();
                object.setId(rs.getInt("id"));
                object.setDoktor_id(rs.getInt("doktor_id"));
                object.setDoktor_adi(rs.getString("doktor_adi"));
                object.setDoktor_adi(rs.getString("doktor_soyadi"));
                object.setHasta_id(rs.getInt("hasta_id"));
                object.setHasta_adi(rs.getString("hasta_adi"));
                object.setHasta_soyadi(rs.getString("hasta_soyadi"));
                object.setHastaTc(rs.getString("hastaTc"));
                object.setHastaYas(rs.getString("hasta_yasi"));
                object.setRandevu_tarihi(rs.getString("randevu_tarihi"));
                
                
                
                //Veritabanından çektiğim tüm doktor bilgileri object'e atanır. ve bu object add methodu ile list içine atılır.
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Randevu.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
      
       return liste;
        
    }
    
    
}
