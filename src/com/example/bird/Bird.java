package com.example.bird;

public class Bird {
	
	enum BirdState{
		flyPrepare,flying,falling
	}
	
	public float y;
	public float x;
	private float startTime;
	private float endTime;
	public BirdState state;
	public Bird() {
		this.x = 142;
		this.y = 394;
		this.startTime = 0;
		this.state = BirdState.falling;
	}

	public void startFly()
	{
		startTime = System.nanoTime();
		endTime = startTime;
		state = BirdState.flyPrepare;
	}
	public void fly(float delta)
	{
		state = BirdState.flying;
		endTime = System.nanoTime();
		float flyedTime = (endTime - startTime) / 1000000000;
		if (flyedTime >= 0.5)
		{
			state = BirdState.falling;
			return;
		}
		y -= 300 * delta;
		if (y <= 0) y = 0;
		
	}
	public void fall(float delta)
	{
		y += 200 * delta;
		if (y >= 999) y = 930;
	}
}
