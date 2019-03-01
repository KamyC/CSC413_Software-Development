package JinghanCao;

import java.awt.*;
import java.util.ArrayList;

public class Check implements CheckScore {
    private Rocket rocket;
    public Start s=new Start();
    private int flag=0;
    private Score score;
//    private int j=0;
    Check(){
        this.score=new Score();
    }
    public Score assignScore(){
        return this.score;
    }

    public void checkMoon(Rocket rocket, ArrayList<Moon> moonCluster) {
//        System.out.println(this.score.getScore());
        this.rocket = rocket;
        this.s.moonCluster=moonCluster;
        for(Moon moon:moonCluster){
            Rectangle r = new Rectangle(rocket.getX() + 10, rocket.getY() + 10, 20, 20);
            Rectangle m = new Rectangle(moon.getX() + 10, moon.getY() + 10, 20, 20);
            if (r.intersects(m)) {
                if(!moon.getBase()){
                    this.score.incScores();
                }
                flag++;
                moon.setVisit();
            }
            else {
                flag--;
            }
        }

        if(flag==-2){
            rocket.landed();
            this.score.decScore();
        }
        else{
            rocket.left();
            this.score.setIncrease();
        }
        flag=0;
    }
    public void rmMoon(Rocket rocket, ArrayList<Moon> moonCluster){
        this.rocket = rocket;
        this.s.moonCluster=moonCluster;
        for(int i=0,len=s.moonCluster.size();i!=len;i++){
//            if(s.moonCluster.get(i).ifVisited()){
//                j++;
//            }
            if(s.moonCluster.get(i).ifVisited() && !s.moonCluster.get(i).getBase() && rocket.isLeft()){
                s.moonCluster.remove(i);
                Moon m=new Moon(false);
                s.moonCluster.add(m);
            }
        }
//        System.out.println(j);
//        j=0;
    }
}
