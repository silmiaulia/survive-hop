/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package Presenter;

/**
 *
 * @author Hp
 */
public interface KontrakPresenter {
    
    public void prosesDataPlayer();
    public void addPlayer(String username);
    public void updatePlayer(int id_player, int Fall, int Adapt);
    public int getIdPlayer(int i);
    public String getUsername(int i);
    public int getAdapt(int i);
    public int getFall(int i);
    public int getSize();
}
