package src;
import java.io.Serializable;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public abstract class Shape implements Serializable{

	protected Color color1 = Color.BLACK;
	protected Color color2 = Color.WHITE;
	protected String name;
	protected boolean isFilled;
	protected boolean drawn;
	protected int lineWidth;

	public Shape()  {
		color1 = null;
		color2 = null;
		name = null;
		lineWidth = 1;
		isFilled = true;
		drawn = true;
	}

	public void setDraw(boolean drawn) {
		this.drawn = drawn;
	}
	
	public boolean drawn() {
		return drawn;
	}
	
	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setPrimaryColor(Color c) {
		color1 = c;
	}

	public Color getPrimaryColor() {
		return color1;
	}

	public void setSecondaryColor(Color c) {
		color2 = c;
	}

	public Color getSecondaryColor() {
		return color2;
	}

	public void setLineWidth(int line) {
		lineWidth = line;
	}
	
	public int getLineWidth() {
		return lineWidth;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	abstract Shape select(MouseEvent e, ArrayList<Shape> currentShapes, int i);

	abstract void move(MouseEvent e, Shape selectedShape);

	abstract void resize(MouseEvent e, Shape selectedShape);

}
