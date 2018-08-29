package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Controller {

    public static boolean isRunning=false;

    @FXML
    public void initialize(){
        try{
            imageDisplay.setImage(new Image(new FileInputStream("cameraImg.jpeg")));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    ImageView imageDisplay;

    @FXML
    public void switchMode(){
        Main.webcam.close();

    }


    @FXML
    public void videoStream(){
        if(!isRunning){
            isRunning=true;

            cameraThread videoStreaming=new cameraThread(imageDisplay);
            videoStreaming.start();
        }else{

            Main.webcam.close();
            isRunning=false;
        }
    }

    public void btnPressed() {
        //java.awt.Image image =webcam.getImage();
        Main.webcam.open();
        BufferedImage image1=Main.webcam.getImage();
        try{
            ImageIO.write(image1,"png",new File("photo.png"));
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        Image captureImage= SwingFXUtils.toFXImage(image1,null);
        if(captureImage!=null){
            imageDisplay.setImage(captureImage);
        }
        else{
            System.out.println("Null Image");
        }
    }
}
