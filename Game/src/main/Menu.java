package main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private final Handler handler;
    private final Random r = new Random();
    private final HUD hud;

    public Menu(Handler handler, HUD hud) {
        this.hud = hud;
        this.handler = handler;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu){
            //play button
            if (mouseOver(mx,my,215,125,200)){
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy,handler));
            }

            //help button
            if (mouseOver(mx,my,215,225,200)){
                Game.gameState = Game.STATE.Help;
            }

            //quit button
            if (mouseOver(mx,my,215,325,200)){
                System.exit(0);
            }
        }

        //back button for help
        if(Game.gameState == Game.STATE.Help){
            if (mouseOver(mx,my,215,325,200)){
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //main menu for end
        if(Game.gameState == Game.STATE.End){
            if (mouseOver(mx,my,205,325,240)){
                handler.clearEnemys();
                Game.gameState = Game.STATE.Menu;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width){
        if (mx > x && mx < x + width){
            return my > y && my < y + 64;
        }else{
            return false;
        }
    }

    public void tick(){

    }

    public void render(Graphics g){
        if(Game.gameState == Game.STATE.Menu){
            hud.setLevel(0);
            hud.setScore(0);
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);


            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Menu",250,75);

            g.setFont(fnt2);
            g.drawRect(215,125,200,64);
            g.drawString("Play",285,165);

            g.drawRect(215,225,200,64);
            g.drawString("Help",285,265);

            g.drawRect(215,325,200,64);
            g.drawString("Quit",285,365);
        } else if (Game.gameState == Game.STATE.Help){
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 20);


            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help",250,75);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move player and dodge enemies",60,170);
            g.drawString("Press \"P\" to pause game",60,195);
            g.drawString("Press \"Esc\" to exit game",60,220);

            g.setFont(fnt2);
            g.drawRect(240,325,150,64);
            g.drawString("Back",280,365);
        }else if (Game.gameState == Game.STATE.End){
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 20);


            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over",175,75);

            g.setFont(fnt3);
            g.drawString("You lost with a score of: " + hud.getScore(),180,170);

            g.setFont(fnt2);
            g.drawRect(205,325,240,64);
            g.drawString("Main Menu",250,365);
        }
     }
}
