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
    private String mapFile;

    public SpriteSheet(AssetManager assetManager, String texture_file, String map_file) {
        BufferedReader in = null;
        mapFile = map_file;
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
                
                //check for animation / roto-information 
                String[] extra_info = sprite.name.split("-");
                if(extra_info.length == 3) {
                    sprite.rotoStance = extra_info[2];
                    sprite.frame = Integer.parseInt(extra_info[1]);
                    
                    //and update sprite name
                    sprite.name = extra_info[0];
                } else if( extra_info.length == 2){
                    System.out.println(extra_info[1]);
                    try {
                        sprite.frame = Integer.parseInt(extra_info[1]);
                    }
                    catch (NumberFormatException ex) {
                        sprite.rotoStance = extra_info[1];
                    }
                    
                    sprite.name = extra_info[0];
                }
                
                System.out.println(sprite.name);
                //calculate coordinates
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
        return getSpriteIndex(name, 0);
    }
    
    public int getSpriteIndex(String name, int frame) {            
        for(int i = 0; i < sprites.size(); i++) {            
            //check if sprite with {name} or {name}-{frame 0} exists and returns index
            if(sprites.get(i).name.equals(name) && sprites.get(i).frame == frame) {
                return i;
            }
        }
        
        throw new java.lang.IndexOutOfBoundsException("Sprite with name : " + name + " does not exist in sheet " + mapFile);
    }
    
    /**
     * 
     * @param name name of a sprite
     * @return returns the SpriteSheetItem for name if it exists else returns null
     */
    public SpriteSheetItem getSpriteItem(String name) {        
        return getSpriteItem(getSpriteIndex(name));
    }
    
    public SpriteSheetItem getSpriteItem(String name, int frame) {        
        return getSpriteItem(getSpriteIndex(name, frame));
    }    
    
    /**
     * @param index index of a sprite
     * @return returns the SpriteSheetItem for name if it exists else returns null
     */
    public SpriteSheetItem getSpriteItem(int index) {
        if (index < 0 || index >= sprites.size()) {
            throw new java.lang.IndexOutOfBoundsException("Sprite with index : " + index + " does not exist in sheet " + mapFile);
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