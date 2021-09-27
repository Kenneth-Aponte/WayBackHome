package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entities.Astronaut;
import Entities.Map;
import Main.Handler;

/*
 * 
 * Created by Kenneth-Aponte on 09/26/2021
 * 
 */

public class GameState extends State{
	
	Map mainMap;
	Astronaut astronaut;
	
	public GameState(Handler handler) {
		super(handler);
		
		astronaut = new Astronaut(handler.getWidth()/2, handler.getHeight()/2, 60, 87, 2, handler);
		//TODO IN THE FUTURE WHEN THE INDIVIDUAL MAPS ARE ADDED, CHANGE THE CONSTRUCTOR SUCH THAT IT HAS A BUFFEREDIMAGE PARAMETER
		mainMap = new Map(-6*handler.getHeight(),-7*handler.getHeight(),14*handler.getHeight(),14*handler.getHeight(),2);
	}

	@Override
	public void tick() {
		mainMap.tick();
		astronaut.tick1();	
		
		//TODO: REMOVE DEBUGGING KEY
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {//makes him 10x faster in the menu (speed = 2 originally)
			if(mainMap.speed != 20) {
				mainMap.speed = 20;
			}
			else
				mainMap.speed=2;
		}
		
		//boundaries and movement
		if(handler.getKeyManager().up && mainMap.y < -(mainMap.height/5 - handler.getHeight()/5)) {
			mainMap.y += mainMap.speed;
		}
		
		if(handler.getKeyManager().down && mainMap.y > -(mainMap.height - 13*handler.getHeight()/4 - handler.getHeight()/15)) {
			mainMap.y -= mainMap.speed;
		}
		
		if(handler.getKeyManager().left && mainMap.x < -(mainMap.width/5 - handler.getWidth()/3 + handler.getWidth()/160)) {
			mainMap.x += mainMap.speed;
		}
		
		if(handler.getKeyManager().right && mainMap.x > -(mainMap.width - 2*handler.getWidth() - handler.getWidth()/3 + handler.getWidth()/16)) {
			mainMap.x -= mainMap.speed;
			
		}
		
	}

	@Override
	public void render(Graphics g) {
		//renders the astronaut and map
		//refer to their individual renders for more details
		mainMap.render(g);
		astronaut.render(g);
		
	}

	@Override
	public void refresh() {
		
	}

}
