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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author İREM SAMUR
 */
public class Resepsiyonist extends User{
    VeriTabaniBaglanti conn = new VeriTabaniBaglanti();
    Connection con = conn.conDb();
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    public Resepsiyonist(int id, String ad, String soyad, String tcno, String parola, String tur, String mail_adresi, String ceptel_no, String adres, String cinsiyet) {
        super(id, ad, soyad, tcno, parola, tur, mail_adresi, ceptel_no, adres, cinsiyet);
    }
    public Resepsiyonist(){
        
    }

    @Override
    public void girisMesajiYaz(String ad) {
        //Polimorfizim kullanarak User içindeki girisMesajiYaz() methodu override edildi
        super.girisMesajiYaz(ad); //To change body of generated methods, choose Tools | Templates.
    }
    public boolean resepsiyonistGeciciSifreDegisimi(String hastatc,String yeniParola){
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
