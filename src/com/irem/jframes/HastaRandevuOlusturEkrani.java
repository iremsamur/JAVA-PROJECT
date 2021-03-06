/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.CalismaSaatleri;
import com.irem.models.Hasta;
import com.irem.models.Item;
import com.irem.models.Poliklinik;
import com.irem.models.Randevu;
import com.irem.models.ResepsiyonistIslemleri;
import com.irem.models.User;
import com.irem.veritabani.YardimciMethotlar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author İREM SAMUR
 */
public class HastaRandevuOlusturEkrani extends javax.swing.JFrame {
    //Hastaya randevu oluşturma ekranıdır

     
     private Poliklinik poliklinik = new Poliklinik();
     private DefaultTableModel doktorModel = null;
     private CalismaSaatleri calismaSaatleri = new CalismaSaatleri();
     private DefaultTableModel randevuModel = null;
     private int seciliDoktorId = 0;
     private String seciliDoktorAdi = null;
     private String seciliDoktorSoyadi = null;
     private String seciliDoktorUnvani = null;
     
     private Randevu randevu= new Randevu();
     private DefaultTableModel hastaRandevusuModel = null;
    /**
     * Creates new form HastaRandevuOlusturEkrani
     */
    public HastaRandevuOlusturEkrani() {
        initComponents();
        comboBoxPoliklinikGoster();//poliklinikleri combo box da gösterir
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    public void comboBoxPoliklinikGoster(){
        try {
            //Combobox içine veritabanında bulunan poliklinikleri bu şekilde ekleyebiliriz.
            kayitPoliklinikSec_kutusu.addItem("--Poliklinik Seç--");
            for(int i=0;i<poliklinik.poliklinikListele().size();i++){
                kayitPoliklinikSec_kutusu.addItem(new Item(poliklinik.poliklinikListele().get(i).getKlinik_adi(),poliklinik.poliklinikListele().get(i).getId()));
                
            }
            kayitPoliklinikSec_kutusu.addActionListener(new ActionListener() {//Combobox 'ı dinleriz ActionListener interface'ini kullanarak
                @Override
                public void actionPerformed(ActionEvent e) {
                    //new ActionListener interface'i içinde bulunan methot
                    if(kayitPoliklinikSec_kutusu.getSelectedIndex()!=0){
                        JComboBox c = (JComboBox) e.getSource();//GetSourcedan gelen değerin türünü bilmediğim için tür dönüşümü yaparım
                        Item item = (Item) c.getSelectedItem();
                        //System.out.println(item.getKey()+"--"+item.getValue());
                         doktorModel = (DefaultTableModel) doktorlar_tablosu2.getModel();
                         doktorModel.setRowCount(0);
                         //Burada kullanıcının seçtiği polikliniğin doktorlarıın tabloda listeler
                          //Bu goruntule methodunu diğer methodların için de de kullanacağım için  her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
                         ArrayList<User> poliklinikDoktorlari = new ArrayList<User>();//Bir tane User classı türünden poliklinikDoktorlari referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
                        try {
                            poliklinikDoktorlari = poliklinik.poliklinikDoktorlariListele(item.getId());
                        
                            if(poliklinikDoktorlari != null){
            
                               for(User poliklinikDoktor : poliklinikDoktorlari){
                //foreach ile bu poliklinikDoktorlari üzerinde gezinirim bu poliklinikDoktorlari ın tipi classtır User classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                               Object[] eklenecek = {poliklinikDoktor.getId(),poliklinikDoktor.getAd(),poliklinikDoktor.getSoyad(),poliklinikDoktor.getUnvan()};//Böylece Calisan classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                               doktorModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
        }
    }
                        
                        } catch (SQLException ex) {
                            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                        }
                 
                         
                        
                        
                    }
                    else{
                        doktorModel = (DefaultTableModel) doktorlar_tablosu2.getModel();
                        doktorModel.setRowCount(0);
                        
                    }
                    
                    
                    
                }
            });
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
        
              
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanellll2 = new javax.swing.JPanel();
        jLabelll2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doktorlar_tablosu2 = new javax.swing.JTable();
        jLabellllll3 = new javax.swing.JLabel();
        kayitPoliklinikSec_kutusu = new javax.swing.JComboBox<>();
        jLabellll4 = new javax.swing.JLabel();
        kayitDoktorSec_butonu = new javax.swing.JButton();
        jLabellll5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        randevuTarihleri_tablosu2 = new javax.swing.JTable();
        jLabellll6 = new javax.swing.JLabel();
        randevuOlustur_butonu = new javax.swing.JButton();
        jLabellllll4 = new javax.swing.JLabel();
        kayitTc_alani = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        geriGit_butonu = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cikisYap_butonu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HASTA RANDEVU OLUŞTUR");

        jPanel1.setBackground(new java.awt.Color(0, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(204, 0, 102)));
        jPanel1.setLayout(null);

        jPanellll2.setBackground(new java.awt.Color(255, 255, 51));
        jPanellll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        jPanellll2.setLayout(null);

        jLabelll2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabelll2.setForeground(new java.awt.Color(255, 0, 102));
        jLabelll2.setText("                      Doktor Listesi");
        jLabelll2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanellll2.add(jLabelll2);
        jLabelll2.setBounds(30, 20, 350, 31);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 4));

        doktorlar_tablosu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Doktor Id", "Doktor Adı", "Doktor Soyadı", "Doktor Unvanı"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(doktorlar_tablosu2);

        jPanellll2.add(jScrollPane1);
        jScrollPane1.setBounds(30, 80, 350, 470);

        jLabellllll3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabellllll3.setForeground(new java.awt.Color(255, 0, 153));
        jLabellllll3.setText("Hasta Tc Kimlik No");
        jLabellllll3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanellll2.add(jLabellllll3);
        jLabellllll3.setBounds(470, 270, 174, 34);

        kayitPoliklinikSec_kutusu.setBackground(new java.awt.Color(102, 255, 102));
        kayitPoliklinikSec_kutusu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kayitPoliklinikSec_kutusu.setForeground(new java.awt.Color(255, 0, 153));
        kayitPoliklinikSec_kutusu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 255), 4));
        jPanellll2.add(kayitPoliklinikSec_kutusu);
        kayitPoliklinikSec_kutusu.setBounds(470, 100, 174, 39);

        jLabellll4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabellll4.setForeground(new java.awt.Color(255, 0, 102));
        jLabellll4.setText("            Randevu");
        jLabellll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanellll2.add(jLabellll4);
        jLabellll4.setBounds(470, 410, 174, 36);

        kayitDoktorSec_butonu.setBackground(new java.awt.Color(255, 255, 255));
        kayitDoktorSec_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kayitDoktorSec_butonu.setForeground(new java.awt.Color(255, 0, 51));
        kayitDoktorSec_butonu.setText("Seç");
        kayitDoktorSec_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 153), 4));
        kayitDoktorSec_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kayitDoktorSec_butonuActionPerformed(evt);
            }
        });
        jPanellll2.add(kayitDoktorSec_butonu);
        kayitDoktorSec_butonu.setBounds(470, 210, 174, 43);

        jLabellll5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabellll5.setForeground(new java.awt.Color(255, 0, 102));
        jLabellll5.setText("    Seçili Doktor İçin Randevu Alınabilecek Tarihler");
        jLabellll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanellll2.add(jLabellll5);
        jLabellll5.setBounds(780, 30, 440, 31);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 4));

        randevuTarihleri_tablosu2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tarihı"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(randevuTarihleri_tablosu2);

        jPanellll2.add(jScrollPane2);
        jScrollPane2.setBounds(780, 90, 450, 420);

        jLabellll6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabellll6.setForeground(new java.awt.Color(255, 0, 102));
        jLabellll6.setText("          Doktor Seç");
        jLabellll6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanellll2.add(jLabellll6);
        jLabellll6.setBounds(470, 150, 174, 36);

        randevuOlustur_butonu.setBackground(new java.awt.Color(153, 255, 255));
        randevuOlustur_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuOlustur_butonu.setForeground(new java.awt.Color(153, 0, 102));
        randevuOlustur_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\327-3273218_clinician-icon-doctor-consulting-icon-hd-png-download_50x50.jpg")); // NOI18N
        randevuOlustur_butonu.setText("Randevu Oluştur");
        randevuOlustur_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 4));
        randevuOlustur_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randevuOlustur_butonuActionPerformed(evt);
            }
        });
        jPanellll2.add(randevuOlustur_butonu);
        randevuOlustur_butonu.setBounds(460, 480, 190, 50);

        jLabellllll4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabellllll4.setForeground(new java.awt.Color(255, 0, 153));
        jLabellllll4.setText("        Poliklinik Adı");
        jLabellllll4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanellll2.add(jLabellllll4);
        jLabellllll4.setBounds(470, 40, 174, 34);

        kayitTc_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kayitTc_alani.setForeground(new java.awt.Color(255, 0, 0));
        kayitTc_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanellll2.add(kayitTc_alani);
        kayitTc_alani.setBounds(470, 330, 170, 50);

        jPanel1.add(jPanellll2);
        jPanellll2.setBounds(30, 90, 1280, 570);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("            HASTANE RANDEVU SİSTEMİ");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 4));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(440, 20, 420, 30);

        geriGit_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\back-158491_1280_50x50.jpg")); // NOI18N
        geriGit_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 4));
        geriGit_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                geriGit_butonuActionPerformed(evt);
            }
        });
        jPanel1.add(geriGit_butonu);
        geriGit_butonu.setBounds(1280, 10, 60, 59);

        jMenu1.setText("Çıkış");

        cikisYap_butonu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        cikisYap_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\342-0-0-0-10000-10000-1690_50x50.jpg")); // NOI18N
        cikisYap_butonu.setText("Çıkış Yap");
        cikisYap_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisYap_butonuActionPerformed(evt);
            }
        });
        jMenu1.add(cikisYap_butonu);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void kayitDoktorSec_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kayitDoktorSec_butonuActionPerformed
        // TODO add your handling code here:

        int seciliSatir = doktorlar_tablosu2.getSelectedRow();//Seçilen doktoru al
        if(seciliSatir>=0){
            String satir = doktorlar_tablosu2.getModel().getValueAt(seciliSatir,0).toString();
            int id = Integer.parseInt(satir);
            randevuModel = (DefaultTableModel) randevuTarihleri_tablosu2.getModel();
            randevuModel.setRowCount(0);
            ArrayList<CalismaSaatleri> randevular = new ArrayList<CalismaSaatleri>();//Bir tane CalismaSaatleri classı türünden randevular referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
            try {            
                randevular = calismaSaatleri.calismaSaatleriListele(id);//seçilen doktorun id'sine göre sadece o doktorun çalışma saatlerini listeler
            
                
                if(randevular != null){
                   
                    for(CalismaSaatleri randevu : randevular){
                        //foreach ile bu calisanlar üzerinde gezinirim bu randevular ın tipi classtır CalismaSaatleri classı olur
                        //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                        Object[] eklenecek = {randevu.getId(),randevu.getTarih()};//Böylece CalismaSaatleri classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                        randevuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım

                        //Müsait randevu tarihlerini doktora gösterir

                    }
                }
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

                

            
            //Burada hastanın tablodan seçtiği doktorun id si ve ismi alınır ve bu yukarıda tanımladığım değere eşitlenir. Her butona basıldığında seçili değerler değişir
            seciliDoktorId = id;
            seciliDoktorAdi = doktorlar_tablosu2.getModel().getValueAt(seciliSatir, 1).toString();
            seciliDoktorSoyadi = doktorlar_tablosu2.getModel().getValueAt(seciliSatir, 2).toString();
            seciliDoktorUnvani = doktorlar_tablosu2.getModel().getValueAt(seciliSatir, 3).toString();
            //System.out.println(selectDoctorId+"------"+selectDoctorName);//Bununla doğru çalışıyor mu diye debuglar yaparak consoldan görebiliriz
            //Konsolun görevi bu tür durumları bu şekilde sout ile çalışmasını kontrol etmektir.Kullanıcı her seçtiğinde seçtiğe doktor ve id ye göre değerler değişir

        }
        else{
            YardimciMethotlar.mesajGoster("Lütfen bir doktor seçiniz!!");
        }
    }//GEN-LAST:event_kayitDoktorSec_butonuActionPerformed

    private void randevuOlustur_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randevuOlustur_butonuActionPerformed
        // TODO add your handling code here:
        //bu butona tıklayınca hastanın randevu alması sağlanır
        String hastaTc = kayitTc_alani.getText();
        ResepsiyonistIslemleri res = new ResepsiyonistIslemleri();
        //String hastaTc = HastaKayit.hastaTcDondur();
       int id=0;
       String hastaIsmi="";
       String hastaSoyadi="";
       String tcNo ="";
       String yas  ="";
        //int  id = res.idDegeriGetir(hastaTc);
        //String hastaName =res.hastaAdiGetir(hastaTc);
         ArrayList<User> randevular = new ArrayList<User>();
        randevular = res.hastaBilgileriGetir(hastaTc);
        if(randevular != null){
           
            for(User randevu : randevular){
              
                id = randevu.getId();//arraylist içindeki randevu bilgilerini değişkenlere atarım
                hastaIsmi = randevu.getAd();
                hastaSoyadi = randevu.getSoyad();
                tcNo = randevu.getTcno();
                yas = randevu.getYas();
            }
        }
        int seciliSatir = randevuTarihleri_tablosu2.getSelectedRow();
        if(seciliSatir>=0){
            String tarih = randevuTarihleri_tablosu2.getModel().getValueAt(seciliSatir, 1).toString();
            boolean kontrol = res.randevuOlustur2(seciliDoktorId, id, seciliDoktorAdi, seciliDoktorSoyadi,seciliDoktorUnvani, hastaIsmi, hastaSoyadi,tcNo,yas, tarih);
            //seçili doktor id, şuanki giriş yapan hasta id , seçili doktor adı tablodan aldığım, şuanki giriş yapan kullanıcı adı ve tarih diye yazdım
            //System.out.println(hastaId+"--"+hastaAdi);
            if(kontrol){
                YardimciMethotlar.mesajGoster("basarili");
                res.calismaZamaniniGuncelle2(seciliDoktorId, tarih);
                randevuTarihleriModelGuncelle(seciliDoktorId);
                

            }
            else{
                YardimciMethotlar.mesajGoster("hata");
            }

        }
        else{
            YardimciMethotlar.mesajGoster("Lütfen geçerli bir tarih giriniz!!");
        }

    }//GEN-LAST:event_randevuOlustur_butonuActionPerformed

    private void cikisYap_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisYap_butonuActionPerformed
        // TODO add your handling code here:
        AnaEkran ana = new AnaEkran();
        ana.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikisYap_butonuActionPerformed

    private void geriGit_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_geriGit_butonuActionPerformed
        // TODO add your handling code here:
        ResepsiyonistEkrani res = new ResepsiyonistEkrani();
       
        res.setVisible(true);
        dispose();
    }//GEN-LAST:event_geriGit_butonuActionPerformed

    
     public void randevuTarihleriModelGuncelle(int doktor_id){
        randevuModel = (DefaultTableModel) randevuTarihleri_tablosu2.getModel();
        randevuModel.setRowCount(0);
        ArrayList<CalismaSaatleri> randevuTarihleri = new ArrayList<CalismaSaatleri>();
        try {
            randevuTarihleri = calismaSaatleri.calismaSaatleriListele(doktor_id);
             if(randevuTarihleri != null){
            
                    for(CalismaSaatleri randevu : randevuTarihleri){
                
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                        Object[] eklenecek = {randevu.getId(),randevu.getTarih()};
                        randevuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
                        //Müsait randevu tarihlerini doktora gösterir
                        
               
            
        }
    }

        

        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
                        
               
        
        
    }
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
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HastaRandevuOlusturEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HastaRandevuOlusturEkrani().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cikisYap_butonu;
    private javax.swing.JTable doktorlar_tablosu2;
    private javax.swing.JButton geriGit_butonu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelll2;
    private javax.swing.JLabel jLabellll4;
    private javax.swing.JLabel jLabellll5;
    private javax.swing.JLabel jLabellll6;
    private javax.swing.JLabel jLabellllll3;
    private javax.swing.JLabel jLabellllll4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanellll2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton kayitDoktorSec_butonu;
    private javax.swing.JComboBox<Object> kayitPoliklinikSec_kutusu;
    private javax.swing.JTextField kayitTc_alani;
    private javax.swing.JButton randevuOlustur_butonu;
    private javax.swing.JTable randevuTarihleri_tablosu2;
    // End of variables declaration//GEN-END:variables
}
