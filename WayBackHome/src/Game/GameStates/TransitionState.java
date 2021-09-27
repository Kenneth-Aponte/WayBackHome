package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class TransitionState extends State{
	
	//stars
	Random rand = new Random();
	int[][] starPos = new int[60][2];//0 = x, 1 = y
	Animation waitTime = new Animation(200);
	Animation onTime = new Animation(200);
	boolean turnedOn = true;
	
	//transition flash
	int flashScreenTime = 10;
	
	//shuttle movement
	int yPos = handler.getHeight() + handler.getHeight()/4;
	int speed = 2;
	
	public TransitionState(Handler handler) {
		super(handler);
		 for(int[] star: starPos) {
	        	star[0] = rand.nextInt(handler.getWidth());//xPos
	        	star[1] = rand.nextInt(handler.getHeight());//yPos
	        }
		
	}

	@Override
	public void tick() {
		if(flashScreenTime != 0) {
			flashScreenTime--;
		}
		//does the rest
		else {
			//stars blinking
			waitTime.tick1();//ticks the waitTime of stars while they are not being shown
	    	if(waitTime.end) {
	    		//if the waitTime is over then it will tick the onTime until its done and repeats all over again
	    		onTime.tick1();
	    		if(!turnedOn) {
	    			onTime.reset();
	    			turnedOn = true;
	    			
	    		}
	    		if(onTime.end) {
	    			waitTime.reset();
	    			turnedOn = false;
	    		}
	    	}
	    	
	    	yPos-=speed;//shuttle moves 
	    	if(yPos <= -300) {
	    		//transitions to next state
	    		handler.changeState(handler.getGameState());
	    	}	
		}	
	}
	

	@Override
	public void render(Graphics g) {
		
		if(flashScreenTime == 0) {//if it already flashed it does this

	    	//--------blinking stars----------
	    	g.setColor(Color.black);
	    	g.fillRect(0,0,handler.getWidth(),handler.getHeight());
	    	
	    	if(waitTime.end) {
	    		g.setColor(Color.white);//changes the color to white such that stars can be seen
	    	}
	    	//the stars are always there, though depending on the index of the animation, the color will be black or white simulating blinking
	    	for(int[] star: starPos) {
	    		
	    		g.fillRect(star[0], star[1], 3, 3);
			}
	    	
	    	g.drawImage(Images.shuttle[1],7*handler.getWidth()/8,yPos,126,171,null);
	    	
	    	
		}
		else {
			g.setColor(Color.white);
			g.fillRect(0, 0, handler.getWidth(), handler.getHeight());	
		}
	}
	

	@Override
	public void refresh() {
		
	}

}
