/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.veritabani;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author İREM SAMUR
 */
public class YardimciMethotlar {
     //YardımciMethotlar sınıfı içinde static methodlar tanımlayarak , böylece nesne üretmeden bu sık kullanılan methodları çağırabilirim
    //Kullanıcıya mesaj vermesi gereken durumlarda kullanılacak
    public static void optionPaneChangeButtonText(){
        //Bu method ile mesaj dialoglardaki ok yes no gibi butonları türkçe isimle değiştirebilirim UIManager içindeki put kullanarak
        UIManager.put("OptionPane.cancelButtonText", "İptal");
        UIManager.put("OptionPane.noButtonText", "Hayır");
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
    }
    public static void mesajGoster(String varsayilanDeger){
        optionPaneChangeButtonText();
        String mesaj;
        switch(varsayilanDeger){
            //switch case ile eğer kullanıcının durumuna göre diğer taraftan gelen str fill olursa msg verir
            
            case "dolu":
                mesaj="Lütfen tüm alanları doldururunuz!!!";
                break;
            case "basarili":
                mesaj = "İşlem başarılı.";
                break;
            case "hata":
                mesaj = "Bir hata gerçekleşti!!";
                break;
            default:
                mesaj = varsayilanDeger;//Dışardan gelen değere eşit olur.
            
        }
       
        JOptionPane.showMessageDialog(null, mesaj, "Mesaj", JOptionPane.INFORMATION_MESSAGE);
    }
    public static boolean onay(String varsayilanDeger){
        optionPaneChangeButtonText();
        //Bu method ile kullanıcı silmek istediğinde emin misiniz sorusunu sorar
        String mesaj;
        switch(varsayilanDeger){
            case "eminim":
                mesaj="Bu işlemi gerçekleştirmek istediğinizden emin misiniz?";
                break;
            default:
                mesaj = varsayilanDeger;
                break;
                
            
            
        }
        int durum =JOptionPane.showConfirmDialog(null, mesaj, "Dikkat!",JOptionPane.YES_NO_OPTION);//Kullanıcıya yes or no olarak soracak
        if(durum==0){//durum 0 olursa true değilse false anlamındadır
            return true;
        }
        else{
            return false;
        }
        
    }
    public static void yonlendirmeMesajiVer(String varsayilanDeger){
        optionPaneChangeButtonText();
        String mesaj;
        switch(varsayilanDeger){
            case "basarili":
                mesaj="Sayfaya yönlendiriliyorsunuz...\nLütfen bekleyiniz...";
               
                break;
            default:
                mesaj=varsayilanDeger;
                
                
                break;
        }
         JOptionPane.showMessageDialog(null, mesaj, "Mesaj", JOptionPane.PLAIN_MESSAGE);
        
    }
       
        
    
    
}
