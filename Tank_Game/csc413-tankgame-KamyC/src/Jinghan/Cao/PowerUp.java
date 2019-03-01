package Jinghan.Cao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PowerUp {
    private BufferedImage img;
    private int x,y;
    private boolean picked=false;
    public PowerUp(int x, int y){
        this.x=x;
        this.y=y;
    }
    public void loadPowerUp(){
        try{
            this.img= ImageIO.read(new File("Pickup.gif"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void changeState(){
        this.picked=true;
    }

    public void drawPickUp(Graphics g){
        if(this.picked){
//            System.out.println("Picked");
        }
        else{
            this.loadPowerUp();
            g.drawImage(this.img,this.x,this.y,null);
        }
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}
