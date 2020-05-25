package src;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Model extends JPanel implements MouseListener, MouseMotionListener {
	JPanel canvas;
	ArrayList<Shape> shapes;
	ArrayList<Shape> currentShapes;
	private Circle cir;
	private Rectangle rect;
	private Line l;
	int lineWidthChoice = 0;
	int flag;
	private int startx, starty;
	Color color;
	boolean isFilled;
	public Shape thisShape;
	String currentShape;
	int lineW;
	Object item;
	String pos, indName;
	int ind;
	private JScrollPane scrollBar;

	public Model() {
		pos = "";
		indName = "";
		canvas = new JPanel();
		canvas.setBackground(Color.white);
		setVisible(true);
		canvas.setVisible(true);
		flag = 0;
		color = Color.BLACK;
		currentShape = "line";
		lineW = 0;
		shapes = new ArrayList<Shape>();
		currentShapes = new ArrayList<Shape>();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void pushItem() {
		try{
			item.setI(currentShapes.size() - 1);
			item.setshapePos(item.getshapePos() + pos);
		} catch (Exception w) {
			// TODO: handle exception
		}
	}
	
	public void clear() {
		shapes = new ArrayList<Shape>();
		repaint();
	}

	public void loadImages(ArrayList<Shape> imageList) {
		shapes = imageList;
		repaint();
	}

	public ArrayList<Shape> getImageList() {
		return shapes;
	}

	public void select(MouseEvent e) {
		for (int i = currentShapes.size() - 1; i >= 0; i--) {
			if (currentShapes.get(i).getName() == "circle") {
				cir = new Circle();
				if (cir.select(e, currentShapes, i) != null) {
					thisShape = cir.select(e, currentShapes, i);
					ind = i;
					indName = "circle";
					break;
				}
			} else if (currentShapes.get(i).getName() == "rectangle") {
				rect = new Rectangle();
				if (rect.select(e, currentShapes, i) != null) {
					thisShape = rect.select(e, currentShapes, i);
					ind = i;
					indName = "rectangle";
					break;
				}
			} else if (currentShapes.get(i).getName() == "line") {
				l = new Line();
				if (l.select(e, currentShapes, i) != null) {
					thisShape = l.select(e, currentShapes, i);
					ind = i;
					indName = "line";
					break;
				}
			}
		}
	}

	private void move(MouseEvent e) {
		if (thisShape != null) {
			if (thisShape.getName() == "circle") {
				cir.move(e, thisShape);
			} else if (thisShape.getName() == "rectangle") {
				rect.move(e, thisShape);
			} else if (thisShape.getName()== "line") {
				l.move(e, thisShape);
			}
			repaint();
		}
	}
	
	private void delete() {
		try{
			if (thisShape != null) {
				for (int i = shapes.size() - 1; i >= 0; i--) {
					if (shapes.get(i).toString().equals(thisShape.toString())) {
						shapes.remove(i);
					}
				}
				for (int i = 0; i < currentShapes.size(); i++) {
					if (currentShapes.get(i).toString()
							.equals(thisShape.toString()))
						currentShapes.remove(i);
				}
			}
		} catch (Exception edd) {
			// TODO: handle exception
		}
	}

	public void resize(MouseEvent e) {
		if (thisShape != null) {
			if (thisShape.getName() == "circle") {
				cir.resize(e, thisShape);
			} else if (thisShape.getName() == "rectangle") {
				rect.resize(e, thisShape);
			} else if (thisShape.getName() == "line") {
				l.resize(e, thisShape);
			}
			repaint();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, 900, 900);
		for (int i = 0; i < shapes.size(); i++) {
			if (shapes.get(i).drawn()) {
				if (shapes.get(i).getName().equals("circle")) {
					g2.setStroke(new BasicStroke(shapes.get(i).getLineWidth()));
					g2.setColor(shapes.get(i).getPrimaryColor());
					if (shapes.get(i).isFilled()) {
						g2.setColor(shapes.get(i).getPrimaryColor());
						g2.fillOval(((Circle) shapes.get(i)).getX(),
								((Circle) shapes.get(i)).getY(),
								((Circle) shapes.get(i)).getRadius(),
								((Circle) shapes.get(i)).getRadius());
						g2.setColor(shapes.get(i).getSecondaryColor());//bound color
					} 
					g2.drawOval(((Circle) shapes.get(i)).getX(),
							((Circle) shapes.get(i)).getY(),
							((Circle) shapes.get(i)).getRadius(),
							((Circle) shapes.get(i)).getRadius());
					shapes.get(i).setSecondaryColor(g2.getColor());//bound color
				} else if (shapes.get(i).getName().equals("rectangle")) {
					g2.setColor(shapes.get(i).getPrimaryColor());
					g2.setStroke(new BasicStroke(shapes.get(i).getLineWidth()));
					if (shapes.get(i).isFilled()) {
						g2.setColor(shapes.get(i).getPrimaryColor());//fill color
						g2.fillRect(((Rectangle) shapes.get(i)).getX(),
								((Rectangle) shapes.get(i)).getY(),
								((Rectangle) shapes.get(i)).getWidth(),
								((Rectangle) shapes.get(i)).getHeight());
						g2.setColor(shapes.get(i).getSecondaryColor());//bound color
					} g2.drawRect(((Rectangle) shapes.get(i)).getX(),
							((Rectangle) shapes.get(i)).getY(),
							((Rectangle) shapes.get(i)).getWidth(),
							((Rectangle) shapes.get(i)).getHeight());
					shapes.get(i).setSecondaryColor(g2.getColor());//bound color
				} else if (shapes.get(i).getName().equals("line")) {
					g2.setColor(shapes.get(i).getPrimaryColor());
					g2.setStroke(new BasicStroke(shapes.get(i).getLineWidth()));
					g2.drawLine(((Line) shapes.get(i)).getX1(),
							((Line) shapes.get(i)).getY1(),
							((Line) shapes.get(i)).getX2(),
							((Line) shapes.get(i)).getY2());
				}
			}
		}

	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try{
			if (flag == 2) {
				select(e);
				if (thisShape != null) {
					thisShape.setPrimaryColor(color);
					thisShape.setFilled(isFilled);
				}
			} else if (flag == 3) {
				select(e);
				delete();
			} else if (flag == 22) {//OPPA
				select(e);
				//System.out.println(thisShape.getPrimaryColor()); 
				//System.out.println(thisShape.getLineWidth());
				color = thisShape.getPrimaryColor();
				currentShape = thisShape.getName();
				lineW = thisShape.getLineWidth();
				System.out.println(color);
				System.out.println(currentShape);
				System.out.println(lineW);
			}
			repaint();
		} catch (Exception eas) {
			// TODO: handle exception
		}
	}

	@Override
	public void mousePressed(MouseEvent ev) {
		try {
			startx = ev.getX();
			starty = ev.getY();
			if (flag == 1 || flag == 8) {
				select(ev);
			} else {
				if (flag == 5) {
					rect = new Rectangle();
					rect.setPrimaryColor(color);
					rect.setName("rectangle");
					rect.setFilled(isFilled);
					rect.setX(ev.getX());
					rect.setY(ev.getY());
					if (lineWidthChoice == 0){
						rect.setLineWidth(1);
					} else if (lineWidthChoice == 1) {
						rect.setLineWidth(5);
					} else if (lineWidthChoice == 2) {
						rect.setLineWidth(10);
					}
				} else if (flag == 6) {
					l = new Line();
					l.setPrimaryColor(color);
					l.setName("line");
					l.setX1(ev.getX());
					l.setY1(ev.getY());
					if (lineWidthChoice == 0){
						l.setLineWidth(1);
					} else if (lineWidthChoice == 1) {
						l.setLineWidth(5);
					} else if (lineWidthChoice == 2) {
						l.setLineWidth(10);
					}
				} else if (flag == 4) {
					cir = new Circle();
					cir.setPrimaryColor(color);
					cir.setName("circle");
					cir.setFilled(isFilled);
					cir.setX(ev.getX());
					cir.setY(ev.getY());
					if (lineWidthChoice == 0){
						cir.setLineWidth(1);
					} else if (lineWidthChoice == 1) {
						cir.setLineWidth(5);
					} else if (lineWidthChoice == 2) {
						cir.setLineWidth(10);
					}
				} 
			}
		} catch (Exception eee) {

		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			if (e.getX() > 950 || e.getY() >= 650) {
			} else {
				if (flag == 1) {
					move(e);
				} else if (flag == 8) {
					resize(e);
				} else {
					if (flag == 4) {
						cir.setX(Math.min(e.getX(), startx));
						cir.setRadius(Math.abs(e.getX() - startx));
						shapes.add(cir);
					} else if (flag == 5) {
						rect.setX(Math.min(e.getX(), startx));
						rect.setY(Math.min(e.getY(), starty));
						rect.setWidth(Math.abs(e.getX() - startx));
						rect.setHeight(Math.abs(e.getY() - starty));
						shapes.add(rect);
					} else if (flag == 6) {
						l.setX2(e.getX());
						l.setY2(e.getY());
						shapes.add(l);
					} 
					repaint();
				}
			}
		} catch (Exception eee) {

		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try{
			thisShape = null;
			item = new Object();
			if (flag != 1 && flag != 8) {
				if (flag == 4) {
					currentShapes.add(cir);
					pos = cir.getX() + "," + cir.getY() + "," + cir.getRadius()
					+ "," + cir.getRadius() + "," + cir.getPrimaryColor().getRGB()
					+ "," + cir.isFilled();
					item.setshapeName("circle");
					pushItem();
				} else if (flag == 5) {
					currentShapes.add(rect);
					pos = rect.getX() + "," + rect.getY() + "," + rect.getWidth() + ","
							+ rect.getHeight() + "," + rect.getPrimaryColor().getRGB() + ","
							+ rect.isFilled();
					item.setshapeName("rectangle");
					pushItem();
				} else if (flag == 6) {
					currentShapes.add(l);
					pos = l.getX1() + "," + l.getY1() + "," + l.getX2() + ","
							+ l.getY2() + "," + l.getPrimaryColor().getRGB() + ","
							+ l.isFilled();
					item.setshapeName("line");
					pushItem();
				} 
			}
		} catch (Exception ewe) {
			// TODO: handle exception
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
