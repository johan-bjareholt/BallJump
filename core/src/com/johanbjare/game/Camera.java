package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
	int camspeed;
	Camera(int w, int h){
		super();
		this.setToOrtho(false, w, h);
		reset();
	}
	void reset(){
		this.position.y = 200;
		this.camspeed = 10;
	}
	void updatePos(){
		// Following camera
		float camY;
		if (Game.mrball.forceY > 0 && 
			Game.mrball.getY()-100 > this.position.y){
				camY = Game.mrball.getY()-100-this.position.y;
		}
		else {
			camY = this.camspeed*Gdx.graphics.getDeltaTime();
		}
		this.translate(0, camY);
	}
}
