package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import static java.awt.Font.BOLD;
import static java.awt.event.KeyEvent.*;

public class Gamepanel extends JPanel implements ActionListener, KeyListener {
    private boolean play=false;
    private Timer timer;
    private int delay =-180;
    private int totalbrick=21;
    private int ballposX=120;
    private int ballposY=350;
    private int ballXdir=-1;
    private int ballYdir=-2;
    private int  playerx=350;
    // FOR CONSTRUCTING BRICKS
    private brickpanel brick;
    private int SCORE=0;

    // CONSTRUCTOR OF GAMEPANEL()
    public Gamepanel(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true); // USED TO FOCUS ON KEYBOARD ACTION
        // TIMER METHOD IS VERY IMP WITHOUT IT BALL WILL NOT MOVE
        timer = new Timer(delay,this);
        timer.start();
        brick = new brickpanel(3,7);

    }
    public void paint(Graphics g)
    {
        // panel background color
        g.setColor(Color.BLACK);
        g.fillRect(1,1,692,592);
        // edges of panel
        g.setColor(Color.red);
        g.fillRect(0,0,692,3);
        g.fillRect(0,3,3,592);
        g.fillRect(683,3,3,592);
        // moving paddle
        g.setColor(Color.orange);
        g.fillRect(playerx,545,100,10);
        // bricks
        brick.draw((Graphics2D)g);

        // balld
        g.setColor(Color.blue);
        g.fillOval(ballposX,ballposY,25,25);


        // TO DISPLAY THE SCORE

        g.setColor(Color.lightGray);
        g.setFont(new Font("plain" ,Font.PLAIN,20 ));
        ((Graphics2D) g).drawString("score"+SCORE,5,15);
        g.setColor(Color.cyan);
        g.setFont(new Font("plain" ,Font.PLAIN,15 ));
        ((Graphics2D) g).drawString("AYUSH SAWLE",570,15);

        //  TO DISPLAY SPACE BOTTON

        if(!play)
        {
        g.setColor(Color.yellow);
        g.setFont(new Font("plain", Font.CENTER_BASELINE, 40));
        ((Graphics2D) g).drawString("PRESS SPACE TO PLAY",120,400);
        }
        if(totalbrick==0||ballposY>600){
            play=false;
            ballYdir=-2;
            ballXdir=-1;
            g.setColor(Color.green);
            g.setFont(new Font("plain",Font.CENTER_BASELINE,40));
            ((Graphics2D)g).drawString("GAME OVER",120,300);
            g.setColor(Color.red);
            g.setFont(new Font("plain",Font.CENTER_BASELINE,40));
            ((Graphics2D)g).drawString("SCORE "+SCORE,120,350);
            g.setFont(new Font("plain",Font.CENTER_BASELINE,40));
            ((Graphics2D)g).drawString("PRESS ENTER TO RESTART ",120,350);


        }

    }
    // METHOD DECLARATION OF PADDLE MOVEMENT
    private void moveleft()
    {

        playerx=playerx-20;
    }
    private void moveright()
    {

        playerx+=20;
    }
    // TO MAKE PLAY=TRUE TO MOVE THE BALL
    private void GameON()
    {

        play=true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // PRESS SPACE TO START THE GAME , I.E TO MOVE THE BALL
        if(e.getKeyCode()== VK_SPACE)
        {
            GameON();


        }

        // WHEN LEFT ARROW KEY OR A KEY IS PRESSED
        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode()== VK_A)
        {
            if(playerx<=3)
                playerx=3;
            else
                 moveleft();
        }
        // WHEN RIGHT ARROW KEY OR D KEY IS PRESSED
        if (e.getKeyCode()==KeyEvent.VK_RIGHT || e.getKeyCode()==VK_D)
        {
            if (playerx>=581)
                playerx=581;
            else
                moveright();
        }
        // USED OF REPAINT THE PADDLE ACCORDING TO ACTION
        if(e.getKeyCode()== VK_ENTER)
        {
            if(!play) {
                ballposX = 120;
                ballposY = 350;
                SCORE = 0;
                totalbrick = 21;
                ballXdir = -1;
                ballYdir = -2;
                playerx = 350;
                brick = new brickpanel(3, 7);
            }
        }
        repaint();

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(play)
        {
            if(ballposX<=0 )
            {
                ballXdir-=ballposX;
            }
            if(ballposX>=650)
            {
                ballXdir=-ballXdir;
            }
            if(ballposY<=0)
            {
                ballYdir=ballYdir-ballposY;
            }
            Rectangle ball = new Rectangle(ballposX,ballposY,25,25);
            Rectangle paddle = new Rectangle(playerx,545,100,10);
            if(ball.intersects(paddle))
            {
                ballYdir=-ballYdir;
                SCORE=SCORE+10;
            }
            //  FOR REMOVING BRICKS
            for(int i=0;i<brick.brick.length;i++)
            {
                for(int j=0;j<brick.brick[0].length;j++)
                {
                    if(brick.brick[i][j]>0)
                    {
                        int width=brick.brickwidth;
                        int height=brick.brickheight;
                        int brickposX=80+j*width;
                        int brickposY=50+i*height;
                        Rectangle brickrect = new Rectangle(brickposX,brickposY,width,height);
                        if(ball.intersects(brickrect))
                        {
                            brick.setBrick(0,i,j);
                            totalbrick--;
                            ballYdir=-ballYdir;
                            ballXdir=-ballXdir;
                            SCORE=SCORE+10;

                        }

                    }
                }
            }
            ballposX= ballposX+ballXdir;
            ballposY= ballposY+ballYdir;
        }

        repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}