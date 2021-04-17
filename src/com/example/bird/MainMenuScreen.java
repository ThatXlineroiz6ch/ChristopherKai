package com.example.bird;

import java.util.List;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.badlogic.androidgames.framework.Screen;

public class MainMenuScreen extends Screen {

	private Graphics graphics;
	private float groundShif;
	private float birdPose;
	private int[] birdPosePosition = {0,61,122};
	private int[] wave = {-15,0,15,0};
	private FpsCounter counter;
	public MainMenuScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		counter = new FpsCounter();
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		counter.elapsedTime(deltaTime);
		groundShif += deltaTime * 170;
		birdPose += 2 * deltaTime;
		birdPose %= 3;
		List<TouchEvent> events = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		for (int i = 0; i < events.size(); i++)
		{
			if (events.get(i).type == TouchEvent.TOUCH_DOWN &&
					inBounds(events.get(i).x,events.get(i).y,225, 422, 280 , 151))
				game.setScreen(new BirdgameScreen(game));
		}
	}
	private boolean inBounds(int x, int y,int left,int top,int width,int height)
	{
		return x >= left && x <= left + width && y >= top && y <= top + height;
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
		
		 
		if (groundShif >= 720) groundShif = 0;
		graphics.drawPixmap(Assets.ground, 0, 999, (int)groundShif, 0,(int)(720 - groundShif),281);
		graphics.drawPixmap(Assets.ground, (int)(720 - groundShif) - 2, 999, 0, 0,(int)groundShif + 2, 281);
		
		// "FlappyBird"
		graphics.drawPixmap(Assets.packer, 142, 206, 1056, 0, 458, 155);
		
		// "tap" logo
		graphics.drawPixmap(Assets.packer, 225, 422, 457, 466, 280 , 151);
		
		// flying bird
		graphics.drawPixmap(Assets.packer, 142, 430 + wave[((int)birdPose + 1)], 673, birdPosePosition[(int)birdPose], 88 , 61);
		BirdgameScreen.drawText(graphics, String.valueOf((int)counter.getFPS()), 0, 0);
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
