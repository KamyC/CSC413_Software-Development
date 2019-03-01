package Jinghan.Cao;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    private int speed=5;

    public Bullet(int x, int y, int angle){
        this.x=x;
        this.y=y;
        this.angle=angle;
    }

    public void fly(){
        vx = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void drawBullet(Graphics g){
        Color c=g.getColor();
        g.setColor(Color.black);
        g.fillOval(x,y,7,7);
        g.setColor(c);
        fly();
    }
}
