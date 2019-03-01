package JinghanCao;

import java.awt.*;

public class Collide implements CheckCollision {
    private Rocket rocket;
    private Asteroid asteroid;

    public void checkAsteroid(Rocket rocket,Asteroid asteroid){
        this.rocket=rocket;
        this.asteroid=asteroid;
        Rectangle r=new Rectangle(rocket.getX(),rocket.getY(),20,20);
        Rectangle a=new Rectangle(asteroid.getX(),asteroid.getY(),20,20);
        if(r.intersects(a) && this.rocket.isLeft()){
            this.rocket.setCollide();
        }
    }

}
