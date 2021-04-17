package com.example.bird;

public class FpsCounter {

	private float fps;
	private int count;
	private float time;
	public FpsCounter() {
		count = 0;
		fps = 0;
		time = 0;
	}
	public void elapsedTime(float delta)
	{
		time += delta;
		count++;
		if (time >= 1)
		{
			fps = count / time;
			time = 0;
			count = 1;
		}
	}
	public float getFPS()
	{
		return fps;
	}

}
