package JinghanCao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Tile {
    private BufferedImage bg;


    public BufferedImage loadImg(){
        BufferedImage img=null;
        try{
            img=ImageIO.read(new File("Background.bmp"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public void DrawTile(Graphics2D g){
        this.bg=loadImg();
        g.drawImage(bg,0,0,1000,600,null);
    }

}
