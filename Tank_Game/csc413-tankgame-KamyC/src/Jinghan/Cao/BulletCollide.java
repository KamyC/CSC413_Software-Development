package Jinghan.Cao;

import java.awt.*;
import java.util.ArrayList;

public class BulletCollide {
    private ArrayList<Rectangle> blocks;
    private int x,y;
    private int a,b;
    public boolean detectCollision(Tile tile, Bullet bullet){
        Rectangle r=new Rectangle(bullet.getX(), bullet.getY(),1,1);
        tile.setBlocks();
        this.blocks=tile.getBlocks();
        for(int i=0;i!=blocks.size();i++){
            if(r.intersects(blocks.get(i))){
                this.y=(int)blocks.get(i).getX()/20;
                this.x=(int)blocks.get(i).getY()/20;
                if(tile.getMap()[x][y]==5){
                    tile.setValue(x,y);
                }
                return true;
            }
        }
        return false;
    }
    public boolean hitTank(Bullet bullet, Tank tank){
        Rectangle r=new Rectangle(bullet.getX(), bullet.getY(),1,1);
        this.a=tank.getX();
        this.b=tank.getY();
        Rectangle t=new Rectangle(a, b,40,40);
        if(r.intersects(t)){
            return true;
        }
        return false;
    }


}
