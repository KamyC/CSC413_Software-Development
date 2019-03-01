package JinghanCao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Rocket {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private boolean offMoon=false;

    private boolean leftPressed;
    private boolean rightPressed;
    private boolean shootPressed;
    private boolean isCollide=false;
    private int CONTROL_ROTATE_SPEED=1;
    private int FLY_SPEED=1;
    private int INITIAL_SPEED=5;
    private BufferedImage rocket;
    private int ROTATE_SPEED=3;

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    Rocket(int x, int y,int angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
    }

    public BufferedImage loadImg(){
        if(this.isLeft()){
            BufferedImage img=null;
            try{
                img= ImageIO.read(new File("Flying.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            return img;
        }
        else{
            BufferedImage img=null;
            try{
                img= ImageIO.read(new File("Landed.png"));
            }catch (IOException e){
                e.printStackTrace();
            }
            return img;
        }

    }

    public void setCollide(){
        this.isCollide=true;
    }

    public boolean getCollide(){
        return this.isCollide;
    }

    public void selfRotate(){
        this.angle+=ROTATE_SPEED;
    }
    public void toggleLeftPressed(){
        this.leftPressed=true;
    }
    public void toggleRightPressed(){
        this.rightPressed=true;
    }
    public void toggleShootPressed(){
        this.shootPressed=true;
    }
    public void unToggleLeftPressed(){
        this.leftPressed=false;
    }
    public void unToggleRightPressed(){
        this.rightPressed=false;
    }
    public void unToggleShootPressed(){
        this.shootPressed=false;
    }

    public void landed(){
        this.offMoon=false;
    }
    public void left(){
        this.offMoon=true;
    }
    public boolean isLeft(){
        return this.offMoon;
    }
    public void update(){
        if(this.leftPressed && this.offMoon){
            this.moveLeft();
        }
        if(this.rightPressed && this.offMoon){
            this.moveRight();
        }
        if(this.shootPressed && !this.offMoon){
            this.shoot();
        }
        if(this.offMoon){
            this.fly();
        }
    }

    public void moveLeft(){
        this.angle-=CONTROL_ROTATE_SPEED;
    }
    public void moveRight(){
        this.angle+=CONTROL_ROTATE_SPEED;
    }
    public void shoot(){
        vx = (int) Math.round(INITIAL_SPEED * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(INITIAL_SPEED * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }
    public void fly(){
        vx = (int) Math.round(FLY_SPEED * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(FLY_SPEED * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;

        if(x<0){
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

    public void DrawRocket(Graphics2D g){
        this.rocket=loadImg();

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.rocket.getWidth() / 2.0, this.rocket.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.rocket, rotation, null);

        if(!offMoon){
            selfRotate();
        }

    }
}
