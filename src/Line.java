package src;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Line extends Shape {

	protected int x1, x2, y1, y2;

	public Line() {
		super();
		x1 = 0;
		x2 = 0;
		y1 = 0;
		y2 = 0;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}
	
	public int getX1() {
		return x1;
	}
	
	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getX2() {
		return x2;
	}
	
	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY1() {
		return y1;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getY2() {
		return y2;
	}
	
	@Override
	Shape select(MouseEvent e, ArrayList<Shape> s, int i) {
		int x1 = ((Line) s.get(i)).getX1();
		int x2 = ((Line) s.get(i)).getX2();
		int y1 = ((Line) s.get(i)).getY1();
		int y2 = ((Line) s.get(i)).getY2();
		int x = e.getX();
		int y = e.getY();
		
		if ((x >= x1 && x <= x2) && (y >= y1 && y <= y2) || 
				(x <= x1 && x >= x2) && (y <= y1 && y >= y2) || 
				(x >= x1 && x <= x2) && (y <= y1 && y >= y2) ||
				(x <= x1 && x >= x2) && (y >= y1 && y <= y2) ||
				(x <= x1 && x >= x2) && (y1 == y2) ||
				(x1 == x2) && (y >= y1 && y <= y2)) {
			return s.get(i);
		} 
		return null;
	}

	@Override
	void move(MouseEvent e, Shape s) {
		int w = ((Line) s).getX1() - ((Line) s).getX2();
		int h = ((Line) s).getY1() - ((Line) s).getY2();
		((Line) s).setX1(e.getX() - w / 2);
		((Line) s).setY1(e.getY() - h / 2);
		((Line) s).setX2(((Line) s).getX1() + w);
		((Line) s).setY2(((Line) s).getY1() + h);
	}

	@Override
	void resize(MouseEvent e, Shape s) {
		int x = e.getX();
		int y = e.getY();
		((Line) s).setX2(x);
		((Line) s).setY2(y);
	}

}
