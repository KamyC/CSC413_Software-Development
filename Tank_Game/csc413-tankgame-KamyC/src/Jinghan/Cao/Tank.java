package Jinghan.Cao;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthScrollBarUI;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Tank{
    private BufferedImage t1Img;
    private BufferedImage t2Img;
    private int LIFE_BAR=4;
    private TankCollide tco= new TankCollide();
    private Start s;
    private HealthBar HP=new HealthBar(this.x,this.y);

    public ArrayList<Bullet> bullets=new ArrayList<>();

    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;

    private boolean powerUp=false;

    private final int R = 2;
    private final int ROTATIONSPEED = 2;

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    Tank(int x, int y, int vx, int vy, int angle) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.angle = angle;
    }
    Tank(int x, int y, int vx, int vy, int angle, Start s){
        this(x,y,vx,vy,angle);
        this.s=s;
    }

    public BufferedImage loadT1(){
        BufferedImage t1Img=null;
        try{
            t1Img=ImageIO.read(new File("tank1.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return t1Img;
    }

    public BufferedImage loadT2(){
        BufferedImage t2Img=null;
        try{
            t2Img=ImageIO.read(new File("tank2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
        return t2Img;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() {
        this.DownPressed = true;
    }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed(){
        this.ShootPressed=true;
    }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed(){
        this.ShootPressed=false;
    }

    public void update() {
        this.checkPick();

        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }
        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if(this.ShootPressed){
//            this.shootBullet();
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x -= vx;
        y -= vy;
        checkBorder();
        tco.checkWall(s.tile,this);
    }

    private void moveForwards() {
        vx = (int) Math.round(R * Math.cos(Math.toRadians(angle)));
        vy = (int) Math.round(R * Math.sin(Math.toRadians(angle)));
        x += vx;
        y += vy;
        checkBorder();
        tco.checkWall(s.tile,this);
    }

    public void shootBullet(){
        Bullet bull =new Bullet(x+20,y+20,angle);//set bullet starting point within the tank
        this.bullets.add(bull);
    }
    public void shootPower_1(){
        Bullet b1=new Bullet(x+20,y+20,angle+10);
        this.bullets.add(b1);
    }
    public void shootPower_2(){
        Bullet b1=new Bullet(x+20,y+20,angle-10);
        this.bullets.add(b1);
    }
    public boolean getPower(){
        return this.powerUp;
    }

    public void checkPick(){
        Rectangle t=new Rectangle(this.x, this.y,40,40);
        Rectangle p=new Rectangle(s.pp.getX(),s.pp.getY(),40,40);
        if(t.intersects(p)){
            s.pp.changeState();
            this.powerUp=true;
        }
    }

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

    public void decLife(){
        this.LIFE_BAR--;
    }

    public int getLife(){
        return this.LIFE_BAR;
    }

    private void checkBorder() {
        if (x < 28) {
            x = 28;
        }
        if (x >= Start.WORLD_WIDTH - 80) {
            x = Start.WORLD_WIDTH - 80;
        }
        if (y < 30) {
            y = 30;
        }
        if (y >= Start.WORLD_HEIGHT - 70) {
            y = Start.WORLD_HEIGHT - 70;
        }
    }

    @Override
    public String toString(){
        String coordinate="x: "+this.x+" y: "+this.y;
        return coordinate;
    }

    public void DrawTankA(Graphics g) {
        this.t1Img=this.loadT1();

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);

        rotation.rotate(Math.toRadians(angle), this.t1Img.getWidth() / 2.0, this.t1Img.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.t1Img, rotation, null);

        for(int i=0;i!=this.LIFE_BAR;i++){
            this.HP.drawLifeCount1(this.x+i*10,this.y-10,g2d);
            this.HP.drawHP1(this.x+i*10,this.y+40,g2d);
        }
    }

    public void DrawTankB(Graphics g) {
        this.t2Img=this.loadT2();

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);

        rotation.rotate(Math.toRadians(angle), this.t2Img.getWidth() / 2.0, this.t2Img.getHeight() / 2.0);

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(this.t2Img, rotation, null);

        for(int i=0;i!=this.LIFE_BAR;i++){
            this.HP.drawLifeCount2(this.x+i*10,this.y-10,g2d);
            this.HP.drawHP2(this.x+i*10,this.y+40,g2d);
        }
    }

}
