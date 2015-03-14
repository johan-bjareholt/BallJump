package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Clock {
	float gamecounter;
	float counttime = 15;
	float angle = 0;
	ShapeRenderer sr;
	Camera camera;
	
	public Clock(Camera camera, ShapeRenderer sr){
		gamecounter = 0;
		this.camera = camera;
		this.sr = sr;
	}
	public void update(){
		gamecounter += Gdx.graphics.getDeltaTime();
		if (gamecounter >= counttime){
			camera.camspeed += 25;
			gamecounter = gamecounter%counttime;
		}
		angle = (float) ((2*Math.PI)*(gamecounter/counttime)-(Math.PI/2));
	}
	
	public void draw(){
		int radius = 50;
		float x = camera.viewportWidth-radius,
			  y = camera.viewportHeight-radius;
		
		sr.begin(ShapeType.Line);
		
		sr.setColor(0, 0, 0, 1);
		
		sr.circle(camera.viewportWidth-radius, camera.viewportHeight-radius, radius);
		sr.line(x, y, x+(radius*MathUtils.cos(-angle)), y+(radius*MathUtils.sin(-angle)));
		
		sr.end();
	}
}
