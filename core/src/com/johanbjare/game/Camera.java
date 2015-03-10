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
}
