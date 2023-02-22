/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.


package Model;

/**
 *
 * @author Hp
 */
public class Player {

    private int id_player;
    private String username;
    private int adapt;
    private int fall;

    public Player(){

    }

    public void setIdPlayer(int id_player){
        this.id_player = id_player;
    }

    public int getIdPlayer(){
        return this.id_player;
    }


    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setAdapt(int adapt){
        this.adapt = adapt;
    }

    public int getAdapt(){
        return this.adapt;
    }
    
    public void setFall(int fall){
        this.fall = fall;
    }

    public int getFall(){
        return this.fall;
    }
    

}