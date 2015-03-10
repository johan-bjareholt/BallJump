package com.johanbjare.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.johanbjare.game.MrBall;


public class Game extends ApplicationAdapter {
	SpriteBatch gamebatch;
	SpriteBatch uibatch;
	Camera camera;
	
	MrBall mrball;
	PlatformHandler platformhandler;
	HighscoreHandler highscore;
	
	int resX;
	int resY;
	
	boolean inGame = false;
	
	BitmapFont font;
	
	String platform;
	
	@Override
	public void create () {
		
		String vendor = (String) System.getProperties().get("java.vendor");
		if (vendor == "The Android Project")
			platform = "android";
		else
			platform = "pc";
		Gdx.app.log("", platform);

		resX = 900;
		resY = 500;
		camera = new Camera(resX,resY);
		
		font = new BitmapFont();
		font.setColor(Color.DARK_GRAY);
		
		highscore = new HighscoreHandler(camera, font);
		highscore.load();
		
		gamebatch = new SpriteBatch();
		uibatch = new SpriteBatch();
		
		mrball = new MrBall(camera);
		
		platformhandler = new PlatformHandler(camera);
	}

	@Override
	public void render () {
		input();
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (inGame){
			camera.update();
			platformhandler.update();
			mrball.collideCheck(platformhandler);
			mrball.move();
			
			mrball.collideCheck(platformhandler);
			
			gamebatch.setProjectionMatrix(camera.combined);
			gamebatch.begin();
			mrball.draw(gamebatch);
			platformhandler.draw(gamebatch);
			gamebatch.end();
			System.out.println(mrball.getY() + " och " + camera.position.y);
			if (mrball.getY() < camera.position.y-250)
				inGame = false;
		}
		
		uibatch.begin();
		highscore.draw(uibatch);
		uibatch.end();
	}
	
	void input(){
		Vector3 touchPos = new Vector3();
		if (platform == "pc"){
			if (inGame){
				if(Gdx.input.isTouched()) {
					touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
					mrball.setPosition(touchPos.x - mrball.getWidth()/2, mrball.getY());
				}
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
			if (Gdx.input.isTouched(0)){
				touchPos.set(Gdx.input.getX(0), Gdx.input.getY(0), 0);
				float forceX = 300*Gdx.graphics.getDeltaTime();
				Gdx.app.log("info", "");
				if (touchPos.x < resX/2)
					forceX *= -1;
				mrball.forceX += forceX;
				mrball.translateX(forceX);
			}
			if (Gdx.input.isTouched(1)){
				mrball.jump();
			}
		}
	}
}
