package src;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Rectangle extends Shape {

	protected int x, y, width, height;

	public Rectangle() {
		super();
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setPrimaryColor(Color color) {
		this.color1 = color;
	}

	public Color getPrimaryColor() {
		return color1;
	}
	
	public void setSecondaryColor(Color color) {
		this.color2 = color;
	}

	public Color getSecondaryColor() {
		return color2;
	}

	@Override
	Shape select(MouseEvent e, ArrayList<Shape> s, int i) {
		int x = e.getX();
		int y = e.getY();
		int rectX = ((Rectangle) s.get(i)).getX();
		int rectY = ((Rectangle) s.get(i)).getY();
		int rectWidth = ((Rectangle) s.get(i)).getWidth();
		int rectHeight = ((Rectangle) s.get(i)).getHeight();
		if ((x >= rectX && x <= (rectX + rectWidth)) && (y >= rectY && y <= (rectY + rectHeight))) {
			return s.get(i);
		}
		return null;
	}

	@Override
	void move(MouseEvent e, Shape s) {
		int width = ((Rectangle) s).getWidth();
		int height = ((Rectangle) s).getHeight();
		((Rectangle) s).setX(e.getX() - (width / 2));
		((Rectangle) s).setY(e.getY() - (height / 2));
	}

	@Override
	void resize(MouseEvent e, Shape s) {
		int x = e.getX();
		int y = e.getY();
		int rectX = ((Rectangle) s).getX();
		int rectY = ((Rectangle) s).getY();
		if (x >= rectX && y >= rectY) {
			((Rectangle) s).setWidth(x - rectX);
			((Rectangle) s).setHeight(y - rectY);
		}
	
	}
}
