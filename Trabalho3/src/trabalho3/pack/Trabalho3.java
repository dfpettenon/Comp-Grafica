/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalho3.pack;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.vp.*;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.GraphicsConfiguration;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.swing.JFrame;



/**
 *
 * @author Daniel
 */
public class Trabalho3 extends JFrame
{

  //The canvas to be drawn upon.
  public Canvas3D myCanvas3D;
  

  public Trabalho3()
  {
    //Mechanism for closing the window and ending the program.
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
    //Default settings for the viewer parameters.
    myCanvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
    
    // Manually create the viewing platform so that we can customize it
    ViewingPlatform viewingPlatform = new ViewingPlatform();

    
    //viewingPlatform.getViewPlatform().setActivationRadius(300f);

    // Set the view position back far enough so that we can see things
    TransformGroup viewTransform = viewingPlatform.getViewPlatformTransform();
    Transform3D t3d = new Transform3D();
   
    t3d.lookAt(new Point3d(-10,-30,20), new Point3d(0,0,0), new Vector3d(0,1,5));
    t3d.invert();
    viewTransform.setTransform(t3d);

    
    Viewer viewer = new Viewer(myCanvas3D);
    

    
    //Construct the SimpleUniverse:
    //First generate it using the Canvas.
    
    SimpleUniverse universe = new SimpleUniverse(viewingPlatform, viewer);
    
   


    //Default position of the viewer.
    //universe.getViewingPlatform().setNominalViewingTransform();


    //The scene is generated in this method.
    createSceneGraph(universe);


    //Add some light to the scene.
    addLight(universe);


    //The following three lines enable navigation through the scene using the mouse.
   OrbitBehavior ob = new OrbitBehavior(myCanvas3D);
   ob.setSchedulingBounds(new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE));
   universe.getViewingPlatform().setViewPlatformBehavior(ob);

  
    
    
    

    //Show the canvas/window.
    setTitle("Trabalho 3");
    setSize(1200,700);
    getContentPane().add("Center", myCanvas3D);
    setVisible(true);

  }




  public static void main(String[] args)
  {
     Trabalho3 le = new Trabalho3();
  }





  //In this method, the objects for the scene are generated and added to 
  //the SimpleUniverse.
  public void createSceneGraph(SimpleUniverse su)
  {

    //Carrega objetos.
    ObjectFile f = new ObjectFile(ObjectFile.RESIZE);
    Scene s = null;
    ObjectFile f2 = new ObjectFile(ObjectFile.RESIZE);
    Scene s2 = null;
    ObjectFile f3 = new ObjectFile(ObjectFile.RESIZE);
    Scene s3 = null;
    
    try
    {
      s = f.load("lp670.obj");
      s2 = f2.load("gas.obj");
      s3 = f3.load("Tree.obj");
    }
    catch (Exception e)
    {
      System.out.println("File loading failed:" + e);
    }
    //Carro
    Transform3D tfVei = new Transform3D();
    tfVei.setTranslation(new Vector3f(0.0f,0f,0.0f));
    TransformGroup tgVei = new TransformGroup(tfVei);
    tgVei.addChild(s.getSceneGroup());  
    
    //Gas Station
    Transform3D tfGas = new Transform3D();
    Transform3D rot = new Transform3D();
    tfGas.rotY(1.56);
    rot.rotZ(1.5);
    tfGas.mul(rot);
    tfGas.setTranslation(new Vector3f(1.0f,0.0f,0.5f));
    Vector3d scale = new Vector3d(3D, 3D, 3D);
    tfGas.setScale(scale);
    TransformGroup tgGas = new TransformGroup(tfGas);
    tgGas.addChild(s2.getSceneGroup()); 
    
    //Tree
    Transform3D tfTree = new Transform3D();
    tfTree.rotX(1.56);
    tfTree.setTranslation(new Vector3f(4.0f,-3.0f,0.7f));
    
    TransformGroup tgTree = new TransformGroup(tfTree);
    
    tgTree.addChild(s3.getSceneGroup()); 
    //In the following way, the names of the parts of the object can be
    //obtained. The names are printed.
    
    //Carro
    Hashtable namedObjects = s.getNamedObjects();
    Enumeration enumer = namedObjects.keys();
    String name;
    while (enumer.hasMoreElements())
    {
      name = (String) enumer.nextElement();
      //System.out.println("Name: "+name);
    }  
    //Gas Station
    Hashtable namedObjects2 = s2.getNamedObjects();
    Enumeration enumer2 = namedObjects2.keys();
    String name2;
    while (enumer2.hasMoreElements())
    {
      name2 = (String) enumer2.nextElement();
      //System.out.println("Name: "+name2);
    }  
    Hashtable namedObjects3 = s3.getNamedObjects();
    Enumeration enumer3 = namedObjects3.keys();
    String name3;
    while (enumer3.hasMoreElements())
    {
      name3 = (String) enumer3.nextElement();
      System.out.println("Name: "+name3);
    }  
    
    
    
    //Veículo
    Appearance blackApp = new Appearance();
    Appearance grayApp = new Appearance();
    Appearance darkBlueApp = new Appearance();
    
    setToMyDefaultAppearance(blackApp,new Color3f(0.0f,0.0f,0.0f));
    setToMyDefaultAppearance(grayApp,new Color3f(0.3f,0.3f,0.3f));
    setToMyDefaultAppearance(darkBlueApp,new Color3f(0.0f,0.0f,0.2f));
    
    Shape3D partOfTheObject;
            
    partOfTheObject = (Shape3D) namedObjects.get("w0");
    partOfTheObject.setAppearance(blackApp);  
    
    partOfTheObject = (Shape3D) namedObjects.get("w1");  
    partOfTheObject.setAppearance(blackApp);  
    
    partOfTheObject = (Shape3D) namedObjects.get("w2");  
    partOfTheObject.setAppearance(blackApp);  
    
    partOfTheObject = (Shape3D) namedObjects.get("w3");  
    partOfTheObject.setAppearance(blackApp);  
   
    partOfTheObject = (Shape3D) namedObjects.get("glass");  
    partOfTheObject.setAppearance(grayApp);    
   
    partOfTheObject = (Shape3D) namedObjects.get("default");  
    partOfTheObject.setAppearance(darkBlueApp);
    
    partOfTheObject = (Shape3D) namedObjects.get("badge");  
    partOfTheObject.setAppearance(grayApp);
      
    //Gas Station
    Appearance redApp = new Appearance();
    Appearance whiteApp = new Appearance();

    setToMyDefaultAppearance(redApp,new Color3f(0.4f,0.0f,0.0f));
    setToMyDefaultAppearance(whiteApp,new Color3f(0.4f,0.4f,0.4f));
   
    
    Shape3D partOfTheGasStation;
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("______");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__57");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_6_-_default");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__266");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__233");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_9_-_default");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__79");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__46");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__255");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__222");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("____4");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("____3");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("____2");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_7_-_default");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_5_-_default");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__68");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__35");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__277");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_10_-_defaul");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("vray_metal_chrom");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("material__90");
    partOfTheGasStation.setAppearance(whiteApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("__-_default1");
    partOfTheGasStation.setAppearance(redApp);
    
    partOfTheGasStation = (Shape3D) namedObjects2.get("_____");
    partOfTheGasStation.setAppearance(whiteApp);
    
    
    
    //Load the image for the texture. (Para o chão)
    TextureLoader imgGrass = new TextureLoader("sandtile.jpg",null);
    TextureLoader imgStone = new TextureLoader("stonetile.jpg",null);
    //Generate a (scaled) image of the texture. Height and width must be
    //powers of 2.
    ImageComponent2D grassComp = imgGrass.getScaledImage(64,128);
    ImageComponent2D stoneComp = imgStone.getScaledImage(64,128);
    //Generate the texture.
    Texture2D grass = new Texture2D(Texture2D.BASE_LEVEL,Texture2D.RGB,grassComp.getWidth(),grassComp.getHeight());  
    Texture2D stone = new Texture2D(Texture2D.BASE_LEVEL,Texture2D.RGB,stoneComp.getWidth(),stoneComp.getHeight());  
    
    grass.setImage(0,grassComp);  
    stone.setImage(0,stoneComp);
    
    //An Appearance which will use the texture.
    Appearance grassApp = new Appearance();
    Appearance stoneApp = new Appearance();
    
    grassApp.setTexture(grass);
    stoneApp.setTexture(stone);
    
    TextureAttributes grassAttr = new TextureAttributes();
    TextureAttributes stoneAttr = new TextureAttributes();
    
    grassAttr.setTextureMode(TextureAttributes.REPLACE);
    stoneAttr.setTextureMode(TextureAttributes.REPLACE);
    
    grassApp.setTextureAttributes(grassAttr);
    stoneApp.setTextureAttributes(stoneAttr);
    
    Material mat = new Material();
    mat.setShininess(100.0f);
    
    grassApp.setMaterial(mat);
    stoneApp.setMaterial(mat);
    
    TexCoordGeneration tcg = new TexCoordGeneration(TexCoordGeneration.OBJECT_LINEAR,TexCoordGeneration.TEXTURE_COORDINATE_2);
    
    grassApp.setTexCoordGeneration(tcg);  
    stoneApp.setTexCoordGeneration(tcg);  
    
    
   //Posicionamento do chão
    Box groundGrass = new Box(100f,100f,1f,grassApp);
    Box groundStone = new Box(3f,1f,0.001f,stoneApp);

    Transform3D groundGrassTrans = new Transform3D();
    Transform3D groundStoneTrans = new Transform3D();
    
    groundGrassTrans.setTranslation(new Vector3f(0f,0f,-1.2f));
    groundStoneTrans.setTranslation(new Vector3f(1f,0f,-0.2f));
  
    TransformGroup tgGrass = new TransformGroup(groundGrassTrans);
    TransformGroup tgStone = new TransformGroup(groundStoneTrans);
    tgGrass.addChild(groundGrass);
    tgStone.addChild(groundStone);
    
    
 

//***Generate a sphere ***

    //Generate an Appearance for the sphere.
    Color3f ambientColourSphere = new Color3f(0.2f,0.2f,0.0f);
    Color3f emissiveColourSphere = new Color3f(0.0f,0.0f,0.0f);
    Color3f diffuseColourSphere = new Color3f(0.4f,0.4f,0.0f);
    Color3f specularColourSphere = new Color3f(0.8f,0.8f,0.0f);
    float shininessSphere = 200.0f;

    Appearance sphereApp = new Appearance();

    sphereApp.setMaterial(new Material(ambientColourSphere,emissiveColourSphere,
                           diffuseColourSphere,specularColourSphere,shininessSphere));



    Sphere mySphere = new Sphere(1.0f,Sphere.GENERATE_NORMALS,100,sphereApp);
    Transform3D tfSphere = new Transform3D();
    tfSphere.setTranslation(new Vector3f(0.0f,0.0f,7.0f));

    TransformGroup tgSphere = new TransformGroup(tfSphere);
    tgSphere.addChild(mySphere);

    
    
    
    //The Alpha for the car movment.
    int timeMoveStart = 6000; //começa nos 6 segs
    
    
    Alpha carAlpha = new Alpha(1,Alpha.INCREASING_ENABLE+Alpha.DECREASING_ENABLE,
                                      timeMoveStart,0,5000,5000,
                                      5000,5000,5000,0);

    //The car movement.
    
    Transform3D axisVei = new Transform3D();
    //axisVei.rotX(-1.56);
    axisVei.rotZ(-1.56);
    //axisVei.rotY(-1.56);
    PositionInterpolator posIPCar = new PositionInterpolator(carAlpha,tgVei,axisVei,0.0f,20.0f);
    BoundingSphere boundss = new BoundingSphere(new Point3d(0.0,0.0,0), 1.0);
    posIPCar.setSchedulingBounds(boundss);
    
    //Add the car movement to the group
    tgVei.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tgVei.addChild(posIPCar);

    
    
    
    



    //*** Generate (the root of) the scenegraph. ***
    BranchGroup theScene = new BranchGroup();


    //Adiciona os objetos na cena
    
    theScene.addChild(tgSphere);
    theScene.addChild(tgGrass);
    theScene.addChild(tgStone);
    theScene.addChild(tgVei);
    theScene.addChild(tgGas);
    theScene.addChild(tgTree);

    //The bounding region for the background.
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),Double.MAX_VALUE);
    //Load the background image.
    TextureLoader textureLoad = new TextureLoader("blue_sky.jpg",null);
    //Define the image as the background and add it to the scene.
    Background bgImage = new Background(textureLoad.getImage());
    bgImage.setApplicationBounds(bounds);
    theScene.addChild(bgImage);

    theScene.compile();

    //Add everything to the universe.
    su.addBranchGraph(theScene);

  }



  /**
  * Generates a default surface (Appearance) in a specified colour.
  *
  * @param app      The Appearance for the surface.
  * @param col      The colour.
  */
  public static void setToMyDefaultAppearance(Appearance app, Color3f col)
  {
    app.setMaterial(new Material(col,col,col,col,150.0f));
  }


  //The different light sources are added to the scene here.
  public void addLight(SimpleUniverse su)
  {

    BranchGroup bgLight = new BranchGroup();

    BoundingSphere boundsLight = new BoundingSphere(new Point3d(0.0,0.0,7.0), 10);




    //Light no. 1: directional light.
    Color3f lightColour1 = new Color3f(0.8f, 0.8f, 0.8f);
    Vector3f lightDir1  = new Vector3f(0.0f, 0.0f, -0.1f);
    DirectionalLight light1 = new DirectionalLight(lightColour1, lightDir1);
    light1.setInfluencingBounds(boundsLight);
   bgLight.addChild(light1);



    //Light no. 2: a point light.
    Color3f lightColour2 = new Color3f(0.3f, 0.3f, 0.3f);
    PointLight light2 = new PointLight(lightColour2,
                                       new Point3f(1.0f,1.0f,1.0f),
                                       new Point3f(0.2f,0.01f,0.01f));
    light2.setInfluencingBounds(boundsLight);
    //bgLight.addChild(light2);


    //Light no. 3: a spotlight.
    Color3f lightColour3 = new Color3f(0.3f, 0.3f, 0.3f);
    SpotLight light3 = new SpotLight(lightColour3,
                                     new Point3f(0.0f,0.0f,1.0f),
                                     new Point3f(0.1f,0.1f,0.01f),
                                     new Vector3f(0.0f,0.0f,-1.0f),
                                     (float) (Math.PI/20),
                                     0.0f);

    light3.setInfluencingBounds(boundsLight);
    //bgLight.addChild(light3);



    //Light no. 4: ambient light.
    Color3f lightColour4 = new Color3f(1.0f, 1.0f, 1.0f);
    AmbientLight light4 = new AmbientLight(lightColour4);
    light4.setInfluencingBounds(boundsLight);
    bgLight.addChild(light4);


    su.addBranchGraph(bgLight);
  }



}

