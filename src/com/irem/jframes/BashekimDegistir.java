/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.YoneticiIslemleri;
import com.irem.veritabani.YardimciMethotlar;

/**
 *
 * @author İREM SAMUR
 */
public class BashekimDegistir extends javax.swing.JFrame {
    YoneticiIslemleri yoneticiIslem = new YoneticiIslemleri();//YoneticiIslemleri classından yoneticiIslem adlı bir nesne oluştururuz

    /**
     * Creates new form BashekimDegistir
     */
    public BashekimDegistir() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        geri_butonu3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        unvanSec_alani3 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        bashekimAdres_alani3 = new javax.swing.JTextArea();
        bashekimCep_alani3 = new javax.swing.JTextField();
        bashekimMail_alani3 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cinsiyetSec_box3 = new javax.swing.JComboBox<>();
        bashekimParola_alani3 = new javax.swing.JTextField();
        bashekimSoyad_alani3 = new javax.swing.JTextField();
        bashekimAd_alani3 = new javax.swing.JTextField();
        bashekimTc_alani3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bashekimDegistir_butonu = new javax.swing.JButton();
        atamaIptali_butonu3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BAŞHEKİM DEĞİŞTİR");

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.setLayout(null);

        geri_butonu3.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\back-158491_1280_50x50.jpg")); // NOI18N
        geri_butonu3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        geri_butonu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geri_butonu3ActionPerformed(evt);
            }
        });
        jPanel1.add(geri_butonu3);
        geri_butonu3.setBounds(1280, 20, 60, 60);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("            BAŞHEKİM DEĞİŞİKLİĞİ EKRANI ");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jPanel1.add(jLabel10);
        jLabel10.setBounds(420, 20, 450, 50);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 204), 4));
        jPanel2.setLayout(null);

        unvanSec_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unvanSec_alani3.setForeground(new java.awt.Color(255, 0, 0));
        unvanSec_alani3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Başhekim Ünvanı Seç--", "Ordinaryus", "Prof", "Doç", "Yardımcı Doçent", "Operatör", "Uzman", "Pratisyen", " " }));
        unvanSec_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(unvanSec_alani3);
        unvanSec_alani3.setBounds(840, 390, 220, 50);

        bashekimAdres_alani3.setColumns(20);
        bashekimAdres_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimAdres_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimAdres_alani3.setRows(5);
        bashekimAdres_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jScrollPane1.setViewportView(bashekimAdres_alani3);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(840, 250, 220, 90);

        bashekimCep_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimCep_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimCep_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimCep_alani3);
        bashekimCep_alani3.setBounds(840, 140, 230, 50);

        bashekimMail_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimMail_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimMail_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimMail_alani3);
        bashekimMail_alani3.setBounds(840, 30, 230, 50);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 102));
        jLabel9.setText("             Ünvan");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel9);
        jLabel9.setBounds(560, 390, 170, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 102));
        jLabel8.setText("                 Adres");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel8);
        jLabel8.setBounds(560, 260, 170, 70);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 102));
        jLabel7.setText("Cep Telefon Numarası");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 4));
        jPanel2.add(jLabel7);
        jLabel7.setBounds(560, 140, 170, 50);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 102));
        jLabel6.setText("           Mail Adresi");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel6);
        jLabel6.setBounds(560, 30, 170, 50);

        cinsiyetSec_box3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cinsiyetSec_box3.setForeground(new java.awt.Color(255, 0, 0));
        cinsiyetSec_box3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Cinsiyet Seç--", "Kadın", "Erkek", "Diğer", " " }));
        cinsiyetSec_box3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(cinsiyetSec_box3);
        cinsiyetSec_box3.setBounds(310, 400, 150, 40);

        bashekimParola_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimParola_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimParola_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimParola_alani3);
        bashekimParola_alani3.setBounds(300, 320, 160, 40);

        bashekimSoyad_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimSoyad_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimSoyad_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimSoyad_alani3);
        bashekimSoyad_alani3.setBounds(300, 230, 160, 40);

        bashekimAd_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimAd_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimAd_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimAd_alani3);
        bashekimAd_alani3.setBounds(300, 130, 160, 50);

        bashekimTc_alani3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimTc_alani3.setForeground(new java.awt.Color(255, 0, 0));
        bashekimTc_alani3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanel2.add(bashekimTc_alani3);
        bashekimTc_alani3.setBounds(300, 40, 160, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 102));
        jLabel1.setText("      Tc Kimlik No");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel1);
        jLabel1.setBounds(60, 40, 159, 50);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 102));
        jLabel2.setText("               Ad");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 255), 4));
        jPanel2.add(jLabel2);
        jLabel2.setBounds(60, 120, 160, 50);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 102));
        jLabel3.setText("            Soyad");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel3);
        jLabel3.setBounds(60, 230, 160, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 102));
        jLabel4.setText("           Parola ");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel4);
        jLabel4.setBounds(60, 320, 150, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 102));
        jLabel5.setText("          Cinsiyet");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel2.add(jLabel5);
        jLabel5.setBounds(60, 400, 150, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(90, 90, 1160, 490);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255), 4));
        jPanel3.setLayout(null);

        bashekimDegistir_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bashekimDegistir_butonu.setForeground(new java.awt.Color(255, 0, 0));
        bashekimDegistir_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\191-1918674_colour-change-icon-hd-png-download_50x50.jpg")); // NOI18N
        bashekimDegistir_butonu.setText("Başhekimi Değiştir");
        bashekimDegistir_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        bashekimDegistir_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bashekimDegistir_butonuActionPerformed(evt);
            }
        });
        jPanel3.add(bashekimDegistir_butonu);
        bashekimDegistir_butonu.setBounds(110, 30, 190, 50);

        atamaIptali_butonu3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        atamaIptali_butonu3.setForeground(new java.awt.Color(255, 0, 0));
        atamaIptali_butonu3.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\cancel_50x50.jpg")); // NOI18N
        atamaIptali_butonu3.setText("İptal Et");
        atamaIptali_butonu3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        atamaIptali_butonu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                atamaIptali_butonu3ActionPerformed(evt);
            }
        });
        jPanel3.add(atamaIptali_butonu3);
        atamaIptali_butonu3.setBounds(500, 30, 160, 50);

        jPanel1.add(jPanel3);
        jPanel3.setBounds(310, 590, 730, 100);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bashekimDegistir_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bashekimDegistir_butonuActionPerformed
        // TODO add your handling code here:
        String tc = bashekimTc_alani3.getText();
        String sifre = bashekimParola_alani3.getText();
        String ad = bashekimAd_alani3.getText();
        String soyad = bashekimSoyad_alani3.getText();
        String mail = bashekimMail_alani3.getText();
        String cepTel = bashekimCep_alani3.getText();
        String adres = bashekimAdres_alani3.getText();
        String cinsiyet = (String) cinsiyetSec_box3.getSelectedItem();
        String unvan = (String) unvanSec_alani3.getSelectedItem();
        boolean durum = yoneticiIslem.bashekimDegistir(ad, soyad,tc,sifre, mail, cepTel, adres, cinsiyet, unvan);//bashekimDegistir methodu çağırılır
        if(durum){
            YardimciMethotlar.mesajGoster("basarili");
        }
        else{
            YardimciMethotlar.mesajGoster("hata");
        }
    }//GEN-LAST:event_bashekimDegistir_butonuActionPerformed

    private void atamaIptali_butonu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_atamaIptali_butonu3ActionPerformed
        // TODO add your handling code here:
        //Kullanıcının iptal etmesini sağlar
        YardimciMethotlar.mesajGoster("eminim");
        YoneticiAnaEkrani yonetici = new YoneticiAnaEkrani();
        yonetici.setVisible(true);
        dispose();
    }//GEN-LAST:event_atamaIptali_butonu3ActionPerformed

    private void geri_butonu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geri_butonu3ActionPerformed
        // TODO add your handling code here:
        //Kullanıcının bir önceki ekrana gitmesini sağlar 
        YoneticiAnaEkrani yonetici = new YoneticiAnaEkrani();
        yonetici.setVisible(true);
        dispose();

    }//GEN-LAST:event_geri_butonu3ActionPerformed

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
            java.util.logging.Logger.getLogger(BashekimDegistir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BashekimDegistir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BashekimDegistir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BashekimDegistir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BashekimDegistir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton atamaIptali_butonu3;
    private javax.swing.JTextField bashekimAd_alani3;
    private javax.swing.JTextArea bashekimAdres_alani3;
    private javax.swing.JTextField bashekimCep_alani3;
    private javax.swing.JButton bashekimDegistir_butonu;
    private javax.swing.JTextField bashekimMail_alani3;
    private javax.swing.JTextField bashekimParola_alani3;
    private javax.swing.JTextField bashekimSoyad_alani3;
    private javax.swing.JTextField bashekimTc_alani3;
    private javax.swing.JComboBox<String> cinsiyetSec_box3;
    private javax.swing.JButton geri_butonu3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> unvanSec_alani3;
    // End of variables declaration//GEN-END:variables
}
