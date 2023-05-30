package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;


public class Animate extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture spriteSheet;
    private TextureRegion[][] frames;
    private Animation<TextureRegion> animation;
    private float stateTime;
private int playx;
private int playy;
    @Override
    public void create() {
        batch = new SpriteBatch();

        // Load the sprite sheet
        spriteSheet = new Texture("Main_Char_Sprite.png");

        // Split the sprite sheet into frames
        int frameCols = 3; // Adjust according to your sprite sheet
        int frameRows = 4; // Adjust according to your sprite sheet
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / frameCols,
                spriteSheet.getHeight() / frameRows);

        frames = new TextureRegion[frameRows][frameCols];
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[i][j] = tmp[i][j];
            }
        }

        // Create the animation
        float frameDuration = 0.001f; // Adjust according to your desired animation speed
        animation = new Animation<>(frameDuration, frames[0][0], frames[0][1], frames[0][2], frames[1][0], frames[1][1], frames[1][2], frames[2][0], frames[2][1]);
        animation.setPlayMode(Animation.PlayMode.LOOP);

        // Initialize the state time
        stateTime = 0f;
    }


    public void render(float x, float y, float w, float h, OrthographicCamera cam) {


        // Clear the screen
      //  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update the state time
        stateTime += Gdx.graphics.getDeltaTime();

        // Get the current frame of the animation
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);
        //XG: The following line helps to get the animations lined up properly with the rest of the
        //XG: game.
batch.setProjectionMatrix(cam.combined);
        // Render the current frame
        batch.begin();
        batch.draw(currentFrame, x, y, w, h); // Adjust the position (x, y) according to your needs
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spriteSheet.dispose();
    }
}



