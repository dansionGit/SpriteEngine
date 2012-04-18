package mygame;

import SpriteSheets.SpriteSheet;
import Sprites.BillboardSprite;
import Sprites.SpriteUpdateControl;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private SpriteSheet staticDebug;
    private Material spriteMat;
    private SpriteUpdateControl spriteControl;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        getFlyByCamera().setMoveSpeed(50);
        staticDebug = new SpriteSheet(assetManager, "/Textures/Debug/static.png", "/Textures/Debug/static.txt");
        spriteControl = new SpriteUpdateControl(rootNode, cam);
        
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.Blue);
        
        PointLight p = new PointLight();
        p.setColor(new ColorRGBA(0.4f, 0.8f, 0.8f, 1.0f));
        
        BillboardSprite static1 = new BillboardSprite();
        BillboardSprite static2 = new BillboardSprite();
        BillboardSprite static3 = new BillboardSprite();
        BillboardSprite static4 = new BillboardSprite(2, 4);           
        BillboardSprite static5 = new BillboardSprite(2, 4);           
        
        static1.setSpriteSheet(staticDebug);
        static2.setSpriteSheet(staticDebug);
        static3.setSpriteSheet(staticDebug);
        static4.setSpriteSheet(staticDebug);        
        static5.setSpriteSheet(staticDebug);        
        
        static1.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        static2.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        static3.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        static4.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        static5.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        
        static1.setSprite("static1");
        static2.setSprite("static1-2");
        static3.setSprite("static3");
        static4.setSprite("static4");
        static5.setSprite("static2");
        
        rootNode.addLight(ambient);
        rootNode.addLight(p);
        
        rootNode.attachChild(static1);
        rootNode.attachChild(static2);
        rootNode.attachChild(static3);
        rootNode.attachChild(static4);
        rootNode.attachChild(static5);        
        
        rootNode.addControl(spriteControl);
        
        static1.setLocalTranslation(-10, 0, -4);
        static2.setLocalTranslation(-5, 2, 4);
        static3.setLocalTranslation(5, -2, -4);
        static4.setLocalTranslation(0, 0, -10);
        static5.setLocalTranslation(0, 0, 10);
        
        static1.setPitchLock(true);
        rootNode.setQueueBucket(Bucket.Transparent);
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
