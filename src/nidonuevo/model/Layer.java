/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nidonuevo.model;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author TOSHIBA
 */
public class Layer {
    // se toma 8 como alto y ancho del tile
    private int id;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tiles;
    private BufferedImage[][] images;
    //podria ser un vector para hacerlo dinamico
    private BufferedImage[] gTilePalette;
    public Layer(String path,String dirImg){
        loadWorld(path);
        gTilePalette = SliceImg(dirImg, width,height);
    }
    private BufferedImage[] SliceImg(String dirImg,int width,int height){
        BufferedImage[] a=new BufferedImage[width*height];
        Sprite sheet = new Sprite(ImageLoader.loadImage(dirImg));		
        
        for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				a[x+x*y]=sheet.crop(x*8, y*8, width, height);
			}
		}
        return a;
    }
    
    private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
    public void render(Graphics g){
        for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				g.drawImage(gTilePalette[tiles[x][y]],(int) (x * 8),
						(int) (y * 8), 8 , 8, null);
			}
		}
        
    }
  
}
