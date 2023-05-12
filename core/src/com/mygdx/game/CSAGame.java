package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class CSAGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	private Rectangle player;
	private OrthographicCamera camera;
	private int moveSpeed;


	//camera.position.set(x, y)
	@Override
	public void create () {

		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		player = new Rectangle();
		player.x = 800 / 2 - 64 / 2;
		player.y = 20;
		player.width = 64;
		player.height = 64;
		moveSpeed = 200;
		camera = new OrthographicCamera();
		camera.setToOrtho(true, 1280, 1240);
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();


	}

	@Override
	public void render () {
		camera = new OrthographicCamera();
		//camera.setToOrtho(true, 1000, 480); //that is for the how big is the view of the camra
		ScreenUtils.clear(1, 0, 0, 1);
		camera.position.x = player.x;
		camera.position.y = player.y;
		batch.begin();
		batch.draw(img, 0, 0);
		batch.draw(img, player.x, player.y);
		batch.end();
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			player.x += moveSpeed * Gdx.graphics.getDeltaTime();
			camera.translate(moveSpeed * Gdx.graphics.getDeltaTime(), 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			player.x -= moveSpeed * Gdx.graphics.getDeltaTime();
			camera.translate(-1 *moveSpeed * Gdx.graphics.getDeltaTime(), 0);
		}
		camera.position.x = player.x;
		camera.position.y = player.y;
		camera.update();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
