package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entities.Astronaut;
import Entities.Map;
import Main.Handler;

public class GameState extends State{
	
	Map mainMap;
	Astronaut astronaut;
	boolean leftBorderReached = false;
	boolean rightBorderReached = false;
	boolean topBorderReached = false;
	boolean bottomBorderReached = false;
	
	public GameState(Handler handler) {
		super(handler);
		
		astronaut = new Astronaut(handler.getWidth()/2, handler.getHeight()/2, 40, 58, 2, handler);
		mainMap = new Map(-handler.getHeight()-100,-handler.getWidth()+100,4*handler.getHeight(),4*handler.getHeight(),2);
	}

	@Override
	public void tick() {
		mainMap.tick();
		astronaut.tick1();
		
		if(mainMap.y >= 0) {
				if(astronaut.moveMap) {
					mainMap.y -=mainMap.speed;
					astronaut.moveMap=false;
					astronaut.reachedTop=false;
				}
				else {
					topBorderReached = true;
					astronaut.moveUp = true;
					astronaut.reachedTop = true;
				}


		}
		
		if(mainMap.y <= -(mainMap.height - handler.getHeight())) {
			if(astronaut.moveMap) {
				mainMap.y +=mainMap.speed;
				astronaut.moveMap = false;
				astronaut.reachedBottom = false;
			}
			else {
				bottomBorderReached = true;
				astronaut.moveDown = true;
				astronaut.reachedBottom = true;
			}


		}
		
		if(mainMap.x >= 0) {
			if(astronaut.moveMap) {
				mainMap.x -=mainMap.speed;
				astronaut.moveMap=false;
				astronaut.reachedLeft=false;
			}
			else {
				leftBorderReached = true;
				astronaut.moveLeft = true;
				astronaut.reachedLeft = true;
			}


		}
		
		if(mainMap.x <= -(mainMap.width - handler.getWidth())) {
			if(astronaut.moveMap) {
				mainMap.x +=mainMap.speed;
				astronaut.moveMap = false;
				astronaut.reachedRight = false;
			}
			else {
				rightBorderReached = true;
				astronaut.moveRight = true;
				astronaut.reachedRight = true;
			}
	
	
		}
		
		
		if(handler.getKeyManager().up && mainMap.y < 0 && !astronaut.moveDown) {
			mainMap.y += mainMap.speed;
		}
		if(handler.getKeyManager().down && mainMap.y > -(mainMap.width - handler.getHeight())  && !astronaut.moveUp) {
			mainMap.y -= mainMap.speed;
		}
		if(handler.getKeyManager().left && mainMap.x < 0 && !astronaut.moveRight) {
			mainMap.x += mainMap.speed;
		}
		if(handler.getKeyManager().right && mainMap.x > -(mainMap.height - handler.getWidth()) && !astronaut.moveLeft) {
			mainMap.x -= mainMap.speed;
		}
		
	}

	@Override
	public void render(Graphics g) {
		
		mainMap.render(g);
		astronaut.render(g);
		
	}

	@Override
	public void refresh() {
		
	}

}
