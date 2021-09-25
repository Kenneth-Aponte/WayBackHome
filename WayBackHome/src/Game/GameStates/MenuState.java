package Game.GameStates;


import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Kenneth-Aponte and isabellagarrido on 09/24/2021.
 */

public class MenuState extends State {
	
	Random rand = new Random();
	int[][] starPos = new int[60][2];//0 = x, 1 = y
	Animation waitTime = new Animation(200);
	Animation onTime = new Animation(200);
	int xPos = handler.getWidth()/4;
	int speed = 1;

	boolean pressedEnter = false;
	Animation walkingRightAnim;
	
	
	Font enterFont = new Font("Monospaced",0,24);
	boolean turnedOn = true;
	

    public MenuState(Handler handler) {
        super(handler);
        for(int[] star: starPos) {
        	star[0] = rand.nextInt(handler.getWidth());
        	star[1] = rand.nextInt(handler.getHeight());
        }
        BufferedImage[] walkingRight = new BufferedImage[3];
        walkingRight[0] = Images.astronaut[9];
        walkingRight[1] = Images.astronaut[10];
        walkingRight[2] = Images.astronaut[11];
        
        walkingRightAnim = new Animation(100,walkingRight);
    }

    @Override
    public void tick() {
    	
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
    	
    	//character movement
    	if(xPos >= 7*handler.getWidth()/8) {//if the pos of the character gets to 7/8ths of the screen the game loads
    		System.out.println("loads the game (changes to the next state)");
    	}
    	
    	if(xPos < handler.getWidth()/2 || pressedEnter) {//astronaut will move if it hasn't reached the middle of the screen or player pressed enter
    		xPos+=speed;
    		walkingRightAnim.tick();
    	}
    	
    	if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
    		pressedEnter = true;//self explanatory
    	}
    	
    }

    @Override
    public void render(Graphics g) {
    	
    	//--------blinking----------
    	g.setColor(Color.black);
    	g.fillRect(0,0,handler.getWidth(),handler.getHeight());
    	
    	if(waitTime.end) {
    		//random stars
    		g.setColor(Color.white);//changes the color to white such that stars can be seen
    	}
    	
    	//the stars are always there, though depending on the index of the animation, the color will be black or white simulating blinking
    	for(int[] star: starPos) {
    		
    		//could be a circle or square, still deciding
//			g.drawOval(star[0], star[1], 5, 5);
    		g.fillRect(star[0], star[1], 3, 3);
		}
    	
    	//logo by isabellagarrido
    	g.drawImage(Images.WBHLogo, handler.getWidth()/2 - handler.getWidth()/4,0,handler.getWidth()/2,handler.getWidth()/2,null);
    	
    	//Press enter text 
    	g.setColor(Color.white);
    	g.setFont(enterFont);
    	g.drawString("PRESS ENTER TO CONTINUE", handler.getWidth()/2 - handler.getWidth()/10, handler.getHeight()/4*3);    	
     	
    	//astronaut walking
    	g.drawImage(walkingRightAnim.getCurrentFrame(),xPos , handler.getHeight()/2 + handler.getHeight()/8, 40, 58,null);

    }


    @Override
    public void refresh() {
    }
}
