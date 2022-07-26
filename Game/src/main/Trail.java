package main;

import java.awt.*;

public class Trail extends GameObject{
    private float alpha = 1;
    private Handler handler;
    private Color color;
    private int width,height;
    private double life;

    //life = 0.001 - 0.1

    public Trail(int x, int y, ID id,Color color,int width,int height,double life, Handler handler) {
        super(x, y, id);
        this.color = color;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.life = life;
    }

    @Override
    public void tick() {
        if (alpha > life){
            alpha -= life - 0.001;
        }else{
            handler.removeObject(this);
        }

    }

    @Override
    public void render(Graphics g) {
        Graphics2D grd = (Graphics2D) g;
        grd.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y,width,height);

        grd.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return(AlphaComposite.getInstance(type,alpha));
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
