package com.example.bird;

import android.graphics.Rect;

public class Pipe {

	public static int width = 132;
	public static int GAP = 230;
	public static int PIPE_SPEED = 170;
	public float location;
	public Rect upPipe;
	public Rect downPipe;
	public boolean passed;
	public Pipe(int newlocation,int up) {
		upPipe = new Rect(newlocation,0,newlocation + width,up);
		downPipe = new Rect(newlocation,up + 230,newlocation + width,999);
		location = newlocation;
		passed = false;
	}
	public boolean intersectBird(Bird bird)
	{
		int l,t,r,b;
		l = (int) bird.x;
		r = (int) (bird.x + 75);
		t = (int) bird.y;
		b = (int) bird.y + 50;
		Rect birdRect = new Rect(l,t,r,b);
		return birdRect.intersect(downPipe) || birdRect.intersect(upPipe);
			
	}

	public void resetLocation(Pipe that,int offset)
	{
		passed = false;
		location = that.location + offset;
		upPipe.offsetTo((int)location, upPipe.top);
		downPipe.offsetTo((int)location, downPipe.top);
	}
	public void resetGap(int position)
	{
		upPipe.bottom = position;
		downPipe.top = position + GAP;
	}
	public boolean outOfWorld()
	{
		return upPipe.right < 0;
	}
	public void move(float delta)
	{
		location -= delta * PIPE_SPEED;
		upPipe.offsetTo((int)location, upPipe.top);
		downPipe.offsetTo((int)location, downPipe.top);
	}
}
