/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;

/**
 *
 * @author Dansion
 */
class SpriteMesh extends Mesh {
    //sprite corners
    private static Vector3f spriteLeftTop = new Vector3f(-.5f, 1, 0);
    private static Vector3f spriteRightTop = new Vector3f(.5f, 1, 0);
    private static Vector3f spriteLeftBottom = new Vector3f(-.5f, 0, 0);
    private static Vector3f spriteRightBottom = new Vector3f(.5f, 0, 0);
    
    //uv Coords
    private static Vector2f uvLeftBottom = new Vector2f(0,0);
    private static Vector2f uvRightBottom = new Vector2f(1,0);
    private static Vector2f uvLeftTop = new Vector2f(0,1);
    private static Vector2f uvRightTop = new Vector2f(1,1); 
    
    private static Vector3f normal = new Vector3f(0,0,1);
    
    private static int [] index = {0, 2, 1, 1, 2, 3};
    
    public SpriteMesh(float size_x, float size_y) {
        this(size_x, size_y, 1, 1);
    }
    
    public SpriteMesh(float size_x, float size_y, float tex_x_scale, float tex_y_scale) {
        Vector3f scale = new Vector3f(size_x, size_y, 0);
        
        setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(
                new Vector3f(spriteLeftTop).multLocal(scale),
                new Vector3f(spriteRightTop).multLocal(scale),
                new Vector3f(spriteLeftBottom).multLocal(scale),
                new Vector3f(spriteRightBottom).multLocal(scale)
        ));        
        
        setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uvLeftTop, uvRightTop, uvLeftBottom, uvRightBottom));        
        setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(normal, normal, normal, normal));
        setBuffer(Type.Index, 1, BufferUtils.createIntBuffer(index));
		
        updateBound();
    }
}
