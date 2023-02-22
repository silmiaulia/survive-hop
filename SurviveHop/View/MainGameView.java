/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package View;
import javax.swing.JFrame;
import Presenter.ProsesMainGame;
/**
 *
 * @author Hp
 */
public class MainGameView {
//    public static void main(String[] args) {
    
    int id_player = 0;
    private JFrame w;
    ProsesMainGame maingame;
    
    public MainGameView(int id_player){
        this.id_player = id_player;
        w = new JFrame("Surivive Hop");
        w.setResizable(false);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maingame = new ProsesMainGame(id_player);
        w.add(maingame);
        w.pack();
        w.setLocationRelativeTo(null);
        w.setVisible(true);
    }
    
    
}
