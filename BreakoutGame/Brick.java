package breakOut;

import java.awt.Color;
import java.awt.Rectangle;

public 	class Brick {
	Rectangle brick;
	Color color;
	Brick(Rectangle brick,Color color) {
		this.brick=brick;
		this.color=color;
	}
	public Rectangle getBrick() {
		return this.brick;
	}
	public Color getColor() {
		return this.color;
	}
}