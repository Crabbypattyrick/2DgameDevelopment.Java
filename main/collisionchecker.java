package main;

import main.player.Entity;

public class collisionchecker {

    GamePanel gp;

    public collisionchecker(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldx + entity.solidArea.x;
        int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entitybottomRow =entityBottomWorldY/gp.tileSize;

        int tilenum1, tilenum2;

        switch(entity.direction){
        case "up":
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tilenum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collision = true;
            }
            break;
        case "down":
            entitybottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tilenum1 = gp.tileM.mapTileNum[entityLeftCol] [entitybottomRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol] [entitybottomRow];
            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collision = true;
            }
            break;
        case "left":
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tilenum1 = gp.tileM.mapTileNum[entityLeftCol] [entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityLeftCol] [entitybottomRow];
            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collision = true;
            }
            break;
        case "right":
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tilenum1 = gp.tileM.mapTileNum[entityRightCol] [entityTopRow];
            tilenum2 = gp.tileM.mapTileNum[entityRightCol] [entitybottomRow];
            if(gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
                entity.collision = true;
            }
            break;

        }

    }
    

}
