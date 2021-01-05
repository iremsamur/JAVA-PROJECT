/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.irem.jframes;

import com.irem.models.CalismaSaatleri;
import com.irem.models.Hasta;
import com.irem.models.Poliklinik;
import com.irem.models.Item;
import com.irem.models.Randevu;
import com.irem.models.User;
import com.irem.veritabani.YardimciMethotlar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author İREM SAMUR
 */
public class HastaIslemEkrani extends javax.swing.JFrame {
     private static Hasta hasta = new Hasta();
     private Poliklinik poliklinik = new Poliklinik();
     private DefaultTableModel doktorModel = null;
     private CalismaSaatleri calismaSaatleri = new CalismaSaatleri();
     private DefaultTableModel randevuModel = null;
     private int seciliDoktorId = 0;
     private String seciliDoktorAdi = null;
     private String seciliDoktorSoyadi = null;
     private String seciliDoktorUnvani = null;
     private String hastaAdi ;
     private String hastaSoyadi;
     private int hastaId;
     private String tcNo;
     private String yas;
     private Randevu randevu= new Randevu();
     private DefaultTableModel hastaRandevusuModel = null;
     private static String hastaIsmi;
     private static String hastaSoyismi;
     private static int hastaIdDegeri;
    private static String hastaTc;
    private static String hastaMail;
    private static String hastaCepTel;
    private static String hastaAdres;
    private static String hastaCinsiyet;
    

    /**
     * Creates new form HastaIslemEkrani
     */
    public HastaIslemEkrani(Hasta hasta) {//Hasta classı bilgilerini kullanabilmek için
        //constructor içinde tanımlarım
        initComponents();
        //Sisteme giriş yapan hastanın bilgilerini tutar
        hastaId = hasta.getId();
        hastaAdi =hasta.getAd();
        hastaSoyadi = hasta.getSoyad();
        tcNo = hasta.getTcno();
        
        yas = hasta.getYas();
        String ad = hasta.getAd();
        hastaIsmi = ad;
        String soyisim = hasta.getSoyad();
        hastaSoyismi = soyisim;
        String tc = hasta.getTcno();
        hastaTc = tc;
        String mail = hasta.getMail_adresi();
        hastaMail = mail;
        String cep = hasta.getCeptel_no();
        hastaCepTel = cep;
         String adres = hasta.getAdres();
        hastaAdres = adres;
        String cinsiyet = hasta.getCinsiyet();
        hastaCinsiyet = cinsiyet;
        
        int idDegeri = hasta.getId();
        hastaIdDegeri= idDegeri;
        //System.out.println(hastaIdDegeri);
        
        comboBoxPoliklinikGoster();//poliklinikleri gösterir
        hastaRandevusuModel = (DefaultTableModel) randevularım_tablosu.getModel();
        randevularımıGoster();//hastaya aldığı randevuları gösterir
        String mesaj = hasta.getAd()+" "+hasta.getSoyad();
        
        

       JOptionPane.showMessageDialog(this, "Hoşgeldiniz Sayın,  "+mesaj);//Constructor içine static olarak tanımladığımız Bashekim classını 
       //atayarak böylece veritabanından alıp login classında eşitlediğimiz bilgilerini burada gösterdik
        //System.out.println(doctor.getId()+doctor.getName());
        mesaj_etiketi.setText(mesaj+" Hesabı ");
    }
    
    public static String hastaAdiniDondur(){
        return hastaIsmi;
    }
    public static String hastaSoyadiniDondur(){
        return hastaSoyismi;
    }
    public static int hastaIdDegeriDondur(){
        return hastaIdDegeri;
    }
  
    public static String hastaTcNoDegeriDondur(){
        return hastaTc;
    }
     public static String hastaMailDondur(){
        return hastaMail;
    }
     public static String hastaCepTelDegeriDondur(){
        return hastaCepTel;
    }
      public static String hastaAdresDondur(){
        return hastaAdres;
    }
       public static String hastaCinsiyetDondur(){
        return hastaCinsiyet;
    }
      
    public void comboBoxPoliklinikGoster(){
        try {
            //Combobox içine veritabanında bulunan poliklinikleri bu şekilde ekleyebiliriz.
            poliklinikSec_kutusu.addItem("--Poliklinik Seç--");
            for(int i=0;i<poliklinik.poliklinikListele().size();i++){
                poliklinikSec_kutusu.addItem(new Item(poliklinik.poliklinikListele().get(i).getKlinik_adi(),poliklinik.poliklinikListele().get(i).getId()));
                
            }
            poliklinikSec_kutusu.addActionListener(new ActionListener() {//Combobox 'ı dinleriz ActionListener interface'ini kullanarak
                @Override
                public void actionPerformed(ActionEvent e) {
                    //new ActionListener interface'i içinde bulunan methot override edildi
                    if(poliklinikSec_kutusu.getSelectedIndex()!=0){
                        JComboBox c = (JComboBox) e.getSource();//GetSourcedan gelen değerin türünü bilmediğim için tür dönüşümü yaparım
                        Item item = (Item) c.getSelectedItem();
                        //System.out.println(item.getKey()+"--"+item.getValue());
                         doktorModel = (DefaultTableModel) doktorlar_tablosu.getModel();
                         doktorModel.setRowCount(0);
                         //Burada kullanıcının seçtiği polikliniğin doktorlarını tabloda listeler
                          //Bu goruntule methodunu diğer methodların için de de kullanacağım için , her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
                         ArrayList<User> poliklinikDoktorlari = new ArrayList<User>();//Bir tane User classı türünden poliklinikDoktorlari referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
                        try {
                            poliklinikDoktorlari = poliklinik.poliklinikDoktorlariListele(item.getId());//Poliklinik classı içindeki poliklinikDoktorlariListele() methodu bir tane arraylist döner o yüzden bunu poliklinikDoktorlari referanslı arrayliste atarım
                        
                            if(poliklinikDoktorlari != null){
            //poliklinikDoktorlari null değilse artık poliklinik doktorları tabloma ekleme işlemlerini yaparım
                               for(User poliklinikDoktor : poliklinikDoktorlari){
                //foreach ile bu poliklinikDoktorlari üzerinde gezinirim bu poliklinikDoktorlari' nın tipi classtır User classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                               Object[] eklenecek = {poliklinikDoktor.getId(),poliklinikDoktor.getAd(),poliklinikDoktor.getSoyad(),poliklinikDoktor.getUnvan()};//Böylece Calisan classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                               doktorModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
        }
    }
                        
                        } catch (SQLException ex) {
                            Logger.getLogger(HastaIslemEkrani.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 
                         
                        
                        
                    }
                    else{
                        doktorModel = (DefaultTableModel) doktorlar_tablosu.getModel();
                        doktorModel.setRowCount(0);
                        
                    }
                    
                    
                    
                }
            });
        } catch (SQLException ex) {
            Logger.getLogger(HastaIslemEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        
              
       
        
    }
    
     public void randevularımıGoster(){
         //Giriş yapan hastaya aldığı randevuları gösterir
            
            hastaRandevusuModel.setRowCount(0);//Bu goruntule methodunu diğer methodların için de de kullanacağım için , her defasında içini boşaltmamız 0'lamamız gerekir. bu yüzden 0 bu komut ile 0'larız
            ArrayList<Randevu> randevularım = new ArrayList<Randevu>();//Bir tane Randevu classı türünden randevularım referanslı arraylist oluşturdum Bununla veritabanından gelen bilgileri tutup gösterecek
            try {
            
                randevularım =randevu.randevularımıListele(hastaId);//Randevu classı içindeki randevularımıListele(id) methodu ile giriş yapan hastanın id değerine göre sadece o hastanın randevularını veritabanından getirmeyi sağlar
// bir tane arraylist döner o yüzden bunu randevularıma atarım. Çünkü Arraylistimi randevularım referansı ile tanımlamıştım
        
            
                if(randevularım != null){
            //randevularım null değilse artık calisanlar tabloma ekleme işlemlerini yaparım
                   for(Randevu randevu : randevularım){
                //foreach ile bu randevularım üzerinde gezinirim bu calisanlar ın tipi classtır Randevu classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                    Object[] eklenecek = {randevu.getId(),randevu.getDoktor_id(),randevu.getDoktor_adi(),randevu.getDoktor_soyadi(),randevu.getDoktor_unvanı(),randevu.getRandevu_tarihi()};//Böylece Randevu classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                    hastaRandevusuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
               
            
                }
           }
    }       catch (SQLException ex) {
               Logger.getLogger(HastaIslemEkrani.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        doktorlar_tablosu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        poliklinikSec_kutusu = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        doktorSec_butonu = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        randevuTarihleri_tablosu = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        randevuAl_butonu = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        randevularım_tablosu = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        randevuId_alani = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        doktorId_alani = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        randevuTarihi_alani = new javax.swing.JTextField();
        randevuIptal_butonu = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        bilgilerimiGoster_item3 = new javax.swing.JMenuItem();
        bilgiGuncelle_item3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        cikis_item3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HASTA İŞLEM EKRANI");

        jPanel1.setBackground(new java.awt.Color(102, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(204, 0, 102)), "Hasta İşlem Ekranı", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(255, 0, 0))); // NOI18N
        jPanel1.setLayout(null);

        mesaj_etiketi.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        mesaj_etiketi.setForeground(new java.awt.Color(255, 0, 153));
        mesaj_etiketi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel1.add(mesaj_etiketi);
        mesaj_etiketi.setBounds(20, 40, 340, 50);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0), 4));
        jTabbedPane1.setForeground(new java.awt.Color(51, 0, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 51));
        jPanel2.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 102));
        jLabel2.setText("                      Doktor Listesi");
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 11, 350, 31);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 4));

        doktorlar_tablosu.setBackground(new java.awt.Color(153, 255, 204));
        doktorlar_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorlar_tablosu.setForeground(new java.awt.Color(153, 0, 204));
        doktorlar_tablosu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(doktorlar_tablosu);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 60, 350, 410);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 153));
        jLabel3.setText("        Poliklinik Adı");
        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanel2.add(jLabel3);
        jLabel3.setBounds(419, 53, 174, 34);

        poliklinikSec_kutusu.setBackground(new java.awt.Color(102, 255, 102));
        poliklinikSec_kutusu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        poliklinikSec_kutusu.setForeground(new java.awt.Color(255, 0, 153));
        poliklinikSec_kutusu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 255), 4));
        jPanel2.add(poliklinikSec_kutusu);
        poliklinikSec_kutusu.setBounds(419, 105, 174, 39);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 102));
        jLabel4.setText("            Randevu");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanel2.add(jLabel4);
        jLabel4.setBounds(420, 330, 174, 36);

        doktorSec_butonu.setBackground(new java.awt.Color(255, 255, 255));
        doktorSec_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorSec_butonu.setForeground(new java.awt.Color(255, 0, 51));
        doktorSec_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\327-3273218_clinician-icon-doctor-consulting-icon-hd-png-download_50x50.jpg")); // NOI18N
        doktorSec_butonu.setText("   Seç");
        doktorSec_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 153), 4));
        doktorSec_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doktorSec_butonuActionPerformed(evt);
            }
        });
        jPanel2.add(doktorSec_butonu);
        doktorSec_butonu.setBounds(419, 250, 180, 60);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 102));
        jLabel5.setText("    Seçili Doktor İçin Randevu Alınabilecek Tarihler");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 102), 4));
        jPanel2.add(jLabel5);
        jLabel5.setBounds(690, 10, 440, 31);

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 4));

        randevuTarihleri_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuTarihleri_tablosu.setForeground(new java.awt.Color(255, 0, 51));
        randevuTarihleri_tablosu.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(randevuTarihleri_tablosu);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(680, 60, 450, 410);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 102));
        jLabel6.setText("          Doktor Seç");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51), 4));
        jPanel2.add(jLabel6);
        jLabel6.setBounds(419, 183, 174, 36);

        randevuAl_butonu.setBackground(new java.awt.Color(153, 255, 255));
        randevuAl_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuAl_butonu.setForeground(new java.awt.Color(153, 0, 102));
        randevuAl_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\timing-icon-calendar-right-time-icon-11553520314p8n1f0v3kx_50x50.jpg")); // NOI18N
        randevuAl_butonu.setText("Randevu Al");
        randevuAl_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0), 4));
        randevuAl_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randevuAl_butonuActionPerformed(evt);
            }
        });
        jPanel2.add(randevuAl_butonu);
        randevuAl_butonu.setBounds(420, 380, 170, 50);

        jTabbedPane1.addTab("Randevu Sistemi", jPanel2);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 51));

        randevularım_tablosu.setBackground(new java.awt.Color(255, 255, 102));
        randevularım_tablosu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevularım_tablosu.setForeground(new java.awt.Color(255, 0, 0));
        randevularım_tablosu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Doktor Id", "Doktor Adı", "Doktor Soyadı", "Doktor Unvanı", "Randevu Tarihi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        randevularım_tablosu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                randevularım_tablosuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(randevularım_tablosu);

        jPanel5.setBackground(new java.awt.Color(255, 102, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255), 4));
        jPanel5.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("                       Randevu Id");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        jPanel5.add(jLabel9);
        jLabel9.setBounds(140, 50, 264, 40);

        randevuId_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuId_alani.setForeground(new java.awt.Color(255, 0, 0));
        randevuId_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel5.add(randevuId_alani);
        randevuId_alani.setBounds(140, 100, 260, 40);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("                       Doktor Id");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        jPanel5.add(jLabel1);
        jLabel1.setBounds(140, 160, 260, 40);

        doktorId_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doktorId_alani.setForeground(new java.awt.Color(255, 0, 0));
        doktorId_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel5.add(doktorId_alani);
        doktorId_alani.setBounds(140, 220, 260, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Randevu Tarihi");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 4));
        jPanel5.add(jLabel7);
        jLabel7.setBounds(140, 280, 260, 40);

        randevuTarihi_alani.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuTarihi_alani.setForeground(new java.awt.Color(255, 0, 0));
        randevuTarihi_alani.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 0), 4));
        jPanel5.add(randevuTarihi_alani);
        randevuTarihi_alani.setBounds(140, 330, 260, 40);

        randevuIptal_butonu.setBackground(new java.awt.Color(255, 255, 255));
        randevuIptal_butonu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        randevuIptal_butonu.setForeground(new java.awt.Color(0, 153, 255));
        randevuIptal_butonu.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\cancel_app_50x50.jpg")); // NOI18N
        randevuIptal_butonu.setText("Randevu İptal Et");
        randevuIptal_butonu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 4));
        randevuIptal_butonu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randevuIptal_butonuActionPerformed(evt);
            }
        });
        jPanel5.add(randevuIptal_butonu);
        randevuIptal_butonu.setBounds(180, 380, 200, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 0));
        jLabel8.setText("    RANDEVU İPTALİ");
        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 0, 255), 4));
        jPanel5.add(jLabel8);
        jLabel8.setBounds(160, 14, 220, 30);

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\depositphotos_99831824-stock-illustration-doctor-appointment-flat-long-shadow_100x100.jpg")); // NOI18N
        jPanel5.add(jLabel10);
        jLabel10.setBounds(10, 10, 110, 130);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Randevularım", jPanel3);

        jPanel1.add(jTabbedPane1);
        jTabbedPane1.setBounds(30, 130, 1240, 530);

        jLabel11.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\randevupng_100x100.jpg")); // NOI18N
        jPanel1.add(jLabel11);
        jLabel11.setBounds(610, 20, 100, 100);

        jMenu1.setText("Hesabım");

        bilgilerimiGoster_item3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_DOWN_MASK));
        bilgilerimiGoster_item3.setText("Bilgilerimi Göster");
        bilgilerimiGoster_item3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgilerimiGoster_item3ActionPerformed(evt);
            }
        });
        jMenu1.add(bilgilerimiGoster_item3);

        bilgiGuncelle_item3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        bilgiGuncelle_item3.setText("Bilgileri Güncelle");
        bilgiGuncelle_item3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bilgiGuncelle_item3ActionPerformed(evt);
            }
        });
        jMenu1.add(bilgiGuncelle_item3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Çıkış");

        cikis_item3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_DOWN_MASK));
        cikis_item3.setIcon(new javax.swing.ImageIcon("C:\\Users\\İREM SAMUR\\Desktop\\javaProjects\\netbeansProjects\\HastaneOtomasyonuProjesi\\src\\com\\irem\\jframes\\image\\4700771_exit-power-off-icon-white-hd-png-download_50x50.jpg")); // NOI18N
        cikis_item3.setText("Çıkış");
        cikis_item3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cikis_item3ActionPerformed(evt);
            }
        });
        jMenu2.add(cikis_item3);

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

    private void doktorSec_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doktorSec_butonuActionPerformed
        // TODO add your handling code here:
        
        int seciliSatir = doktorlar_tablosu.getSelectedRow();//Seçilen doktoru al
        if(seciliSatir>=0){
            String satir = doktorlar_tablosu.getModel().getValueAt(seciliSatir,0).toString();
            int id = Integer.parseInt(satir);
            randevuModel = (DefaultTableModel) randevuTarihleri_tablosu.getModel();
            randevuModel.setRowCount(0);
            ArrayList<CalismaSaatleri> randevular = new ArrayList<CalismaSaatleri>();
            try {
                randevular = calismaSaatleri.calismaSaatleriListele(id);
                        
                if(randevular != null){
            //randevular null değilse artık calisanlar tabloma ekleme işlemlerini yaparım
                    for(CalismaSaatleri randevu : randevular){
                //foreach ile bu randevular üzerinde gezinirim bu randevular' ın tipi classtır CalismaSaatleri classı olur
                //Object arrayi oluşturarak model üzerinden değerlerimi tablo ya eklerim
                        Object[] eklenecek = {randevu.getId(),randevu.getTarih()};//Böylece CalismaSaatleri classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                        randevuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
                        //Seçili doktorun müsait randevu tarihlerini hastaya gösterir
                        
               
            
        }
    }
                        
                        } catch (SQLException ex) {
                            Logger.getLogger(HastaIslemEkrani.class.getName()).log(Level.SEVERE, null, ex);
                        }
            //Burada hastanın tablodan seçtiği doktorun id si ve ismi alınır ve bu yukarıda tanımladığım değere eşitlenir. Her butona basıldığında seçili değerler değişir
            seciliDoktorId = id;
            seciliDoktorAdi = doktorlar_tablosu.getModel().getValueAt(seciliSatir, 1).toString();
            seciliDoktorSoyadi = doktorlar_tablosu.getModel().getValueAt(seciliSatir, 2).toString();
            seciliDoktorUnvani = doktorlar_tablosu.getModel().getValueAt(seciliSatir, 3).toString();
            //System.out.println(selectDoctorId+"------"+selectDoctorName);//Bununla doğru çalışıyor mu diye debuglar yaparak consoldan görebiliriz
            //Konsolun görevi bu tür durumları bu şekilde sout ile çalışmasını kontrol etmektir.Kullanıcı her seçtiğinde seçtiğe doktor ve id ye göre değerler değişir
            
        }
        else{
            YardimciMethotlar.mesajGoster("Lütfen bir doktor seçiniz!!");
        }
    }//GEN-LAST:event_doktorSec_butonuActionPerformed

    private void randevuAl_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randevuAl_butonuActionPerformed
        // TODO add your handling code here:
        //bu butona tıklayınca hastanın randevu alması sağlanır
          int seciliSatir = randevuTarihleri_tablosu.getSelectedRow();
          if(seciliSatir>=0){
            String tarih = randevuTarihleri_tablosu.getModel().getValueAt(seciliSatir, 1).toString();
            boolean kontrol = hasta.randevuOlustur(seciliDoktorId, hastaId, seciliDoktorAdi, seciliDoktorSoyadi,seciliDoktorUnvani, hastaAdi, hastaSoyadi,tcNo,yas, tarih);
            //seçili doktor id, şuanki giriş yapan hasta id , seçili doktor adı tablodan aldığım, şuanki giriş yapan kullanıcı adı ve tarih diye yazdım
            //System.out.println(hastaId+"--"+hastaAdi);
            if(kontrol){
                YardimciMethotlar.mesajGoster("basarili");
                hasta.calismaZamaniniGuncelle(seciliDoktorId, tarih);//hasta randevu alınca bir daha o tarihten randevu alınamaması için
                //veritabanında müsaitlik durumunu pasife çevirir ve tablodan gösterilmesini kaldırmış olur
                randevuTarihleriModelGuncelle(seciliDoktorId);//randevuTarihleri tablousunu aktif pasifliğe göre günceller
                randevularımıGoster();
                
            }
            else{
                YardimciMethotlar.mesajGoster("hata");
            }
            
            
        }
        else{
               YardimciMethotlar.mesajGoster("Lütfen geçerli bir tarih giriniz!!");
        }
        
        
    }//GEN-LAST:event_randevuAl_butonuActionPerformed

    private void randevularım_tablosuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_randevularım_tablosuMouseClicked
        // TODO add your handling code here:
        int seciliSatir = randevularım_tablosu.getSelectedRow();//Hangi satır seçildiys tablodan onu alır.
        randevuId_alani.setText(hastaRandevusuModel.getValueAt(seciliSatir, 0).toString());//her bir değeri kendi yazı alanına koyarım . 1.sütunu al anlamındadır
        //Burada seçili satırın 0.ıncı sütununu id sini alarak bu aldığı id'yi fld_doktorId ye yazar. Table dan aldıklarını
        //id'ye göre silecek bunun için bashekimin içine silme methodu yazarım
        doktorId_alani.setText(hastaRandevusuModel.getValueAt(seciliSatir, 1).toString());
        randevuTarihi_alani.setText(hastaRandevusuModel.getValueAt(seciliSatir, 5).toString());
    }//GEN-LAST:event_randevularım_tablosuMouseClicked

    private void randevuIptal_butonuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randevuIptal_butonuActionPerformed
        // TODO add your handling code here:
        //randevu iptalini böylece bu kez p olan müsaitlik durumunun a olmasını sağlar
        //Ve o tarihi tekrar seçilebilir yapar
        if(randevuId_alani.getText().length()==0){
            YardimciMethotlar.mesajGoster("Lütfen geçerli bir randevu seçiniz.");//İçine verdiğimiz mesajı helper'a göndererek ekrana onu yazdırır
        }
        else{
            if(YardimciMethotlar.onay("eminim")){
                int seciliId = Integer.parseInt(randevuId_alani.getText());
                boolean kontrol = hasta.randevuIptalEt(seciliId);
                if(kontrol){
                    int seciliDoktorId = Integer.valueOf(doktorId_alani.getText());
                    String tarih = randevuTarihi_alani.getText();
                    YardimciMethotlar.mesajGoster("basarili");
                    
                   
                    hasta.calismaZamaniniGuncelle2(seciliDoktorId, tarih);
                    randevuTarihleriModelGuncelle(seciliDoktorId);
                    randevularımıGoster();
                
            }
            }
        }
    }//GEN-LAST:event_randevuIptal_butonuActionPerformed

    private void bilgiGuncelle_item3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgiGuncelle_item3ActionPerformed
        // TODO add your handling code here:
        HastaBilgileriniGuncelle guncelle = new HastaBilgileriniGuncelle();
        guncelle.setVisible(true);
    }//GEN-LAST:event_bilgiGuncelle_item3ActionPerformed

    private void bilgilerimiGoster_item3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bilgilerimiGoster_item3ActionPerformed
        // TODO add your handling code here:
        HastaBilgileriniGoster goster = new HastaBilgileriniGoster();
        goster.setVisible(true);
    }//GEN-LAST:event_bilgilerimiGoster_item3ActionPerformed

    private void cikis_item3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cikis_item3ActionPerformed
        // TODO add your handling code here:
        AnaEkran ana = new AnaEkran();
        ana.setVisible(true);
        dispose();
    }//GEN-LAST:event_cikis_item3ActionPerformed

      public void randevuTarihleriModelGuncelle(int doktor_id){
          //Tabloyu günceller
        randevuModel = (DefaultTableModel) randevuTarihleri_tablosu.getModel();
        randevuModel.setRowCount(0);
        ArrayList<CalismaSaatleri> randevuTarihleri = new ArrayList<CalismaSaatleri>();
        try {
            randevuTarihleri = calismaSaatleri.calismaSaatleriListele(doktor_id);
             if(randevuTarihleri != null){
           
                    for(CalismaSaatleri randevu : randevuTarihleri){
                
                        Object[] eklenecek = {randevu.getId(),randevu.getTarih()};//Böylece CalismaSaatleri classıma bağlandım ve get methodu ile her içindeki bilgileri aldım
                        randevuModel.addRow(eklenecek);//model referansı ile veritabanından aldığım bu bilgileri addRow methodu ile Object array referansını vererek atarım
                
                        //Müsait randevu tarihlerini hastaya gösterir
                        
               
            
        }
    }

        

        } catch (SQLException ex) {
            Logger.getLogger(HastaIslemEkrani.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(HastaIslemEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HastaIslemEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HastaIslemEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HastaIslemEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HastaIslemEkrani(hasta).setVisible(true);//Main içine hasta classı referansını veririm
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem bilgiGuncelle_item3;
    private javax.swing.JMenuItem bilgilerimiGoster_item3;
    private javax.swing.JMenuItem cikis_item3;
    private javax.swing.JTextField doktorId_alani;
    private javax.swing.JButton doktorSec_butonu;
    private javax.swing.JTable doktorlar_tablosu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel mesaj_etiketi;
    private javax.swing.JComboBox<Object> poliklinikSec_kutusu;
    private javax.swing.JButton randevuAl_butonu;
    private javax.swing.JTextField randevuId_alani;
    private javax.swing.JButton randevuIptal_butonu;
    private javax.swing.JTextField randevuTarihi_alani;
    private javax.swing.JTable randevuTarihleri_tablosu;
    private javax.swing.JTable randevularım_tablosu;
    // End of variables declaration//GEN-END:variables
}
