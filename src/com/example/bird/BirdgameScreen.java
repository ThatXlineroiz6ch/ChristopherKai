package com.example.bird;

import java.util.List;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.widget.Toast;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Graphics;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.Input.TouchEvent;

public class BirdgameScreen extends Screen {

	
	private World world;
	private Graphics graphics;
	private int groundShif;
	private float birdselect;
	private int[] birdPosePosition = {61,0,122};
	private int[] select = {0,1,0,2};
	private int fps;
	private float total;
	private int lastFps;
	private int score;
	private String scoreStr = "0";
	public BirdgameScreen(Game game) {
		super(game);
		// TODO Auto-generated constructor stub
		this.world = new World();
		birdselect = 0;
		fps = 0;
		total = 0;
		lastFps = 0;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		fps++;
		total += deltaTime;
		if (total >= 1) { lastFps = fps;total = 0; fps = 0;}
		groundShif += deltaTime * 170;
		List<TouchEvent> events = game.getInput().getTouchEvents();
		game.getInput().getKeyEvents();
		world.update(deltaTime, events);
		birdselect += deltaTime * 2;
		birdselect %= 4;
		if (score != world.score)
		{
			score = world.score;
			scoreStr = ""+score;
		}
		if (world.gameover)
		{
			game.setScreen(new GameOverScreen(game,world.score));
		}
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub
		drawWorld();
		drawText(graphics, String.valueOf(lastFps), 0, 0);
	}

	private void drawWorld() {
		// TODO Auto-generated method stub
		graphics = game.getGraphics();
		graphics.drawPixmap(Assets.background, 0, 0);
		
		// 
		if (groundShif >= 720) groundShif = 0;
		graphics.drawPixmap(Assets.ground, 0, 999, (int)groundShif, 0,(int)(720 - groundShif),281);
		graphics.drawPixmap(Assets.ground, (int)(720 - groundShif) - 2, 999, 0, 0,(int)groundShif + 2, 281);

		graphics.drawPixmap(Assets.packer, (int)world.bird.x, (int)world.bird.y, 673, birdPosePosition[select[(int)birdselect]], 88 , 61);
		
		drawPipes();
		drawText(graphics, scoreStr, 360 - scoreStr.length() * 30, 1098);
	}
	private void drawPipes()
	{
		for (int i = 0; i < world.pipes.size(); i++)
		{
			Pipe pipe = world.pipes.get(i);
			Rect upsrc = new Rect(pipe.upPipe);
			Rect downsrc = new Rect(pipe.downPipe);
			upsrc.offsetTo(160, 1290 - upsrc.height());
			downsrc.offsetTo(12, 479);
			
			graphics.drawPixmap(Assets.packer, upsrc,pipe.upPipe);
			graphics.drawPixmap(Assets.packer, downsrc,pipe.downPipe);
		}
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
