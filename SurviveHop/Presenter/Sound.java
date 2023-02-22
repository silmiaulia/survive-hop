package Presenter;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



public class Sound{

    Clip clip;
    static String filePath;


    public Sound(){
        filePath = "./resources/Pim_Poy_Pockert.wav";
    }

    public void setFile(){ //utnuk set file musik nya

        try{

            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audio);

        }catch(Exception e){

            System.err.println(e.getMessage());
        }

    }

    public void play(){ //fungsi untuk memulai musik
        clip.start();
    }

    public void loop(){ //fungsi untuk melooping musik
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){ //fungsi untuk memberhentikan musik
        clip.stop();
    }
    
    
}