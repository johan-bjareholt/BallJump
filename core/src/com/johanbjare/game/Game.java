package com.johanbjare.game;

import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.johanbjare.game.MrBall;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	static OrthographicCamera camera;
	ShapeRenderer shapeRenderer;
	static MrBall mrball;
	
	
	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 400, 800);
		
		batch = new SpriteBatch();
		
		mrball = new MrBall();
		
		shapeRenderer = new ShapeRenderer();;
	}

	@Override
	public void render () {
		input();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		batch.draw(mrball, mrball.getX(), mrball.getY(), mrball.getWidth(), mrball.getHeight());
		batch.end();
		
		/*
		int x = 300;
		int y = 300;
		int radius = 20;
		int x2=320, y2=340;
		int width = 100;
		int height = 100;
		
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 0, 0, 1);
		shapeRenderer.line(x, y, x2, y2);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.circle(x, y, radius);
		shapeRenderer.end();
		 
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.rect(x, y, width, height);
		shapeRenderer.circle(x, y, radius);
		shapeRenderer.end();
		*/
	}
	
	static void input(){
		if(Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			mrball.setPosition(touchPos.x - mrball.getWidth()/2, mrball.getY());
			camera.unproject(touchPos);
			//bucket.x = touchPos.x - 64 / 2;
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)) 
			mrball.setX(mrball.getX()-200 * Gdx.graphics.getDeltaTime());
	    if(Gdx.input.isKeyPressed(Keys.RIGHT)) mrball.setX(mrball.getX()+200 * Gdx.graphics.getDeltaTime());
	}
}
