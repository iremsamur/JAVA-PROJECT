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
public class CalismaSaatleri {
    
    private int id,doktor_id;
    private String doktor_adi,doktor_soyadi,unvan,tarih,musaitlik_durumu;
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();
    Connection con = conn.conDb();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public CalismaSaatleri(int id, int doktor_id, String doktor_adi, String doktor_soyadi,String unvan, String tarih, String musaitlik_durumu) {
        //class constructor'ı oluşturduk
        this.id = id;
        this.doktor_id = doktor_id;
        this.doktor_adi = doktor_adi;
        this.doktor_soyadi = doktor_soyadi;
        this.unvan = unvan;
        this.tarih = tarih;
        this.musaitlik_durumu = musaitlik_durumu;
    }
    public CalismaSaatleri(){//override yaparak ikinci boş bir constructor oluşturduk
        
    }

    //get - set methotları ile bu classın özelliklerini encapsulate ettik class dışında kullanılabilmesini sağladık
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

    public String getDoktor_adi() {
        return doktor_adi;
    }

    public void setDoktor_adi(String doktor_adi) {
        this.doktor_adi = doktor_adi;
    }

    public String getDoktor_soyadi() {
        return doktor_soyadi;
    }

    public String getUnvan() {
        return unvan;
    }

    public void setUnvan(String unvan) {
        this.unvan = unvan;
    }
    

    public void setDoktor_soyadi(String doktor_soyadi) {
        this.doktor_soyadi = doktor_soyadi;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getMusaitlik_durumu() {
        return musaitlik_durumu;
    }

    public void setMusaitlik_durumu(String musaitlik_durumu) {
        this.musaitlik_durumu = musaitlik_durumu;
    }
    
    public ArrayList<CalismaSaatleri> calismaSaatleriListele(int doktor_id) throws SQLException{
        ArrayList<CalismaSaatleri> liste = new ArrayList<>();
        CalismaSaatleri object;
        String sorgu = "SELECT * FROM calisma_saatleri WHERE musaitlik_durumu = 'a' AND doktor_id = "+doktor_id;//Kural olarak mysql komutları , select, from gibi büyük harflerle yazılır.
        //Burada calisma_saatleri tablosundan sadece musaitlik durumu aktif yani a olan ve doktor_id si methota
        //gönderilen değer olan calisma saatlerini getirir
        
        
        
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
                object.setUnvan(rs.getString("unvan"));
                
                object.setMusaitlik_durumu(rs.getString("musaitlik_durumu"));
                object.setTarih(rs.getString("calisma_zamani"));
                
                
                
                liste.add(object);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalismaSaatleri.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
       return liste;
        
    }
    
    
    
    
}
