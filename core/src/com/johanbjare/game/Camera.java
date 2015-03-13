package com.johanbjare.game;

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
}
