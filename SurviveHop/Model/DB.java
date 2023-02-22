/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.


package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 *
 * @author Hp
 */
public class DB {
    
    //konfigurasi koneksi
    private String CondAddress = 
    "jdbc:mysql://localhost:3306/db_survive_hop?user=root&password=";

    private Statement stmt = null; //koneksi query
    private ResultSet rs = null; //hasil query
    public Connection conn = null; //koneksi MySQL dengan basis data

    public DB() throws Exception, SQLException{ //koneksi ke database dan MySQL

        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            conn = DriverManager.getConnection(CondAddress);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_UNCOMMITTED);
        }catch(SQLException es){
            //mengeluarkan pesan error jika koneksi gaagal
            throw es;
            
        }
    }

    public void createQuery(String Query)throws Exception, SQLException{ //untuk eksekusi perintah sql tanpa mengubah isi data


        try{
            stmt = conn.createStatement();

            //eksekusi query
            rs = stmt.executeQuery(Query);

            if(stmt.execute(Query)){
                //ambil hasil query
                rs = stmt.getResultSet();
            }


        }catch(SQLException es){
            //mengeluarkan pesan error jika koneksi gaagal
            throw es;
        }
    }

    public void createUpdate(String Query)throws Exception, SQLException{

        // Method createQuery
        // Mengeksekusi query yang mengubah isi data (update, insert, delete)
        // Menerima masukan berupa string query

        try{

            stmt = conn.createStatement();

            // eksekusi query

            int hasil = stmt.executeUpdate(Query);

        }catch (SQLException es){

        // eksepsi jika query gagal dieksekusi

            throw es;
        }
    }
    


    public ResultSet getResult()throws Exception{

        // * Method getResult
        // * Memberikan hasil query

        ResultSet Temp = null;

        try{

            return rs;

        }catch (Exception ex){
        // eksepsi jika hasil tidak dapat dikembalikan
            return Temp;
        }

        
    }

    public void closeResult()throws SQLException, Exception{
        
        // untuk menutup hubungan dari eksekusi query
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException sqlEx){
                rs = null;
                throw sqlEx;
            }
        }

        if(stmt != null){
            try{
                stmt.close();
            }catch(SQLException sqlEx){
                stmt = null;
                throw sqlEx;
            }
        }

    }

    public void closeConnection()throws SQLException, Exception{
        // untuk menutup koneksi dengan MySQL dan basis data
        if(conn != null){
            try{
                conn.close();
            }catch(SQLException sqlEx){
                conn = null;
               
            }
        }
    }

}
