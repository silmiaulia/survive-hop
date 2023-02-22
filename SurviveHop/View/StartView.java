/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package View;

import Presenter.ProsesPlayer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hp
 */
public class StartView extends javax.swing.JFrame implements KontrakView{
    
    private ProsesPlayer prosesplayer; //untuk objek prosesPlayaer
    private MainGameView maingame; //untuk objek class MainGameView
    DefaultTableModel dataTabel = null; //untuk data pada tabel
    private String username; //untuk menyimpan username 
    int id_player = 0; //untuk menyimpan id_player

    /**
     * Creates new form StartView
     */
    public StartView() { //konstruktor
        initComponents();
        prosesplayer = new ProsesPlayer();
        setVisible(true);
    }
    
    
    
    public void tampilTabel(){ //untuk menampilkan data di database ke dalam tabel tbl_username
        
        try{
            
            prosesplayer.prosesDataPlayer(); //ambil data tabel player
            
            Object[] column = {"Username", "Fall", "Adapt"};
            dataTabel = new DefaultTableModel(null, column);
            
            for(int i=0; i<prosesplayer.getSize(); i++){
                
                Object[] hasil = new Object[3];

                hasil[0] = prosesplayer.getUsername(i);
                hasil[1] = prosesplayer.getFall(i);
                hasil[2] = prosesplayer.getAdapt(i);

                dataTabel.addRow(hasil); //add data ke tiap row di tabel
                
            }
            
            tbl_username.setModel(dataTabel); //set model tabel
            
            
        }catch(Exception e){
            
            System.out.println(prosesplayer.getError());

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

        PanelStart = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_username = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btn_quit = new javax.swing.JButton();
        btn_play = new javax.swing.JButton();
        PanelGame = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(182, 211, 239));

        PanelStart.setBackground(new java.awt.Color(221, 242, 250));

        tbl_username.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbl_username.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Fall", "Adapt"
            }
        ));
        tbl_username.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_usernameMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_username);
        if (tbl_username.getColumnModel().getColumnCount() > 0) {
            tbl_username.getColumnModel().getColumn(0).setMaxWidth(3500);
            tbl_username.getColumnModel().getColumn(1).setMaxWidth(800);
            tbl_username.getColumnModel().getColumn(2).setMaxWidth(800);
        }

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jLabel1.setText("THE SURVIVE HOP");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Username");

        btn_quit.setText("QUIT");
        btn_quit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitActionPerformed(evt);
            }
        });

        btn_play.setText("PLAY");
        btn_play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_playActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelStartLayout = new javax.swing.GroupLayout(PanelStart);
        PanelStart.setLayout(PanelStartLayout);
        PanelStartLayout.setHorizontalGroup(
            PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStartLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStartLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStartLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(169, 169, 169))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelStartLayout.createSequentialGroup()
                        .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(btn_quit, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(164, 164, 164))))
            .addGroup(PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelStartLayout.createSequentialGroup()
                    .addGap(214, 214, 214)
                    .addComponent(jLabel1)
                    .addContainerGap(104, Short.MAX_VALUE)))
        );
        PanelStartLayout.setVerticalGroup(
            PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelStartLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addGroup(PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_quit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_play, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
            .addGroup(PanelStartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelStartLayout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jLabel1)
                    .addContainerGap(385, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout PanelGameLayout = new javax.swing.GroupLayout(PanelGame);
        PanelGame.setLayout(PanelGameLayout);
        PanelGameLayout.setHorizontalGroup(
            PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
        PanelGameLayout.setVerticalGroup(
            PanelGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelStart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelStart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(PanelGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void btn_quitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitActionPerformed

        //exit jika button quit di tekan
        System.exit(0);
    }//GEN-LAST:event_btn_quitActionPerformed

    private void btn_playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_playActionPerformed

            //jika button play ditekan 
            
            try {
                
                username = tf_username.getText(); //ambil dari field username

                String checkUsername = "tidak"; //inisialisasi pengecekan username
                
                prosesplayer.prosesDataPlayer(); //ambil seluruh data dari tabel player di database

                if(!"".equals(username)){ //jika text yang ada di field tidak kosong
                    
                    for(int j=0; j<prosesplayer.getSize(); j++){
                        if(prosesplayer.getUsername(j).equals(username)){ //pencarian username yang diinput di field dan yang ada di database
                            //jika sama maka checkUsername menjadi ada, dan diambil nilai id_playernya
                            id_player = prosesplayer.getIdPlayer(j);
                            checkUsername = "ada";
                        }
                    }

                    if("tidak".equals(checkUsername)){ //jika username yang diinput tidak ada di database
                        //lakukan add ke dalam database tabel player
                        prosesplayer.addPlayer(username);
                        
                        for(int j=0; j<prosesplayer.getSize(); j++){
                            if(prosesplayer.getUsername(j).equals(username)){
                                //kemudian ambil id_player dari username yang baru di add ke database
                                id_player = prosesplayer.getIdPlayer(j);
                            }
                        }
                    }
                }
                
                if(id_player != 0){ //jika id_player tidak 0
                    
                    //instansiasi objek maingame view
                    maingame = new MainGameView(id_player);
                    setVisible(false); //buat frame menu tidak terlihat
                    
                    
                }

            }catch (Exception e){
                System.out.println(prosesplayer.getError());
            }
            
            
    }//GEN-LAST:event_btn_playActionPerformed

    private void tbl_usernameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_usernameMouseClicked
        // JIKA SALAH SATU RECORD PADA TABEL DI CLICK
        
        //Get Selected Data
        int row = tbl_username.getSelectedRow(); 
        
        String selectedUsername = (tbl_username.getModel().getValueAt(row, 0).toString()); //ambil value dari selected data di tabel

        for(int j=0; j<prosesplayer.getSize(); j++){
            //lakukan pencarian data yang di select pada database tabel player
            if (selectedUsername.equals(prosesplayer.getUsername(j))) {
                id_player = prosesplayer.getIdPlayer(j); //jika ada, ambil id_player nya
                break;
            }
        }
        
        
    }//GEN-LAST:event_tbl_usernameMouseClicked

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
            java.util.logging.Logger.getLogger(StartView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelGame;
    private javax.swing.JPanel PanelStart;
    private javax.swing.JButton btn_play;
    private javax.swing.JButton btn_quit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_username;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
