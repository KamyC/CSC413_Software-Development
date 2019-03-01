package JinghanCao;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Opening {
    private BufferedImage open;

    public BufferedImage loadImg(){
        BufferedImage img=null;
        try{
            img= ImageIO.read(new File("Title.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return img;
    }

    public void DrawOpen(Graphics2D g){
        this.open=loadImg();
        g.drawImage(open,0,0,990,590,null);
    }
}
