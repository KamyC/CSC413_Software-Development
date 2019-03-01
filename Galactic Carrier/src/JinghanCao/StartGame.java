package JinghanCao;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class StartGame extends KeyAdapter {

    private final int enter;
    private boolean enterPressed=false;
    private Start s;
    public StartGame(int enter){
        this.enter=enter;
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e){
        int keyPressed=e.getKeyCode();
        if(keyPressed==enter){

            this.enterPressed=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e){
        int keyReleased=e.getKeyCode();
        if(keyReleased==enter){
            this.enterPressed=false;
        }
    }
}
