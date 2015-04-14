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

    private int id;
    private int width, height;
    private int totalX, totalY;
    private int[][] tiles;
  
    //podria ser un vector para hacerlo dinamico
    private BufferedImage[] gTilePalette;
    public Layer(String path,String dirImg){
        loadWorld(path);
        gTilePalette = SliceImg(dirImg, width,height,totalX,totalY);
    }
    private BufferedImage[] SliceImg(String dirImg,int width,int height,int totalX,int totalY){
        int cW=(int)(totalX*1.0/width);
        int cH=(int)(totalY*1.0/height);
        BufferedImage[] a=new BufferedImage[width*height];
        Sprite sheet = new Sprite(ImageLoader.loadImage(dirImg));		
        
        for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				a[x+width*y]=sheet.crop(x*cW, y*cH,cW, cH);
			}
		}
        return a;
    }
    
    private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		totalX = Utils.parseInt(tokens[2]);
		totalY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);//pensar
			}
		}
	}
    public void render(Graphics g){
        int cW=(int)(totalX*1.0/width);
        int cH=(int)(totalY*1.0/height);
        for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				g.drawImage(gTilePalette[getTiles()[x][y]],(int) (x * cW),
						(int) (y * cH), cW , cH, null);
			}
		}
        
    }

    /**
     * @return the tiles
     */
    public int[][] getTiles() {
        return tiles;
    }
  
}
