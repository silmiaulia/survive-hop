/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//Saya Silmi Aulia Rohmah mengerjakan evaluasi Tugas Masa Depan dalam mata kuliah
//Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya
//tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

package Presenter;

//library
import View.StartView;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class ProsesMainGame extends JPanel implements Runnable, MouseListener, KeyListener {

    private int id_player = 0; //variabel yang menyimpan id_player yang sedang bermain game
    
    private boolean isRunning; //boolean untuk kondisi sedang running
    Thread thread; //deklarasi thread
    BufferedImage view, background, floor, dino, tapToStartTheGame; //deklarasi buuferimage untuk handle img assets yang dipakai 
    BufferedImage topBox, bottomBox; 
    
    BufferedImage[] Dino; //untuk img dino
    Rectangle backgroundBox, floorBox, dinoBox, tapToStartTheGameBox; //untuk membuat ruang koordinat 
    Font font; //untuk font
    
    private Obstacle[] obstacles; //untuk objek class obstacle
    private Direction direction; //untuk objek class direction
    private ProsesPlayer prosesplayer; //untuk objek class ProsesPlayer
    private StartView menu;
    private Sound sound = new Sound();

    //variabel untuk proses jump 
    float speed = 1;
    int dy = 0;
    int gravitasi = 2;
    
    // boolean untuk setiap kondisi pada game
    private boolean isOnFloor = true; //untuk kondisi player berada di floor
    private boolean isOnBlock = false; //untuk kondisi player berada di atas block
    private boolean jumped = false; //untuk kondisi player sedang lompat
    private boolean inGame; //untuk kondisi game sedang berjalan atau tidak
    private boolean isGameOver; //untuk kondiisi game gameover atau tidak
    private boolean startJump = false; //untuk kondisi jump setelah melewati block pertama dalam game 
                                       //(agar jump sebelum muncul block tidak dihitung fall nya)
    private boolean isFalltoFloor = false; //untuk kondisi player melewati block 

    //untuk ukuran game
    int DISTORTION, SCALE = 2, SIZE = 256;

    //untuk looping gambar sprite karakter dino
    int frameIndexFly = 0, intervalFrame = 5;
    
    //untuk poin adapt dan fall user
    private int adapt, fall; 
    
    //untuk menyimpan posisi 
    private int blocky;
    private int blockx;
    private int widthblock;
    private int tempBlocky = 0;
    
    
    

	
    public ProsesMainGame(int id_player) {
        //konstruktor 
        
        SIZE *= SCALE;
        setPreferredSize(new Dimension(SIZE, SIZE));
        addMouseListener(this);
        addKeyListener(this);
        
        prosesplayer = new ProsesPlayer(); //Initialization and Instantiation class ProsesPlayer
        this.id_player = id_player; //set id_player
        
        
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this); //Initialization and Instantiation thread
            isRunning = true; //buat kondisi isRunning true
            thread.start(); //memulai eksekusi thread, start() akan memanggil metode run() pada thread
            playMusic();
        }
    }

    public void start() {
        
        try {
            //membaca file image dan men decode nya menjadi bufferimage
            view = new BufferedImage(SIZE, SIZE, BufferedImage.TYPE_INT_RGB);
            background = ImageIO.read(getClass().getResource("/resources/background.png")); //untuk backgroundd
            floor = ImageIO.read(getClass().getResource("/resources/floor.png")); //untuk lantai / floor
            tapToStartTheGame = ImageIO.read(getClass().getResource("/resources/tap_to_start_the_game1.png")); //untuk gambar perintah tap sebelum mulai game
            BufferedImage player = ImageIO.read(getClass().getResource("/resources/dino_sprite.png")); //untuk gambar main player yang berbentuk dinosaurus
            topBox = ImageIO.read(getClass().getResource("/resources/top_pipe.png")); //untuk block atas 
            bottomBox = ImageIO.read(getClass().getResource("/resources/bottom_pipe.png")); //untuk block bawah

            Dino = new BufferedImage[7];
            for (int i = 0; i < 7; i++) {
                //membuat subimage dari satu file image dino, menjadi 7 bagian
                Dino[i] = player.getSubimage(i * 24, 0, 24, 24);
            }
            dino = Dino[0];

            //inisialisasi distortion
            DISTORTION = (SIZE / background.getHeight());

            obstacles = new Obstacle[4]; //looping block sebanyak 4 kali 
            startPositionObstacles(); //panggil fungsi untuk inisialisasi 4 block pertama yang muncul

            //membuat rectangle untuk gambar tap, dino, background, dan floor
            int widthTapStartGame = tapToStartTheGame.getWidth() * DISTORTION;
            int heightTapStartGame = tapToStartTheGame.getHeight() * DISTORTION;
            tapToStartTheGameBox = new Rectangle(
                    (SIZE / 2) - (widthTapStartGame / 2),
                    (SIZE / 2) - (heightTapStartGame / 2),
                    widthTapStartGame,
                    heightTapStartGame
            );
            dinoBox = new Rectangle(
                    0,
                    0,
                    dino.getWidth() * DISTORTION,
                    dino.getHeight() * DISTORTION
            );
            backgroundBox = new Rectangle(
                    0,
                    0,
                    background.getWidth() * DISTORTION,
                    background.getHeight() * DISTORTION
            );
            floorBox = new Rectangle(
                    0,
                    SIZE - (floor.getHeight() * DISTORTION),
                    floor.getWidth() * DISTORTION,
                    floor.getHeight() * DISTORTION
            );

            startPositionDino(); //panggil fungsi untuk inisialisasi posisi x dan y dino/player

            font = new Font("TimesRoman", Font.BOLD, 9 * DISTORTION); //utnuk font yang digunakan
            
            // ambil informasi player pada database
            prosesplayer.prosesDataPlayer();
            for(int i=0; i<prosesplayer.getSize(); i++){
                
                if(prosesplayer.getIdPlayer(i) == id_player){ //pencarian id_player yang sedang bermain dan yang ada di database
                    //jika sama id_playernya, ambil record point fall dan adapt dari user yang sedang bermain
                    fall = prosesplayer.getFall(i);
                    adapt = prosesplayer.getAdapt(i);
                }
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startPositionObstacles() {
        
//      // untuk membuat block block pada game
        for (int i = 0; i < 4; i++) {
            obstacles[i] = new Obstacle(0, 0, (20 + topBox.getWidth()) * DISTORTION, topBox.getHeight() * DISTORTION);
            obstacles[i].resetToNewPosition((SIZE + topBox.getWidth() * DISTORTION) + (i * 190));
        }
    }

    public void startPositionDino() {
    	
        //inisialisasi posisi dino dan kondisi game 
        direction = Direction.None;
        inGame = false;
        dinoBox.x = (SIZE / 2) - (dinoBox.width * 3);
        dinoBox.y = SIZE - (floorBox.height + 45);  
       
    }

    public void update() { //method update yang akan selalu dipanggil selama game isRunning
        
        backgroundBox.x -= 1; //untuk kecepatan backgorund bergerak
        floorBox.x -= 3; //untuk kecepatan floor bergerak

        //jika background x + width sudah <= 0 (sudah keluar dari frame game)
        if (backgroundBox.x + backgroundBox.getWidth() <= 0) {
            //munculkan background lagi
            backgroundBox.x = (int) (backgroundBox.x + backgroundBox.getWidth());
        }

        //jika floorboc x + width sudah <= 0 (sudah keluar dari frame game)
        if (floorBox.x + floorBox.getWidth() <= 0) {
            //munculkan floor lagi
            floorBox.x = (int) (floorBox.x + floorBox.getWidth());
        }

        //untuk melakukan looping gambar dino (7 bagian dari gambar dilooping agar dino seperti sedang berjalan)
        intervalFrame++;
        if (intervalFrame > 5) {
            intervalFrame = 0;
            frameIndexFly++;
            if (frameIndexFly > 5) {
                frameIndexFly = 0;
            }
            dino = Dino[frameIndexFly];
        }

        if (inGame) { //jika permain sudah dimulai
        	
            if(isOnFloor || isOnBlock) {
                //jika player sedang di floor atau di block, buat karakter player terus mundur ke kiri
                    if(adapt < 30) { 
                            dinoBox.x -= speed + 1;
                    }else {
                            //jika poin adapt sudah lebih dari 30 maka kecepatan player mundur bertambah
                            dinoBox.x -= speed + 2;
                    }

            }
	        
            if(dinoBox.x  <= -50) { //jika player dino sudah keluar dari frame (tidak dapat beradaptasi)
                //maka game selesai
            	gameOver();
            }
        	
            for (Obstacle obstacle : obstacles) { 
            	
            	if(adapt < 30) {
            		obstacle.moveX(2);
            	}else {
            		// jika point adapt sudah lebih dari 30, kecepatan block bertambah
            		obstacle.moveX(3);
            	}
                

                if (obstacle.x + obstacle.width < 0) {
                    //jika kumpulan 4 block sudah keluar dari frame
                    //reset untuk posisi baru block
                    obstacle.resetToNewPosition(SIZE + obstacle.width + 65);
                }
                
                if(obstacle.touchLeftSideObstacle(dinoBox) != 0) { //fungsi mengecek apakah dino berada di sisi kiri block
                	
                        //buat player tidak bisa terus berjalan ke kanan (karna terhadang block yg tidak dilompati)
                	dinoBox.x = obstacle.touchLeftSideObstacle(dinoBox);
              
                }
                
                if(obstacle.touchUpperObstacle(dinoBox) != 0) { //jika jika player berada di atas block
                	obstacle.isTouch = true;
                        
                        //isi blocky dengan tinggi block dan blockx dengan posisi dino x setelah loncat
                	blocky = obstacle.touchUpperObstacle(dinoBox);
                	blockx = dinoBox.x;
                	widthblock = obstacle.x + obstacle.width - 30; //simpan lebar block
                
                }
                

                if (obstacle.fallOnFloor(dinoBox, dy, SIZE - (floorBox.height + 45))) { //cek apakah player melewati block
                		
                        obstacle.isPassedOn = true;
                	
                        //jika player melewati block, maka player akan jatuh ke lantai
                    	isOnFloor = true;
                    	isFalltoFloor  = true;                			
                        
                        //simpan posisi kanan block 
                    	blockx = obstacle.x + obstacle.width - 10;
                   
                }
            }
        }
        
//      Proses tekan keyboard panah atas, kanan, dan kiri
        if (direction == Direction.Up) {

            if (jumped) {
            	
            	if(dinoBox.y >= blocky - 10 && dinoBox.x <= widthblock && blocky != 0 
            	&& blocky != tempBlocky && !isOnBlock ) { //jika player melompat dan memiliki posisi diatas block
            		
            		// rubah posisi x dan y dino agar jatuh dan berada di atas block
            		dinoBox.y = blocky;
                	dinoBox.x = blockx;
                	tempBlocky  = blocky; // simpan posisi y di temp

                	// rubah kondisi variabel boolean
                	jumped = false;
                	isOnBlock = true;
                	startJump  = true;
                	isOnFloor = false;
                	
                	// nilai adapt bertambah
                	adapt++;
                	
                	dy = 0; 
	        			
            	}else if (dinoBox.y + dy > SIZE - (floorBox.height + 45)) { //jika player jatuh ke floor
            		
                    if(isFalltoFloor) { // jika player melewati block (tidak mendarat dulu di atas block)

                            blocky = 0; //buat nilai block y jadi 0 agar posisi player saat jump lagi
                                        // tidak mendarat di block sebelumnya

                    }
            	
                    // buat posisi player menjadi di floor
                    dinoBox.y = SIZE - (floorBox.height + 45);
                    jumped = false;
                    isOnFloor = true;
                    isOnBlock = false;
                    
                    if(startJump) {
                    	// nilai point fall bertambah
                    	fall++;
                    }
                    dy = 0; 
            		

            	}else {
                    // jika belum mendarat di block atau di floor, buat player terus melompat
                    dinoBox.y += dy;
                    dinoBox.x += speed + 3; 
                    dy += gravitasi;
                }
            }

        	
        } else if (direction == Direction.Right ) {
        	
            if(isFalltoFloor && isOnFloor) { // buat player jatuh jika user terus move player ke ujung kanan block

                // buat posisi player di lantai
                dinoBox.x = blockx;
                dinoBox.y = SIZE - (floorBox.height + 45);

                isFalltoFloor = false;
                isOnFloor = true;
                isOnBlock = false;

                if(startJump) {
                    fall++; //nilai point fall bertambah
                }
                
            }else{ 
                // buat player berjalan/bergerak ke arah kanan 
                dinoBox.x += speed + 2; 
            }
        	
        } else if (direction == Direction.Left) {
        	
        	// buat player mundur ke arah kiri dengan mengurangi x nya
        	dinoBox.x -= speed + 2; 
        }
	        
    }

    public void gameOver() { //jika gameover
		
        // rubah konidisi variabel boolean
        isGameOver = true;
        isOnFloor = true;
        isOnBlock = false;
        inGame = false;
        blockx = 0;
        blocky = 0;
        jumped = false;
        startJump  = false;

        //update point fall and adapt dari player di database
        prosesplayer.updatePlayer(id_player, fall, adapt);

        // kembali ke posisi awal
        
        startPositionObstacles();
        startPositionDino();
    }

    public void draw() { //untuk membuat gambar
        
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        
        g2.drawImage( //gammbar background
                background,
                backgroundBox.x,
                backgroundBox.y,
                (int) backgroundBox.getWidth(),
                (int) backgroundBox.getHeight(),
                null
        );
        
        g2.drawImage(
                background,
                (int) (backgroundBox.x + backgroundBox.getWidth()),
                backgroundBox.y,
                (int) backgroundBox.getWidth(),
                (int) backgroundBox.getHeight(),
                null
        );

        for (Obstacle obstacle : obstacles) { //gambar block
            g2.drawImage(bottomBox, obstacle.x, obstacle.bottomBox.y, obstacle.width, obstacle.height, null);
        }

        //gambar floor
        g2.drawImage( 
                floor,
                floorBox.x,
                floorBox.y,
                (int) floorBox.getWidth(),
                (int) floorBox.getHeight(),
                null
        );
        g2.drawImage(
                floor,
                (int) (floorBox.x + floorBox.getWidth()),
                floorBox.y,
                (int) floorBox.getWidth(),
                (int) floorBox.getHeight(),
                null
        );

        //gambar dino
        g2.drawImage(
        	dino,
                dinoBox.x,
                dinoBox.y,
                (int) dinoBox.getWidth(),
                (int) dinoBox.getHeight(),
                null
        );

        if (!inGame) {
            //gambar keterangan tap to start the game sebelum mulai permainan
            g2.drawImage(
                    tapToStartTheGame,
                    tapToStartTheGameBox.x,
                    tapToStartTheGameBox.y,
                    (int) tapToStartTheGameBox.getWidth(),
                    (int) tapToStartTheGameBox.getHeight(),
                    null
            );
        }

        g2.setColor(Color.WHITE);
        g2.setFont(font);
        

        //tampilkan skor fall dan adapt 
        g2.drawString("Adapt: " + adapt + "", 20, 35);
        g2.drawString("Fall: " + fall + "", 20, 60);

        if(isGameOver) {
            //jika permainan selesai, gambarkan tulisan game over
            g2.setColor(Color.RED);
            g2.drawString("Game Over!", 200, 200);
        }

        Graphics g = getGraphics();
        g.drawImage(view, 0, 0, SIZE, SIZE, null);
        g.dispose();
    }

    @Override
    public void run() { //method run thread
        try {
            requestFocus();
            start();
            while (isRunning) {
                update();
                draw();
                Thread.sleep(1000 / 60);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playMusic(){ 

        //set musik dan play selama permainan berlangsung

        sound.setFile();
        sound.play();
        sound.loop();
    }
    
    public void stopMusic(){ //musik akan dibuat berhenti jika game over

        sound.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        direction = Direction.Up;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //saat mouce di released buat game 
        inGame = true;
        isGameOver = false;
        direction = Direction.None;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        if(key==KeyEvent.VK_UP) { //jika key panah atas ditekan
            
            if(isOnFloor || isOnBlock) { //lakukan jump player jika sedang berada di floor atau di block
                if (!jumped) {
                    dinoBox.y -= 1;
                    dy = -27;
                    isOnFloor = false;
                    isOnBlock = false;
                    jumped = true;
                    direction = Direction.Up;
                }
            }
        	
        }

        if(key==KeyEvent.VK_LEFT) { //jika key panah kiri ditekan
        	if(!jumped) {
                    if(isOnFloor || isOnBlock) {
                        direction = Direction.Left; //jika sedang di floor atau block maka bisa menggerakan ke kiri
                    }
        	}
        }
        
        if(key==KeyEvent.VK_RIGHT) { //jika key panah kiri ditekan
        	if(!jumped) {
                    if(isOnFloor || isOnBlock) {
                        direction = Direction.Right; //jika sedang di floor atau block maka bisa menggerakan ke kanan
                    }
        	}

        }
        
        if(key==KeyEvent.VK_SPACE) {

                gameOver(); //maka game berakhir
                stopMusic();
                //tampilkan menu 
                menu =  new StartView(); 
                menu.tampilTabel();

        }
		
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: 
                    if(!jumped) {
                        if(isOnFloor || isOnBlock) {
                                direction = Direction.None;  //buat jadi none agar player tidak terus bergerak ke kiri
                        }
                    }
            case KeyEvent.VK_RIGHT:
                    if(!jumped) {
                        if(isOnFloor || isOnBlock) {
                                direction = Direction.None;  //buat jadi none agar player tidak terus bergerak ke kanan
                        }
                    }
            case KeyEvent.VK_UP: break; // down
            case KeyEvent.VK_W: break; // up
        }
		
    }



}