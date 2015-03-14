package com.johanbjare.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.johanbjare.game.MrBall;


public class Game extends ApplicationAdapter {
	SpriteBatch gamebatch;
	SpriteBatch uibatch;
	ShapeRenderer shapeRenderer;
	
	static Clock clock;
	static MrBall mrball;
	static PlatformHandler platformhandler;
	static HighscoreHandler highscore;
	
	static Camera camera;
	
	boolean inGame = false;

	static BitmapFont font;
	static BitmapFont font_small;
	
	String menutext;
	
	String platform;
	
	
	@Override
	public void create () {
		String vendor = (String) System.getProperties().get("java.vendor");
		if (vendor == "The Android Project")
			platform = "android";
		else
			platform = "pc";
		
		gamebatch = new SpriteBatch();
		uibatch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		
		camera = new Camera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		Gdx.app.log("", platform);
		
		font = new BitmapFont(Gdx.files.internal("fonts/dejavu_normal.fnt"));
		font_small = new BitmapFont(Gdx.files.internal("fonts/dejavu_small.fnt"));
		font.setColor(Color.DARK_GRAY);
		font_small.setColor(Color.DARK_GRAY);
		
		if (platform == "android")
			menutext = "Touch to start the game";
		else
			menutext = "Press space to start the game";
		
		highscore = new HighscoreHandler(camera);
		
		loadLevel();
	}

	public void loadLevel(){
		camera.reset();
		
		clock = new Clock(camera, shapeRenderer);
		
		mrball = new MrBall(camera);
		
		platformhandler = new PlatformHandler(camera);
	}
	
	@Override
	public void render () {
		input();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (inGame){
			// Game logic
			camera.update();
			clock.update();
			platformhandler.update();
			mrball.collideCheck(platformhandler);
			mrball.move();
			camera.updatePos();
			mrball.collideCheck(platformhandler);
			
			// Game drawing
			gamebatch.setProjectionMatrix(camera.combined);
			gamebatch.begin();
			mrball.draw(gamebatch);
			platformhandler.draw(gamebatch);
			clock.draw();
			gamebatch.end();
			
			// Lose check
			if (mrball.getY() < camera.position.y-(camera.viewportHeight/2)){
				inGame = false;
				highscore.registerScore(mrball.score, mrball.highestCombo);
				loadLevel();
			}
		}
		
		uibatch.begin();
		if (inGame){
			font_small.draw(uibatch, "Score: " + Integer.toString(mrball.score), 0,camera.viewportHeight);
			font_small.draw(uibatch, "Combo: " + Integer.toString(mrball.combo), 0,camera.viewportHeight-20);
		}
		else {
			font.draw(uibatch, menutext, camera.viewportWidth/2-(font.getSpaceWidth()*menutext.length()/2)-100,camera.viewportHeight/2);
			font_small.draw(uibatch, "Highscore: " + Integer.toString(highscore.highestscore), 0, camera.viewportHeight);
			font_small.draw(uibatch, "Highest combo: " + Integer.toString(highscore.highestcombo), 0,camera.viewportHeight-20);
		}
		uibatch.end();
	}
	
	void input(){
		Vector3 touchPos = new Vector3();
		if (platform == "pc"){
			if (inGame){
				if(Gdx.input.isKeyPressed(Keys.LEFT)) 
					mrball.forceX -= 1000 * Gdx.graphics.getDeltaTime();
			    if(Gdx.input.isKeyPressed(Keys.RIGHT))
			    	mrball.forceX += 1000 * Gdx.graphics.getDeltaTime();
			    if(Gdx.input.isKeyPressed(Keys.SPACE)){
			    	mrball.jump();
			    }
			}
			else {
				if (Gdx.input.isKeyJustPressed(Keys.SPACE))
					inGame = true;
			}
		}
		if (platform == "android"){
			if (inGame){
				if (Gdx.input.isTouched(0)){
					touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
					float forceX = 1000*Gdx.graphics.getDeltaTime();
					if (touchPos.x < camera.viewportWidth/2)
						forceX *= -1;
					mrball.forceX += forceX;
				}
				if (Gdx.input.isTouched(1)){
					mrball.jump();
				}
			}
			else {
				if (Gdx.input.isTouched())
					inGame = true;
			}
		}
	}
}
