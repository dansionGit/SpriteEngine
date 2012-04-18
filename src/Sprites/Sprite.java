/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import SpriteSheets.SpriteSheet;
import SpriteSheets.SpriteSheetItem;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.scene.Geometry;

/**
 *
 * @author Dansion
 */
public class Sprite extends Geometry {
    private SpriteSheet sheet;
    private SpriteMesh sprite;
    private SpriteSheetItem item;
    
    private boolean lockPitch = false;
    
    private Vector2f size;
    
    public Sprite() {
        this(1, 1);
    }
    
    public Sprite(float size_x, float size_y) {        
        super();  
        
        sprite = new SpriteMesh(size_x, size_y);
        
        size = new Vector2f(size_x, size_y);
        setMesh(sprite);
    }
    
    public void setSpriteSheet(SpriteSheet sheet) {
        this.sheet = sheet;
        
        if(getMaterial() != null) {
            getMaterial().setTexture("DiffuseMap", sheet.getTexture());
            getMaterial().setColor("Diffuse", ColorRGBA.White);
            getMaterial().setBoolean("UseAlpha", true);   
            getMaterial().setBoolean("TranslateUV", true);   
            getMaterial().getAdditionalRenderState().setBlendMode(BlendMode.Alpha);           
        }
    }

    @Override
    public void setMaterial(Material material) {
        super.setMaterial(material);
        
        setSpriteSheet(sheet);
    }        
    
    public void setSprite(String name) {
        setSprite(sheet.getSpriteIndex(name));
    }
    
    public void setSprite(int index) {        
        item = sheet.getSpriteItem(index);        
        sprite = new SpriteMesh(size.x, size.y, item.tex_scale.x , item.tex_scale.y);
                
        setMesh(sprite);        
        getMaterial().setVector2("TranslateAmount", item.uv_offset);
    }
    
    public void setUVOffset(Vector2f offset) {
        getMaterial().setVector2("TranslateAmount", offset);
    }
    
    public void setPitchLock(boolean lock) {
        lockPitch = lock;
    }
    
    public boolean isPitchLocked() {
        return lockPitch;
    }
}