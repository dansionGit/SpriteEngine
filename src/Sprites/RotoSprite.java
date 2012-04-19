/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.util.TempVars;

/**
 *
 * @author Dansion
 */
public class RotoSprite extends AnimatedSprite {
    private Quaternion localRot = new Quaternion();
    
    public RotoSprite() {
        super();
    }
    
    public RotoSprite(int frames) {
        super(frames);
    }
    
    public RotoSprite(float size_x, float size_y) {
        super(size_x, size_y);
    }
    
    public RotoSprite(float size_x, float size_y, int frames) {
        super(size_x, size_y, frames);
    }
    
    public void update() {
        Quaternion spriteRot = new Quaternion(super.getLocalRotation());
        spriteRot = spriteRot.inverseLocal();
        
        Quaternion actorRot = new Quaternion(getLocalRotation());
        actorRot = actorRot.multLocal(spriteRot);
        float [] angles = actorRot.toAngles(null);
        
        float rot = angles[1];
        float pi = FastMath.PI;
        
        //right
        if(rot <= 0.75 * pi && rot > 0.25 * pi) {
            setOrientation("right");            
            return;
        }
        
        //left;
        if(rot >= -0.75 * pi && rot < -0.25 * pi) {            
            setOrientation("left");
            return;
        }
        
        //back
        if(rot < -0.75 * pi || rot > 0.75 * pi) {            
            setOrientation("back");            
            return;
        }
        
        //front
        if(rot >= -0.25 * pi && rot <= 0.25 * pi) {            
            setOrientation("front");
            return;
        }
    }

    @Override
    public Quaternion getLocalRotation() {
        return localRot;
    }

    @Override
    public void setLocalRotation(Quaternion quaternion) {
        localRot = quaternion;
    }

    @Override
    public Quaternion getWorldRotation() {
        return localRot;
    }

    @Override
    public void lookAt(Vector3f position, Vector3f upVector) {
        Vector3f worldTranslation = getWorldTranslation();

        TempVars vars = TempVars.get();

        Vector3f compVecA = vars.vect4;
        vars.release();

        compVecA.set(position).subtractLocal(worldTranslation);
        super.getLocalRotation().lookAt(compVecA, upVector);

        setTransformRefresh();
    }

    public void setOrientation(String roto_orientation) {
        Vector2f uv_offset = getSheet().getSpriteItem(getSpriteName(), getFrame(), roto_orientation).uv_offset;
        setUVOffset(uv_offset);

        return;
    }
}
