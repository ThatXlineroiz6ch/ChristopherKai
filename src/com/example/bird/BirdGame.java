package com.example.bird;

import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.AndroidGame;

public class BirdGame extends AndroidGame {

	@Override
	public Screen getStartScreen() {
		// TODO Auto-generated method stub
		return new LoadScreen(this);
	}

}
