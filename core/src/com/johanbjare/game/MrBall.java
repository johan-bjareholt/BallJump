package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MrBall extends Sprite {
	Camera camera;
	
	boolean falling=false;
	float forceY;

	MrBall(Camera camera){
		super(new Texture("mr ball.png"),200,200);
		this.camera = camera;
		forceY = 300;
		setSize(50, 50);
		setPosition(100, 200);
	}
	
	void collideCheck(PlatformHandler platformhandler){
		if (falling && platformhandler.collide(this))
			forceY = 300;
	}
	
	void move(){
		if (forceY > 0)
			falling = false;
		else
			falling = true;

		forceY -= 200 * Gdx.graphics.getDeltaTime();
		float y = forceY * Gdx.graphics.getDeltaTime();
		super.translate(0, y);
		camera.translate(0,y);
	}
}
