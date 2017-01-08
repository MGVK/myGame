package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class myContent extends JPanel {
    public Timer timerDraw, timerMoving;
    private BufferedImage image1;
    public static Integer persX = 0, persY = 0;

    public final static int UP=1;
    public final static int RIGHT=2;
    public final static int DOWN=3;
    public final static int LEFT=4;

    public myContent() {

        initiate();

        timerDraw = new Timer(1, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timerDraw.start();


    }

    private final int dx = 5;
    private final int dy = 5;
    int kx = 1;
    int ky = 1;


    /**
     * @param direction 1- up 2- right 3 - down 4 - left
     */
    public void startMoving(int direction) {

        if(timerMoving!=null&&timerMoving.isRunning()){
            timerMoving.stop();
        }




        switch (direction) {
            case 1: {
                kx = 0;
                ky = -1;
                break;
            }
            case 2: {
                kx = 1;
                ky = 0;
                break;
            }
            case 3: {
                kx = 0;
                ky = 1;
                break;
            }
            case 4: {
                kx = -1;
                ky = 0;
                break;
            }
        }

        timerMoving = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                persX += kx * dx;
                persY += ky * dy;

            }
        });
        timerMoving.setRepeats(false);
        timerMoving.start();


    }


    public void paint(Graphics g) {

        g.clearRect(0, 0, getWidth(), getHeight());
        g.fillOval(100, 100, 50, 40);


        g.drawImage(image1, 300 + persX, 300 + persY, null);


    }

    public void initiate() {
        try {
            image1 = ImageIO.read(new File("pers.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("cant find image");
        }
    }

}
