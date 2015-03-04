package com.johanbjare.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class MrBall extends Sprite {
	static Texture tex;
	static Sprite img;

	MrBall(){
		super(new Texture("mr ball.png"),200,200);
		setSize(50, 50);
		setPosition(100, 100);
	}
}
