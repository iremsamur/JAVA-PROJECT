/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.veritabani;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author İREM SAMUR
 */
public class VeriTabaniBaglanti {
    //Veritabanı bağlantısını gerçekleştirdiğimiz class
    Connection con = null;
    String dbUrl = "jdbc:mysql://localhost:3306/hastanedatabase?useUnicode=true&characterEncoding=utf8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public VeriTabaniBaglanti(){
        
    }
    
    
    public Connection conDb(){
        
        try {
            this.con=DriverManager.getConnection(dbUrl,VeriTabaniBilgileri.kullanici_adi,VeriTabaniBilgileri.parola);//Veritabanına bağlantı sağlar
            return con;
            
        } catch (SQLException ex) {
            Logger.getLogger(VeriTabaniBaglanti.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
