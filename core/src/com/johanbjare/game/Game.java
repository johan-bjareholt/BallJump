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
	
	int resX;
	int resY;
	
	BitmapFont font;
	
	String platform;
	
	@Override
	public void create () {
		
		String vendor = (String) System.getProperties().get("java.vendor");
		if (vendor == "The Android Project")
			platform = "android";
		else
			platform = "pc";
		System.out.println(platform);

		resX = 900;
		resY = 500;
		camera = new Camera(resX,resY);
		
		font = new BitmapFont();
		font.setColor(Color.DARK_GRAY);
		
		gamebatch = new SpriteBatch();
		uibatch = new SpriteBatch();
		
		mrball = new MrBall(camera);
		
		platformhandler = new PlatformHandler(camera);
	}

	@Override
	public void render () {
		input();
		camera.update();
		platformhandler.update();
		mrball.collideCheck(platformhandler);
		mrball.move();
		
		mrball.collideCheck(platformhandler);
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gamebatch.setProjectionMatrix(camera.combined);
		gamebatch.begin();
		gamebatch.draw(mrball, mrball.getX(), mrball.getY(), mrball.getWidth(), mrball.getHeight());
		platformhandler.draw(gamebatch);
		gamebatch.end();
		
		uibatch.begin();
		font.draw(uibatch, "Testing", 0,camera.viewportHeight);
		uibatch.end();
	}
	
	void input(){
		Vector3 touchPos = new Vector3();
		if (platform == "pc"){
			if(Gdx.input.isTouched()) {
				touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				mrball.setPosition(touchPos.x - mrball.getWidth()/2, mrball.getY());
				//camera.unproject(touchPos);
				//bucket.x = touchPos.x - 64 / 2;
			}
			if(Gdx.input.isKeyPressed(Keys.LEFT)) 
				mrball.forceX -= 1000 * Gdx.graphics.getDeltaTime();
		    if(Gdx.input.isKeyPressed(Keys.RIGHT))
		    	mrball.forceX += 1000 * Gdx.graphics.getDeltaTime();
		    if(Gdx.input.isKeyPressed(Keys.SPACE)){
		    	mrball.jump();
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
