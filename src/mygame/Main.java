package mygame;

import SpriteSheets.SpriteSheet;
import Sprites.AnimatedSprite;
import Sprites.AnimatedStaticSprite;
import Sprites.BillboardSprite;
import Sprites.RotoSprite;
import Sprites.SpriteUpdateControl;
import Sprites.StaticSprite;
import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.material.Material;
import com.jme3.material.RenderState.FaceCullMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;

/**
 * test
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private SpriteSheet staticDebug;
    private SpriteSheet rotoDebug;
    private Material spriteMat;
    private SpriteUpdateControl spriteControl;
    private RotoSprite roto1 = new RotoSprite(2,4,2);
    private Geometry roto2;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        getViewPort().setBackgroundColor(new ColorRGBA(0.9f, 1, 1, 1));
        getFlyByCamera().setMoveSpeed(50);
        staticDebug = new SpriteSheet(assetManager, "/Textures/Debug/static.png", "/Textures/Debug/static.txt");
        rotoDebug = new SpriteSheet(assetManager, "/Textures/Debug/roto.png", "/Textures/Debug/roto.txt");
        spriteControl = new SpriteUpdateControl(rootNode, cam);
        
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        
        PointLight p = new PointLight();
        p.setColor(new ColorRGBA(0.9f, 0.8f, 0.8f, 1.0f));
        
        BillboardSprite static1 = new BillboardSprite();
        StaticSprite static2 = new StaticSprite();
        AnimatedStaticSprite static3 = new AnimatedStaticSprite(3);
        BillboardSprite static4 = new BillboardSprite(2, 4);           
        AnimatedSprite static5 = new AnimatedSprite(2, 4, 3);                   
        
        roto1.setSpriteSheet(rotoDebug);
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
        roto1.setMaterial(new Material(assetManager, "MatDefs/UVOffsetLighting.j3md"));
        
        static1.setSprite("static1");
        static2.setSprite("static1");
        static3.setSprite("static3");
        static4.setSprite("static4");
        static5.setSprite("static2");
        
        roto1.setSprite("walk");
        roto1.setFps(2);
        roto1.setPlaying(true);
        roto1.setPitchLock(true);
        
        static2.getMaterial().getAdditionalRenderState().setFaceCullMode(FaceCullMode.Off);
        
        static3.setPlaying(true);
        static3.setFps(3);
        
        static5.setPlaying(true);
        static5.setLoop(true);
        static5.setFps(1);
        
        rootNode.addLight(ambient);
        rootNode.addLight(p);
        
        rootNode.attachChild(static1);
        rootNode.attachChild(static2);
        rootNode.attachChild(static3);
        rootNode.attachChild(static4);
        rootNode.attachChild(static5);        
        
        rootNode.attachChild(roto1);
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
        Quaternion rot = roto1.getLocalRotation();
        rot = rot.mult(new Quaternion().fromAngleAxis(tpf * FastMath.PI / 8,   new Vector3f(0,1,0)));
        
        Vector3f dir = rot.getRotationColumn(2);
        dir.normalizeLocal();                        
        dir = dir.mult(4f * tpf);

        roto1.setLocalRotation(rot);        
        roto1.setLocalTranslation(roto1.getLocalTranslation().add(dir));
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
