/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package Model;


import java.sql.SQLException;


/**
 *
 * @author Hp
 */
public class PlayerTable extends DB{
    
    public PlayerTable() throws Exception, SQLException{
        //konstruktor 
        super();
    }
    
    public void getPlayer(){

        //mengeksekusi query untuk mengambil semua data pada tabel dan diurutkan berdasarkan point adapt terbesar ke kecil
        try{
            String query = "SELECT * FROM player ORDER BY adapt DESC"; 
            createQuery(query);

        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan 
            System.out.println(e.toString());
        }
    }
    
   
    public void add(String username){
        
        //mengeksekusi query untuk menambah data pada tabel 
        try{
            String query = "INSERT INTO player (username, fall, adapt) VALUES (\""+username+"\", 0,0)"; 
            
            createUpdate(query);

        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan 
            System.out.println(e.toString());
       
        }
    }
    
        
    public void update(int id_player, int Fall, int Adapt){
        
        //mengeksekusi query untuk update data fall dan adapt user pada tabel player
        try{
            String query = "update player set fall = " + Fall + ", adapt = " + Adapt + " where id_player = " + id_player;
            createUpdate(query);

        }catch(Exception e){
            //tampilkan kesalahan jika terjadi kesalahan 
            System.out.println(e.toString());
          
        }
    }
    
    
}
