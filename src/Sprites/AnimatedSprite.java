/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import com.jme3.math.Vector2f;

/**
 *
 * @author Dansion
 */
public class AnimatedSprite extends Sprite {
    private int currentFrame = 0;
    private int fps = 12;
    private int length = 0;
    private int frames = 1;
    private float playTime = 0;    
    
    private boolean playing = false;
    private boolean loop = true;    
    
    public AnimatedSprite() {
        super();
    }
    
    public AnimatedSprite(int frames) {
        super();
        
        this.frames = frames;
    }
    
    public AnimatedSprite(float size_x, float size_y) {
        super(size_x, size_y);
    }
    
    public AnimatedSprite(float size_x, float size_y, int frames) {
        super(size_x, size_y);
        
        this.frames = frames;
    }
    
    public void setFrame(int frame) {              
        try {                        
            Vector2f uv_offset = getSheet().getSpriteItem(getSpriteName(), frame).uv_offset;
            setUVOffset(uv_offset);
            
            currentFrame = frame;
            
            return;
        }
        catch (IndexOutOfBoundsException ex) {
            //first check if we are in a looping animation
            if(!isLooping()) {
                setPlaying(false);
                
                return;
            }
            //frame doesnt seem to exist so go back to frame 0;
            currentFrame = 0;                        
            
            Vector2f uv_offset = getSheet().getSpriteItem(getSpriteName(), currentFrame).uv_offset;
            setUVOffset(uv_offset);
            
            return;
        }               
    }
    
    public void update(float tpf) {
        if(isPlaying()) {
            playTime = playTime + tpf;

            int cur_frame_pos = (int) (playTime * fps) % frames;

            if(cur_frame_pos == frames - 1 && !isLooping()) {
                setPlaying(false);
            }            

            if(cur_frame_pos != currentFrame) {
                setFrame(cur_frame_pos);
            }
        }
    }
    
    /**
     * 
     * @return returns current animation frame
     */
    public int getFrame() {
        return currentFrame;
    }
    
    public void setFps(int fps) {
        this.fps = fps;
    }
    
    public void setPlaying(boolean play) {
        playing = play;
    }
    
    public void setLoop(boolean active) {
        loop = active;
    }
    
    public boolean isPlaying()     {
        return playing;
    }
    
    public boolean isLooping() {
        return loop;
    }
    
    public int getFps() {
        return fps;
    }
    
    public float getPlayTime() {
        return playTime;
    }    
}
