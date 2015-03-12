package com.johanbjare.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Platform extends Sprite {
	Platform(int x, int y)
	{
		super(new Texture("png/platform.png"),200,25);
		if (y%1000==0){
			this.setSize(900,10);
			x=0;
			SpriteBatch textext = new SpriteBatch(this.getTexture().glTarget);
			textext.begin();
			
			textext.end();
		}
		else
			this.setSize(150, 10);
		this.setPosition(x, y);
	}
}
