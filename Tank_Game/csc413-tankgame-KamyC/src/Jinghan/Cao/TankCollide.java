package Jinghan.Cao;

import java.awt.*;
import java.util.ArrayList;

public class TankCollide {
    private int t1_a,t1_b;
    private int t2_a,t2_b;
    private ArrayList<Rectangle> blocks;
    private int t_x, t_y;

    public void checkTank(Tank t1,Tank t2){
        Rectangle r1=new Rectangle(t1.getX(),t1.getY(),40,40);
        Rectangle r2=new Rectangle(t2.getX(),t2.getY(),40,40);
        if(!r1.intersects(r2)){
            this.t1_a=t1.getX();
            this.t1_b=t1.getY();

            this.t2_a=t2.getX();
            this.t2_b=t2.getY();
        }
        else{
            t1.setX(this.t1_a);
            t1.setY(this.t1_b);

            t2.setX(this.t2_a);
            t2.setY(this.t2_b);
        }
    }

    public void checkWall(Tile tile,Tank tank){
        Rectangle t=new Rectangle(tank.getX(),tank.getY(),40,40);
        tile.setBlocks();
        this.blocks=tile.getBlocks();
//        System.out.println(this.blocks.size());
        for(int i=0;i!=blocks.size();i++){
            if(t.intersects(blocks.get(i))){
                tank.setX(this.t_x);
                tank.setY(this.t_y);
            }
        }

        for(int j=0;j!=blocks.size();j++){
            if(!t.intersects(blocks.get(j))){
                this.t_x = tank.getX();
                this.t_y=tank.getY();
            }
        }
    }
}
