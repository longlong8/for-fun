package breakOut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Paddle {
	private int x,y;
	public final static int width=100,height=20;
	
	public Paddle() {
		x = (BreakOutPanel.FRAME_WIDTH-this.width)/2;
		y = BreakOutPanel.FRAME_HEIGHT-this.height;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void moveX(int dx) {
		this.x+=dx;
	}
	public int getWidth() {
		return this.width;
	}
	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x,y,width,height);
		g.setColor(Color.black);
		g.drawRect(x,y,width,height);
	}

}
