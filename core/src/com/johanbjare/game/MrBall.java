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
	
	int score = 0;
	int height = 0;
	Platform lastPlatform = null;
	int combo = 0;
	int highestCombo = 0;

	MrBall(Camera camera){
		super(new Texture(Gdx.files.internal("png/mr ball.png").path()),200,200);
		this.camera = camera;
		forceY = 0;
		forceX = 0;
		setSize(50, 50);
		setOrigin(getWidth()/2, getHeight()/2);
		setPosition(camera.viewportWidth/2, 20);
	}
	
	void collideCheck(PlatformHandler platformhandler){
		Platform colliding = platformhandler.collide(this); 
		if (forceY < 0 && colliding!=null){
			this.setY(colliding.getY()+colliding.getHeight()-1);
			onGround = true;
			forceY = 0;
			if (height/2 < (int)colliding.getY()/2){
				score = (int)colliding.getY()/2;
			}
			height = (int)colliding.getY();
			if (lastPlatform != null && 
				colliding.getY() >= lastPlatform.getY())
			{
				if (lastPlatform != colliding)
					combo += 1;
				if (combo > highestCombo)
					highestCombo = combo;
			}
			else{
				combo = 0;
			}
			lastPlatform = colliding;
		}
		else
			onGround = false;
	}
	
	void jump(){
		if (onGround){
			forceY = 200;
			forceY += Math.abs(forceX/3);
			onGround = false;
		}
	}
	
	void move(){
		float y = 0;
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
		}

		super.translate(x, y);
		
		if (flipping){
			fliptime += Gdx.graphics.getDeltaTime();
			setRotation((360)*fliptime);
			if (fliptime > 1){
				flipping = false;
				setRotation(0);
			}
		}
		if (!flipping && forceY >= 150){
			flipping = true;
			fliptime = (float) 0.01;
		}
	}
}
