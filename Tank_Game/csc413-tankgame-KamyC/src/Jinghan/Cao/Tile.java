package Jinghan.Cao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile {
    private int[][] map;
    private BufferedImage tile;
    private BufferedImage wallA;
    private BufferedImage wallB;
    private BufferedImage bound;
    private ArrayList<Rectangle> blocks=new ArrayList<Rectangle>();

    Tile(){//pass in the map object
        Map m=new Map();
        m.setMap();
        this.map=m.getMap();
    }

    public BufferedImage loadTile(){
        BufferedImage img=null;
        try{
            img= ImageIO.read(new File("tile.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public BufferedImage loadBound(){
        BufferedImage img=null;
        try{
            img= ImageIO.read(new File("bound.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public BufferedImage loadWallA(){
        BufferedImage wallA=null;
        try{
            wallA=ImageIO.read(new File("wallA.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return wallA;
    }

    public BufferedImage loadWallB(){
        BufferedImage wallB=null;
        try{
            wallB=ImageIO.read(new File("wallB.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
        return wallB;
    }

    public void setBlocks(){
        this.blocks.clear();
        for(int i=0;i!=this.map.length;i++){
            for(int j=0;j!=this.map[i].length;j++){
                if(this.map[i][j]!=0&&this.map[i][j]!=1){
                    Rectangle rec=new Rectangle(j*20,i*20,20,20);
                    this.blocks.add(rec);
                }
            }
        }
//        System.out.println(this.blocks.size());//OK!!
    }
    public ArrayList<Rectangle> getBlocks(){
        return this.blocks;
    }

    public int[][] getMap(){
        return this.map;
    }

    public void setValue(int x, int y){
        this.map[x][y]=0;
    }

    public void DrawLayer(Graphics g){
        tile=loadTile();
        bound=loadBound();
        wallA=loadWallA();
        wallB=loadWallB();

        for(int y=0;y!=map.length;y++){
            for(int x=0;x!=map[y].length;x++){
                int index=map[y][x];
                switch(index){
                    case 1:
                        g.drawImage(bound,x*20, y*20, 20,20,null);
                        break;
                    case 5:
                        g.drawImage(wallB,x*20, y*20, 20,20,null);
                        break;
                    case 9:
                        g.drawImage(wallA,x*20, y*20, 20,20,null);
                        break;
                    default:
                        g.drawImage(tile,x*20, y*20, 20,20,null);
                        break;
                }
            }
        }
    }//draw according to the map
}
