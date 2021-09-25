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
	
	public GameState(Handler handler) {
		super(handler);
		
		astronaut = new Astronaut(handler.getWidth()/2, handler.getHeight()/2, 40, 58, 5, handler);
		mainMap = new Map(0,0,8*handler.getWidth(),8*handler.getHeight(),5);
	}

	@Override
	public void tick() {
		mainMap.tick();
		astronaut.tick1();
		
		if(handler.getKeyManager().up) {
			mainMap.y += mainMap.speed;
		}
		if(handler.getKeyManager().down) {
			mainMap.y -= mainMap.speed;
		}
		if(handler.getKeyManager().left) {
			mainMap.x += mainMap.speed;
		}
		if(handler.getKeyManager().right) {
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
