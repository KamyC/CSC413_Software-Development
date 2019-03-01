package JinghanCao;

public class Score {
    private int scores;
    private boolean increase=true;
    Score(){
        this.scores=0;
    }
    public void incScores(){
        if(increase){
            this.scores+=1000;
        }
        this.increase=false;
    }
    public void setIncrease(){
        this.increase=true;
    }

    public int getScore(){
        return this.scores;
    }
    public void decScore(){
        this.scores-=1;
        if(this.scores<0){
            this.scores=0;
        }
    }

}
