package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MrBall extends Sprite {
	Camera camera;
	
	boolean falling=true; // Falling == not on ground
	float forceY;
	float forceX;

	MrBall(Camera camera){
		super(new Texture("mr ball.png"),200,200);
		this.camera = camera;
		forceY = 0;
		forceX = 0;
		setSize(50, 50);
		setPosition(100, 200);
	}
	
	void collideCheck(PlatformHandler platformhandler){
		Platform colliding = platformhandler.collide(this); 
		if (forceY < 0 && colliding!=null){
			this.setY(colliding.getY()+colliding.getHeight()-1);
			falling = false;
			forceY = 0;
		}
		else
			falling = true;
	}
	
	void jump(){
		if (!falling){
			forceY = 300;
			falling = true;
		}
	}
	
	void move(){
		float y = 0,
			  camY = 0;
		float x = 0;
		// X
		if (forceX > 300)
			forceX = 300;
		if (falling){
			x = forceX * Gdx.graphics.getDeltaTime();
		}
		else {
			x = 300 * Gdx.graphics.getDeltaTime();
		}
		if (getX()+x < 0){
			forceX *= -1;
			x = -(getX()+x);
		}
		else if (getX()+getWidth()+x > camera.viewportWidth){
			forceX *= -1;
			x = 0;
			x = -(getX()+getWidth()+x-camera.viewportWidth);
		}
		// Y
		if (falling){
			forceY -= 200 * Gdx.graphics.getDeltaTime();
			y = forceY * Gdx.graphics.getDeltaTime();
			
			if (forceY > 0 && 
				getY()-150 > camera.position.y){
					camY = getY()-150-camera.position.y;
			}
			else {
				camY = -5*Gdx.graphics.getDeltaTime();
			}
			/*if (y < 0)
				camY = 10 * Gdx.graphics.getDeltaTime();
			else
				camY = y;
			*/
		}
		else {
			camY = 10 * Gdx.graphics.getDeltaTime();
		}
		super.translate(x, y);
		camera.translate(0, camY);
	}
}
