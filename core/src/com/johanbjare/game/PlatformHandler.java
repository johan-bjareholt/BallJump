package com.johanbjare.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlatformHandler {
	OrthographicCamera camera;
	ArrayList<Platform> platforms;
	int platformcount = 10;

	PlatformHandler(OrthographicCamera camera){
		this.camera = camera;
		platforms = new ArrayList<Platform>();
		platforms.add(new Platform(200,100));
		platforms.add(new Platform(200,200));
		platforms.add(new Platform(200,300));
	}
	
	void update(){
		for (int i=0; i<platforms.size(); i++){
			if (platforms.get(i).getY() < camera.position.y-camera.viewportHeight){
				platforms.remove(i);
				i--;
			}
		}
		while (platforms.size() < platformcount){
			int y = (int)platforms.get(platforms.size()-1).getY()+100;
			int x = (int)(Math.random()*(camera.viewportWidth-100));
			platforms.add(new Platform(x,y));
		}
	}
	
	boolean collide(Sprite sprite){
		for (Platform platform: platforms){
			if (platform.getX() <= sprite.getX()+sprite.getWidth() &&
				platform.getX()+platform.getWidth() >= sprite.getX()){
				if (platform.getY() <= sprite.getY()+sprite.getHeight() &&
					platform.getY()+platform.getHeight() >= sprite.getY()){
					return true;
				}
			}
		}
		return false;
	}
	
	void draw(SpriteBatch batch){
		for (Platform platform: platforms){
			batch.draw(platform, 
					   platform.getX(), platform.getY(), 
					   platform.getWidth(), platform.getHeight());
		}
	}
}
