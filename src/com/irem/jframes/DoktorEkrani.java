/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.CalismaSaatleri;
import com.irem.models.Doktor;
import com.irem.models.Randevu;
import com.irem.veritabani.YardimciMethotlar;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author İREM SAMUR
 */
public class DoktorEkrani extends javax.swing.JFrame {
    //Doktor nesnesinin methodlarını kullanabilmek için burada tanımlarım
    static Doktor doktor = new Doktor();
    private String isim;//Doktor bilgilerini ancak bu şekilde kullanabildim
    private String soyad;
    private String unvan;
    private int id;
    private static String doktorAdi;
    private static String doktorSoyadi;//static methot içinde kullanabilmek için giriş yapan doktor bilgilerini tutacak değişkenleri static tanımladım
    private static int doktorId;
    private static String doktorTc;
    private static String doktorMail;
    private static String doktorCepTel;
    private static String doktorAdres;
    private static String doktorCinsiyet;
    private static String doktorUnvan;
    
    
    //Kullanacağım tablolar için modeller oluşturdum
    private DefaultTableModel calismaSaatleriModel = null;
    private Randevu randevu= new Randevu();
    private DefaultTableModel doktorRandevusuModel = null;

    /**
     * Creates new form DoktorEkrani
     */
    public DoktorEkrani(Doktor doktor) {
        initComponents();
         
        isim = doktor.getAd();//giren doktor bilgilerini almak için
        soyad = doktor.getSoyad();
        unvan = doktor.getUnvan();
        id = doktor.getId();
        String ad = doktor.getAd();
        doktorAdi = ad;
        String soyisim = doktor.getSoyad();
        doktorSoyadi = soyisim;
        String tc = doktor.getTcno();
        doktorTc = tc;
        String mail = doktor.getMail_adresi();
        doktorMail = mail;
        String cep = doktor.getCeptel_no();
        doktorCepTel = cep;
         String adres = doktor.getAdres();
        doktorAdres = adres;
        String cinsiyet = doktor.getCinsiyet();
        doktorCinsiyet = cinsiyet;
        String unvan = doktor.getUnvan();
        doktorUnvan = unvan;
        int idDegeri = doktor.getId();
        doktorId = idDegeri;
        
        
        
         
       calismaSaatleriModel = (DefaultTableModel) calismaSaatleri_tablosu.getModel();
        try {
            calismaSaatleriGoruntule();//Constructor içinde yazarak kullanıcı bu ekranı açtığında tablodaki bilgilerin görünmesini sağlar
        } catch (SQLException ex) {
            Logger.getLogger(DoktorEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        doktorRandevusuModel = (DefaultTableModel) hastaRandevuları_tablosu.getModel();
        randevularıGoster();//randevuların tabloda görünmesini sağlar
        
        String mesaj = doktor.getAd()+" "+doktor.getSoyad();
        
        

       JOptionPane.showMessageDialog(this, "Hoşgeldiniz sayın "+unvan+"  doktor  "+mesaj);//Constructor içine static olarak tanımladığımız Bashekim classını 
       //atayarak böylece veritabanından alıp login classında eşitlediğimiz bilgilerini burada gösterdik
        //System.out.println(doctor.getId()+doctor.getName());
        mesaj_etiketi.setText("Doktor  "+unvan+" "+mesaj+" Hesabı ");//mesaj etiketine giriş mesajı yazar
    }
    public static String doktorAdiniDondur(){
        return doktorAdi;
    }
    public static String doktorSoyadiniDondur(){
        return doktorSoyadi;
    }
    public static int doktorIdDegeriDondur(){
        return doktorId;
    }
  
    public static String doktorTcNoDegeriDondur(){
        return doktorTc;
    }
     public static String doktorMailDondur(){
        return doktorMail;
    }
     public static String doktorCepTelDegeriDondur(){
        return doktorCepTel;
    }
      public static String doktorAdresDondur(){
        return doktorAdres;
    }
       public static String doktorCinsiyetDondur(){
        return doktorCinsiyet;
    }
        public static String doktorUnvanDondur(){
        return doktorUnvan;
    }
     public void randevularıGoster(){
            
            doktorRandevusuModel.setRowCount(0);//Bu goruntule methodunu diğer methodların için de de kullanacağım için (randevu saati ekle gibi) her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
            ArrayList<Randevu> randevular = new ArrayList<Randevu>();//Bir tane Randevu classı türünden randevular referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
            try {
            
                randevular = randevu.hastaRandevularınıListele(id);//Randevu classı içindeki hastaRandevularınıListele() methodu ile giriş yapan doktorun id sine göre sadece o id'li doktorların hastalarını görmesini sağlar
// bir tane arraylist döner o yüzden bunu randevular'a atarım. Çünkü Arraylistimi randevular referansı ile tanımlamıştım
        
            
                if(randevular != null){
            //randevular null değilse artık randevulartabloma ekleme işlemlerini yaparım
                   for(Randevu randevu : randevular){
                //foreach ile bu randevular üzerinde gezinirim bu calisanlar ın tipi classtır Randevu classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                    Object[] eklenecek = {randevu.getId(),randevu.getHasta_adi(),randevu.getHasta_soyadi(),randevu.getHastaTc(),randevu.getHastaYas(),randevu.getRandevu_tarihi()};//Böylece Randevu classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                    doktorRandevusuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
                }
           }
    }       catch (SQLException ex) {
               Logger.getLogger(DoktorEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            
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
        mesaj_etiketi = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        tarih_secimi = new com.toedter.calendar.JDateChooser();
        saat_secimi = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tarihEkle_butonu = new javax.swing.JButton();
        tarihSil_butonu = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        calismaSaatleri_tablosu = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        hastaRandevuları_tablosu = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        bilgilerimiGoster_menu2 = new javax.swing.JMenuItem();
        bilgilerimiGuncelle_menu2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        cikisYap_menu2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DOKTOR EKRANI");

        jPanel1.setBackground(new java.awt.Color(51, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)), "Doktor Hesabı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(0, 102, 204))); // NOI18N
        jPanel1.setLayout(null);

        mesaj_etiketi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mesaj_etiketi.setForeground(new java.awt.Color(255, 0, 0));
        mesaj_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanel1.add(mesaj_etiketi);
        mesaj_etiketi.setBounds(60, 40, 384, 36);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 4));
        jTabbedPane1.setForeground(new java.awt.Color(255, 0, 153));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));

        tarih_secimi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        tarih_secimi.setForeground(new java.awt.Color(204, 0, 102));
        tarih_secimi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        saat_secimi.setBackground(new java.awt.Color(255, 51, 204));
        saat_secimi.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        saat_secimi.setForeground(new java.awt.Color(255, 0, 0));
        saat_secimi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00" }));
        saat_secimi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0), 4));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 204));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\187-1876915_calendar-icon-icono-de-fecha-png_50x50.jpg")); // NOI18N
        jLabel2.setText("     Tarih Seçimi");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 204));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\kisspng-portable-network-graphics-computer-icons-clock-vec-5cc8ec749783b8.1641939015566716046206_50x50.jpg")); // NOI18N
        jLabel3.setText("    Saat Seçimi");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));

        tarihEkle_butonu.setBackground(new java.awt.Color(0, 255, 102));
        tarihEkle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tarihEkle_butonu.setForeground(new java.awt.Color(255, 0, 102));
        tarihEkle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\computer-icons-download-clip-art-set-list_50x50.jpg")); // NOI18N
        tarihEkle_butonu.setText("Ekle");
        tarihEkle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        tarihEkle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarihEkle_butonuActionPerformed(evt);
            }
        });

        tarihSil_butonu.setBackground(new java.awt.Color(102, 255, 102));
        tarihSil_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tarihSil_butonu.setForeground(new java.awt.Color(255, 0, 102));
        tarihSil_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\100-512_50x50.jpg")); // NOI18N
        tarihSil_butonu.setText("Sil");
        tarihSil_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        tarihSil_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tarihSil_butonuActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 51, 102));
        jLabel4.setText("                                        Seçilen Çalışma Saatleri");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 102), 4));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));

        calismaSaatleri_tablosu.setBackground(new java.awt.Color(153, 255, 153));
        calismaSaatleri_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        calismaSaatleri_tablosu.setForeground(new java.awt.Color(255, 0, 102));
        calismaSaatleri_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tarih"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        calismaSaatleri_tablosu.setSelectionForeground(new java.awt.Color(255, 0, 0));
        jScrollPane1.setViewportView(calismaSaatleri_tablosu);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tarihEkle_butonu, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                            .addComponent(tarihSil_butonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tarih_secimi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(saat_secimi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tarih_secimi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saat_secimi, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tarihEkle_butonu)
                .addGap(57, 57, 57)
                .addComponent(tarihSil_butonu)
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Çalışma Saatleri", jPanel2);

        hastaRandevuları_tablosu.setBackground(new java.awt.Color(255, 153, 255));
        hastaRandevuları_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        hastaRandevuları_tablosu.setForeground(new java.awt.Color(0, 102, 255));
        hastaRandevuları_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Hasta Adı", "Hasta Soyadı", "Hasta Tc Kimlik No", "Hasta Yaşı", "Randevu Tarihi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(hastaRandevuları_tablosu);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1287, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Randevular", jPanel3);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(30, 90, 1300, 560);

        jMenu1.setText("Hesabım");

        bilgilerimiGoster_menu2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_DOWN_MASK));
        bilgilerimiGoster_menu2.setText("Bilgilerimi Göster");
        bilgilerimiGoster_menu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgilerimiGoster_menu2ActionPerformed(evt);
            }
        });
        jMenu1.add(bilgilerimiGoster_menu2);

        bilgilerimiGuncelle_menu2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        bilgilerimiGuncelle_menu2.setText("Bilgilerimi Guncelle");
        bilgilerimiGuncelle_menu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgilerimiGuncelle_menu2ActionPerformed(evt);
            }
        });
        jMenu1.add(bilgilerimiGuncelle_menu2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Çıkış");

        cikisYap_menu2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        cikisYap_menu2.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\4700771_exit-power-off-icon-white-hd-png-download_50x50.jpg")); // NOI18N
        cikisYap_menu2.setText("Çıkış");
        cikisYap_menu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikisYap_menu2ActionPerformed(evt);
            }
        });
        jMenu2.add(cikisYap_menu2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 681, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tarihEkle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarihEkle_butonuActionPerformed
        // TODO add your handling code here:
        //Tarihleri kullanmak için java SimpleDateFormat sınıfı kullanırım
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String tarih="";
        

       
        
     
        try{
            tarih = sdf.format(tarih_secimi.getDate());//getDate kullanarak jdate den seçilen tarihi alabiliyorum 
            
        }
        catch(Exception e2){
            
        }
        //String date = sdf.format(select_date.getDate());//getDate kullanarak jdate den seçilen tarihi alabiliyorum 
        
        if(tarih.length()==0){
            
            YardimciMethotlar.mesajGoster("Lütfen geçerli bir tarih seçiniz.");
        }
        else{
            String saat = " "+saat_secimi.getSelectedItem().toString();//Combox box içinde bulunan zamanı getSelectedItem ile alabilirim
            String selectDate = tarih+saat;//Seçilen aldığım tarih ve zaman birleştirilerek bir tarih oluşturulur
           
            String kontrol = doktor.randevuSaatiEkle(id, isim, soyad,unvan, selectDate);//Doktor classından oluşturduğum doktor nesnesi ile içindeki randevuSaatiEkle methodu ile doktorun kendisine uygun
            //çalışma saatleri ekleyebilmesini sağlarım
            if(kontrol=="true"){
                YardimciMethotlar.mesajGoster("basarili");
                try {
                    calismaSaatleriGoruntule();//Çalışma saatlerinn güncel halini  gösterir
                } catch (SQLException ex) {
                    Logger.getLogger(DoktorEkrani.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(kontrol=="var"){
                YardimciMethotlar.mesajGoster("Bu randevu saati zaten seçili.\nBir randevu saati bir kez seçilebilir!!");
                //Eğer methot var dönerse çalışma saati zaten seçili demektir. Bir kez seçilmesini sağlar
            }
            else{
                YardimciMethotlar.mesajGoster("hata!!");
            }
            
        }
    }//GEN-LAST:event_tarihEkle_butonuActionPerformed

    private void tarihSil_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tarihSil_butonuActionPerformed
        // TODO add your handling code here:
        int seciliSatir = calismaSaatleri_tablosu.getSelectedRow();//tablodan seçili satırı alır
        if(seciliSatir>=0){
            String selectRow = calismaSaatleri_tablosu.getModel().getValueAt(seciliSatir,0).toString();//tablodan seçilen satırın id değerini alır
            int seciliId = Integer.parseInt(selectRow);
            boolean kontrol =  doktor.calismaSaatiSil(seciliId);//doktor classında bulunan calismaSaatiSil methodu ile id ye göre silme işlemi yapar
            if(kontrol){
                YardimciMethotlar.mesajGoster("basarili");
               
                try {
                    calismaSaatleriGoruntule();
                } catch (SQLException ex) {
                    Logger.getLogger(DoktorEkrani.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                YardimciMethotlar.mesajGoster("hata");
            }
        }
        else{
            YardimciMethotlar.mesajGoster("Lütfen bir tarih seçiniz!!!");
        }
    }//GEN-LAST:event_tarihSil_butonuActionPerformed

    private void bilgilerimiGuncelle_menu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgilerimiGuncelle_menu2ActionPerformed
        // TODO add your handling code here:
      
        DoktorBilgileriGuncelle guncelle = new DoktorBilgileriGuncelle();
        guncelle.setVisible(true);
       
    }//GEN-LAST:event_bilgilerimiGuncelle_menu2ActionPerformed

    private void bilgilerimiGoster_menu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgilerimiGoster_menu2ActionPerformed
        // TODO add your handling code here:
        DoktorBilgileriGoster goster = new DoktorBilgileriGoster();
        goster.setVisible(true);
       
      
        
    }//GEN-LAST:event_bilgilerimiGoster_menu2ActionPerformed

    private void cikisYap_menu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikisYap_menu2ActionPerformed
        // TODO add your handling code here:
        AnaEkran ana = new AnaEkran();
        ana.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikisYap_menu2ActionPerformed

      public void calismaSaatleriGoruntule() throws SQLException{
        //Doktor classı içinde oluşturduğumuz calismaSaatleriListele(id) methodunu kullanarak sadece o an hesabına giriş yapan doktorun çalışma saatlerinin ekrana getirmesini sağladık
          calismaSaatleriModel.setRowCount(0);//Bu goruntule methodunu diğer methodların için de de kullanacağım için (calismasaati ekle gibi) her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
          ArrayList<CalismaSaatleri> calismaSaatleri = new ArrayList<CalismaSaatleri>();//Bir tane Calisan CalismaSaatleri türünden calisanlar referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
         //eğer Doktor doktor dan gelen id yi kullanmak istiyorsak doktor local olduğu için sadece constructorda değişkenleri
         // geçerli olur bunu yapabilmek için constructor içinde id tuttuk
         
          calismaSaatleri = doktor.calismaSaatleriListele(id);//Doktor classı içindeki calismaSaatleriListele() methodu bir tane arraylist döner o yüzden bunu calismaSaatleri'ne atarım. Çünkü Arraylistimi calismaSaatleri referansı ile tanımlamıştım
          if(calismaSaatleri != null){
            //calismaSaatleri null değilse artık calisma saatleri tabloma ekleme işlemlerini yaparım
            for(CalismaSaatleri calisma_saati : calismaSaatleri){
                //foreach ile bu calisanlar üzerinde gezinirim bu calismaSaatleri ın tipi classtır CalismaSaatleri classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                Object[] eklenecek = {calisma_saati.getId(),calisma_saati.getTarih()};//Böylece CalismaSaatleri classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                calismaSaatleriModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
        }
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
            java.util.logging.Logger.getLogger(DoktorEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoktorEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoktorEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoktorEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoktorEkrani(doktor).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bilgilerimiGoster_menu2;
    private javax.swing.JMenuItem bilgilerimiGuncelle_menu2;
    private javax.swing.JTable calismaSaatleri_tablosu;
    private javax.swing.JMenuItem cikisYap_menu2;
    private javax.swing.JTable hastaRandevuları_tablosu;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel mesaj_etiketi;
    private javax.swing.JComboBox<String> saat_secimi;
    private javax.swing.JButton tarihEkle_butonu;
    private javax.swing.JButton tarihSil_butonu;
    private com.toedter.calendar.JDateChooser tarih_secimi;
    // End of variables declaration//GEN-END:variables
}
