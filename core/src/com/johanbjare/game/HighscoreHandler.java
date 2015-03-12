package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HighscoreHandler {
	int highestscore;
	int highestcombo;
	BitmapFont font;
	Camera camera;
	Preferences prefs;
	HighscoreHandler(Camera camera, BitmapFont font){
		this.camera = camera;
		this.font = font;
		prefs = Gdx.app.getPreferences("highscore");
		highestcombo = prefs.getInteger("highestcombo");
		highestscore = prefs.getInteger("highscore");
	}
	
	void registerScore(int newscore, int newcombo){
		if (newscore > highestscore){
			highestscore = newscore;
			prefs.putInteger("highscore", newscore);
		}
		if (newcombo > highestcombo){
			highestcombo = newcombo;
			prefs.putInteger("highestcombo", highestcombo);
		}
		prefs.flush();
	}
}
