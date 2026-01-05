package main.tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Tilemanager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public Tilemanager(GamePanel gp){
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxworldcol][gp.maxworldrow];

        getTileImage();
        loadmap("/main/maps/world.txt");
    }

    public void getTileImage(){
    try {
        tile[0] = new Tile();
        var grassStream = getClass().getResourceAsStream("/main/tiles/grass.png");
        if (grassStream != null) {
            tile[0].image = ImageIO.read(grassStream);
        }

        tile[1] = new Tile();
        var tilesStream = getClass().getResourceAsStream("/main/tiles/tiles.png");
        if (tilesStream != null) {
            tile[1].image = ImageIO.read(tilesStream);
        }

        tile[2] = new Tile();
        var waterStream = getClass().getResourceAsStream("/main/tiles/water.png");
        if (waterStream != null) {
            tile[2].image = ImageIO.read(waterStream);
            tile[2].collision = true;
        }

        tile[3] = new Tile();
        var treeStream = getClass().getResourceAsStream("/main/tiles/tree.png");
        if (treeStream != null) {
            tile[3].image = ImageIO.read(treeStream);
            tile[3].collision = true;
        }

        tile[4] = new Tile();
        var pathStream = getClass().getResourceAsStream("/main/tiles/path.png");
        if (pathStream != null) {
            tile[4].image = ImageIO.read(pathStream);
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public void loadmap(String filePath){
    try{
        InputStream is = getClass().getResourceAsStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int col = 0;
        int row = 0;

        while(col < gp.maxworldcol && row < gp.maxworldrow){
            String line = br.readLine();
            while(col < gp.maxworldcol){
                String numbers[] = line.split(" ");

                int num = Integer.parseInt(numbers[col]);

                mapTileNum[col][row] = num;
                col++;
            }
            if(col == gp.maxworldcol){
                col = 0;
                row++;
            }
        }
        br.close();

    }catch(Exception e){

    }
}

public void draw(Graphics2D g2){
    
    int worldcol = 0;
    int worldrow = 0;

    while(worldcol < gp.maxworldcol && worldrow < gp.maxworldrow){

        int tileNum = mapTileNum[worldcol][worldrow];

        int worldx = worldcol * gp.tileSize;
        int worldy = worldrow * gp.tileSize;
        int screenx = worldx - gp.player.worldx + gp.player.screenx;
        int screeny = worldy - gp.player.worldy + gp.player.screeny;

        if(worldx + gp.tileSize > gp.player.worldx - gp.player.screenx &&
           worldx - gp.tileSize < gp.player.worldx + gp.player.screenx &&
           worldy + gp.tileSize > gp.player.worldy - gp.player.screeny &&
           worldy - gp.tileSize < gp.player.worldy + gp.player.screeny) {
            
            if(tile[tileNum] != null && tile[tileNum].image != null) {
                g2.drawImage(tile[tileNum].image, screenx, screeny, gp.tileSize, gp.tileSize, null);
            }
        }
        
        worldcol++;

        if(worldcol == gp.maxworldcol){
            worldcol = 0;
            worldrow++;
            
        }
    }
}
    
}