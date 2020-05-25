package src;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Circle extends Shape {

	protected int x, y, r;

	public Circle() {
		super();
		x = y = r = 0;
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

	public void setRadius(int r) {
		this.r = r;
	}
	
	public int getRadius() {
		return r;
	}

	@Override
	Shape select(MouseEvent e, ArrayList<Shape> s, int i) {
		int x = e.getX();
		int y = e.getY();
		int cirX = ((Circle) s.get(i)).getX();
		int cirY = ((Circle) s.get(i)).getY();
		int radius = ((Circle) s.get(i)).getRadius();
		if ((x >= cirX && x <= (cirX + radius)) && (y >= cirY && y <= (cirY +radius))) {
			return s.get(i);
		}
		return null;
	}

	@Override
	void move(MouseEvent e, Shape selectedShape) {
		int radius = ((Circle) selectedShape).getRadius();
		((Circle) selectedShape).setX(e.getX() - (radius/ 2));
		((Circle) selectedShape).setY(e.getY() - (radius / 2));

	}

	@Override
	void resize(MouseEvent e, Shape selectedShape) {
		int x = e.getX();
		int y = e.getY();
		int cirX = ((Circle) selectedShape).getX();
		int cirY = ((Circle) selectedShape).getY();
		if (x >= cirX && y >= cirY) {
			((Circle) selectedShape).setRadius((x - cirX));
		}
	}

}
