package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

/*
 * 
 * Created by Kenneth-Aponte on 09/26/2021
 * 
 */

public class Astronaut {
	
	public Handler handler;
	
	public int x,y,width,height,speed;
	
	//Animations for every walking Direction
	public Animation walkingU;//up
	public Animation walkingD;//down
	public Animation walkingL;//left
	public Animation walkingR;//right
	
	public String walkingDir = "D";//assumed looking down when the game starts, uses U,D,L, & R for up, down, left, & right respectively
	
	
	
	public Astronaut(int x, int y, int width, int height, int speed,Handler handler) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		
		//animations are created
		BufferedImage[] walkingUArray = new BufferedImage[3];
		walkingUArray[0] = Images.astronaut[0];
		walkingUArray[1] = Images.astronaut[1];
		walkingUArray[2] = Images.astronaut[2];
		walkingU  = new Animation(100,walkingUArray);
		
		BufferedImage[] walkingDArray = new BufferedImage[3];
		walkingDArray[0] = Images.astronaut[6];
		walkingDArray[1] = Images.astronaut[7];
		walkingDArray[2] = Images.astronaut[8];
		walkingD  = new Animation(100,walkingDArray);
		
		BufferedImage[] walkingLArray = new BufferedImage[3];
		walkingLArray[0] = Images.astronaut[3];
		walkingLArray[1] = Images.astronaut[4];
		walkingLArray[2] = Images.astronaut[5];
		walkingL  = new Animation(100,walkingLArray);
		
		BufferedImage[] walkingRArray = new BufferedImage[3];
		walkingRArray[0] = Images.astronaut[9];
		walkingRArray[1] = Images.astronaut[10];
		walkingRArray[2] = Images.astronaut[11];
		walkingR  = new Animation(100,walkingRArray);
		
		this.handler = handler;
		
	}
	
	//theres a reason as to why its called tick1
	//TODO: WHEN INDIVIDUALS MAPS ARE CREATED VERIFY IF THE SAME TICK CAN BE USED FOR DIFFERENT MOVEMENT OR A NEW TICK SHOULD BE CREATED
	public void tick1() {
		//depending on the key pressed, it will tick the animation and change the dir variable
		if(handler.getKeyManager().up) {
			walkingDir = "U";
			walkingU.tick();
		}
		
		if(handler.getKeyManager().down) {
			walkingDir = "D";
			walkingD.tick();
		}
		
		if(handler.getKeyManager().left) {
			walkingDir = "L";
			walkingL.tick();
		}
		
		if(handler.getKeyManager().right) {
			walkingDir = "R";
			walkingR.tick();
		}
		
	}
	
	
	public void render(Graphics g) {
		//depending on the direction, the proper animation is rendered
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
	
	//I had created this method thought i don't think it will be used, therefore:
	//TODO: LOOK INTO THIS METHOD IN THE FUTURE -Kenneth
	public void move(String direction) {
	}
	
	
	public void refresh() {
	}
}
