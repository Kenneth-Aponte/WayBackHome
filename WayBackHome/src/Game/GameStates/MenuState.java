package Game.GameStates;


import Display.UI.ClickListener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Main.Handler;
import Resources.Images;

import java.awt.*;

/**
 * Created by KAponte on 09/24/2021.
 */
public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler) {
        super(handler);
        refresh();
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0,0,handler.getWidth(),handler.getHeight());
        g.drawImage(Images.titleScreenBackground,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }


    @Override
    public void refresh() {
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


        uiManager.addObjects(new UIImageButton((handler.getWidth() / 2) - (handler.getWidth() /16), (handler.getHeight() /2) - (handler.getHeight() /8), handler.getWidth()/8, handler.getHeight()/16, Images.startGameButton, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUimanager(null);
            }
        }));
    }
}
