package com.example.bird;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics.PixmapFormat;
import com.badlogic.androidgames.framework.Screen;

public class LoadScreen extends Screen {

	public LoadScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Assets.background = game.getGraphics().newPixmap("bg.png", PixmapFormat.RGB565);
		Assets.ground = game.getGraphics().newPixmap("ground.png", PixmapFormat.RGB565);
		Assets.packer = game.getGraphics().newPixmap("flappy_packer.png", PixmapFormat.RGB565);
		Assets.numbers = game.getGraphics().newPixmap("numbers.png", PixmapFormat.RGB565);
		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
