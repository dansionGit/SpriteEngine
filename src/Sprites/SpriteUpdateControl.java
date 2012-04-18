/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;
import com.jme3.scene.control.UpdateControl;
import java.util.List;

/**
 *
 * @author Dansion
 */
public class SpriteUpdateControl extends UpdateControl {
    private Node updateNode;
    private Camera cam;
    
    public SpriteUpdateControl(Node controlNode, Camera camera) {
        updateNode = controlNode;
        cam = camera;
    }
    
    @Override
    public void update(float tpf) {
        super.update(tpf);
        
        if(updateNode != null) {
            List <Sprite> sprites = updateNode.descendantMatches(Sprite.class);
            
            for(Sprite sprite : sprites) {
                //first update sprite rotation :                
                sprite.lookAt(cam.getLocation(), Vector3f.UNIT_Y);
                
                if(sprite.isPitchLocked()) {
                    sprite.rotateUpTo(Vector3f.UNIT_Y);
                }
            }                        
        }
    }        
}