package com.johanbjare.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Camera extends OrthographicCamera {
	int x, y;

	Camera(int w, int h){
		super();
		this.setToOrtho(false, w, h);
		this.x = 0;
		this.y = 0;
	}
	
	void moveUp(int y){
		super.translate(0, y, 0);
	}
	
	int getYPos(){
		return y;
	}
}
