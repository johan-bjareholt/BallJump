package com.johanbjare.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform extends Sprite {

	Platform(int x, int y)
	{
		super(new Texture("png/platform_long.png"));

		if (y%1000==0) {
			this.setTexture(new Texture("png/platform_long.png"));
			this.setSize(Game.camera.viewportWidth,10);
			x=0;
			SpriteBatch textext = new SpriteBatch(this.getTexture().glTarget);
			textext.begin();
			Game.font_small.draw(textext, Float.toString(this.getY()) , this.getWidth()/2, 0);
			textext.end();
		}
		else {
			this.setTexture(new Texture("png/platform.png"));
			this.setSize(150, 10);
		}
		this.setPosition(x, y);
		System.out.println(this.getTexture());
		System.out.println(this.getY());
	}
}
