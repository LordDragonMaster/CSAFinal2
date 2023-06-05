//XG: Yo wassup boys! I've added a whole lot of documentation and notes on what everything does, so that
//XG: it's easier to understand everything, even if you didn't work on one section of the code.
//XG: in the future, I'd like both of you to mark your notes with your initials so that we know who is
//XG: talking about what. You definitely don't need to be as descriptive as I am though.

//XG: There are a couple sections where I will directly say that I'm not entirely sure what something does.
//XG: If either of you are better informed about that thing, feel free to delete that comment and clarify.





//XG: Not actually sure what this top bit does. Definitely important but I don't know why.
//XG: If I comment it out all our bullet stuff turns to errors and our main class doesn't work.
package com.mygdx.game;


//XG: Heyo boys. I've gone through the code of the main class and added comments to EVERYTHING to describe what they do.
//XG: There's so much documentation that if we all got amnesia tomorrow we would still be able to finish this project.

//XG: not sure the full breadth of what the next two do, but they are very important.
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
//XG: Used to track inputs. (keyboard, mouse, etc) Fairly self explanatory.
import com.badlogic.gdx.Input;
//XG: Used for sounds and music. Self-explanatory.
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;
//XG: Used for adding multiple screens, like the title screen and the game over screen.
import com.badlogic.gdx.Screen;
//XG: Used for displaying the game and keeping everything using the same coordinate system.
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
//XG: Allows us to use textures. Self-explanatory.
import com.badlogic.gdx.graphics.Texture;
//XG: Draws all our graphic stuff.
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//XG: Map objects are used for whatever we import from tiled.
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
//XG: Used for spawning enemies.
import com.badlogic.gdx.maps.objects.EllipseMapObject;
//XG: Used for walls.
import com.badlogic.gdx.maps.objects.RectangleMapObject;
//XG: Math sucks. But it gives us our shapes and the intersector and other garbage.
import com.badlogic.gdx.math.*;
//XG: Used for the bullet array.
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
//XG: I got no clue for this one.
import com.badlogic.gdx.utils.ScreenUtils;
//XG: Used for making sure things don't get stretched when the window is resized, and so that later on we'll
//XG: be able to use world coordinates instead of pixel measurements.
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
//XG: Used for calculating bullet trajectories. Will likely be reused later on for enemy pathing.
//XG: I was originally intending to use MathUtils for this but somehow ended up with these instead.
//XG: The following stuff is what we need in order to use the Tiled map editor for our map.
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//XG: Imports array lists. Obviously.
import java.util.ArrayList;
//XG: Used for timers and cool-downs.
import com.badlogic.gdx.utils.TimeUtils;

import static java.lang.Math.*;

public class CSAGame extends ApplicationAdapter implements Screen {
	//XG: Don't remove the following variable even though intellij tells you safely can.
	private Manager parent;
	//XG: Gives the Manager class access to our game. This is what makes our menus work.
	Stage stage = new Stage(new ScreenViewport());

	public CSAGame(Manager manager) {
		parent = manager;     // setting the argument to our field.
		Gdx.input.setInputProcessor(stage);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		create();
	}
	//private Sound damageSound;
	//private Sound shootSound;
	//private Sound hitSound;
	//private Sound dashSound;
	//private Sound waveCompleteSound;
//	private Sound deathSound;

	//XG: Just a bunch of UI stuff.
	ProgressBar healthBar;

	Label ammoText;
	Label waveText;
	Label livesText;
	Skin skin;
	Table table;
	BitmapFont textFont;
	Color titleColor;
	Label.LabelStyle textStyle;
	Label healthText;
	Label scoreText;
	TextButton startWave;
	TextButton upgradeMenu;
	SpriteBatch batch;
	//XG: Creates textures for our images.
	Texture img;
	Texture ime;
	Texture bul;
	int lives =3;

	private Rectangle enemyRec;
	//XG: Creates a camera and viewport to display the game properly.
	private OrthographicCamera camera;
	private Viewport viewport;
	//XG: Gives our game audio.
	private Music music;
	private Sound sound;
	private Array<Rectangle> bullets;
	//XG: Creates an arraylist full of the enemies.
	private ArrayList<dumbEnemy> enemies;
	//XG: Creates the player.
	private Rectangle player;
	//XG: Tracks the players position.
	Vector2 pl;
	//XG: Sets the speed of the player.
	private int moveSpeed;
	//XG: Checks if the player is moving
	private boolean moving;
	//XG: Sets the speed of the players bullets.
	private int bulletSpeed;
	//XG: Tracks how many points the player has.
	private int points = 0;
	//XG: Tracks what wave the player is on.
	//XG: Sets how much ammo the player has.
	private int ammo=50;
	//XG: Used to calculate bullet trajectories.
	double bulletVelX;
	double bulletVelY;
	//XG: Serves as a stand-in for the player in order to check if they will be colliding with a wall.
	//XG: Gives us the tiled map we're using for the level.
	TiledMap tiledMap;
	//XG: Renders the tiled map.
	TiledMapRenderer tiledMapRenderer;
	//XG: sets the players max possible health
	int healthMax;
	//XG: Sets the players health.
	int health;
	//XG: Determines how much damage the player does to enemies.
	int damage;
	//XG: Tracks if the player is currently using Iframes.
	Boolean invincible;
	//XG: Sets how long invincibility lasts for and sets when the invincibility finishes.
	private long invinciblePeriod;
	private long invincibleFinish;
	//XG: Checks if the player can dash or is dashing and creates cool-downs for dashing, and sets the dashing speed.
	private boolean dashing;
	private long dashFinish;
	private boolean canDash;
	private int dashTime;
	private long dashCooldown;
	private long whenDash;
	private int dashSpeed;
	private float dashYVel;
	private float dashXVel;
	private Boolean inWave;

	//XG: Creates animations for the game.
	private Animate playerAnimation;
	private Animate bugAnimation;
	//XG: Tracks the wave the player is currently on.
	int wave = 1;
	Texture img2;
	MapObjects StaticObjects;
	MapObjects EnemySpawns;

	RectangleMapObject startWaveButton;
	RectangleMapObject shopButton;
	MapObject shopButtonText;
	MapObject startWaveButtonText;



		@Override
	public void create() {
		Rectangle b = new Rectangle(0, 0, 64, 64);
		inWave = false;
		//damageSound = Gdx.audio.newSound(Gdx.files.internal("data/hurt.wav"));
		/*hitSound= Gdx.audio.newSound(Gdx.files.internal("data/hit.wav"));
		shootSound= Gdx.audio.newSound(Gdx.files.internal("data/laserShoot.wav"));
		dashSound= Gdx.audio.newSound(Gdx.files.internal("data/jump.wav"));
		deathSound= Gdx.audio.newSound(Gdx.files.internal("data/explosion.wav"));
		waveCompleteSound= Gdx.audio.newSound(Gdx.files.internal("data/random.wav"));*/
		playerAnimation = new Animate();
		bugAnimation = new Animate();
		//Music music = Gdx.audio.newMusic(Gdx.files.internal("in_the_element.wav"));

		//start playing music
		//music.setVolume(0.5f);
		//music.setLooping(true);
		//music.play();
		//XG: Creates an animation for our player.
		playerAnimation.create(new Texture("Main_Char_Sprite.png"), 4, 3, 0.1f);
		//XG: We should probably move the enemy animations into the enemy class.
		bugAnimation.create(new Texture("BugAnim.png"), 3, 2, 0.2f);
		//XG: So basically what our camera/viewport does is set our world to use a single united coordinates system
		//XG: and lets us see that world as well.
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 300, 300);
		viewport = new FitViewport(300, 300, camera);
		batch = new SpriteBatch();
		//XG: Setting our textures.
		img = new Texture("badlogic.jpg");
		img2 = new Texture("Main_Char_Sprite.png");
		ime = new Texture("badlogic.jpg");
		bul = new Texture("Bullet.png");
		//XG: Not sure what we need this rectangle for.
		enemyRec = new Rectangle();
		//XG: These are used for storing data imported from Tiled.
		StaticObjects = new MapObjects();
		EnemySpawns = new MapObjects();
		//XG: Creates the player and sets their attributes.
		player = new Rectangle();
		player.x = 870;
		player.y = 780;
		healthMax = 5;
		health = healthMax;

		moving = false;
		player.width = 32;
		player.height = 32;
		moveSpeed = 100;
		damage = 5;
		bulletSpeed = 400;
		invincible = false;
		invinciblePeriod = 700;
		invincibleFinish = 0;
		dashSpeed = 250;
		dashing = false;
		canDash = true;
		dashFinish = 0;
		dashTime = 300;
		dashCooldown = 1200;
		whenDash = 0;
		//XG: Creates our collision checker, which is what lets us avoid walking into walls.
		pl = new Vector2(player.x, player.y);

		enemyRec.x = 20;
		enemyRec.y = 20;
		enemyRec.width = 32;
		enemyRec.height = 32;
		//XG: Sets the camera to the players location
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();
		//XG: Just a bunch of UI stuff.

		//XG: Creates arrays for the things we need those for.
		bullets = new Array<Rectangle>();
		enemies = new ArrayList<dumbEnemy>();
		//XG: Loads our map file and gets our renderer ready to render it.
		tiledMap = new TmxMapLoader().load("SampleMap.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//XG: Spawns enemies at the locations where we've set them to spawn at.
		EnemySpawns = tiledMap.getLayers().get("Enemy Spawns").getObjects();
		Gdx.app.log("OMG", "IT WORKS!" + EnemySpawns.getCount());
		startWaveButton = (RectangleMapObject) tiledMap.getLayers().get("Buttons").getObjects().get(0);
		shopButton = (RectangleMapObject) tiledMap.getLayers().get("Buttons").getObjects().get(1);
		startWaveButtonText = tiledMap.getLayers().get("Buttons").getObjects().get(2);
		shopButtonText = tiledMap.getLayers().get("Buttons").getObjects().get(3);





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

	// XG: Since everything is measured from its bottom-left corner, the two methods below account for that
	// XG: offset by adding half the players height/width to the players y/x value.
	private float yOrigin() {
		return player.y + player.height / 2;
	}

	private float xOrigin() {
		return player.x + player.width / 2;
	}

	//XG: fires a bullet. Triggered by a method that checks for clicking far below.
	private void fire(double velX, double velY) {
		Rectangle b = new Rectangle(player.x, player.y, 32, 32);
		//XG: creates a new bullet as a bullet object.
		bullet bullet = new bullet(velX, velY, b);
		//XG: sets the bullets width to be the same as its height.
		bullet.width = bullet.height = 32;
		//XG: spawns the bullet at the center of the player, and now also accounts for the bullets own size!
		bullet.x = xOrigin() - bullet.width / 2;
		bullet.y = yOrigin() - bullet.height / 2;
		//XG: adds the bullet to the 'bullets' list.
		bullets.add(bullet);

	}


	//XG: Gentlemen and other gentleman, we have collision! Once I managed to get the objects imported successfully
	//XG: it was honestly laughably easy to implement.

	public boolean collisionCheck(float x, float y) {
		Rectangle collisionChecker = new Rectangle(x, y, player.width, player.height);
		for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {

			Rectangle wall = rectangleObject.getRectangle();
			if (collisionChecker.overlaps(wall)) {
				return false;
			}
		}
		return true;
	}
	//XG: This one is so to address the bug that prevented the player from rubbing against the walls.
	public boolean collisionCheck2(float x, float y) {
		Rectangle collisionChecker = new Rectangle(x, player.y, player.width, player.height);
		for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {
			Rectangle wall = rectangleObject.getRectangle();
			if (Intersector.overlaps(wall, collisionChecker)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void render(float delta) {
			//XG: This line of code causes the game to spaz out when you die for some reason.
		if(lives==0){die();}
		pl.set(player.x, player.y);
		healthBar.setValue(health);
		ammoText.setText("Ammo: " +ammo);
		waveText.setText("Wave:  " +wave);
		livesText.setText("Lives: " + lives);

		ScreenUtils.clear(1, 0, 1, 1);
		//XG: sets the camera to the center of the player, then updates the camera.
		camera.position.y = yOrigin();
		camera.position.x = xOrigin();

		//XG: We call the camera to update 3 different times during render. Are any of these unnecessary?
		//XG: This update is here because otherwise the camera lags slightly behind the player.
		camera.update();
		//XG: Displays the game.
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		//XG: Makes the world run on a single system of measurement.
		batch.setProjectionMatrix(camera.combined);

		if (invincible) {
			if (!dashing) {
				if (invincibleFinish < TimeUtils.millis()) {
					invincible = false;
				}
			}
		}
		if (!canDash) {
			if (whenDash < TimeUtils.millis()) {
				canDash = true;
			}
		}
		if (dashing) {
			if (dashFinish < TimeUtils.millis()) {
				dashFinish();
			} else dashing();
		}
		//XG: Displays the player. Do not set the location values to use the players center.
		playerAnimation.render(player.x, player.y, player.width, player.height, camera);

		//XG: this has to be a for loop. For each loops give an error with 'concurrent modifications' to its loops. it took me
		//XG: quite a few hours to find out what the issue was.
		//XG: Renders the enemies and makes them attack the players.
		for (int i = 0; i < enemies.size(); i++) {
			if (player.overlaps(enemies.get(i).getEnemy())&& dashing){
				enemies.get(i).damage(damage);
				points+=15;
				scoreText.setText("Score: "+ points);
				ammo++;
			}
			if (player.overlaps(enemies.get(i).getEnemy()) && !invincible) {
				health -= 1;
				if (health <=0){
					lives-=1;
					ammo+=50;
					healthBar.clear();

					health=healthMax;

					create();
				}

				Gdx.app.log("OMG", "IT WORKS!" + TimeUtils.millis());
				invincibility();
			}

			if(enemies.size()>0) {
				enemies.get(i).attack(xOrigin(), yOrigin());
				bugAnimation.render(enemies.get(i).posx(), enemies.get(i).posy(), enemyRec.width, enemyRec.height, camera);
				if (!enemies.get(i).alive) {
					enemies.remove(i);
					if (i > 0) i--;
					ammo += 1;
				}
			}

		}
		//XG: Yo guys I moved the players movement into its own method.
		movement();
		//XG: Resets the game.
		if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
			dispose();
			create();
		}
		if(inWave&&enemies.size()==0){
			waveEnd();
		}

		if((player.overlaps(startWaveButton.getRectangle()))&&!inWave){
			waveStart();
		}
		//XG: if the player returns to the shop after going there once, the game crashes.
		if((player.overlaps(shopButton.getRectangle()))&&!inWave){
			goToShop();
		}
		//XG: Lets the player dash.
		if (!dashing && canDash && moving && Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
			dashStart();

		//XG: Restarts the player when they go out of bounds.
		if (player.y < 0) create();
		if (player.y > 5000) create();// hey it ahmed  create() restart the game
		if (player.x < 0) create();
		if (player.x > 5000) create();

		//XG: When the screen is clicked, it does some boring math stuff with 'sin' and 'cos' and whatever. The end result
		//XG: is that it fires a bullet towards the mouse.
		if (Gdx.input.justTouched() && ammo > 0) {
			ammo--;
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			double angle = Math.atan2(touchPos.y - yOrigin(), touchPos.x - xOrigin());
			bulletVelX = bulletSpeed * cos(angle);
			bulletVelY = bulletSpeed * sin(angle);
			fire(bulletVelX, bulletVelY);
		}

		//XG: I think I would like some additional information about the whole 'batch/draw' thing. I'm a little
		//XG: unclear on its capabilities and limitations at the moment.
		batch.begin();
		//XG: This 'iterator' thing is just a fancy enhanced for loop. Also, there is a bug where the bullets are slightly off.
		//XG: Goes through the 'bullets' array.
		for (Array.ArrayIterator<Rectangle> iter = bullets.iterator(); iter.hasNext(); ) {
			boolean remove = false;
			//XG: Not sure what the next line of code does. I think it makes the bullet class available to access?
			bullet bullet = (com.mygdx.game.bullet) iter.next();
			//XG: Moves the bullet along its path.
			bullet.y += bullet.getVelY() * Gdx.graphics.getDeltaTime();
			bullet.x += bullet.getVelX() * Gdx.graphics.getDeltaTime();
			//XG: Removes the bullet if it goes beyond set boundaries.
			if (bullet.y < 0) remove = true;
			if (bullet.y > 5000) remove = true;
			if (bullet.x < 0) remove = true;
			if (bullet.x > 5000) remove = true;
			//XG: Draws the bullets.
			batch.draw(bul, bullet.x, bullet.y, bullet.width, bullet.height);
			//XG: Removes the bullets if they run into a wall.

			for (RectangleMapObject rectangleObject : StaticObjects.getByType(RectangleMapObject.class)) {
				Rectangle wall = rectangleObject.getRectangle();
				if (Intersector.overlaps(wall, bullet)) {
					remove = true;
				}
			}

//XG: do NOT replace the following with an enhanced loop. Trust me, I was dealing with a stupid crashing issue for AGES
//XG: before learning that for loops don't have the same issues.
			//XG: Damages enemies and removes bullets when they collide.
			for (int i = 0; i < enemies.size(); i++) {
				if (Intersector.overlaps(bullet, enemies.get(i).getEnemy())) {
					points+=10;
					scoreText.setText("Score: " +points);
					remove = true;
					enemies.get(i).damage(damage);

				}
			}
			if (remove) iter.remove();

		}

		//XG: Ends the drawing thingy and updates the camera.

		batch.end();
		camera.update();
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		table.setFillParent(true);


	}

	private void dashStart() {
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
			dashYVel = -dashSpeed;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
				dashXVel = -dashSpeed;
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
				dashXVel = dashSpeed;
			} else dashXVel = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
			dashYVel = dashSpeed;
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
				dashXVel = -dashSpeed;
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
				dashXVel = dashSpeed;
			} else
				dashXVel = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			dashXVel = dashSpeed;
			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
				dashYVel = dashSpeed;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
				dashYVel = -dashSpeed;
			} else
				dashYVel = 0;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			dashXVel = -dashSpeed;
			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
				dashYVel = dashSpeed;
			} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
				dashYVel = -dashSpeed;
			} else
				dashYVel = 0;
		}
		invincible = true;
		dashFinish = TimeUtils.millis() + dashTime;
	//	dashSound.play();
		dashing = true;
	}

	private void movement() {
		//XG: the following code moves the player according to inputs. Works with both arrow keys and WASD.
		//XG: it adds the players movement speed to their x/y value, so you can currently move faster
		//XG: if the player is moving diagonally.
		if (!dashing) {
			if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
				if (collisionCheck(pl.x, pl.y += moveSpeed * Gdx.graphics.getDeltaTime()))
					player.y += moveSpeed * Gdx.graphics.getDeltaTime();
				moving = true;
			}
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
				if (collisionCheck(pl.x, pl.y -= moveSpeed * Gdx.graphics.getDeltaTime()))
					player.y -= moveSpeed * Gdx.graphics.getDeltaTime();
				moving = true;

			}
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
				if (collisionCheck(pl.x += moveSpeed * Gdx.graphics.getDeltaTime(), pl.y)) {
					player.x += moveSpeed * Gdx.graphics.getDeltaTime();
					moving = true;
				} else if (collisionCheck2(pl.x += moveSpeed * Gdx.graphics.getDeltaTime(), pl.y)) {
					player.x += moveSpeed * Gdx.graphics.getDeltaTime();
					moving = true;
				}
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
				if (collisionCheck(pl.x -= moveSpeed * Gdx.graphics.getDeltaTime(), pl.y)) {
					player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
					moving = true;
				} else if (collisionCheck2(pl.x -= moveSpeed * Gdx.graphics.getDeltaTime(), pl.y)) {
					player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
					moving = true;
				}
			}
		}
	}


//XG: Makes the player invincible for a set time.
	private void invincibility() {
		invincible = true;
		invincibleFinish = TimeUtils.millis() + invinciblePeriod;
	}
//XG: Moves the player forward while dashing as long as there isn't a wall in the way.
	private void dashing() {
		if (collisionCheck(pl.x + dashXVel / 50, pl.y+ dashYVel / 50)){
			player.x += dashXVel * Gdx.graphics.getDeltaTime();
			player.y += dashYVel * Gdx.graphics.getDeltaTime();}
		}


//XG: Ends the dash and begins the cool down before the player can dash again.
	private void dashFinish() {
		dashing=false;
		invincible=false;
		canDash=false;
		whenDash = TimeUtils.millis() + dashCooldown;
	}
	//XG: Ends the wave
	private void waveEnd() {
			wave++;
			health=healthMax;
			inWave=false;
	}
	//XG: Starts the wave
	private void waveStart() {
		inWave=true;
		int maxEnemies = wave*10;
		int enem;

		while(enemies.size()<maxEnemies){
		for (EllipseMapObject circleObject : EnemySpawns.getByType(EllipseMapObject.class)) {
			Ellipse enemySpawn = circleObject.getEllipse();
			if(enemies.size()<=maxEnemies){
			int speed = (int)(random() * wave *10 +30);
			int h = (int)(3+wave + 3*random());
			dumbEnemy enemy = new dumbEnemy(enemySpawn.x, enemySpawn.y, speed, h, 32, 32, "walk");
			enemies.add(enemy);}
		}}
	}
	//XG: brings the player to the shop menu. Bugs: Spawns the player on the rectangle where the shop trigger is, doesn't have items yet, creates a new set of UI every time you use it.
	private void goToShop() {
			parent.setPoints(points);
		parent.changeScreen(Manager.SHOP);
		table.clear();
	}
	public int getPoints() {
		return points;
	}
	private void die() {
		parent.setPoints(points);
		parent.changeScreen(Manager.ENDGAME);
		table.clear();
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
		playerAnimation.dispose();
		bugAnimation.dispose();
//music.dispose();
		ime.dispose();
		stage.dispose();
	//	dashSound.dispose();
		//damageSound.dispose();
		//hitSound.dispose();
		//deathSound.dispose();
	//	waveCompleteSound.dispose();
	}

	@Override
	public void show() {
		table = new Table();

		stage.addActor(table);
		table.setFillParent(true);
		table.setDebug(true);
		//XG: creates a skin using the imported assets.
		skin = new Skin(Gdx.files.internal("pixthuluSkin/pixthulhu-ui.json"));
		healthBar = new ProgressBar(0, healthMax, 1, false, skin);
		healthBar.setValue(health);
		textFont = new BitmapFont(Gdx.files.internal("pixthuluSkin/font-export.fnt"));
		titleColor = new Color(100, 0, 0, 1);
		textStyle = new Label.LabelStyle(textFont, titleColor);
		ammoText = new Label("Ammo: " + ammo, textStyle);
		healthText = new Label("Health", textStyle);
		waveText= new Label("Wave: ", textStyle);
		livesText= new Label("Lives: ", textStyle);
		scoreText = new Label("Score: " + points, textStyle);
		healthBar.setValue(health);
		scoreText.setText("Score: " + points);
		livesText.setText("Lives: " + lives);
		waveText.setText("Wave: " + wave);
		table.top();
		table.left();


//XG: Creates the UI.
		table.add(healthText).left().size(70, 50).fillX();
		//table.row().pad(0, 0, 0, 0);
		table.add(healthBar).left().size(450, 50).fillX();
		//table.row().right().pad(-50,0,0,0);
		table.row();
		table.row().pad(290,0,0,40);
		table.add(waveText).fillX();
		table.row();
		table.add(scoreText).fillX();
		table.row();
		table.add(ammoText).fillX();
		table.row();
		table.add(livesText).fillX();
	}
	//XG: The resize thing makes it so our screen no longer gets distorted when we change the window size.
	@Override
	public void resize (int width, int height) {
		stage.getViewport().update(600, 475, true);
		viewport.update(width, height);
	}

	@Override
	public void hide() {
	}
}
