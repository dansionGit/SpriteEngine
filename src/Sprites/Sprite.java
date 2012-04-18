/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import SpriteSheets.SpriteSheet;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;

/**
 *
 * @author Dansion
 */
public class Sprite extends Geometry {
    private SpriteSheet sheet;
    private SpriteMesh sprite;
    private boolean lockPitch = false;
    
    public Sprite() {
        this(1, 1);
    }
    
    public Sprite(float size_x, float size_y) {        
        super();  
        
        sprite = new SpriteMesh(size_x, size_y);
        
        setMesh(sprite);
    }
    
    public void setSpriteSheet(SpriteSheet sheet) {
        this.sheet = sheet;
        
        if(getMaterial() != null) {
            getMaterial().setTexture("DiffuseMap", sheet.getTexture());
            getMaterial().setColor("Diffuse", ColorRGBA.White);
            getMaterial().setBoolean("UseAlpha", true);   
            getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);           
        }
    }

    @Override
    public void setMaterial(Material material) {
        super.setMaterial(material);
        
        setSpriteSheet(sheet);
    }        
    
    public void setSprite(String name) {
        sheet.getTextureUv(sheet.getTextureIndex(name));
    }
    
    public void setSprite(int index) {        
    }
    
    public void setPitchLock(boolean lock) {
        lockPitch = lock;
    }
    
    public boolean isPitchLocked() {
        return lockPitch;
    }
}