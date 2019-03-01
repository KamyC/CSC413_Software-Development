package Jinghan.Cao;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Start extends JPanel {
    private JFrame frame;
    public static final int WORLD_WIDTH=1200;
    public static final int WORLD_HEIGHT=800;

    public static final int SCREEN_WITDH=1000;
    public static final int SCREEN_HEIGHT=600;
    public static final String NAME="Tank Game Beta_0.01";
    private BufferedImage world;
    private Graphics2D buffer;
    private Tank t1;
    private Tank t2;
    public PowerUp pp;
    public Tile tile;
    private boolean running=true;
    public BufferedImage left;
    public BufferedImage right;
    public void init(){
        this.frame=new JFrame(NAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(SCREEN_WITDH,SCREEN_HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this);
        this.frame.setResizable(false);//not change size of frame
        this.frame.setLocationRelativeTo(null);//center the frame
        this.frame.setVisible(true);//visible frame

        this.world=new BufferedImage(WORLD_WIDTH,WORLD_HEIGHT,BufferedImage.TYPE_INT_RGB);

        this.tile=new Tile();
        this.t1=new Tank(100,100,0,0,0,this);
        this.t2=new Tank(1060,670,0,0,0,this);//1060 670
        this.pp=new PowerUp(518,50);
        TankControl tc1=new TankControl(t1, KeyEvent.VK_UP, KeyEvent.VK_DOWN,KeyEvent.VK_RIGHT,KeyEvent.VK_LEFT,KeyEvent.VK_ENTER);
        TankControl tc2=new TankControl(t2, KeyEvent.VK_W, KeyEvent.VK_S,KeyEvent.VK_D,KeyEvent.VK_A,KeyEvent.VK_SPACE);
        this.frame.addKeyListener(tc1);
        this.frame.addKeyListener(tc2);
    }

    public static void main(String [] args) {

        Start s=new Start();
        s.init();
        TankCollide tco=new TankCollide();
        BulletCollide bc=new BulletCollide();

        try{
            while(s.running){
                Thread.sleep(1000/144);
                s.setleftPanel(s.t1);
                s.setrightPanel(s.t2);

                s.repaint();

                if(!s.t1.bullets.isEmpty()){
                    for(int i=0;i!=s.t1.bullets.size();i++){
                        if(bc.detectCollision(s.tile,s.t1.bullets.get(i))){
                            s.t1.bullets.remove(i);
                            i--;
                        }
                    }
                }

                if(!s.t1.bullets.isEmpty()){
                    for(int i=0;i!=s.t1.bullets.size();i++) {
                        if (bc.hitTank(s.t1.bullets.get(i), s.t2)) {
                            s.t1.bullets.remove(i);
                            s.t2.decLife();
                            i--;
                        }
                    }
                }

                if(!s.t2.bullets.isEmpty()){
                    for(int i=0;i!=s.t2.bullets.size();i++){
                        if(bc.detectCollision(s.tile,s.t2.bullets.get(i))){
                            s.t2.bullets.remove(i);
                            i--;
                        }
                    }
                }
                if(!s.t2.bullets.isEmpty()){
                    for(int i=0;i!=s.t2.bullets.size();i++){
                        if(bc.hitTank(s.t2.bullets.get(i),s.t1)){
                            s.t2.bullets.remove(i);
                            s.t1.decLife();
                            i--;
                        }
                    }
                }
/*
below is an iterator method, but it causes some bugs due to the index problem. I leave it here for future revision.
*/
//                Iterator<Bullet> iter_t1=s.t1.bullets.iterator();
//                Iterator<Bullet> iter_t2=s.t2.bullets.iterator();
//                while(iter_t1.hasNext()){
//                    Bullet b=iter_t1.next();
//                    if(bc.detectCollision(s.tile,b)){
//                        iter_t1.remove();
//                    }
//                    if(bc.hitTank(b,s.t2)){
//                        iter_t1.remove();
//                        s.t2.decLife();
//                    }
//                }
//
//                while(iter_t2.hasNext()){
//                    Bullet b=iter_t2.next();
//                    if(bc.detectCollision(s.tile,b)){
//                        iter_t2.remove();
//                    }
//                    if(bc.hitTank(b,s.t1)){
//                        iter_t2.remove();
//                        s.t1.decLife();
//                    }
//                }
                tco.checkTank(s.t1,s.t2);

                s.t1.update();
                s.t2.update();

                if(s.t1.getLife()<=0){
                    JOptionPane.showMessageDialog(null, "Red Player Wins!!!!");
                    System.exit(0);
                }
                if(s.t2.getLife()<=0){
                    JOptionPane.showMessageDialog(null, "Blue Player Wins!!!!");
                    System.exit(0);
                }
            }
        }catch (InterruptedException ignored) {
        }
    }

    public void getBackground(Graphics2D g){
        this.tile.DrawLayer(g);
    }

    public void setleftPanel(Tank t1){
        if(t1.getX()>700 && t1.getY()>200){
            this.left=this.world.getSubimage(680,240,500,560);
        }
        else if(t1.getX()>700){
            this.left=this.world.getSubimage(680,t1.getY()-20,500,600);
//            System.out.println("x");
        }
        else if(t1.getY()>200){
            this.left=this.world.getSubimage(t1.getX()-20,240,500,560);
//            System.out.println("y");
        }
        else if(t1.getX()<=700 && t1.getY()<=200){
            this.left=this.world.getSubimage(t1.getX()-20,t1.getY()-20,500,600);
//            System.out.println("else");
        }
    }
    public void setrightPanel(Tank t2){
        if(t2.getX()>700 && t2.getY()>200){
            this.right=this.world.getSubimage(700,240,500,560);
        }
        else if(t2.getX()>700){
            this.right=this.world.getSubimage(700,t2.getY()-20,500,600);
//            System.out.println("x");
        }
        else if(t2.getY()>200){
            this.right=this.world.getSubimage(t2.getX()-20,240,500,560);
//            System.out.println("y");
        }
        else if(t2.getX()<=700 && t2.getY()<=200){
            this.right=this.world.getSubimage(t2.getX()-20,t2.getY()-20,500,600);
//            System.out.println("else");
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        this.getBackground(buffer);

        if(!this.t1.bullets.isEmpty() ){
            for(int i=0;i!=t1.bullets.size();i++){
                this.t1.bullets.get(i).drawBullet(buffer);
            }
        }
        if(!this.t2.bullets.isEmpty() ){
            for(int i=0;i!=t2.bullets.size();i++){
                this.t2.bullets.get(i).drawBullet(buffer);
            }
        }

        this.pp.drawPickUp(buffer);

        this.t1.DrawTankA(buffer);
        this.t2.DrawTankB(buffer);
        g2.drawImage(world,0,0,null);
        g2.drawImage(this.left,0,0,null );
        g2.drawImage(this.right,500,0,null );
        g2.drawImage(this.world.getScaledInstance(150,100,Image.SCALE_SMOOTH),425,462,null);
    }

}
