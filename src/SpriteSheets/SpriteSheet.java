/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteSheets;

import com.jme3.asset.AssetManager;
import com.jme3.math.Vector2f;
import com.jme3.texture.Texture;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dansion
 */
public class SpriteSheet {
    private ArrayList <Vector2f> offset_array = new <Vector2f> ArrayList();
    private ArrayList <SpriteSheetItem> sprites = new <SpriteSheetItem> ArrayList();
    private int length, width, height;
    private Texture texture;
    private boolean mipmap_status = false;

    public SpriteSheet(AssetManager assetManager, String texture_file, String map_file) {
        BufferedReader in = null;

        //load texture
        texture = assetManager.loadTexture(texture_file);
        
        width = texture.getImage().getWidth();
        height = texture.getImage().getHeight();
    
        try {
            //System.out.println("Trying to open Spritesheet map file : " + map_file);
            InputStream inp = getClass().getResourceAsStream(map_file); 
            in = new BufferedReader(new InputStreamReader(inp)); 
            String str;
            
            //check length of map file
            while ((str = in.readLine()) != null) {
                SpriteSheetItem sprite = new SpriteSheetItem();                
                
                String[] strArr = str.split(" = ");
                sprite.name = strArr[0].trim();
                
                String [] coords = strArr[1].split(" ");                   
                
                float scale_x = Float.parseFloat(coords[2]) / width;
                float scale_y = Float.parseFloat(coords[3]) / height;
                
                float x = Float.parseFloat(coords[0]) / width;
                float y = 1 - Float.parseFloat(coords[1]) / height - scale_y;
                
                sprite.uv_offset = new Vector2f(x, y);                
                sprite.tex_scale = new Vector2f(scale_x, scale_y);
                
                sprites.add(sprite);
            } 

        } catch (IOException ex) {
            System.out.println("Could not open Spritesheet map file : " + map_file);
            Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(in != null) {
                    in.close();                
                }
            } catch (IOException ex) {
                Logger.getLogger(SpriteSheet.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }           
    }      

    /**
     * @return get the sheet texture
     */
    public Texture getTexture() {
        if(!mipmap_status) {
            texture.setMinFilter(Texture.MinFilter.NearestLinearMipMap);
            texture.setAnisotropicFilter(8);
            texture.setMagFilter(Texture.MagFilter.Nearest);
            
            mipmap_status = true;
        }
        
        return texture;
    }       
    
    public Vector2f getSpriteUv(int index) {
        return sprites.get(index).uv_offset;
    }
    
     public Vector2f getSpriteUv(String name) {
        return getSpriteUv(getSpriteIndex(name));
    }
    
    public Vector2f getSpriteScale(int index) {
        return sprites.get(index).tex_scale;
    }
    
    public Vector2f getSpriteScale(String name) {
        return getSpriteScale(getSpriteIndex(name));
    }

    /**
     * 
     * @param name is the name of a sprite in the spritesheet
     * @return returns the index of the sprite in the spritesheet if it exists else returns -1
     */
    public int getSpriteIndex(String name) {
        for(int i = 0; i < sprites.size(); i++) {
            if(sprites.get(i).name.equals(name)) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * 
     * @param name name of a sprite
     * @return returns the SpriteSheetItem for name if it exists else returns null
     */
    public SpriteSheetItem getSpriteItem(String name) {        
        return getSpriteItem(getSpriteIndex(name));
    }
    
    /**
     * @param index index of a sprite
     * @return returns the SpriteSheetItem for name if it exists else returns null
     */
    public SpriteSheetItem getSpriteItem(int index) {
        if (index < 0) {
            return null;
        }
        else {
            return sprites.get(index);
        }
    }
    
    /**
     * 
     * @return amount of available sprites
     */
    public int length() {
        return sprites.size();                
    }
}