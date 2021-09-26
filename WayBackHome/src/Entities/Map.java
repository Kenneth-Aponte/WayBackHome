package Entities;

import java.awt.Color;
import java.awt.Graphics;

import Resources.Images;

public class Map {
	
	public int x,y,width,height;
	public int speed;
	
	public Map(int x, int y, int width, int height, int speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
	}
	
	
	public void tick() {
		
	}
	
	
	public void render(Graphics g) {
		g.drawImage(Images.mainMap, x, y, width, height, null);
	}
	
	
	public void refresh() {
		
	}
}
