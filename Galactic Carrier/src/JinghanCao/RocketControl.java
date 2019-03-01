package JinghanCao;

import JinghanCao.Rocket;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RocketControl extends KeyAdapter {
    private Rocket rocket;
    private final int left;
    private final int right;
    private final int shoot;

    public RocketControl(Rocket rocket,int left,int right,int shoot){
        this.rocket=rocket;
        this.left=left;
        this.right=right;
        this.shoot=shoot;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e){
        int keyPressed = e.getKeyCode();
        if(keyPressed==left){
            this.rocket.toggleLeftPressed();
        }

        if(keyPressed==right){
            this.rocket.toggleRightPressed();
        }

        if(keyPressed==shoot){
            this.rocket.toggleShootPressed();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int keyReleased = e.getKeyCode();
        if (keyReleased == left) {
            this.rocket.unToggleLeftPressed();
        }

        if (keyReleased == right) {
            this.rocket.unToggleRightPressed();
        }

        if (keyReleased == shoot) {
            this.rocket.unToggleShootPressed();
        }
    }

}
