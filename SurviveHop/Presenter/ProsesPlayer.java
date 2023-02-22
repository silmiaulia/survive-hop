/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package Presenter;

import java.util.ArrayList;
import Model.DB;
import Model.PlayerTable;
import Model.Player;


/**
 *
 * @author Hp
 */
public class ProsesPlayer implements KontrakPresenter{
    
    private String error; //untuk pesan error
    private PlayerTable tabelplayer; //untuk objek class PlayerTable
    private ArrayList<Player> data; //untuk list data tabel
    
    public ProsesPlayer(){
        //konstruktor
        try{
            tabelplayer = new PlayerTable(); //instansiasi objek
            data = new ArrayList<Player>();
        }catch(Exception e){
            error = e.toString();
        }
    }
    
    
    public void prosesDataPlayer(){ //untuk mendapatkan semua data pada tabel player

        try{

            tabelplayer.getPlayer(); //ambil semua data player order by adapt
            while(tabelplayer.getResult().next()){

                Player player = new Player(); //instansiasi objek class player

                //set data player
                player.setIdPlayer(tabelplayer.getResult().getInt(1));
                player.setUsername(tabelplayer.getResult().getString(2));
                player.setAdapt(tabelplayer.getResult().getInt(3));
                player.setFall(tabelplayer.getResult().getInt(4));

                //simpan ke dalam list data
                data.add(player);

            }

            tabelplayer.closeResult();

            
        }catch(Exception e){
            error = e.toString();
        }

    }
    
    public void addPlayer(String username){ //method untuk menambahkan data baru ke dalam tabel database
        
        
        try{

            tabelplayer.add(username); //panggil methode untuk add
            tabelplayer.closeResult();

        }catch(Exception e){
            error = e.toString();
        }
    }
    
    public void updatePlayer(int id_player, int Fall, int Adapt){ //metode untuk mengupdate data pada tabel di database
        
        
        try{

            tabelplayer.update(id_player, Fall, Adapt); //panggil method untuk update
            tabelplayer.closeResult();
         
        }catch(Exception e){
            error = e.toString();
        }
    }
    
    //methode getter untuk tiap field di tabel, size dan error
    public int getIdPlayer(int i){
        return data.get(i).getIdPlayer();
    }

    public String getUsername(int i){
        return data.get(i).getUsername();
    }

    public int getAdapt(int i){
        return data.get(i).getAdapt();
    }
    
    public int getFall(int i){
        return data.get(i).getFall();
    }

    public int getSize(){
        return data.size();
    }

    public String getError(){
        return this.error;
    }
}
