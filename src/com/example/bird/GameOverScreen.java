package com.example.bird;

import java.util.List;

import android.animation.ValueAnimator;
import android.util.Log;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class GameOverScreen extends Screen{
	private int score;
	private Graphics graphics;
	public GameOverScreen(Game game, int score) {
		// TODO Auto-generated constructor stub
		super(game);
		this.score = score;
	}
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		List<TouchEvent> events = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		for (int i = 0; i < events.size(); i++)
		{
			if (events.get(i).type == TouchEvent.TOUCH_DOWN &&
					inBounds(events.get(i).x,events.get(i).y,225, 422, 280 , 151))
				game.setScreen(new MainMenuScreen(game));
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
		graphics.drawPixmap(Assets.ground, 0, 999, 0, 0,720,281);
		// "Play"
		graphics.drawPixmap(Assets.packer, 225, 422, 457, 466, 280 , 151);
		graphics.drawPixmap(Assets.packer, 138, 190, 0, 308, 510 , 138);
		drawText(graphics, String.valueOf(score), 360 - String.valueOf(score).length() * 30,630);

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
	public static void drawText(Graphics g, String line, int x, int y) {
        int len = line.length();
        for (int i = 0; i < len; i++) {
            char character = line.charAt(i);
    
            if (character == ' ') {
                x += 20;
                continue;
            }
    
            int srcX = 0;
            int srcWidth = 0;
            if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 60;
                srcWidth = 60;
            }
    
            g.drawPixmap(Assets.numbers, x, y, 0 + srcX, 0, srcWidth, 95);
            x += srcWidth;
        }
	}

}
