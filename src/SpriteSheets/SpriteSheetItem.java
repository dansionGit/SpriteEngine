/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SpriteSheets;

import com.jme3.math.Vector2f;

/**
 *
 * @author Dansion
 */
public class SpriteSheetItem {
    public String name;
    
    //uv pos
    public Vector2f uv_offset;
    
    //relative scaled size of sprite in relation to spritesheet
    public Vector2f tex_scale;
    
    public int frame = 0;
    
    public String rotoStance;
}
