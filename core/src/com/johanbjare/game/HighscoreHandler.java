package com.johanbjare.game;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HighscoreHandler {
	int scores[];
	String highscorefile = "highscore.txt";
	BitmapFont font;
	Camera camera;
	HighscoreHandler(Camera camera, BitmapFont font){
		scores = new int[5];
		this.camera = camera;
		this.font = font;
	}
	void load(){
		try {
			String current = new java.io.File( "." ).getCanonicalPath();
	        Gdx.app.log("Highscore","Cwd:"+current);
	        
			Scanner in = new Scanner(new File(highscorefile));
			for (int i=0; i<5; i++){
				scores[i] = in.nextInt();
				System.out.println(scores[i]);
			}
			in.close();
		}
		catch (FileNotFoundException e){
			System.out.println("Couldn't read highscore file: " + e);
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	void registerScore(int newscore){
		for (int i=0; i<5; i++){
			if (newscore > scores[i]){
				for (int ii=i; i<4; i++){
					scores[ii+1] = scores[ii];
				}
				scores[i] = newscore;
				return;
			}
		}
	}
	
	void draw(SpriteBatch batch){
		for (int i=0; i<5; i++){
			font.draw(batch, Integer.toString(scores[i]), 0,camera.viewportHeight-(20*i));
		}
	}
}
