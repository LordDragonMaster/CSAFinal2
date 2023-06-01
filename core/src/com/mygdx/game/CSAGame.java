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
//XG: not sure what the next two do, but they are also important.
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//XG: Used to track inputs. (keyboard, mouse, etc) Fairly self explanatory.
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
//XG: Allows us to use textures. Self-explanatory.
import com.badlogic.gdx.graphics.Texture;
//XG: Draws all our graphic stuff. Don't understand this bit too well at the moment, but it's definitely important
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
		import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
		import com.badlogic.gdx.math.*;
//XG: We use the rectangle for basically all our objects at the moment. We should probably have our own
//XG: objects for the player and other stuff.
//XG: Used for calculating the bullet trajectory. Only used once. Could probably be replaced.
//XG: Used for the bullet array.
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
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

import java.util.ArrayList;

public class CSAGame extends ApplicationAdapter implements Screen {
	private Manager parent; // a field to store our orchestrator

	// our constructor with a Box2DTutorial argument
	public CSAGame(Manager manager){
		parent = manager;     // setting the argument to our field.
		create();
	}
	public int heath =100;
	public int bulits= 5;
	SpriteBatch batch;
	Texture img;
	Texture ime;
	Texture bul;
	private Rectangle player;
	private Rectangle enemy;
	dumbEnemy oneem= new dumbEnemy(20, 20, 0, 600, 100,img ,true);
	dumbEnemy oneem1= new dumbEnemy(20, 20, 100, 600, 200,img ,false);
	dumbEnemy oneem2= new dumbEnemy(20, 20, 200, 800, 200,img ,true);
	dumbEnemy oneem3= new dumbEnemy(20, 20, 0, 100, 200,img ,false);
	private OrthographicCamera camera;
	private Viewport viewport;
	private int moveSpeed;
	private Music music;
	private Sound sound;

	Rectangle box;
	private Array<Rectangle> bullets;
	private Array<dumbEnemy> enemies;
	private int bulletSpeed;
	 double bulletVelX;
	 double bulletVelY;
	 int timeSeconds = 0;
	 int period = 1;
	private Rectangle collisionChecker;
	TiledMap tiledMap;

	TiledMapRenderer tiledMapRenderer;
	int ymove;
	int xmove;
	int health;
	//XG: determines how much damage the player does to enemies.
	int damage;
private Animate anm= new Animate();
	private Animate anm2= new Animate();


Texture img2;
//XG: collision stuff(still testing)
	MapObjects StaticObjects;


	//camera.position.set(x, y)
	@Override
	public void create () {
		/* oneem= new dumbEnemy(20, 20, 0, 600, 200,img ,true);
		 oneem1=ew dumbEnemy(20, 20, 100, 600, 200,img ,false);
		 oneem2=  dumbEnemy(20, 20, 200, 800, 200,img ,true);
		 oneem3=  dumbEnemy(20, 20, 0, 100, 200,img ,false);
		 */
		//Sound sound = Gdx.audio.newSound(Gdx.files.internal("Battle-Dawn_loop.ogg"));

		//long id

anm.create(new Texture("Main_Char_Sprite.png"),4,3, 0.1f);
		anm2.create(new Texture("BugAnim.png"),3,2, 0.2f);
 box =new Rectangle(200,540,100,100);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 300, 300);
		// XG: not entirely certain how this works, but what it does is set the viewport to a certain aspect-ratio
		// XG: so that distortion effect doesn't happen anymore. We might have to edit some stuff later so that
		// XG: everything in our game runs on the same world coordinate system. Also, maybe shrink the whole game
		// XG: so we're not using such large values. That's what the wiki advises at least.
		viewport = new FitViewport(300, 300, camera);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("Main_Char_Sprite.png");
		ime = new Texture ("badlogic.jpg");
		bul = new Texture("Bullet.png");

		//image2 size needs to be set
		//XG: creates the player and sets their attributes.
		enemy = new Rectangle();
		player = new Rectangle();
		collisionChecker = new Rectangle(123, 123, 123, 123);
		StaticObjects = new MapObjects();
		player.x = 20;
		player.y = 20;
		health = 5;
		enemy.x = 20;
		enemy.y = 20;
		enemy.width = 32;
		enemy.height = 32;
		player.width = 32;
		player.height = 32;
		collisionChecker = new Rectangle(player.x, player.y, player.width, player.height);
		moveSpeed = 100;
		damage = 1;

		bulletSpeed = 400;
		//XG: sets the camera to the players location
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();
		//XG: Creates the 'bullets' array.
		bullets = new Array<Rectangle>();
		enemies = new Array<dumbEnemy>();
		//ArrayList<dumbEnemy> enemies = new ArrayList<dumbEnemy>();
		tiledMap = new TmxMapLoader().load("SampleMap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		ymove=0;
		xmove=0;
		Gdx.app.log("OMG", "IT WORKS!"+  moveSpeed * Gdx.graphics.getDeltaTime());

//XG: I have done it. I have found a way to import objects from the Tiled object layer into the game. This will make
//XG: creating levels  in the future much easier. The method is simple.
//XG: The following code pulls all of the objects from the correct layer and puts them into a list of map objects. I was
//XG: using the correct approach the whole time. There was merely a small formatting error.
//XG: The following method is in fact slightly less complicated than the various other ones I have tried that have
//XG: been failing me for the past week, and preventing me from making any further progress on this project.
//XG: I am filled with rage. I must go lie down now.

//XG: After a few moments of reflection, I have decided that while I don't hate Java, Java clearly despises me.


		StaticObjects = tiledMap.getLayers().get("CollisionLayer").getObjects();
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
		bullet.y = yOrigin()-bullet.height/2;
		//XG: adds the bullet to the 'bullets' list. Why do we need this list again?
		bullets.add(bullet);
	}


	//XG: Gentlemen and other gentleman, we have collision! Once I managed to get the objects imported successfully
	//XG: it was honestly laughably easy to implement. There is a bug where holding against a wall in one direction will
	//XG: keep you from moving a different direction, but it's pretty small and we can fix it later on down the line.

	public boolean collisionCheck(float x, float y){
		collisionChecker.x=x;
		collisionChecker.y=y;
				for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {

					Rectangle wall = rectangleObject.getRectangle();
					if (Intersector.overlaps(wall, collisionChecker)) {
						return true;
						//insert collision here
					}
				}
		return false;
	}



	@Override
	public void render (float delta) {
		Vector2 pl = new Vector2(player.x, player.y);
//render(player.x,player.y);


		for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {

			Rectangle wall = rectangleObject.getRectangle();
			if (Intersector.overlaps(wall, player)) {
				Gdx.app.log("OMG", "IT WORKS!" + moveSpeed * Gdx.graphics.getDeltaTime());
				//insert collision here
			}
		}


//XG: Makes the enemy walk towards the player. We could make the enemy an instance variable so that we don't have to make
//XG: a ton of enemy objects. This would be helpful for an endless 'waves' type mode.
		oneem.attack(xOrigin(), yOrigin());
		oneem1.attack(xOrigin(), yOrigin());
		oneem2.attack(xOrigin(), yOrigin());
		oneem3.attack(xOrigin(), yOrigin());


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
		anm.render(player.x, player.y, player.width, player.height, camera);
		anm2.render(oneem.posx(), oneem.posy(), enemy.width, enemy.height, camera);
		anm2.render(oneem1.posx(), oneem1.posy(), enemy.width, enemy.height, camera);
		anm2.render(oneem2.posx(), oneem2.posy(), enemy.width, enemy.height, camera);
		anm2.render(oneem3.posx(), oneem3.posy(), enemy.width, enemy.height, camera);

		//XG: I think I would like some additional information about the whole 'batch/draw' thing. I'm a little
		//XG: unclear on its capabilities and limitations at the moment.
		batch.begin();
		//XG: draws the enemies at specified coordinates. Doesn't set their size.
//XG: The following line is a very basic and simple sample of what it might look like if an enemy died.
//XG: We will need to replace it soon with actual dying.
		if (oneem.health > 0) batch.draw(img, oneem.posx(), oneem.posy(), enemy.width, enemy.height);

		/*batch.draw(img, oneem2.posx(), oneem2.posy());
		batch.draw(img, oneem3.posx(), oneem3.posy());*/
		//XG: very simple way of dealing damage. Will need invincibility frames down the line.
		if(Intersector.overlaps(enemy,player)){ health-=1;}
		//XG: Draws the player. Do not set the following values to use the players origin/center.
		//batch.draw(img, player.x, player.y, player.width, player.height);
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
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			if (!collisionCheck(pl.x, pl.y += moveSpeed * Gdx.graphics.getDeltaTime()))
				player.y += moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.y = player.y;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			if (!collisionCheck(pl.x, pl.y -= moveSpeed * Gdx.graphics.getDeltaTime()))
				player.y -= moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.y = player.y;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D) && (!collisionCheck(pl.x += moveSpeed * Gdx.graphics.getDeltaTime(), pl.y))) {
			player.x += moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.x = player.x;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			if (!collisionCheck(pl.x -= moveSpeed * Gdx.graphics.getDeltaTime(), pl.y))
				player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
			//camera.position.x = player.x;

		}

		if ((Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			for (int i = 0; i < 5; i++) {
				player.y -= moveSpeed - 5 * Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");

			}
			//camera.position.y = player.y;

		}
		if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			for (int i = 0; i < 2; i++) {
				player.x += moveSpeed + 5 * Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");
			}

			//camera.position.x = player.x;
		}
		if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {
			for (int i = 0; i < 5; i++) {
				player.x -= moveSpeed - 5 * Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");
			}
			//camera.position.x = player.x;

		}
		if ((Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) && Gdx.input.isKeyJustPressed(Input.Keys.SHIFT_LEFT)) {

			for (int i = 0; i < 10; i++) {
				player.y += moveSpeed + 5 * Gdx.graphics.getDeltaTime();
				//Gdx.app.log("MyTag", "the for works");
			}
			//camera.position.y = player.y;
		}
		if (player.y < 0) create();
		if (player.y > 5000) create();// hey it ahmed  create() restart the game
		if (player.x < 0) create();
		if (player.x > 5000) create();
		//if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {}
		//XG: When the screen is clicked, it does some boring math stuff and
		if (Gdx.input.justTouched() && bulits>0) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			double angle = Math.atan2(touchPos.y - yOrigin(), touchPos.x - xOrigin());
			bulletVelX = bulletSpeed * cos(angle);
			bulletVelY = bulletSpeed * sin(angle);
			fire(bulletVelX, bulletVelY);
			bulits--;

		}


		camera.update();


//Xander here. I kept pressing a button that changed the formatting, so it wouldn't break and somehow this
// ended up working??
// Each of the bullets has their own trajectory now, and I fixed their trajectory so its accurate as well.
// I have no clue what the next line of code does. If either of you could figure it out I would be grateful.
		//XG: There's a recurring bug where spawning bullets can randomly cause a crash. No idea why yet.

		for (Array.ArrayIterator<Rectangle> iter = bullets.iterator(); iter.hasNext(); ) {


			bullet bullet = (com.mygdx.game.bullet) iter.next();

			if(bullet.y < 0) iter.remove();
			if(bullet.y > 5000) iter.remove();
			if(bullet.x < 0) iter.remove();
			if(bullet.x > 5000) iter.remove();


			for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {

				Rectangle wall = rectangleObject.getRectangle();
				if (Intersector.overlaps(wall, bullet)) {
					iter.remove();
					//insert collision here
				}

			}
			bullet.y += bullet.getVelY() * Gdx.graphics.getDeltaTime();
			bullet.x += bullet.getVelX() * Gdx.graphics.getDeltaTime();
			//XG: For some reason, the following line of code doesn't remove the bullet.
			if (Intersector.overlaps(enemy, bullet)) {
				oneem.damage(damage);
				iter.remove();
				//insert collision here
			}

			// deletes the bullets if it reaches the set bounds.
			// There's a small bug where if you go too far past those bounds and try to shoot, the game crashes.

			/*float temp =timeSeconds ;
			if(temp> period){
				timeSeconds -= period;*/
//  System.exit(0);AM: this to exit the game
		}


	}
//XG: So we need to dispose some of our assets, or we get a memory leak (don't know what that is but it sounds bad
//XG: So if we can dispose something here, we should.
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		bul.dispose();
		tiledMap.dispose();
		img2.dispose();
anm.dispose();
		ime.dispose();
	}

	@Override
	public void show() {

	}



	//XG: The resize thing makes it so our screen no longer gets distorted when we change the window size.
	@Override
	public void resize (int width, int height) {
		viewport.update(width, height);
	}

	@Override
	public void hide() {

	}
}
