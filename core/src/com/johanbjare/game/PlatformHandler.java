package com.johanbjare.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlatformHandler {
	OrthographicCamera camera;
	ArrayList<Platform> platforms;
	int platformcount;
	int highestplatform = 0;

	PlatformHandler(OrthographicCamera camera){
		this.camera = camera;
		platformcount = (int) ((camera.viewportHeight/100)+5);
		platforms = new ArrayList<Platform>();
	}
	
	void update(){
		while (platforms.size() < platformcount){
			int y = (int)highestplatform;
			int x = (int)(Math.random()*(camera.viewportWidth-100));
			platforms.add(new Platform(x,y));
			highestplatform += 100;
		}
		for (int i=0; i<platforms.size(); i++){
			if (platforms.get(i).getY() < camera.position.y-(camera.viewportHeight/2)){
				platforms.remove(i);
				i--;
			}
		}
	}
	
	Platform collide(Sprite sprite){
		for (Platform platform: platforms){
			if (platform.getX() <= sprite.getX()+sprite.getWidth() &&
				platform.getX()+platform.getWidth() >= sprite.getX()){
				if (platform.getY() <= sprite.getY()+10 &&
					platform.getY()+platform.getHeight() >= sprite.getY()){
					return platform;
				}
			}
		}
		return null;
	}
	
	void draw(SpriteBatch batch){
		for (Platform platform: platforms){
			batch.draw(platform, 
					   platform.getX(), platform.getY(), 
					   platform.getWidth(), platform.getHeight());
		}
	}
}
