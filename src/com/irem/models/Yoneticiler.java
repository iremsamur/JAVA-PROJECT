/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.models;

import com.irem.veritabani.YardimciMethotlar;
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
public class Yoneticiler extends User{//User classından miras alır
    Connection con = conn.conDb();//Üst class olan user aracılığıyla veritabanına bağlantı sağlar
    Statement statement = null;
    ResultSet rs = null;
    private String mesaj;
   
    PreparedStatement preparedStatement = null;
    

    public Yoneticiler(int id, String ad, String soyad, String tcno, String parola, String tur, String mail_adresi, String ceptel_no, String adres, String cinsiyet) {
        super(id, ad, soyad, tcno, parola, tur, mail_adresi, ceptel_no, adres, cinsiyet);
    }
    public Yoneticiler(){
        
    }

    public void setMesaj(String mesaj) {
        
        this.mesaj = mesaj;
        YardimciMethotlar.mesajGoster(this.mesaj);
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
            Logger.getLogger(Yoneticiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parola;
             
            
            
        }
      
      public boolean yoneticiGeciciSifreDegisimi(String hastatc,String yeniParola){
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
