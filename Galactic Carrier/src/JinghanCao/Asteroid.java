package JinghanCao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Asteroid {
    private BufferedImage asteroid;
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int rotate_angle=0;
    private int ROTATESPEED=3;
    private int speed=3;
    Asteroid(){
        this.x= new Random().nextInt(1000)+1;
        this.y=new Random().nextInt(600)+1;
        this.angle=new Random().nextInt(360);
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }


    @Override
    public String toString() {
        return "X: "+this.x+" Y: "+this.y;
    }

    public void fly(){
        vx = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        rotate_angle+=ROTATESPEED;

        if(x<0){//out from left
            x+=1000;
        }
        if(y<0){
            y+=600;
        }
        if(y>600){
            y-=600;
        }
        if(x>1000){
            x-=1000;
        }
    }

    public BufferedImage loadImg(){
        BufferedImage img=null;
        try{
            img= ImageIO.read(new File("Asteroid.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public void DrawAsteroid(Graphics2D g){
        this.asteroid=loadImg();

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(rotate_angle), this.asteroid.getWidth() / 2.0, this.asteroid.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.asteroid, rotation, null);
        fly();

    }
}
