package Jinghan.Cao;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HealthBar {

    private BufferedImage img;
    private int x,y;
    public HealthBar(int x,int y){
        this.x=x;
        this.y=y;
    }

    public void drawLifeCount1(int x,int y,Graphics g){
        Color c=g.getColor();
        g.setColor(Color.blue);
        g.fillOval(x,y,10,10);
        g.setColor(c);
    }

    public void drawLifeCount2(int x,int y,Graphics g){
        Color c=g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,10,10);
        g.setColor(c);
    }

    public void drawHP1(int x, int y, Graphics g){
        Color c=g.getColor();
        g.setColor(Color.blue);
        g.fillRect(x,y,10,10);
        g.setColor(c);
    }
    public void drawHP2(int x, int y, Graphics g){
        Color c=g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,10,10);
        g.setColor(c);
    }
}
