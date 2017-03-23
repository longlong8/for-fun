package breakOut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class BreakOutBall {
	private int r=10;
	private int moveX =3, moveY = -3;
	private Point center;

	
	public BreakOutBall() {
		setRadius(r);
		center = new Point(BreakOutPanel.FRAME_WIDTH/2,BreakOutPanel.FRAME_HEIGHT-30);
	}

	public int getR() {
		return r;
	}

	public void setRadius(int r) {
		this.r = r;
	}
    public int getX() {
    	return center.x;
    }

    public int getY() {
    	return center.y;
    }
	public void setMoveX(int moveX) {
		this.moveX = moveX;
	}
	
	public void setMoveY(int moveY) {
		this.moveY = moveY;
	}
	
	public int getMoveX() {
		return moveX;
	}
	
	public int getMoveY() {
		return moveY;
	}
	
	public void move(int dx, int dy) {
		center.translate(dx, dy);
	}
	
	public Point getCenter() {
		return center;
	}

	void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.black);
	    g.drawOval(center.x-r, center.y-r, r * 2, r * 2);
	    g.setColor(Color.cyan);
	    g.fillOval(center.x-r, center.y-r, r * 2, r * 2);
	}
}
