package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Astronaut {
	
	public int x,y,width,height;
	public int speed;
	public Animation walkingU;
	public Animation walkingD;
	public Animation walkingL;
	public Animation walkingR;
	
	public String walkingDir = "D";
	public boolean moveUp = false;
	public boolean moveDown = false;
	public boolean moveLeft = false;
	public boolean moveRight = false;
	
	public boolean reachedTop = false;
	public boolean reachedBottom = false;
	public boolean reachedLeft = false;
	public boolean reachedRight = false;
	
	public boolean moveMap = true;
	
	public Handler handler;
	
	
	public Astronaut(int x, int y, int width, int height, int speed,Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		//animation
		BufferedImage[] walkingUArray = new BufferedImage[3];
		walkingUArray[0] = Images.astronaut[0];
		walkingUArray[1] = Images.astronaut[1];
		walkingUArray[2] = Images.astronaut[2];
		walkingU  = new Animation(80,walkingUArray);
		
		BufferedImage[] walkingDArray = new BufferedImage[3];
		walkingDArray[0] = Images.astronaut[6];
		walkingDArray[1] = Images.astronaut[7];
		walkingDArray[2] = Images.astronaut[8];
		walkingD  = new Animation(80,walkingDArray);
		
		BufferedImage[] walkingLArray = new BufferedImage[3];
		walkingLArray[0] = Images.astronaut[3];
		walkingLArray[1] = Images.astronaut[4];
		walkingLArray[2] = Images.astronaut[5];
		walkingL  = new Animation(80,walkingLArray);
		
		BufferedImage[] walkingRArray = new BufferedImage[3];
		walkingRArray[0] = Images.astronaut[9];
		walkingRArray[1] = Images.astronaut[10];
		walkingRArray[2] = Images.astronaut[11];
		walkingR  = new Animation(80,walkingRArray);
		
		this.handler = handler;
		
	}
	
	
	public void tick1() {
		if(handler.getKeyManager().up) {
			walkingDir = "U";
			moveMap = false;
			if(moveUp) {
				if(y > 0) {
					y-=speed;
				}	
				moveDown = true;
				if(reachedBottom) {//then that means he is on the bottom border
					if(y <= handler.getHeight()/2+4) {
						moveMap = true;
						moveUp = false;
						moveDown = false;
					}
				}
			}
			walkingU.tick();
		}
		
		
		if(handler.getKeyManager().down) {
			walkingDir = "D";
			moveMap = false;
			if(moveDown) {
				if(y < handler.getHeight() - height) {
					y+=speed;
				}
				moveUp = true;
				if(reachedTop) {//then that means he is on the top border
					if(y >= handler.getHeight()/2-4) {
						moveMap = true;
						moveDown = false;
						moveUp = false;
					}
				}
			}
			walkingD.tick();
		}
		
		if(handler.getKeyManager().left) {
			walkingDir = "L";
			moveMap = false;
			if(moveLeft) {
				if(x > 0) {
					x-=speed;
				}
				moveRight = true;
				if(reachedRight) {//then that means he is on the right border
					if(x <= handler.getWidth()/2+4) {
						moveMap = true;
						moveLeft = false;
						moveRight = false;
					}
				}
			}
			walkingL.tick();
		}
		
		if(handler.getKeyManager().right) {
			walkingDir = "R";
			moveMap = false;
			if(moveRight) {
				if(x < handler.getWidth() - width ) {
					x+=speed;
				}
				moveLeft = true;
				if(reachedLeft) {//then that means he is on the left border
					if(x >= handler.getWidth()/2-4) {
						moveMap = true;
						moveRight = false;
						moveLeft = false;
					}
				}
			}
			walkingR.tick();
		}
	}
	
	
	public void render(Graphics g) {
		if(walkingDir == "U") {
			g.drawImage(walkingU.getCurrentFrame(), x, y, width, height, null);
		}
		if(walkingDir == "D") {
			g.drawImage(walkingD.getCurrentFrame(), x, y, width, height, null);
		}
		if(walkingDir == "L") {
			g.drawImage(walkingL.getCurrentFrame(), x, y, width, height, null);
		}
		if(walkingDir == "R") {
			g.drawImage(walkingR.getCurrentFrame(), x, y, width, height, null);
		}
	}
	
	public void move(String direction) {
		
	}
	
	
	public void refresh() {
		
	}
}
