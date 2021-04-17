package com.example.bird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.androidgames.framework.Input.TouchEvent;
import com.example.bird.Bird.BirdState;

public class World {
	Bird bird;
	List<Pipe> pipes = new ArrayList<Pipe>();
	private int PIPE_INTERVAL = 400;
	public boolean gameover;
	private static Random random = new Random();
	public int score = 0;
	public World() {
		bird = new Bird();
		for (int i = 0; i < 4; i++)
		{
			pipes.add(new Pipe(720 + i * PIPE_INTERVAL,random.nextInt(250) + 100));
		}
		gameover = false;
	}
	public void update(float delta,List<TouchEvent> events)
	{
		for (int i = 0;i < pipes.size(); i++)
		{
			
			Pipe p = pipes.get(i);
			p.move(delta);
			if (p.upPipe.right < bird.x + 75 && !p.passed)
			{
				score++;
				p.passed = true;
			}
			if (p.intersectBird(bird))
			{
				gameover = true;
				return;
			}
			if (p.outOfWorld())
			{
				p.resetLocation(pipes.get(lastPipe(i)),PIPE_INTERVAL);
				p.resetGap(random.nextInt(250) + 100);
			}
			
		}
		for (int i = 0; i < events.size();i++)
		{
			if (events.get(i).type == TouchEvent.TOUCH_DOWN)
			{
				if (bird.state == BirdState.falling || bird.state == BirdState.flying)
				{
					bird.startFly();
				}
				
			}
		}
		if (bird.state == BirdState.flyPrepare)
			bird.fly(delta);
		else if (bird.state == BirdState.flying)
			bird.fly(delta);
		else 
			bird.fall(delta);
	}
	private int lastPipe(int i) {
		// TODO Auto-generated method stub
		if (i == 0) return 3;
		return i - 1;
	}
	
}
