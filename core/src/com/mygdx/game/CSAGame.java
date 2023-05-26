//XG: Yo wassup boys! I've added a whole lot of documentation and notes on what everything does, so that
//XG: it's easier to understand everything, even if you didn't work on one section of the code.
//XG: in the future, I'd like both of you to mark your notes with your initials so that we know who is
//XG: talking about what. You definitely don't need to be as descriptive as I am though.

//XG: There are a couple sections where I will directly say that I'm not entirely sure what something does.
//XG: If either of you are better informed about that thing, feel free to delete that comment and clarify.





//XG: Not actually sure what this top bit does. Definitely important but I don't know why.
//XG: If I comment it out all our bullet stuff turns to errors and our main class doesn't work.
package com.mygdx.game;

//XG: Was used to calculate bullet trajectories but became irrelevant after I managed to fix the bullets
//XG: by means that I currently do not understand.
import java.util.Iterator;
import java.util.Objects;
//XG: not sure what the next two do, but they are also important.
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//XG: Used to track inputs. (keyboard, mouse, etc) Fairly self explanatory.
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.OrthographicCamera;
//XG: Allows us to use textures. Self-explanatory.
import com.badlogic.gdx.graphics.Texture;
//XG: Draws all our graphic stuff. Don't understand this bit too well at the moment, but it's definitely important
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.*;
//XG: We use the rectangle for basically all our objects at the moment. We should probably have our own
//XG: objects for the player and other stuff.
//XG: Used for calculating the bullet trajectory. Only used once. Could probably be replaced.
//XG: Used for the bullet array.
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
//XG: Used for making sure things don't get stretched when the window is resized, and so that later on we'll
//XG: be able to use world coordinates instead of pixel measurements.
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//XG: Used for calculating bullet trajectories. Will likely be reused later on for enemy pathing.
//XG: I was originally intending to use MathUtils for this but somehow ended up with these instead.
import static java.lang.Math.cos;
import static java.lang.Math.sin;
//XG: The following stuff is what we need in order to use the Tiled map editor for our map.
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.TextureMapObject;
public class CSAGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture ime;
	Texture bg;
	Texture bul;
	private Rectangle player;
	private Rectangle emey;
	dumbenmey oneem= new dumbenmey(20, 20, 0, 600, 200,img ,true);
	dumbenmey oneem1= new dumbenmey(20, 20, 100, 600, 200,img ,false);
	dumbenmey oneem2= new dumbenmey(20, 20, 200, 800, 200,img ,true);
	dumbenmey oneem3= new dumbenmey(20, 20, 0, 100, 200,img ,false);
	private OrthographicCamera camera;
	private Viewport viewport;
	private int moveSpeed;
	private int emenymovement;
	Rectangle box2;
	Rectangle box;
	private Array<Rectangle> bullets;
	private int bulletSpeed;
	 double bulletVelX;
	 double bulletVelY;
	 int timeSeconds = 0;
	 int period = 1;
	private Rectangle wall;
	TiledMap tiledMap;
	Map map;
	TiledMapRenderer tiledMapRenderer;
private int wallsize;



Texture img2;
//XG: collision stuff(still testing)
	MapObjects objects;


	//camera.position.set(x, y)
	@Override
	public void create () {
		/* oneem= new dumbenmey(20, 20, 0, 600, 200,img ,true);
		 oneem1=ew dumbenmey(20, 20, 100, 600, 200,img ,false);
		 oneem2=  dumbenmey(20, 20, 200, 800, 200,img ,true);
		 oneem3=  dumbenmey(20, 20, 0, 100, 200,img ,false);
		 */

		map = tiledMap;
 box =new Rectangle(200,540,100,100);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 1000);
		// XG: not entirely certain how this works, but what it does is set the viewport to a certain aspect-ratio
		// XG: so that distortion effect doesn't happen anymore. We might have to edit some stuff later so that
		// XG: everything in our game runs on the same world coordinate system. Also, maybe shrink the whole game
		// XG: so we're not using such large values. That's what the wiki advises at least.
		viewport = new FitViewport(1000, 1000, camera);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("badlogic.jpg");
		ime = new Texture ("badlogic.jpg");
		bul = new Texture("Bullet.png");
		bg = new Texture("Background1.png");
		//image2 size needs to be set
		//XG: creates the player and sets their attributes.
		emey = new Rectangle();
		player = new Rectangle();
		player.x = 20;
		player.y = 20;
		emey.x = 20;
		emey.y = 20;
		emey.width = 32;
		emey.height = 32;
		player.width = 32;
		player.height = 32;
		moveSpeed = 100;
		emenymovement = 100;
		bulletSpeed = 400;
		//XG: sets the camera to the players location
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();
		//XG: Creates the 'bullets' array.
		bullets = new Array<Rectangle>();
		tiledMap = new TmxMapLoader().load("SampleMap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		map = tiledMap;
		objects = new MapObjects();
		int objectLayerId = 2;

		TiledMapTileLayer collisionObjectLayer = (TiledMapTileLayer)map.getLayers().get(objectLayerId);
		 objects = collisionObjectLayer.getObjects();




wall = new Rectangle(10,20,10000,10000);
	}
	
	// XG: libGDX measures everything from its bottom-left corner, so the two methods below account for that
	// XG: offset by adding half the players height/width to the players y/x value.
 private float yOrigin(){
		return player.y +player.height/2;
 }
	private float xOrigin(){
		return player.x +player.width/2;
	}
	//XG: fires a bullet. Triggered by clicking.
	private void fire(double velX, double velY) {
		//XG: creates a new bullet as a bullet object. Not certain how this works tbh.
		bullet bullet = new bullet(velX, velY);
		//XG: sets the bullets width to be the same as its height.
		bullet.width = bullet.height = 64;
		//XG: spawns the bullet at the center of the player, and now also accounts for the bullets own size!
		bullet.x = xOrigin()-bullet.width/2;
		bullet.y = yOrigin()-bullet.width/2;
		//XG: adds the bullet to the 'bullets' list. Why do we need this list again?
		bullets.add(bullet);
	}

	@Override
	public void render () {

Vector2 playerLocation = new Vector2(player.x, player.y);


		Gdx.app.log("COUNT:", ":" +objects.getCount());
		//Gdx.app.log("Count", "Object count:"+ collisionObjectLayer.getObjects().getCount());
		for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

			Rectangle rectangle = rectangleObject.getRectangle();
			if (Intersector.overlaps(rectangle, player)) {
				Gdx.app.log("OMG", "IT WORKS!");
			}
		}


// there are several other types, Rectangle is probably the most common one


		/*if(player.y - emey.y < 20)  Gdx.app.log("MyTag", "my informative message"); ;
		if(player.y + emey.y > 50) Gdx.app.log("MyTag", "my informative message"); // hey it ahmed  create() restart the game
		if(player.x +emey.x < 0)  Gdx.app.log("MyTag", "my informative message");
		if(player.x - emey.x > 50)

if (emey.x<200) {

			emey.x += emenymovement * Gdx.graphics.getDeltaTime();
			//Gdx.app.log("MyTag", "ENMEY IS MOVEING");
			//camera.position.x = player.x;
		}
		if(emey.x ==0)
			if(emey.x ==200)
		 if (emey.x>0) {
			emey.x -= emenymovement * Gdx.graphics.getDeltaTime();
			//Gdx.app.log("MyTag", "ENMEY IS MOVEING");
			//camera.position.x = player.x;
		}*/
		oneem.move();
		oneem1.move();
		oneem2.move();
		oneem3.move();
		ScreenUtils.clear(1, 0, 1, 1);
		//XG: sets the camera to the center of the player, then updates the camera.
		camera.position.y = yOrigin();
		camera.position.x = xOrigin();
		//XG: We call the camera to update 3 different times during render. Are any of these unnecessary?
		//XG: This update is here because otherwise the camera lags slightly behind the player.
		camera.update();
		//XG: what does the following code thing do exactly? it says it sets the 'projection matrix' but i'm
		//XG: unsure what that means. Should we be using the viewport instead of the camera?
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);
		//XG: I think I would like some additional information about the whole 'batch/draw' thing. I'm a little
		//XG: unclear on its capabilities and limitations at the moment.
		batch.begin();
		//XG: draws the wall at specified coordinates. Doesn't set its size.
		batch.draw(img, wall.x, wall.y);
		batch.draw(img, oneem.posx(), oneem.posy());
		batch.draw(img, oneem1.posx(), oneem1.posy());
		batch.draw(img, oneem2.posx(), oneem2.posy());
		batch.draw(img, oneem3.posx(), oneem3.posy());
		//XG: Draws the background using specified values and texture.
		//batch.draw(bg, 400, 32, 5000, 5000);
		//XG: Draws the player. Do not set the following values to use the players origin/center.
		batch.draw(img, player.x, player.y, player.width, player.height);
		//XG: For each loop that goes through each bullet and draws them.
		for (Rectangle bullet : bullets) {
			batch.draw(bul, bullet.x, bullet.y, bullet.width, bullet.height);
		}
		//XG: a second camera update. No notable difference when removed. Commenting it out for now.
		//camera.update();
		batch.end();
		//XG: the following code moves the player according to inputs. Works with both arrow keys and WASD.
		//XG: it adds the players movement speed to their x/y value, so you can currently move much faster
		//XG: if the player is moving diagonally.
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)|| Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x += moveSpeed * Gdx.graphics.getDeltaTime();

			//camera.position.x = player.x;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.x = player.x;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)|| Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.y += moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.y = player.y;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.y -= moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.y = player.y;

		}
		if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) ||Gdx.input.isKeyPressed(Input.Keys.S))&&Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) ) {
			for(int i =0; i<5; i++) {
				player.y -= moveSpeed - 5 * Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");

			}
			//camera.position.y = player.y;

		}
		if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT)|| Gdx.input.isKeyPressed(Input.Keys.D))&&Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			for(int i =0; i<2; i++){
			player.x += moveSpeed +5* Gdx.graphics.getDeltaTime();
			//Gdx.app.log("MyTag", "the for works");
			}

			//camera.position.x = player.x;
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.A) )&&Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			for(int i =0; i<5; i++){
			player.x -= moveSpeed -5* Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");
			}
			//camera.position.x = player.x;

		}
		if ((Gdx.input.isKeyPressed(Input.Keys.UP)|| Gdx.input.isKeyPressed(Input.Keys.W))&&Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT) ) {

			for(int i =0; i<10; i++){
			 player.y += moveSpeed +5* Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");
			}
			//camera.position.y = player.y;
		}
		if(player.y < 0)    create();
		if(player.y > 5000)   create() ;// hey it ahmed  create() restart the game
		if(player.x < 0)   create();
		if(player.x > 5000)  create() ;
		//if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {}
		//XG: When the screen is clicked, it does some boring math stuff and
		if(Gdx.input.justTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			double angle = Math.atan2(touchPos.y - yOrigin(), touchPos.x - xOrigin());
			bulletVelX= bulletSpeed * cos(angle);
			bulletVelY = bulletSpeed * sin(angle);
			fire(bulletVelX, bulletVelY);
		}


		camera.update();


//Xander here. I kept pressing a button that changed the formatting, so it wouldn't break and somehow this
// ended up working??
// Each of the bullets has their own trajectory now, and I fixed their trajectory so its accurate as well.
// I have no clue what the next line of code does. If either of you could figure it out I would be grateful.

		for (Array.ArrayIterator<Rectangle> iter = bullets.iterator(); iter.hasNext(); ) {
			bullet bullet = (com.mygdx.game.bullet) iter.next();
			// sets the bullets to go forward in the right direction. Now works 100% correctly!
			bullet.y += bullet.getVelY()* Gdx.graphics.getDeltaTime();
			bullet.x += bullet.getVelX()* Gdx.graphics.getDeltaTime();
			// deletes the bullets if it reaches the set bounds.
			// There's a small bug where if you go too far past those bounds and try to shoot, the game crashes.
			if(bullet.y < 0) iter.remove();
			if(bullet.y > 5000) iter.remove();
			if(bullet.x < 0) iter.remove();
			if(bullet.x > 5000) iter.remove();

			/*float temp =timeSeconds ;
			if(temp> period){
				timeSeconds -= period;*/
//  System.exit(0);AM: this to exit the game
			}


		}
//XG: So we need to dispose some of our assets, or we get a memory leak. So if we can dispose something
//XG: here, we should
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bg.dispose();
		bul.dispose();
		tiledMap.dispose();
		img2.dispose();
	}
	//XG: The resize thing makes it so our screen no longer gets distorted when we change the window size.
	@Override
	public void resize (int width, int height) {
		viewport.update(width, height);
	}
}
