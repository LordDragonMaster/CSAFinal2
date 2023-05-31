package com.mygdx.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;




public class Animate extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture spriteSheet;
    private TextureRegion[] frames;
    private Animation<TextureRegion> playerAnimation;
    private float stateTime;
    private int playx;
    private int playy;
    private Texture thing;


    //XG: To use: first, put the desired animations spritesheet into the first parameter, then the rows and columns of
    //XG: the animation. Lastly put in the frame duration as a float to say how long you want each frame to last for.
    public void create(Texture img,int row, int col, float frameDuration ) {
        batch = new SpriteBatch();


        // Load the sprite sheet
        // spriteSheet = new Texture("Main_Char_Sprite.png");
        spriteSheet = img;
        // Split the sprite sheet into frames
        int frameCols = col; // Adjust according to your sprite sheet
        int frameRows = row; // Adjust according to your sprite sheet
        TextureRegion[][] tmp = TextureRegion.split(spriteSheet, spriteSheet.getWidth() / frameCols,
                spriteSheet.getHeight() / frameRows);

int index =0;
        frames = new TextureRegion[frameRows *frameCols];
        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames[index++] = tmp[i][j];
            }
        }


        // Create the animation
       ; // Adjust according to your desired animation speed


        playerAnimation = new Animation<>(frameDuration, frames);
        playerAnimation.setPlayMode(Animation.PlayMode.LOOP);


        // Initialize the state time
        stateTime = 0f;
    }




    public void render(float x, float y, float w, float h, OrthographicCamera cam) {




        // Clear the screen
        //  Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        // Update the state time
        stateTime += Gdx.graphics.getDeltaTime();


        // Get the current frame of the animation
        TextureRegion currentFrame = playerAnimation.getKeyFrame(stateTime);
        //XG: The following line helps to get the animations lined up properly with the rest of the game.
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






