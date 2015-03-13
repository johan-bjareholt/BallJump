package com.johanbjare.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class HighscoreHandler {
	int highestscore;
	int highestcombo;
	Camera camera;
	Preferences prefs;
	HighscoreHandler(Camera camera){
		this.camera = camera;
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
