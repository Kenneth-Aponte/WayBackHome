package Resources;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 1/24/2020.
 */

public class Animation {

    private int speed,index;
    private long lastTime,timer;
    private BufferedImage[] frames;
    private String[] s;
    public boolean end=false;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public Animation(int speed, BufferedImage[] frames, int index){
        this.speed=speed;
        this.frames=frames;
        this.index=index;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }
    
    public Animation(int speed, String[] s) {//new constructor for strings 
        this.speed=speed;
        this.s=s;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                end =true;
                index = 0;
            }
        }

    }
    
    public void tickS(){//for strings
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= s.length){
                end =true;
                index = 0;
            }
        }

    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public String getCurrentString() {
    	return s[index];
    }
    
    public int getIndex() {
        return index;
    }

    public void reset(){
        index =0;
        end = false;
    }
}
