package JinghanCao;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;

public class Start extends JPanel {

    private JFrame frame;
    public static final int WORLD_WIDTH=1000;
    public static final int WORLD_HEIGHT=600;

    public static final String NAME="Galactic Carrier";
    private BufferedImage world;
    private Graphics2D buffer;

    private ArrayList<Asteroid> astCluster=new ArrayList<>();
    public ArrayList<Moon> moonCluster=new ArrayList<>();

    public static final int DIFFICULTY_LEVEL=3;
    private int astAmount=5;
    private int moonAmount=4;
    private int timer=50;
    private Tile tile;
    private Rocket rocket;
    private Check check;
    private Score score;
    private Collide collide;
    private Opening opening;
    private int level=0;
    private boolean running=true;

    public void init(){
        this.frame=new JFrame(NAME);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this);
        this.frame.setResizable(false);//not change size of frame
        this.frame.setLocationRelativeTo(null);//center the frame
        this.frame.setVisible(true);//visible frame

        this.world=new BufferedImage(WORLD_WIDTH,WORLD_HEIGHT,BufferedImage.TYPE_INT_RGB);
        this.opening=new Opening();
        this.tile=new Tile();

        for( int i=0;i!=this.astAmount;i++){
            Asteroid a=new Asteroid();
            astCluster.add(a);
        }
        for( int i=0;i!=this.moonAmount;i++){
            Moon m=new Moon(false);
            moonCluster.add(m);
        }
        moonCluster.get(0).setBase();

        this.check=new Check();
        this.score=this.check.assignScore();

        this.rocket=new Rocket(moonCluster.get(0).getX(), moonCluster.get(0).getY(),moonCluster.get(0).getAngle());
        RocketControl rc=new RocketControl(this.rocket,KeyEvent.VK_LEFT,KeyEvent.VK_RIGHT,KeyEvent.VK_SPACE);
        this.frame.addKeyListener(rc);
        this.collide=new Collide();
    }

    public static void main(String [] args) {

        Start s=new Start();
        s.init();

        try{
            while(s.running){
                Thread.sleep(100/10);
                s.repaint();
                s.rocket.update();
                s.check.checkMoon(s.rocket,s.moonCluster);
                s.check.rmMoon(s.rocket,s.moonCluster);
                for(int i=0;i!=s.astCluster.size();i++){
                    s.collide.checkAsteroid(s.rocket,s.astCluster.get(i));
                }

                if(s.rocket.getCollide()){
                    JOptionPane.showMessageDialog(null, "Your Final Score: "+s.score.getScore());
                    System.exit(0);
                }

                if(s.score.getScore()>1000 && s.score.getScore()<=2000){
                    if(s.level==1){
                        continue;
                    }
                    s.level=1;
                    for(int i=0;i!=DIFFICULTY_LEVEL;i++){
                        Asteroid a=new Asteroid();
                        s.astCluster.add(a);
                    }
                }
                if(s.score.getScore()>2000 && s.score.getScore()<=3000){
                    if(s.level==2){
                        continue;
                    }
                    s.level=2;
                    for(int i=0;i!=DIFFICULTY_LEVEL;i++){
                        Asteroid a=new Asteroid();
                        s.astCluster.add(a);
                    }
                }
                if(s.score.getScore()>3000 && s.score.getScore()<=4000){
                    if(s.level==3){
                        continue;
                    }
                    s.level=3;
                    for(int i=0;i!=DIFFICULTY_LEVEL;i++){
                        Asteroid a=new Asteroid();
                        s.astCluster.add(a);
                    }
                }
//                System.out.println(s.astCluster.size());
            }
        }catch (InterruptedException ignored) {
        }
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.white);
        Font f1 = new Font("Helvetica", Font.BOLD,20);
        g.setFont(f1);

        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        if(this.timer>0){
            this.opening.DrawOpen(buffer);
            g2.drawImage(world,0,0,null);
            this.timer--;
        }
        else{

            this.tile.DrawTile(buffer);

            for( int i=0;i!=this.astCluster.size();i++){
                this.astCluster.get(i).DrawAsteroid(buffer);
            }

//        for(Moon m:moonCluster){
//            m.DrawMoon(buffer);
//        }
            for( int i=0;i!=this.moonCluster.size();i++){
                this.moonCluster.get(i).DrawMoon(buffer);
            }
            this.rocket.DrawRocket(buffer);

            g2.drawImage(world,0,0,null);

            String s="Score: "+String.valueOf(this.score.getScore());

            g.drawString(s,800,50);
        }
    }

}

