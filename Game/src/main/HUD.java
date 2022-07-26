package main;

import java.awt.*;

public class HUD {
    public static double HEALTH = 100;
    private double greenValue = 255;

    private int score = 0;
    private int level = 1;

    public void tick(){
        HEALTH = Game.clamp(HEALTH,0,100);
        greenValue = HEALTH*2;

        score++;
    }

    public void render(Graphics g){
        Font fnt = new Font("arial", Font.PLAIN, 15);
        g.setColor(Color.gray);
        g.fillRect(15,15,200,32);
        g.setColor(Color.white);
        g.setFont(fnt);
        g.setColor(new Color(75, (int) greenValue,0));
        g.fillRect(15,15, (int) (HEALTH * 2),32);
        g.setColor(Color.white);
        g.drawRect(15,15,200,32);
        g.drawString(String.valueOf(HEALTH),20,35);


        g.setFont(fnt);
        g.drawString("Score: " + score,15,66);
        g.drawString("Level: " + level,15,83);

    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
