package com.johanbjare.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Platform extends Sprite {
	Platform(int x, int y)
	{
		super(new Texture("platform.png"),200,25);
		this.setPosition(x, y);
		this.setSize(50, 10);
	}
}
