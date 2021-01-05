/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.Bashekim;
import com.irem.models.Poliklinik;
import com.irem.models.Item;
import com.irem.models.User;
import com.irem.veritabani.YardimciMethotlar;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author İREM SAMUR
 */
public class BashekimEkrani extends javax.swing.JFrame {
     static Bashekim bashekim = new Bashekim();//Mecbur başhekim olacağı için onun bilgilerini burada kullanmak için static tanımladım
    
    Poliklinik poliklinik = new Poliklinik();
    private DefaultTableModel doktorModel = null;//Doktor bilgilerini tabloda tutmak için doktorModel oluştururuz
    private DefaultTableModel poliklinikModel = null;//Poliklinikler için tablo modeli
    private DefaultTableModel poliklinikDoktorlariModel = null;//Poliklinik ve doktorların birleşimi için tablo modeli
    private static String bashekimAdi;
    private static String bashekimSoyisim;
    private static String bashekimParola;
    private static int bashekimIdValue;
    private static String bashekimTcNo;
    private static String bashekimMail;
    private static String bashekimCepTel;
    private static String bashekimAdres;
     private static String bashekimCinsiyet;
     private static String bashekimUnvan;
    
     
    

    /**
     * Creates new form BashekimEkrani
     */
    public BashekimEkrani(Bashekim bashekim) {
        //Tanımladığımız Bashekim classını, giriş yapan kullanıcının bilgilerini burada kullanabilmek için 
        //constructor içinde tanımladık. Aynı zamanda bu classın nesnesini aşağıda main içinde de yazarız
        initComponents();
        comboBoxDoktorEkle();//Bu methodu çağırarak veritabanındaki doktorları comboBox'a ekler
        doktorModel = (DefaultTableModel) doktor_tablosu.getModel();//model referansını aldık () içinde DefaultTableModel ile tür dönüşümü yaparak.Böylece doktor tablomu bir tane modele atamış olurum
         try {//veritabanı işlemleri içeren methodu, bir hata durumunda hatayı yakalaması için try-catch içinde yazdım
             doktorGoruntule();//Mutlaka burada da doktorGoruntule methodunu çağırırım tabloda görünmesi için 
         } catch (SQLException ex) {
             Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
         }
        poliklinikModel = (DefaultTableModel) klinik_tablosu.getModel();
         try {
             poliklinikGoruntule();//polikliniklerin tabloda görünmesini sağlar
         } catch (SQLException ex) {
             Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
         }
        String mesaj =bashekim.getAd()+" "+bashekim.getSoyad();//giriş yapan kullanıcıya isim ve soyismini içeren mesaj verir
       YardimciMethotlar.optionPaneChangeButtonText();//JOPtionPane mesajlarının onay butonlarını türkçe istediğimiz yazı olarak değiştirir
       String bashekimIsim = bashekim.getAd();
       bashekimAdi = bashekimIsim;//Bashekim classından gelen, giriş yapan kullanıcının bilgilerini burada değişkenlerle tutarız
       String bashekimSoyad = bashekim.getSoyad();
       bashekimSoyisim = bashekimSoyad;
       
       int bashekimId = bashekim.getId();
       bashekimIdValue= bashekimId;
       String bashekimSifre = bashekim.getParola();
       bashekimParola = bashekimSifre;
       String bashekimTc = bashekim.getTcno();
       bashekimTcNo = bashekimTc;
       String mail = bashekim.getMail_adresi();
       bashekimMail = mail;
       String cepTel = bashekim.getCeptel_no();
       bashekimCepTel = cepTel;
       String adres = bashekim.getAdres();
       bashekimAdres = adres;
       String cinsiyet = bashekim.getCinsiyet();
       bashekimCinsiyet = cinsiyet;
       String unvan = bashekim.getUnvan();
       bashekimUnvan = unvan;
        
       JOptionPane.showMessageDialog(this, "Hoşgeldiniz sayın "+bashekim.getUnvan()+". Doktor "+mesaj);//Constructor içine static olarak tanımladığımız Bashekim classını 
       //atayarak böylece veritabanından alıp AnaEkran classında giriş yapan kullanıcının bilgilerini aldık ve eşitlediğimiz bilgilerini burada gösterdik
        mesaj_lbl.setText("Başhekim  "+bashekim.getUnvan()+". Doktor "+bashekim.getAd()+" "+bashekim.getSoyad()+" Hesabı ");
    }
        public void doktorGoruntule() throws SQLException{
        //Bashekim classı içinde oluşturduğumuz doktorlariListele methodunu kullanarak sadece doktorları ekrana getirmesini sağladık
          doktorModel.setRowCount(0);//Bu goruntule methodunu diğer methodların için de de kullanacağım için (doktor ekle gibi) her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
          ArrayList<User> doktorlar = new ArrayList<User>();//Bir tane User classı türünden doktorlar referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
          doktorlar = bashekim.doktorlariListele();//Bashekim classı içindeki doktorlariListele() methodu bir tane arraylist döner o yüzden bunu calisanlara atarım. Çünkü Arraylistimi calisanlar referansı ile tanımlamıştım
          if(doktorlar != null){
            //doktorlar null değilse artık doktorModel tabloma ekleme işlemlerini yaparım
            for(User doktor : doktorlar){
                //foreach ile bu doktorlar üzerinde gezinirim bu doktorların ın tipi classtır User classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                Object[] eklenecek = {doktor.getId(),doktor.getAd(),doktor.getSoyad(),doktor.getTcno(),doktor.getParola(),doktor.getMail_adresi(),doktor.getCeptel_no(),doktor.getAdres(),doktor.getCinsiyet(),doktor.getUnvan()};//Böylece Calisan classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                doktorModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
        }
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

        doktorCinsiyetSec_group = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        mesaj_lbl = new javax.swing.JLabel();
        uyarı_lbl = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        klinik_tablosu = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        poliklinikDoktorlar_tablosu = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        arama_cubugu2 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        poliklinikAdi_lbl = new javax.swing.JLabel();
        poliklinikAdi_alani = new javax.swing.JTextField();
        poliklinikEkle_butonu = new javax.swing.JButton();
        poliklinikGuncelle_butonu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        poliklinikId_alani = new javax.swing.JTextField();
        poliklinikSil_butonu = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        poliklinikSec_butonu = new javax.swing.JButton();
        doktorSec_kutusu = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        doktorSec_butonu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doktor_tablosu = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        doktorad_alani = new javax.swing.JTextField();
        doktorCepTelNo_alani = new javax.swing.JTextField();
        doktortc_alani = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        doktorparola_alani = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        doktorSoyad_alani = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        doktorMailAdres_alani1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        doktorAdres_alani = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        cinsiyetK_radio = new javax.swing.JRadioButton();
        cinsiyetE_radio = new javax.swing.JRadioButton();
        cinsiyetD_radio = new javax.swing.JRadioButton();
        jLabel19 = new javax.swing.JLabel();
        unvanSec_combo = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        doktorId_alani = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        doktorEkle_butonu = new javax.swing.JButton();
        doktorGuncelle_butonu = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        doktorSil_butonu = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        arama_cubugu = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        bilgilerimiGoster_item = new javax.swing.JMenuItem();
        bilgileriGuncelle_item = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        cikis_menu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BAŞHEKİM EKRANI");

        jPanel1.setBackground(new java.awt.Color(102, 255, 102));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(153, 0, 102)), "Başhekim Hesabı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        jPanel1.setLayout(null);

        mesaj_lbl.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        mesaj_lbl.setForeground(new java.awt.Color(255, 0, 102));
        mesaj_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));
        jPanel1.add(mesaj_lbl);
        mesaj_lbl.setBounds(30, 50, 360, 40);

        uyarı_lbl.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        uyarı_lbl.setForeground(new java.awt.Color(255, 0, 51));
        uyarı_lbl.setText("UYARI : Güncelleme işlemlerinde id değiştirilemez!!!!");
        uyarı_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 3));
        jPanel1.add(uyarı_lbl);
        uyarı_lbl.setBounds(450, 50, 770, 40);

        jTabbedPane1.setForeground(new java.awt.Color(0, 204, 204));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 102, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 0)));
        jPanel8.setLayout(null);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 4));

        klinik_tablosu.setBackground(new java.awt.Color(255, 255, 51));
        klinik_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        klinik_tablosu.setForeground(new java.awt.Color(255, 0, 102));
        klinik_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Poliklinik Adı"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        klinik_tablosu.setGridColor(new java.awt.Color(204, 0, 255));
        klinik_tablosu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klinik_tablosuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(klinik_tablosu);

        jPanel8.add(jScrollPane2);
        jScrollPane2.setBounds(10, 100, 390, 350);

        jScrollPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));

        poliklinikDoktorlar_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikDoktorlar_tablosu.setForeground(new java.awt.Color(255, 0, 51));
        poliklinikDoktorlar_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", " Poliklinik Doktor Adı", " Poliklinik Doktor Soyadı", "Doktor Ünvanı"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        poliklinikDoktorlar_tablosu.setGridColor(new java.awt.Color(153, 0, 255));
        poliklinikDoktorlar_tablosu.setSelectionBackground(new java.awt.Color(0, 204, 255));
        poliklinikDoktorlar_tablosu.setSelectionForeground(new java.awt.Color(153, 0, 102));
        jScrollPane3.setViewportView(poliklinikDoktorlar_tablosu);

        jPanel8.add(jScrollPane3);
        jScrollPane3.setBounds(620, 10, 500, 440);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 255));
        jLabel8.setText("                       Arama Alanı");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanel8.add(jLabel8);
        jLabel8.setBounds(10, 10, 390, 40);

        arama_cubugu2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        arama_cubugu2.setForeground(new java.awt.Color(0, 153, 153));
        arama_cubugu2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        arama_cubugu2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arama_cubugu2KeyReleased(evt);
            }
        });
        jPanel8.add(arama_cubugu2);
        arama_cubugu2.setBounds(10, 60, 390, 25);

        jSeparator2.setBackground(new java.awt.Color(102, 255, 102));
        jPanel8.add(jSeparator2);
        jSeparator2.setBounds(10, 90, 390, 10);

        jPanel9.setBackground(new java.awt.Color(255, 255, 0));
        jPanel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        poliklinikAdi_lbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikAdi_lbl.setForeground(new java.awt.Color(255, 0, 0));
        poliklinikAdi_lbl.setText("Poliklinik Adı");
        poliklinikAdi_lbl.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        poliklinikAdi_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikAdi_alani.setForeground(new java.awt.Color(255, 0, 0));
        poliklinikAdi_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        poliklinikEkle_butonu.setBackground(new java.awt.Color(255, 255, 255));
        poliklinikEkle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikEkle_butonu.setForeground(new java.awt.Color(255, 153, 0));
        poliklinikEkle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\vector-add-icon_50x50.jpg")); // NOI18N
        poliklinikEkle_butonu.setText("Klinik Ekle");
        poliklinikEkle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));
        poliklinikEkle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poliklinikEkle_butonuActionPerformed(evt);
            }
        });

        poliklinikGuncelle_butonu.setBackground(new java.awt.Color(255, 255, 255));
        poliklinikGuncelle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikGuncelle_butonu.setForeground(new java.awt.Color(255, 153, 0));
        poliklinikGuncelle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\191-1918674_colour-change-icon-hd-png-download_50x50.jpg")); // NOI18N
        poliklinikGuncelle_butonu.setText("Klinik Güncelle");
        poliklinikGuncelle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));
        poliklinikGuncelle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poliklinikGuncelle_butonuActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("Klinik Id");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 4));

        poliklinikId_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikId_alani.setForeground(new java.awt.Color(255, 0, 0));
        poliklinikId_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));

        poliklinikSil_butonu.setBackground(new java.awt.Color(255, 255, 255));
        poliklinikSil_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikSil_butonu.setForeground(new java.awt.Color(255, 153, 0));
        poliklinikSil_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\waste-vector-icon_50x50.jpg")); // NOI18N
        poliklinikSil_butonu.setText("Klinik Sil");
        poliklinikSil_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));
        poliklinikSil_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poliklinikSil_butonuActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 102));
        jLabel10.setText("Poliklinik Yönetimi İşlemleri");
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(poliklinikAdi_lbl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poliklinikAdi_alani, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(poliklinikId_alani, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(poliklinikGuncelle_butonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(poliklinikEkle_butonu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(poliklinikSil_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poliklinikAdi_lbl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(poliklinikAdi_alani, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(poliklinikEkle_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poliklinikGuncelle_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(poliklinikId_alani, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(poliklinikSil_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.add(jPanel9);
        jPanel9.setBounds(410, 10, 200, 440);

        jPanel10.setBackground(new java.awt.Color(0, 255, 204));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51), 4));
        jPanel10.setLayout(null);

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("Poliklinik - Doktor Atama");
        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel10.add(jLabel11);
        jLabel11.setBounds(10, 0, 160, 90);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("İşlemleri");
        jPanel10.add(jLabel12);
        jLabel12.setBounds(50, 50, 96, 53);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 204));
        jLabel13.setText("Poliklinik Adı");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 4));
        jPanel10.add(jLabel13);
        jLabel13.setBounds(30, 130, 130, 30);

        poliklinikSec_butonu.setBackground(new java.awt.Color(102, 255, 204));
        poliklinikSec_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikSec_butonu.setForeground(new java.awt.Color(255, 0, 0));
        poliklinikSec_butonu.setText("Poliklinik Seç");
        poliklinikSec_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        poliklinikSec_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poliklinikSec_butonuActionPerformed(evt);
            }
        });
        jPanel10.add(poliklinikSec_butonu);
        poliklinikSec_butonu.setBounds(30, 190, 130, 30);

        doktorSec_kutusu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        jPanel10.add(doktorSec_kutusu);
        doktorSec_kutusu.setBounds(30, 330, 130, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 153, 51));
        jLabel14.setText("Doktor Adı");
        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 153), 4));
        jPanel10.add(jLabel14);
        jLabel14.setBounds(30, 260, 130, 40);

        doktorSec_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorSec_butonu.setForeground(new java.awt.Color(204, 0, 102));
        doktorSec_butonu.setText("Doktor Ekle");
        doktorSec_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        doktorSec_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doktorSec_butonuActionPerformed(evt);
            }
        });
        jPanel10.add(doktorSec_butonu);
        doktorSec_butonu.setBounds(40, 373, 110, 40);

        jPanel8.add(jPanel10);
        jPanel10.setBounds(1120, 10, 170, 440);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Klinikler", jPanel7);

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(255, 0, 51)));
        jPanel2.setLayout(null);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204), 4));
        jScrollPane1.setForeground(new java.awt.Color(0, 102, 255));

        doktor_tablosu.setBackground(new java.awt.Color(255, 102, 255));
        doktor_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktor_tablosu.setForeground(new java.awt.Color(255, 0, 102));
        doktor_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Ad", "Soyad", "Tc kimlik No", "Parola", "Mail Adresi", "Cep Tel No", "Adres", "Cinsiyet", "Ünvan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        doktor_tablosu.setGridColor(new java.awt.Color(255, 0, 0));
        doktor_tablosu.setRowHeight(18);
        doktor_tablosu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doktor_tablosuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(doktor_tablosu);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 120, 650, 400);

        jPanel4.setBackground(new java.awt.Color(204, 51, 255));
        jPanel4.setForeground(new java.awt.Color(51, 204, 255));
        jPanel4.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 0));
        jLabel1.setText("Cep Telefon No");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel1);
        jLabel1.setBounds(300, 220, 140, 35);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("Soyad");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel2);
        jLabel2.setBounds(10, 120, 92, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 0));
        jLabel3.setText("Tc Kimlik No");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel3);
        jLabel3.setBounds(10, 180, 92, 34);

        doktorad_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorad_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorad_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktorad_alani);
        doktorad_alani.setBounds(120, 55, 146, 35);

        doktorCepTelNo_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorCepTelNo_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorCepTelNo_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktorCepTelNo_alani);
        doktorCepTelNo_alani.setBounds(460, 220, 146, 40);

        doktortc_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktortc_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktortc_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktortc_alani);
        doktortc_alani.setBounds(120, 180, 146, 40);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 0));
        jLabel5.setText("Parola");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel5);
        jLabel5.setBounds(10, 270, 90, 30);

        doktorparola_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorparola_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorparola_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktorparola_alani);
        doktorparola_alani.setBounds(120, 260, 150, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 51));
        jLabel4.setText("                                                       Doktor Hesap Bilgileri");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 4));
        jPanel4.add(jLabel4);
        jLabel4.setBounds(10, 10, 600, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 0));
        jLabel15.setText("Ad");
        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel15);
        jLabel15.setBounds(10, 55, 92, 35);

        doktorSoyad_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorSoyad_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorSoyad_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktorSoyad_alani);
        doktorSoyad_alani.setBounds(120, 120, 146, 40);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 0));
        jLabel16.setText("Ünvan");
        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel16);
        jLabel16.setBounds(300, 280, 140, 35);

        doktorMailAdres_alani1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorMailAdres_alani1.setForeground(new java.awt.Color(255, 0, 0));
        doktorMailAdres_alani1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204), 4));
        jPanel4.add(doktorMailAdres_alani1);
        doktorMailAdres_alani1.setBounds(460, 60, 146, 40);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 0));
        jLabel17.setText("Mail Adresi");
        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel17);
        jLabel17.setBounds(300, 60, 140, 35);

        doktorAdres_alani.setColumns(20);
        doktorAdres_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorAdres_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorAdres_alani.setRows(5);
        doktorAdres_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 255), 4));
        jScrollPane4.setViewportView(doktorAdres_alani);

        jPanel4.add(jScrollPane4);
        jScrollPane4.setBounds(460, 110, 150, 90);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 0));
        jLabel18.setText("Adres");
        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel18);
        jLabel18.setBounds(300, 120, 140, 35);

        doktorCinsiyetSec_group.add(cinsiyetK_radio);
        cinsiyetK_radio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cinsiyetK_radio.setForeground(new java.awt.Color(255, 0, 0));
        cinsiyetK_radio.setText("Kadın");
        jPanel4.add(cinsiyetK_radio);
        cinsiyetK_radio.setBounds(450, 320, 80, 25);

        doktorCinsiyetSec_group.add(cinsiyetE_radio);
        cinsiyetE_radio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cinsiyetE_radio.setForeground(new java.awt.Color(255, 0, 0));
        cinsiyetE_radio.setText("Erkek");
        jPanel4.add(cinsiyetE_radio);
        cinsiyetE_radio.setBounds(450, 360, 80, 25);

        doktorCinsiyetSec_group.add(cinsiyetD_radio);
        cinsiyetD_radio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cinsiyetD_radio.setForeground(new java.awt.Color(255, 0, 0));
        cinsiyetD_radio.setText("Diğer");
        jPanel4.add(cinsiyetD_radio);
        cinsiyetD_radio.setBounds(540, 340, 80, 25);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 0));
        jLabel19.setText("Cinsiyet");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 4));
        jPanel4.add(jLabel19);
        jLabel19.setBounds(300, 330, 140, 35);

        unvanSec_combo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        unvanSec_combo.setForeground(new java.awt.Color(255, 0, 0));
        unvanSec_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Doktor Ünvanı Seç--", "Ordinaryus", "Prof", "Doç", "Yardımcı Doçent", "Operatör", "Uzman", "Pratisyen" }));
        unvanSec_combo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel4.add(unvanSec_combo);
        unvanSec_combo.setBounds(460, 280, 160, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 0));
        jLabel6.setText("Doktor Id");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        jPanel4.add(jLabel6);
        jLabel6.setBounds(0, 330, 130, 40);

        doktorId_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorId_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorId_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 4));
        jPanel4.add(doktorId_alani);
        doktorId_alani.setBounds(150, 330, 110, 40);

        jPanel2.add(jPanel4);
        jPanel4.setBounds(670, 20, 620, 390);

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));

        doktorEkle_butonu.setBackground(new java.awt.Color(255, 255, 51));
        doktorEkle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorEkle_butonu.setForeground(new java.awt.Color(255, 0, 0));
        doktorEkle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\114-1148813_reinvent-qchp-home-home-occupational-outlook-handbook-doctor-icon-png_50x50.jpg")); // NOI18N
        doktorEkle_butonu.setText("Doktor Ekle");
        doktorEkle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        doktorEkle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doktorEkle_butonuActionPerformed(evt);
            }
        });

        doktorGuncelle_butonu.setBackground(new java.awt.Color(255, 255, 51));
        doktorGuncelle_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorGuncelle_butonu.setForeground(new java.awt.Color(255, 0, 0));
        doktorGuncelle_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\computer-icons-user-login-switch-icon-png-clip-art_50x50.jpg")); // NOI18N
        doktorGuncelle_butonu.setText("Doktor Bilgi Güncelle");
        doktorGuncelle_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        doktorGuncelle_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doktorGuncelle_butonuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(doktorEkle_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(doktorGuncelle_butonu, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(doktorEkle_butonu)
                    .addComponent(doktorGuncelle_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel5);
        jPanel5.setBounds(690, 420, 410, 100);

        jPanel6.setBackground(new java.awt.Color(51, 204, 255));

        doktorSil_butonu.setBackground(new java.awt.Color(255, 255, 51));
        doktorSil_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorSil_butonu.setForeground(new java.awt.Color(255, 0, 0));
        doktorSil_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\130-1300377_transparent-eliminate-clipart-delete-user-icon-png-png_50x50.jpg")); // NOI18N
        doktorSil_butonu.setText("Doktor Sil");
        doktorSil_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 102), 4));
        doktorSil_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doktorSil_butonuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(doktorSil_butonu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(doktorSil_butonu)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel6);
        jPanel6.setBounds(1110, 420, 180, 100);

        jLabel7.setBackground(new java.awt.Color(51, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("                                                           Arama Alanı");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 102), 4));
        jPanel2.add(jLabel7);
        jLabel7.setBounds(14, 15, 650, 33);

        arama_cubugu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        arama_cubugu.setForeground(new java.awt.Color(255, 0, 102));
        arama_cubugu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 0), 4));
        arama_cubugu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                arama_cubuguKeyReleased(evt);
            }
        });
        jPanel2.add(arama_cubugu);
        arama_cubugu.setBounds(14, 59, 650, 27);

        jSeparator1.setBackground(new java.awt.Color(255, 51, 204));
        jSeparator1.setForeground(new java.awt.Color(255, 0, 153));
        jPanel2.add(jSeparator1);
        jSeparator1.setBounds(14, 98, 650, 10);

        jTabbedPane1.addTab("Doktor Yönetimi", jPanel2);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(20, 100, 1300, 560);

        jMenu1.setText("Hesabım");

        bilgilerimiGoster_item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_DOWN_MASK | java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bilgilerimiGoster_item.setText("Bilgilerimi Göster");
        bilgilerimiGoster_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgilerimiGoster_itemActionPerformed(evt);
            }
        });
        jMenu1.add(bilgilerimiGoster_item);

        bilgileriGuncelle_item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_DOWN_MASK));
        bilgileriGuncelle_item.setText("Bilgileri Güncelle");
        bilgileriGuncelle_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgileriGuncelle_itemActionPerformed(evt);
            }
        });
        jMenu1.add(bilgileriGuncelle_item);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Çıkış");

        cikis_menu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        cikis_menu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\4700771_exit-power-off-icon-white-hd-png-download_50x50.jpg")); // NOI18N
        cikis_menu.setText("Çıkış");
        cikis_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikis_menuActionPerformed(evt);
            }
        });
        jMenu2.add(cikis_menu);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1335, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void doktor_tablosuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doktor_tablosuMouseClicked
        // TODO add your handling code here:
        //seçili satırı alır ve arayüze uygun yerlere bilgilerini yazar
        
        int selectedrow = doktor_tablosu.getSelectedRow();//Hangi satır seçildiys tablodan onu alır.
        
        doktorId_alani.setText(doktorModel.getValueAt(selectedrow, 0).toString());//her bir değeri kendi yazı alanına koyarım . 1.sütunu al anlamındadır
        //Burada seçili satırın 0.ıncı sütununu id sini alarak bu aldığı id'yi doktorId_alani'na  yazar. Tablodan  aldıklarını
        
        doktorad_alani.setText(doktorModel.getValueAt(selectedrow, 1).toString());
        doktorSoyad_alani.setText(doktorModel.getValueAt(selectedrow, 2).toString());
        doktortc_alani.setText(doktorModel.getValueAt(selectedrow, 3).toString());
        doktorparola_alani.setText(doktorModel.getValueAt(selectedrow, 4).toString());
        doktorMailAdres_alani1.setText(doktorModel.getValueAt(selectedrow, 5).toString());
        doktorCepTelNo_alani.setText(doktorModel.getValueAt(selectedrow, 6).toString());
        doktorAdres_alani.setText(doktorModel.getValueAt(selectedrow, 7).toString());
        
    }//GEN-LAST:event_doktor_tablosuMouseClicked

        public void comboBoxDoktorEkle(){
        //Combobox içine veritabanında bulunan doktorları bu şekilde ekleyebiliriz.
              
        try {
            for(int i=0;i<bashekim.doktorlariListele().size();i++){
                doktorSec_kutusu.addItem(new Item(bashekim.doktorlariListele().get(i).getAd()+" "+bashekim.doktorlariListele().get(i).getSoyad(),bashekim.doktorlariListele().get(i).getId()));//combobox 'ın properties kısımından type parameters içine string yerine kullanmak istediğim tür olan object yaparak tür hatasını önlerim
                
            }
            doktorSec_kutusu.addActionListener(e -> {//e pointer anlamındadır
                JComboBox c = (JComboBox) e.getSource();//tür dönüşümü yaptım JComboBox ile . değişen veriyi yakalarım 
                Item poliklinikDoktor = (Item) c.getSelectedItem();//seçilen item i bana verir
                //System.out.println(poliklinikDoktor.getId()+" : "+poliklinikDoktor.getAd());//Bununla bana combobox üzerinde tıkladığım doktorun id sinin ve name'inin gelip gelmediğini alırım. Bunu actionListener ile yapmayı sağlarım
                
                
            });
        } catch (SQLException ex) {
            Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void doktorEkle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doktorEkle_butonuActionPerformed
        // TODO add your handling code here:
         ///Bashekim, doktor ekle butonuna tıkladığında doktor eklemesini sağlarız
        if(doktorad_alani.getText().length()==0 || doktorCepTelNo_alani.getText().length()==0 || doktorparola_alani.getPassword().toString().length()==0 || doktortc_alani.getText().length()==0 || doktorMailAdres_alani1.getText().length()==0 || doktorCepTelNo_alani.getText().length()==0 || doktorAdres_alani.getText().length()==0  ){
            YardimciMethotlar.mesajGoster("dolu");//Tüm alanları doldurunuz mesajı vermesi için YardimciMethotlar içine dolu gönderiririm
        }
        else{
            //Arayüzden başhekimin girdiği bilgileri alır ve değişkenlere atar
            String tcNo = doktortc_alani.getText();
            String parola = new String(doktorparola_alani.getPassword());
            String isim = doktorad_alani.getText();
            String soyad = doktorSoyad_alani.getText();
            String mail = doktorMailAdres_alani1.getText();
            String cepTelNo = doktorCepTelNo_alani.getText();
            String adres = doktorAdres_alani.getText();
            cinsiyetK_radio.setActionCommand("Kadın");
            cinsiyetE_radio.setActionCommand("Erkek");
            cinsiyetD_radio.setActionCommand("Diğer");
            String unvan = (String) unvanSec_combo.getSelectedItem();
        
            String cinsiyet = doktorCinsiyetSec_group.getSelection().getActionCommand();//button grup ile seçilen cinsiyeti alır
            
            boolean kontrol = bashekim.doktorEkle(isim,soyad,tcNo,parola,mail,cepTelNo,adres,cinsiyet,unvan);//doktorEkle methodunu çağırdık
            if(kontrol){
                YardimciMethotlar.mesajGoster("basarili");//işlem başarılı mesajı verecek içine success gönderiririz
                doktortc_alani.setText(null);//null ile tüm yazı alanlarını işlemden sonra boşaltırım
                doktorparola_alani.setText(null);
                doktorad_alani.setText(null);
                doktorSoyad_alani.setText(null);
                doktorMailAdres_alani1.setText(null);
                doktorCepTelNo_alani.setText(null);
                doktorAdres_alani.setText(null);
                unvanSec_combo.setSelectedIndex(0);//0'ıncı index'ine döner
         
                
                
                try {
                    doktorGoruntule();//Güncellenmiş halini ekleme işleminden sonra bize göstermesini sağlar
                } catch (SQLException ex) {
                    Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_doktorEkle_butonuActionPerformed

    private void doktorGuncelle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doktorGuncelle_butonuActionPerformed
        // TODO add your handling code here:
        //doktor güncelleme işlemlerini yapar . Bu butona tıkladığımızda.
              if(doktortc_alani.getText().length()==0 || doktorad_alani.getText().length()==0 || doktorCepTelNo_alani.getText().length()==0 || doktorparola_alani.getPassword().toString().length()==0){
                  YardimciMethotlar.mesajGoster("Lütfen güncellenecek bir doktor seçiniz.");
            
        }
              else{
                  if(YardimciMethotlar.onay("Bilgilerdeki yaptığınız değişiklikleri kaydetmek istediğinizden emin misiniz?")){
                      int selectId = Integer.parseInt(doktorId_alani.getText());
                      String tcNo = doktortc_alani.getText();
                      String ad = doktorad_alani.getText();
                      String soyad = doktorSoyad_alani.getText();
                      String parola = new String(doktorparola_alani.getPassword());
                      String mail = doktorMailAdres_alani1.getText();
                      String cepTelNo = doktorCepTelNo_alani.getText();
                      String adres = doktorAdres_alani.getText();
                      
                      String unvan = (String) unvanSec_combo.getSelectedItem();
        
                      
                      
                      boolean kontrol = bashekim.doktorBilgisiGuncelle(selectId,ad, soyad, tcNo, parola,mail,cepTelNo,adres,unvan);
                      if(kontrol){
                          YardimciMethotlar.mesajGoster("basarili");
                          doktorId_alani.setText(null);
                          doktortc_alani.setText(null);
                          doktorad_alani.setText(null);
                          doktorSoyad_alani.setText(null);
                          doktorMailAdres_alani1.setText(null);
                          doktorCepTelNo_alani.setText(null);
                          doktorAdres_alani.setText(null);
                           try {
                               doktorGoruntule();
                    }      catch (SQLException ex) {
                        Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                   else{
                          YardimciMethotlar.mesajGoster("Bir hata oluştu.");
                }
            }
            
        }
    }//GEN-LAST:event_doktorGuncelle_butonuActionPerformed

    private void doktorSil_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doktorSil_butonuActionPerformed
        // TODO add your handling code here:
        //Doktor silme işlemini yapar
        if(doktorId_alani.getText().length()==0){
            YardimciMethotlar.mesajGoster("Lütfen silinecek bir doktor seçiniz.");//İçine verdiğimiz mesajı YardimciMethotlar'a göndererek ekrana onu yazdırır
        }
        else{
            if(YardimciMethotlar.onay("Seçili doktoru kaldırmak istediğinizden emin misiniz?")){
                int selectId = Integer.parseInt(doktorId_alani.getText());
                boolean kontrol = bashekim.doktorSil(selectId);//seçili satırdan seçili doktor id değerine göre doktoru siler
                if(kontrol){
                    YardimciMethotlar.mesajGoster("basarili");
                    doktorId_alani.setText(null);
                    try {
                        doktorGoruntule();
                    } catch (SQLException ex) {
                        Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
            }
        }

    }//GEN-LAST:event_doktorSil_butonuActionPerformed

       public void dinamikAra(String ara){
        //Tablodan dinamik arama her zaman bu şekilde yapılır. 
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(doktorModel);
        doktor_tablosu.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(ara));
        
    }
    private void arama_cubuguKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arama_cubuguKeyReleased
        // TODO add your handling code here:
         //keyreleased aktifleştirilerek arama çubuğu ile tablodan arama yapmamızı sağlar
        String ara = arama_cubugu.getText();//Arama cubugu üzerinden kullanıcının aradığı şeyi alır
        dinamikAra(ara);//dinamikAra isimli oluşturduğum method kullanıcının aramasını alarak ona göre tablo üzerinde arama yapacak
    }//GEN-LAST:event_arama_cubuguKeyReleased

     //Doktor için yapılan aynı işlemler poliklinik içinde yapılır
     public void poliklinikGoruntule() throws SQLException{
        //Poliklinik classı içinde oluşturduğumuz poliklinikListele methodunu  kullanarak sadece poliklinikleri ekrana getirmesini sağladık
          poliklinikModel.setRowCount(0);//Bu goruntule methodunu diğer methodların için de de kullanacağım için (poliklinik ekle gibi) her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
          ArrayList<Poliklinik> poliklinikler = new ArrayList<Poliklinik>();//Bir tane Poliklinik classı türünden poliklinikler referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
          poliklinikler = poliklinik.poliklinikListele();//Poliklinik  classı içindeki poliklinikleriListele() methodu bir tane arraylist döner o yüzden bunu poliklinikler'e atarım. Çünkü Arraylistimi poliklinikler referansı ile tanımlamıştım
          if(poliklinikler != null){
            //poliklinikler null değilse artık poliklinikModel tabloma ekleme işlemlerini yaparım
            for(Poliklinik poliklinik : poliklinikler){
                //foreach ile poliklinik ile, bu poliklinikler üzerinde gezinirim bu Poliklinik ın tipi classtır Poliklinik classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                Object[] eklenecek = {poliklinik.getId(),poliklinik.getKlinik_adi()};//Böylece Calisan classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                poliklinikModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
        }
    }

        
    }
    private void poliklinikEkle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poliklinikEkle_butonuActionPerformed
          ///poliklinik ekle butonuna tıkladığında yeni poliklinik eklemesini sağlarız
        if(poliklinikAdi_alani.getText().length()==0){
            YardimciMethotlar.mesajGoster("dolu");//Tüm alanları doldurunuz mesajı vermesi için YardimciMethotlar içine dolu gönderiririm
        }
        else{
            String poliklinik_adi = poliklinikAdi_alani.getText();
           
            boolean kontrol = poliklinik.poliklinikEkle(poliklinik_adi);
            if(kontrol){
                YardimciMethotlar.mesajGoster("basarili");//işlem başarılı mesajı verecek içine basarili gönderiririz
                poliklinikAdi_alani.setText(null);//null ile tüm yazı alanlarını işlemden sonra boşaltırım
               
                
                try {
                    poliklinikGoruntule();//eklenmiş yani üncellenmiş halini ekleme işleminden sonra bize göstermesini sağlar
                } catch (SQLException ex) {
                    Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
    }//GEN-LAST:event_poliklinikEkle_butonuActionPerformed

    private void poliklinikGuncelle_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poliklinikGuncelle_butonuActionPerformed
        // TODO add your handling code here:
        //Poliklinik Güncelleme yapar
         if(poliklinikId_alani.getText().length()==0){
            YardimciMethotlar.mesajGoster("Lütfen geçerli bir klinik seçiniz.");
            
        }
        else{
            if(YardimciMethotlar.onay("eminim")){
                int selectId = Integer.parseInt(poliklinikId_alani.getText());
               
                String poliklinik_adi = poliklinikAdi_alani.getText();
                
                boolean kontrol =poliklinik.poliklinikGuncelle(selectId, poliklinik_adi);
                if(kontrol){
                    YardimciMethotlar.mesajGoster("basarili");
                    poliklinikId_alani.setText(null);
                    poliklinikAdi_alani.setText(null);
                   
                    try {
                        poliklinikGoruntule();
                    } catch (SQLException ex) {
                        Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    YardimciMethotlar.mesajGoster("Bir hata oluştu.");
                }
            }
            
        }
    }//GEN-LAST:event_poliklinikGuncelle_butonuActionPerformed

    private void klinik_tablosuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klinik_tablosuMouseClicked
        // TODO add your handling code here:
        //seçili satırı alır ve arayüze uygun yere yazar
        
        int selectedrow = klinik_tablosu.getSelectedRow();//Hangi satır seçildiys tablodan onu alır.
        
        poliklinikId_alani.setText(poliklinikModel.getValueAt(selectedrow, 0).toString());//her bir değeri kendi yazı alanına koyarım . 1.sütunu al anlamındadır
        //Burada seçili satırın 0.ıncı sütununu id sini alarak bu aldığı id'yi poliklinikId_alani na  ye yazar. Table dan aldıklarını
        //id'ye göre silecek bunun için Poliklinik içine silme methodu yazarım
        poliklinikAdi_alani.setText(poliklinikModel.getValueAt(selectedrow, 1).toString());
    }//GEN-LAST:event_klinik_tablosuMouseClicked

    private void poliklinikSil_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poliklinikSil_butonuActionPerformed
        // TODO add your handling code here:
        if(poliklinikId_alani.getText().length()==0){
            YardimciMethotlar.mesajGoster("Lütfen silinecek bir poliklinik seçiniz.");//İçine verdiğimiz mesajı helper'a göndererek ekrana onu yazdırır
        }
        else{
            if(YardimciMethotlar.onay("Seçili polikliniği kaldırmak istediğinizden emin misiniz?")){
                int selectId = Integer.parseInt(poliklinikId_alani.getText());
                boolean kontrol = poliklinik.poliklinikSil(selectId);//id'ye göre poliklinik içindeki poliklinikSil methodu ile seçili polikliniği silme işlemini yapar
                if(kontrol){
                    YardimciMethotlar.mesajGoster("basarili");
                    poliklinikId_alani.setText(null);
                    try {
                        poliklinikGoruntule();
                    } catch (SQLException ex) {
                        Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            }
            }
        }

        
    }//GEN-LAST:event_poliklinikSil_butonuActionPerformed

      public void dinamikAra2(String ara){
        //Tablodan dinamik arama her zaman bu şekilde yapılır.
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(doktorModel);
        klinik_tablosu.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(ara));
        
    }
    private void arama_cubugu2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_arama_cubugu2KeyReleased
        // TODO add your handling code here:
        //keyreleased aktifleştirilerek arama çubuğu ile tablodan arama yapmamızı sağlar
        String ara = arama_cubugu2.getText();//Arama cubugu üzerinden kullanıcının aradığı şeyi alır
        dinamikAra(ara);//dinamikAra isimli oluşturduğum method kullanıcının aramasını alarak ona göre tablo üzerinde arama yapacak
        
    }//GEN-LAST:event_arama_cubugu2KeyReleased

    private void doktorSec_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doktorSec_butonuActionPerformed
        // TODO add your handling code here:
             int selRow = klinik_tablosu.getSelectedRow();//poliklinik tablosunda seçilen bir değer var mı kontrol eder
             if(selRow>=0){
            //Eğer bir poliklinik seçildiyse seçilen poliklinikte id 'yi alırım
                String seciliKlinik = klinik_tablosu.getModel().getValueAt(selRow, 0).toString();//tabloda seçilen kısımın id'sini al anlamına gelir.
            //şimdi bu string olarak alınan id'yi int'e dönüştürürüm
                int seciliKlinikId = Integer.parseInt(seciliKlinik);//Böylece tablodan seçili id yi alırım
                Item poliklinikDoktor= (Item) doktorSec_kutusu.getSelectedItem();//Burada type casting yaparım.combobox dan seçilen doktoru bize getirir
                String kontrol = bashekim.poliklinikDoktorEkle(poliklinikDoktor.getId(), seciliKlinikId);//Item ile içindeki seçilen doktorun value değeri yani id sini alırım
                if(kontrol=="true"){
                    YardimciMethotlar.mesajGoster("basarili");
                //ekleme yapıldıktan sonra tabloyu güncelleriz
                    poliklinikDoktorlariModel = (DefaultTableModel) poliklinikDoktorlar_tablosu.getModel();
                    poliklinikDoktorlariModel.setRowCount(0);
                    ArrayList<User> poliklinikDoktorlari = new ArrayList<User>();
                    try {
                        poliklinikDoktorlari = bashekim.poliklinikDoktorlariListele(seciliKlinikId);//CalisanIslemleri içindeki calisanlariGetir() methodu bir tane arraylist döner o yüzden bunu calisanlara atarım. Çünkü Arraylistimi calisanlar referansı ile tanımlamıştım
                        if(poliklinikDoktorlari != null){
                            for(User klinikDoktor : poliklinikDoktorlari){
                            Object[] eklenecek = {klinikDoktor.getId(),klinikDoktor.getAd(),klinikDoktor.getSoyad(),klinikDoktor.getUnvan()};//Böylece Calisan classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                            poliklinikDoktorlariModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
                    }
                }
                } catch (SQLException ex) {
                    Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
                }
            

            }
                else if(kontrol=="var"){
                    YardimciMethotlar.mesajGoster("Bu doktor zaten dolu.\nBir doktor sadece bir poliklinikde olabilir .Ve bir kez olabilir!!!");
                    //Bir doktorun sadece bir tane poliklinikde olmasını sağlar
                
            }
                else{
                    YardimciMethotlar.mesajGoster("error");
            }
            
            
        }
            else{
               YardimciMethotlar.mesajGoster("Lütfen bir poliklinik seçiniz!");
        }
    }//GEN-LAST:event_doktorSec_butonuActionPerformed

    private void poliklinikSec_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poliklinikSec_butonuActionPerformed
        // TODO add your handling code here:
        //Tabloya aktarım yapılır
        int seciliSatir = klinik_tablosu.getSelectedRow();
        if(seciliSatir>=0){
            String seciliKlinik = klinik_tablosu.getModel().getValueAt(seciliSatir, 0).toString();//tabloda seçilen kısımın id'sini al anlamına gelir.
            //şimdi bu string olarak alınan id'yi int'e dönüştürürüm
            int seciliKlinikId = Integer.parseInt(seciliKlinik);//Böylece tablodan seçili id yi alırım
            poliklinikDoktorlariModel = (DefaultTableModel) poliklinikDoktorlar_tablosu.getModel();
            poliklinikDoktorlariModel.setRowCount(0);
            ArrayList<User> poliklinikDoktorlar = new ArrayList<User>();//Bir tane User classı türünden poliklinikDoktorlar referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
            try {
                poliklinikDoktorlar = bashekim.poliklinikDoktorlariListele(seciliKlinikId);//Bashekim içindeki poliklinikDoktorlariListele() methodu bir tane arraylist döner o yüzden bunu calisanlara atarım. Çünkü Arraylistimi poliklinikDoktorlar referansı ile tanımlamıştım
            
                 if(poliklinikDoktorlar != null){
                     for(User poliklinikDoktor : poliklinikDoktorlar){
                         Object[] eklenecek = {poliklinikDoktor.getId(),poliklinikDoktor.getAd(),poliklinikDoktor.getSoyad(),poliklinikDoktor.getUnvan()};//Böylece User classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                         poliklinikDoktorlariModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(BashekimEkrani.class.getName()).log(Level.SEVERE, null, ex);
            }
         
        
            
            
        }
        else{
            YardimciMethotlar.mesajGoster("Lütfen bir poliklinik seçiniz!!");
        }
    }//GEN-LAST:event_poliklinikSec_butonuActionPerformed

    //static tanımlanan methotlar ile bashekim bilgilerini başka bir class  içinde sadece class adı ile çağırarak kullanmamızı sağlar
    public static String bashekimAdiniDondur(){
        return bashekimAdi;
    }
    public static String bashekimSoyadiniDondur(){
        return bashekimSoyisim;
    }
    public static int bashekimIdDegeriDondur(){
        return bashekimIdValue;
    }
    public static String bashekimParolasiDondur(){
        return bashekimParola;
    }
    public static String bashekimTcNoDegeriDondur(){
        return bashekimTcNo;
    }
     public static String bashekimMailDondur(){
        return bashekimMail;
    }
     public static String bashekimCepTelDegeriDondur(){
        return bashekimCepTel;
    }
      public static String bashekimAdresDondur(){
        return bashekimAdres;
    }
       public static String bashekimCinsiyetDondur(){
        return bashekimCinsiyet;
    }
        public static String bashekimUnvanDondur(){
        return bashekimUnvan;
    }
    private void bilgilerimiGoster_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgilerimiGoster_itemActionPerformed
        // TODO add your handling code here:
        BashekimBilgileriGoster bashekimBilgileriGoster = new BashekimBilgileriGoster();//BashekimBilgileriGoster ekranına gider
        bashekimBilgileriGoster.setVisible(true);
    }//GEN-LAST:event_bilgilerimiGoster_itemActionPerformed

    private void bilgileriGuncelle_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgileriGuncelle_itemActionPerformed
        // TODO add your handling code here:
        BashekimBilgileriGuncelle bashekimBilgileriGuncelle = new BashekimBilgileriGuncelle();
        bashekimBilgileriGuncelle.setVisible(true);
    }//GEN-LAST:event_bilgileriGuncelle_itemActionPerformed

    private void cikis_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikis_menuActionPerformed
        // TODO add your handling code here:
        AnaEkran anaEkran = new AnaEkran();
        anaEkran.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikis_menuActionPerformed

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
            java.util.logging.Logger.getLogger(BashekimEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BashekimEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BashekimEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BashekimEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BashekimEkrani(bashekim).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField arama_cubugu;
    private javax.swing.JTextField arama_cubugu2;
    private javax.swing.JMenuItem bilgileriGuncelle_item;
    private javax.swing.JMenuItem bilgilerimiGoster_item;
    private javax.swing.JMenuItem cikis_menu;
    private javax.swing.JRadioButton cinsiyetD_radio;
    private javax.swing.JRadioButton cinsiyetE_radio;
    private javax.swing.JRadioButton cinsiyetK_radio;
    private javax.swing.JTextArea doktorAdres_alani;
    private javax.swing.JTextField doktorCepTelNo_alani;
    private javax.swing.ButtonGroup doktorCinsiyetSec_group;
    private javax.swing.JButton doktorEkle_butonu;
    private javax.swing.JButton doktorGuncelle_butonu;
    private javax.swing.JTextField doktorId_alani;
    private javax.swing.JTextField doktorMailAdres_alani1;
    private javax.swing.JButton doktorSec_butonu;
    private javax.swing.JComboBox<Object> doktorSec_kutusu;
    private javax.swing.JButton doktorSil_butonu;
    private javax.swing.JTextField doktorSoyad_alani;
    private javax.swing.JTable doktor_tablosu;
    private javax.swing.JTextField doktorad_alani;
    private javax.swing.JPasswordField doktorparola_alani;
    private javax.swing.JTextField doktortc_alani;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable klinik_tablosu;
    private javax.swing.JLabel mesaj_lbl;
    private javax.swing.JTextField poliklinikAdi_alani;
    private javax.swing.JLabel poliklinikAdi_lbl;
    private javax.swing.JTable poliklinikDoktorlar_tablosu;
    private javax.swing.JButton poliklinikEkle_butonu;
    private javax.swing.JButton poliklinikGuncelle_butonu;
    private javax.swing.JTextField poliklinikId_alani;
    private javax.swing.JButton poliklinikSec_butonu;
    private javax.swing.JButton poliklinikSil_butonu;
    private javax.swing.JComboBox<String> unvanSec_combo;
    private javax.swing.JLabel uyarı_lbl;
    // End of variables declaration//GEN-END:variables
}
