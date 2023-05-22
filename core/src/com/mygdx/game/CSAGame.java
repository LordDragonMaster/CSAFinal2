package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class CSAGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture bg;
	Texture bul;
	private Rectangle player;
	private OrthographicCamera camera;
	private int moveSpeed;
	private  Rectangle box;
	private Array<Rectangle> bullets;
	private int bulletSpeed;
	private double bulletVelX;
	private double bulletVelY;
	private int timeSeconds = 0;
	private int period = 1;
	private Rectangle wall;
private int wallsize;
// bug: when the player reaches a high enough Y-cordinate and fires a bullet, the game crashes.


Texture img2;


	//camera.position.set(x, y)
	@Override
	public void create () {
 box =new Rectangle(200,540,100,100);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1000, 1000);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		img2 = new Texture("badlogic.jpg");
		bul = new Texture("Bullet.png");
		bg = new Texture("Background1.png");
		//image2 size needs to be set
		player = new Rectangle();
		player.x = 20;
		player.y = 20;
		player.width = 64;
		player.height = 64;
		moveSpeed = 300;
		bulletSpeed = 400;
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();
		bullets = new Array<Rectangle>();

wall = new Rectangle(10,20,10000,10000);
	}


	private void fire(double velX, double velY) {
		bullet bullet = new bullet(velX, velY);
		bullet.x = player.x;
		bullet.y = player.y;
		bullet.width = bullet.height = 64;
		bullets.add(bullet);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 1, 1);


		camera.position.y = player.y;
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, wall.x, wall.y);
		batch.draw(bg, 400, 32,
				5000, 5000);
		batch.draw(img, player.x, player.y);
		for (Rectangle bullet : bullets) {
			batch.draw(bul, bullet.x, bullet.y, 100, 100);
		}
		camera.update();
		batch.end();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)|| Gdx.input.isKeyPressed(Input.Keys.D)) {
			player.x += moveSpeed * Gdx.graphics.getDeltaTime();
			camera.position.x = player.x;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)|| Gdx.input.isKeyPressed(Input.Keys.A)) {
			player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
			camera.position.x = player.x;

		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)|| Gdx.input.isKeyPressed(Input.Keys.W)) {
			player.y += moveSpeed * Gdx.graphics.getDeltaTime();
			camera.position.y = player.y;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) ||Gdx.input.isKeyPressed(Input.Keys.S)) {
			player.y -= moveSpeed * Gdx.graphics.getDeltaTime();
			camera.position.y = player.y;

		}
		//if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {}
		if(Gdx.input.justTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			double angle = Math.atan2(touchPos.y - player.y, touchPos.x - player.x);
			bulletVelX= bulletSpeed * cos(angle);
			bulletVelY = bulletSpeed * sin(angle);
			fire(bulletVelX, bulletVelY);
		}

		camera.update();
//Xander here. I kept pressing a button that changed the formatting so it wouldn't break and somehow this ended up working??
// Each of the bullets has their own trajectory now, and i fixed their trajectory so its accurate as well.
// I honestly have no clue what the next two lines of code do. If either of you could figure it out I would be grateful.
// Also, I think we have to clean up the bullet code because I think there is a lot of redundant/unnecessary bits
// I accidentally added while trying to fix this damn thing.
		for (Array.ArrayIterator<Rectangle> iter = bullets.iterator(); iter.hasNext(); ) {
			bullet bullet = (com.mygdx.game.bullet) iter.next();
			// sets the bullets to go forward in the right direction. Slightly off but mostly works.
			bullet.y += bullet.getVelY()* Gdx.graphics.getDeltaTime();
			bullet.x += bullet.getVelX()* Gdx.graphics.getDeltaTime();
			// make sure the bucket stays within the screen bounds
			if(bullet.y < 0) iter.remove();
			if(bullet.y > 1000) iter.remove();
			if(bullet.x < 0) iter.remove();
			if(bullet.x > 1000) iter.remove();
			/*float temp =timeSeconds ;
			if(temp> period){
				timeSeconds -= period;*/

			}


		}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
