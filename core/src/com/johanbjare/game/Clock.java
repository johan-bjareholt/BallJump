package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;

public class Clock {
	float time = 0;
	float counttime = 15;
	int speedups = 0;
	float angle = 0;
	ShapeRenderer sr;
	Camera camera;
	
	public Clock(Camera camera, ShapeRenderer sr){
		this.camera = camera;
		this.sr = sr;
	}
	public void update(){
		time += Gdx.graphics.getDeltaTime();
		if ((int)(time/counttime) > speedups){
			camera.camspeed += 25;
			speedups += 1;
		}
		angle = (float) ((2*Math.PI)*(time%counttime/counttime)-(Math.PI/2));
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
