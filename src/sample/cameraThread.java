package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class cameraThread extends Thread{
    ImageView imageDisplay;

    public cameraThread(ImageView imageDisplay){
        this.imageDisplay=imageDisplay;
        Main.webcam.setViewSize(new Dimension(320,240));
        Main.webcam.open();
    }

    @Override
    public void run() {
        while(Controller.isRunning){
            BufferedImage image=Main.webcam.getImage();
            Image image1= SwingFXUtils.toFXImage(image,null);
            imageDisplay.setImage(image1);
            try{
                cameraThread.sleep(60);
            }catch(InterruptedException e){
                System.out.println("I have been Woken up"+e.getMessage());
            }
        }
    }
}
