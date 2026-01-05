package main.player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.Keyhandler;

public class Player extends Entity {

    GamePanel gp;
    Keyhandler keyh;

    public final int screenx;
    public final int screeny;

    public Player(GamePanel gp, Keyhandler keyh){
        this.gp = gp;
        this.keyh = keyh;

        screenx = gp.screenWidth/2 - (gp.tileSize/2);
        screeny = gp.screenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldx = gp.tileSize * 23;
        worldy = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        solidArea = new Rectangle(12, 12, 8, 32);
    }

    public void getPlayerImage(){
    try {
        var upStream = getClass().getResourceAsStream("/main/player/naniback.png");
        if (upStream != null) {
            up1 = ImageIO.read(upStream);
            up2 = up1;
        }
        
        var downStream = getClass().getResourceAsStream("/main/player/nani.png");
        if (downStream != null) {
            down1 = ImageIO.read(downStream);
            down2 = down1;
        }
        
        var leftStream = getClass().getResourceAsStream("/main/player/naniright.png");
        if (leftStream != null) {
            left1 = ImageIO.read(leftStream);
            left2 = left1;
        }
        
        var rightStream = getClass().getResourceAsStream("/main/player/nanileft.png");
        if (rightStream != null) {
            right1 = ImageIO.read(rightStream);
            right2 = right1;
        }
        
    } catch (IOException e) {

    }
}

public void update(){

    boolean moving = false;

    if(keyh.upPressed == true){
        direction = "up";
        moving = true;
    }else if(keyh.downPressed == true){
        direction = "down";
        moving = true;
    }else if(keyh.leftPressed == true){
        direction = "left";
        moving = true;
    }else if(keyh.rightPressed == true){
        direction = "right";
        moving = true;
    }

    if(moving){
        collision = false;
        gp.cchecker.checkTile(this);

        if(collision == false){
        switch(direction){
            case "up":
                worldy -= speed;
                break;
            case "down":
                worldy += speed;
                break;
            case "left":
                worldx -= speed;
                break;
            case "right":
                worldx += speed;
                break;
            }
        }
    }

    if(worldx < gp.tileSize * 8) worldx = gp.tileSize * 8;
    if(worldy < gp.tileSize * 6) worldy = gp.tileSize * 6;
    if(worldx > gp.tileSize * 41) worldx = gp.tileSize * 41;
    if(worldy > gp.tileSize * 43) worldy = gp.tileSize * 43;
}

    public void draw(Graphics2D g2){
        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        if(image != null){
            g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize, null);
        } else {

            g2.setColor(Color.BLUE);
            g2.fillRect(screenx, screeny, gp.tileSize, gp.tileSize);
        }
    }
}