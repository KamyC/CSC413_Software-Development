package Jinghan.Cao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class TankControl extends KeyAdapter {
    private Tank t;
    private final int up;
    private final int down;
    private final int right;
    private final int left;
    private final int shoot;

    public TankControl (Tank t,int up,int down,int right, int left, int shoot){
        this.t=t;
        this.up=up;
        this.down=down;
        this.right=right;
        this.left=left;
        this.shoot=shoot;
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e){
        int keyPressed = e.getKeyCode();
        if (keyPressed == up) {
            this.t.toggleUpPressed();
        }
        if (keyPressed == down) {

            this.t.toggleDownPressed();
        }
        if (keyPressed == left) {

            this.t.toggleLeftPressed();
        }
        if (keyPressed == right) {

            this.t.toggleRightPressed();
        }
        if(keyPressed==shoot){
//            System.out.println(this.shoot);
            this.t.toggleShootPressed();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        int keyReleased = e.getKeyCode();
        if (keyReleased  == up) {
            this.t.unToggleUpPressed();
        }
        if (keyReleased == down) {
            this.t.unToggleDownPressed();
        }
        if (keyReleased  == left) {
            this.t.unToggleLeftPressed();
        }
        if (keyReleased  == right) {
            this.t.unToggleRightPressed();
        }
        if(keyReleased==shoot){
            this.t.unToggleShootPressed();
            this.t.shootBullet();
            if(this.t.getPower()){
                this.t.shootPower_1();
                this.t.shootPower_2();
            }
        }
    }



}
