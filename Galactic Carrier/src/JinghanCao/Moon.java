package JinghanCao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Moon {
    private BufferedImage moon;
    private int x;
    private int y;
    boolean visit=false;
    private int angle;
    private int ROTATE_SPEED=3;
    private boolean isBase=false;

    Moon(boolean visit){

        this.x= new Random().nextInt(800)+1;
        this.y=new Random().nextInt(500)+1;
        this.visit=visit;
    }

    public int getAngle(){
        return this.angle;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean ifVisited(){
        return this.visit;
    }
    public void setBase(){
        this.isBase=true;
    }
    public boolean getBase(){
        return this.isBase;
    }
    public void setVisit(){
        this.visit=true;
    }

    public BufferedImage loadImg(){
        if(this.getBase()){
            BufferedImage img=null;
            try{
                img= ImageIO.read(new File("Bases.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            return img;
        }
        else if(this.ifVisited()){
            BufferedImage img=null;
            try{
                img= ImageIO.read(new File("Visited.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            return img;
        }
        else{
            BufferedImage img=null;
            try{
                img= ImageIO.read(new File("Moon.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            return img;
        }
    }


    public void selfRotate(){
        this.angle+=ROTATE_SPEED;
    }

    public void DrawMoon(Graphics2D g){

        this.moon=loadImg();

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.moon.getWidth() / 2.0, this.moon.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.moon, rotation, null);
        selfRotate();
    }



}
