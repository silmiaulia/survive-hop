/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.


package Presenter;

import java.awt.Rectangle;
import java.util.Random;



class Obstacle {
    
    int x, y, width, height;
    Rectangle topBox, bottomBox;
    int distance = 190;
    boolean isPassedOn = false;
    boolean isTouch = false;
    int blocky = 0, blockx = 0;

    public Obstacle(int x, int y, int width, int height) {
        //konstruktor
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        //membuat rectangle untuk block
        bottomBox = new Rectangle(x, height + distance, width, height);
    }

    //fungsi untuk membuat posisi baru block dengan nilai y (ketinggian block) yang random
    public void resetToNewPosition(int newX) {
        
        bottomBox.x = newX;
        x = newX;
        bottomBox.y = -(new Random().nextInt(140) + 120)+ height + distance;
        isPassedOn = false;

    } 

    // fungsi untuk mengecek apakah objek player sudah melewati block dan berada di atas ketinggian floor
    public boolean fallOnFloor(Rectangle rectangle, int dy, int floorHeight) {
        return rectangle.x > x + width - 20 && rectangle.y <= floorHeight && !isPassedOn;
    }
        
    public void moveX(int dx) { // method untuk perpindahan dan kecepatan kemunculan block
        x -= dx;
        bottomBox.x -= dx;
    }

    // fungsi untuk cek apakah player berada di samping kiri block (untuk membuat player tidak bisa melewati block tanpa jump)
    public int touchLeftSideObstacle(Rectangle rectangle) {

            blockx = 0;

            if(rectangle.x >= bottomBox.x - 30 && rectangle.x < x + width - 30 && rectangle.y > bottomBox.y - 40) {

                    blockx = bottomBox.x - 30; //blockx akan diisi nilai x dari posisi kiri block

            }

            return blockx;

    }
	
    //fungsi untuk mengecek apakah player berada diatas block
    public int touchUpperObstacle(Rectangle rectangle){

            blocky = 0;
            if(rectangle.y <= bottomBox.y - 40 && rectangle.x >= bottomBox.x - 30 && rectangle.x < x + width - 30) {
                //jika posisi player berada diatas block dan kurang dari atau sama dengan posisi x kanan 
                //dan lebih dari atau sama dengan x kiri block   
                
                blocky = bottomBox.y - 40; //simpan nilai posisi y (atas block) untuk nanti set posisi player 
            }

            return blocky;

    }

}