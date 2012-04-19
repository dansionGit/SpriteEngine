/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

/**
 *
 * @author Dansion
 */
public class AnimatedStaticSprite extends AnimatedSprite {
    public AnimatedStaticSprite(int frames) {
        super(frames);
    }
    
    public AnimatedStaticSprite(float size_x, float size_y) {
        super(size_x, size_y);
    }
    
    public AnimatedStaticSprite(float size_x, float size_y, int frames) {
        super(size_x, size_y, frames);
    }
}
