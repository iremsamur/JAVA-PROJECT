/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.User;
import com.irem.models.YoneticiIslemleri;
import com.irem.veritabani.YardimciMethotlar;
import java.util.ArrayList;

/**
 *
 * @author İREM SAMUR
 */
public class BashekimBilgiGuncelle extends javax.swing.JFrame {
    YoneticiIslemleri yoneticiIslem = new YoneticiIslemleri();
    private String tc;
    private String ad;
    private String soyad;
    private String parola;
   
    private String mail;
    private String cep;
    private String adres;
  

    /**
     * Creates new form BashekimBilgiGuncelle
     */
    public BashekimBilgiGuncelle() {
        initComponents();
        
        ArrayList<User> bashekim = new ArrayList<User>();//Bir tane User classı türünden bashekim referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
        bashekim = yoneticiIslem.BashekimBilgileriGetir();//yoneticiIslem nesnesi ile YoneticiIslemleri classı içindeki bashekimBilgileriGetir() methodu bir tane arraylist döner o yüzden bunu bashekim'e atarım. Çünkü Arraylistimi bashekim referansı ile tanımlamıştım
          if(bashekim != null){
            //bashekim arraylist'i null değilse artık başhekimin bilgilerini buradaki değişkenlere atama işlemlerini yaparım
            for(User bhekim : bashekim){
                //foreach ile bu bashekim üzerinde gezinirim bu bashekim ın tipi classtır User classı olur
                
                tc = bhekim.getTcno();
                ad = bhekim.getAd();
                soyad = bhekim.getSoyad();
                parola = bhekim.getParola();
                mail = bhekim.getMail_adresi();//Değişkenlere değerleri atanıyor
                cep = bhekim.getCeptel_no();
                adres = bhekim.getAdres();
                
                
                
                
                
                
               
            
        }
    }
          bashekimTc_alani2.setText(tc);
          bashekimAd_alani2.setText(ad);
          bashekimSoyad_alani2.setText(soyad);
          bashekimParola_alani2.setText(parola);//Arraylist de aldığım bu değişken değerlerini arayüzde uygun etiketlere veya metin alanlarına setText ile yazdrırır
          bashekimMail_alani2.setText(mail);
          bashekimCep_alani2.setText(cep);
          bashekimAdres_alani2.setText(adres);
          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GuncelleEkrani = new javax.swing.JPanel();
        geri_butonu2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        unvanSec_alani2 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        bashekimAdres_alani2 = new javax.swing.JTextArea();
        bashekimCep_alani2 = new javax.swing.JTextField();
        bashekimMail_alani2 = new javax.swing.JTextField();
        mail_etiketi = new javax.swing.JLabel();
        cepTel_etiketi = new javax.swing.JLabel();
        adres_etiketi = new javax.swing.JLabel();
        unvan_etiketi = new javax.swing.JLabel();
        bashekimParola_alani2 = new javax.swing.JTextField();
        bashekimSoyad_alani2 = new javax.swing.JTextField();
        bashekimAd_alani2 = new javax.swing.JTextField();
        bashekimTc_alani2 = new javax.swing.JTextField();
        parola_etiketi = new javax.swing.JLabel();
        soyad_etiketi = new javax.swing.JLabel();
        ad_etiketi = new javax.swing.JLabel();
        tcKimlik_etiketi = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        bashekimBilgiGuncelle_butonu = new javax.swing.JButton();
        atamaIptali_butonu2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BAŞHEKİM BİLGİ GÜNCELLE");

        GuncelleEkrani.setBackground(new java.awt.Color(153, 255, 153));
        GuncelleEkrani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        GuncelleEkrani.setLayout(null);

        geri_butonu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\back-158491_1280_50x50.jpg")); // NOI18N
        geri_butonu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 4));
        geri_butonu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geri_butonu2ActionPerformed(evt);
            }
        });
        GuncelleEkrani.add(geri_butonu2);
        geri_butonu2.setBounds(1270, 10, 60, 60);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setText("                 BAŞHEKİM BİLGİLERİNİ GÜNCELLEME EKRANI");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 4));
        GuncelleEkrani.add(jLabel1);
        jLabel1.setBounds(310, 40, 600, 40);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 4));
        jPanel1.setLayout(null);

        unvanSec_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unvanSec_alani2.setForeground(new java.awt.Color(204, 0, 102));
        unvanSec_alani2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Başhekim Ünvanı Seç--", "Ordinaryus", "Prof", "Doç", "Yardımcı Doçent", "Operatör", "Uzman", "Pratisyen", " " }));
        unvanSec_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(unvanSec_alani2);
        unvanSec_alani2.setBounds(820, 370, 230, 50);

        bashekimAdres_alani2.setColumns(20);
        bashekimAdres_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimAdres_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimAdres_alani2.setRows(5);
        bashekimAdres_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jScrollPane1.setViewportView(bashekimAdres_alani2);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(820, 250, 230, 90);

        bashekimCep_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimCep_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimCep_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(bashekimCep_alani2);
        bashekimCep_alani2.setBounds(820, 160, 230, 40);

        bashekimMail_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimMail_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimMail_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(bashekimMail_alani2);
        bashekimMail_alani2.setBounds(820, 60, 230, 40);

        mail_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mail_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        mail_etiketi.setText("           Mail Adresi");
        mail_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(mail_etiketi);
        mail_etiketi.setBounds(560, 60, 180, 40);

        cepTel_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cepTel_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        cepTel_etiketi.setText("Cep Telefon Numarası");
        cepTel_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(cepTel_etiketi);
        cepTel_etiketi.setBounds(560, 160, 180, 40);

        adres_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        adres_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        adres_etiketi.setText("                 Adres");
        adres_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(adres_etiketi);
        adres_etiketi.setBounds(560, 240, 180, 80);

        unvan_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unvan_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        unvan_etiketi.setText("                Ünvan");
        unvan_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(unvan_etiketi);
        unvan_etiketi.setBounds(570, 380, 170, 40);

        bashekimParola_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimParola_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimParola_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(bashekimParola_alani2);
        bashekimParola_alani2.setBounds(290, 370, 160, 40);

        bashekimSoyad_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimSoyad_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimSoyad_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(bashekimSoyad_alani2);
        bashekimSoyad_alani2.setBounds(290, 270, 160, 40);

        bashekimAd_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimAd_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimAd_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        bashekimAd_alani2.setCaretColor(new java.awt.Color(255, 0, 102));
        jPanel1.add(bashekimAd_alani2);
        bashekimAd_alani2.setBounds(290, 170, 160, 40);

        bashekimTc_alani2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimTc_alani2.setForeground(new java.awt.Color(204, 0, 102));
        bashekimTc_alani2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(bashekimTc_alani2);
        bashekimTc_alani2.setBounds(290, 60, 160, 40);

        parola_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        parola_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        parola_etiketi.setText("            Parola ");
        parola_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(parola_etiketi);
        parola_etiketi.setBounds(60, 370, 160, 40);

        soyad_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        soyad_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        soyad_etiketi.setText("            Soyad");
        soyad_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(soyad_etiketi);
        soyad_etiketi.setBounds(60, 270, 160, 40);

        ad_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ad_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        ad_etiketi.setText("              Ad");
        ad_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(ad_etiketi);
        ad_etiketi.setBounds(60, 170, 160, 40);

        tcKimlik_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tcKimlik_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        tcKimlik_etiketi.setText("     Tc Kimlik No");
        tcKimlik_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel1.add(tcKimlik_etiketi);
        tcKimlik_etiketi.setBounds(60, 60, 159, 40);

        GuncelleEkrani.add(jPanel1);
        jPanel1.setBounds(100, 130, 1130, 460);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanel2.setLayout(null);

        bashekimBilgiGuncelle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimBilgiGuncelle_butonu.setForeground(new java.awt.Color(255, 0, 0));
        bashekimBilgiGuncelle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\191-1918674_colour-change-icon-hd-png-download_50x50.jpg")); // NOI18N
        bashekimBilgiGuncelle_butonu.setText("  Bilgileri Güncelle");
        bashekimBilgiGuncelle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        bashekimBilgiGuncelle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bashekimBilgiGuncelle_butonuActionPerformed(evt);
            }
        });
        jPanel2.add(bashekimBilgiGuncelle_butonu);
        bashekimBilgiGuncelle_butonu.setBounds(90, 20, 180, 50);

        atamaIptali_butonu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        atamaIptali_butonu2.setForeground(new java.awt.Color(255, 0, 0));
        atamaIptali_butonu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\cancel_50x50.jpg")); // NOI18N
        atamaIptali_butonu2.setText("İptal Et");
        atamaIptali_butonu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        atamaIptali_butonu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atamaIptali_butonu2ActionPerformed(evt);
            }
        });
        jPanel2.add(atamaIptali_butonu2);
        atamaIptali_butonu2.setBounds(410, 20, 170, 50);

        GuncelleEkrani.add(jPanel2);
        jPanel2.setBounds(330, 600, 660, 90);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GuncelleEkrani, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GuncelleEkrani, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bashekimBilgiGuncelle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bashekimBilgiGuncelle_butonuActionPerformed
        // TODO add your handling code here:
        
        String tc = bashekimTc_alani2.getText();
        String sifre = bashekimParola_alani2.getText();
        String ad = bashekimAd_alani2.getText();
        String soyad = bashekimSoyad_alani2.getText();
        String mail = bashekimMail_alani2.getText();
        String cepTel = bashekimCep_alani2.getText();
        String adres = bashekimAdres_alani2.getText();
        
        String unvan = (String) unvanSec_alani2.getSelectedItem();
       
        boolean durum = yoneticiIslem.bashekimGuncelle(ad, soyad,tc,sifre,mail, cepTel, adres, unvan);
        //bashekimGuncelle methodu ile  veritabanında guncelleme işlemini gerçekleştirir
        YardimciMethotlar.onay("eminim");
        if(durum){
            YardimciMethotlar.mesajGoster("basarili");
        }
        else{
            YardimciMethotlar.mesajGoster("hata");
        }
     
    }//GEN-LAST:event_bashekimBilgiGuncelle_butonuActionPerformed

    private void atamaIptali_butonu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atamaIptali_butonu2ActionPerformed
        // TODO add your handling code here:
        YardimciMethotlar.mesajGoster("eminim");
        YoneticiAnaEkrani yonetici = new YoneticiAnaEkrani();//YoneticiAnaEkrani 'na geçişi sağlar
        yonetici.setVisible(true);//YoneticiAnaEkrani 'na geçişi sağlar
        dispose();//İçinde bulunduğumuz ekranı kapatır
    }//GEN-LAST:event_atamaIptali_butonu2ActionPerformed

    private void geri_butonu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geri_butonu2ActionPerformed
        // TODO add your handling code here:
        YoneticiAnaEkrani yonetici = new YoneticiAnaEkrani();
        yonetici.setVisible(true);
        dispose();

    }//GEN-LAST:event_geri_butonu2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BashekimBilgiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BashekimBilgiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BashekimBilgiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BashekimBilgiGuncelle.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BashekimBilgiGuncelle().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GuncelleEkrani;
    private javax.swing.JLabel ad_etiketi;
    private javax.swing.JLabel adres_etiketi;
    private javax.swing.JButton atamaIptali_butonu2;
    private javax.swing.JTextField bashekimAd_alani2;
    private javax.swing.JTextArea bashekimAdres_alani2;
    private javax.swing.JButton bashekimBilgiGuncelle_butonu;
    private javax.swing.JTextField bashekimCep_alani2;
    private javax.swing.JTextField bashekimMail_alani2;
    private javax.swing.JTextField bashekimParola_alani2;
    private javax.swing.JTextField bashekimSoyad_alani2;
    private javax.swing.JTextField bashekimTc_alani2;
    private javax.swing.JLabel cepTel_etiketi;
    private javax.swing.JButton geri_butonu2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mail_etiketi;
    private javax.swing.JLabel parola_etiketi;
    private javax.swing.JLabel soyad_etiketi;
    private javax.swing.JLabel tcKimlik_etiketi;
    private javax.swing.JComboBox<String> unvanSec_alani2;
    private javax.swing.JLabel unvan_etiketi;
    // End of variables declaration//GEN-END:variables
}
