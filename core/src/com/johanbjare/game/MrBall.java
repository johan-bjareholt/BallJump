package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MrBall extends Sprite {
	Camera camera;
	
	boolean onGround=false;
	boolean flipping=false;
	float fliptime = (float) 0.01;
	float forceY;
	float forceX;

	MrBall(Camera camera){
		super(new Texture("mr ball.png"),200,200);
		this.camera = camera;
		forceY = 0;
		forceX = 0;
		setSize(50, 50);
		setOrigin(getWidth()/2, getHeight()/2);
		setPosition(camera.viewportWidth/2, 20);
		camera.translate(0, -50);
	}
	
	void collideCheck(PlatformHandler platformhandler){
		Platform colliding = platformhandler.collide(this); 
		if (forceY < 0 && colliding!=null){
			this.setY(colliding.getY()+colliding.getHeight()-1);
			onGround = true;
			forceY = 0;
		}
		else
			onGround = false;
	}
	
	void jump(){
		if (onGround){
			forceY = 300;
			onGround = false;
			//if (forceY >= 300){
				flipping = true;
				fliptime = (float) 0.01;
			//}
		}
	}
	
	void move(){
		float y = 0,
			  camY = 0;
		float x = 0;
		// X
		if (forceX > 500)
			forceX = 500;
		else if (forceX < -500)
			forceX = -500;

		if (onGround){
			if (forceX > 50)
				forceX = 500;
			else if (forceX < -50)
				forceX = -500;
			else
				forceX = 0;
		}
		x = forceX * Gdx.graphics.getDeltaTime();
		if (onGround)
			forceX = 0;

		// Side bounce
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
		if (!onGround){
			forceY -= 200 * Gdx.graphics.getDeltaTime();
			y = forceY * Gdx.graphics.getDeltaTime();
			
			// Following camera
			if (forceY > 0 && 
				getY()-100 > camera.position.y){
					camY = getY()-100-camera.position.y;
			}
			else {
				camY = 5*Gdx.graphics.getDeltaTime();
			}
		}
		else {
			camY = 10 * Gdx.graphics.getDeltaTime();
		}
		super.translate(x, y);
		camera.translate(0, camY);
		
		if (flipping){
			fliptime += Gdx.graphics.getDeltaTime();
			setRotation((360)*fliptime);
			if (fliptime > 2){
				flipping = false;
				setRotation(0);
			}
		}
	}
}
